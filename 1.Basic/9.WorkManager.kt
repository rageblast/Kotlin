// Like worker threads concept

Work Manager -> we can execute tasks and functionality reliably
Tasks that user does not directly need to be aware of
we can show a notification when the task is running
but we don't have to in comparison to a foreground service

Example:
Sync data in the background or one-time task 

Need to upload 20 images inside the server but our internet connection is slow
but the user closes the app because the net is slow
the upload will be canceled so that's where work manager comes into play
it guarantees that our task will be executed.

to begin we need to add two dependency inside gradle

implementation: "android.work:work-runtime-ktx:2.8.1"
implementation: "io.coil-kt:coil-compose:2.4.0"

Manifest:
<application>
<activity>
<intent-filter>
<action android:name="android.intent.action.SEND" />
<category android:name="android.intent.category.DEFAULT" />
<data android:mimeType="image/*" />
</intent-filter>
</activity>
</application>

Create a class called photoCompressionWorker

// There are different types of worker we use CoroutineWorker hear

class PhotoCompressionWorker(
  // We need to have default constructor
  // Because we won't initiated this class android system will 
  // Intiate it
  private val appContext: Context,
  private val params: WorkerParameters // we can't pass parameters so we need it 
): CoroutineWorker(appContext, params) {

doWork will do the work 
check what is -> suspend

Result -> determines whether the work is success or failure
when we want to retry to it 

URIS -> Actual address of the image 

    override suspend fun doWork(): Result {
   // We can show foreground service if needed

      // Check Corontines playlist for below 

      return withContext(Dispatchers.IO) {

      val stringUri = params.inputData.getString(KEY_CONTENT_URI)
      val compressionThresholdInBytes = params.inputData.getLong(
        KEY_COMPRESSION_THRESHOLD,
        0L // default
      )

      // construct real uri 
      val uri = Uri(android.net).parse(stringUri)
      // Read the actual byte of the image 
      // check the below method and "use" will automatically closes the input stream
      // once it read 
      val bytes = appContext.contentResolver.openInputStream(uri)?.use {
        it.readBytes()
      } ?: return@withContext Result.failure() // if there is no byte it will return failure 
    
      // How to Intepret as Image and compress it using the above byte 
      // for that we use bitMap(Image Object)
      val bitmap = BitmapFactory.decodeByteArray(bytes, 0(starting value), bytes.size(ending value))

      // to get the correct output file size that fits in our system 
      // getting the compressed bytes 
      var outputBytes: ByteArray

      // quality of image if it fits to our system we don't need to 
      // compress it.
      var quality = 100

      // every iteration of while loop we will create a new o/p stream 
      // read it and new compressed bytes and check how large the array is 
      // which is the byte size 
      do {
        val outputStream = ByteArrayOutputStream()
        outputStream.use { outputStream ->
            bitMap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            outputBytes = outputStream.toByteArray()
            quality -= (quality * 0.1).roundToInt() // reduce the quality and check if it fits the size 
        }
      } while(outputBytes.size > compressionThresholdInBytes && quality > 5)
         
         // Check android storage
        val file = File(appContext.cacheDir, "${params.id}.jpg")
        file.writeBytes(outputBytes)

        // send data from this worker to who had called this worker
        Result.success(
          workDataOf(
            KEY_RESULT_PATH to file.absolutePath
          )
        )
    }
  }



    companion object {
      const val KEY_CONTENT_URI = "KEY_CONTENT_URI" // the URI of the Image
      const val KEY_COMPRESSION_THRESHOLD = "KEY_COMPRESSION_THRESHOLD" // Threshold it has to reduce
      const val KEY_RESULT_PATH = "KEY_RESULT_PATH" // path in which it has to store the compressed file 
    }
}



class MainActivity : ComponentActivity() {

// to Intiate the below work request we need the 
// initiate the work manager
private lateinit var workManager: WorkManager 

// storage of compressed image 
private val viewModel by viewModels<PhotoViewModel>()

override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  workManager = WorkManager.getInstance(applicationContext)
  setContent {
    WorkManagerTheme{
      //check the work result of the worker 
      val workerResult = viewModel.workId?.let { 
    add dependency for the below one inside gradle 
    implementation "androidx.compose.runtime:runtime-livedata:1.4.3"
      workManager.getWorkInfoByIdLiveData(id).observeAsState().value // like observer in css 
    }

    // output data changes we can trigger the below 
    LaunchedEffect(key1 = workResult?.outputData) {
      if(workerResult?.outputData != null) {
        val filePath = workerResult.outputData.getString( // getting the file path from worker 
          PhotoCompressionWorker.KEY_RESULT_PATH
        )
        filePath?.let {
          val bitmap = BitmapFactory.decodeFile(it)
          viewModel.updateCompressedBitmap(bitmap)
        }
      }
    }

    // Now show uncompressed and compressed image 
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      viewModel.uncompressedUri?.let {
        Text("Uncompressed photo:")
        AsyncImage(model = it, contentDescription = null)
      }
      Spacer(modifier = Modifier.height(16.dp))
      viewModel.compressedBitmap?.let {
        Text("Uncompressed photo:")
        Image(bitmap = it.asImageBitmap(), contentDescription = null)
      }
    }
    }
  }
}

// Then we need to start the work manager using the activity using

the below override function it is written in work manager\

override fun onNewIntent(intent: Intent?) {
  super.onNewIntent(intent)

  // the intent contain the uri of the photo that we need to compress
    val uri = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
      intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
    } else {
      intent?.getParcelableExtra(Intent.EXTRA_STREAM)
    } ?: return 

 // store the uri 
 viewModel.updateUncompressUri(uri)

    // now we need to make a work request to start the work manager 
    // we have work request like conditional, periodic, one time etc 

    val request = OneTimeRequestBuilder<PhotoCompressionWorker>()
    .setInputData( // need to set input data 
      workDataOf(
         PhotoCompressionWorker.KEY_CONTENT_URI to uri.toString(), // send the uri in string
         PhotoCompressionWorker.KEY_COMPRESSION_THRESHOLD to 1024 * 20L // compress the image into 20 kilo bytes
      )
    )
    .setContraints(Constraints( 
    // we can check storage is engough because we upload photos 
    // we can do many things like check battery percentage inside constraints etc.
    // check many methods
      requiresStorageNotLow = true
    ))
    // you put "." and explore others methods of work manager 
    .build() // finally we use this method to build our work request 
    viewModel.updateWorkId(request.id) // we can now listen to the work manager who made the changes
     workManager.enqueue(request) // Now it will take care of the request at best possible time 
}
}

to use the above we need to define the activity as single Top
<activity
android:launchMode="singleTop"
>


To display the compressed image inside the activity that we get from our work manager 
we use view model 

class PhotoViewModel: ViewModel() {

  var uncompressedUri: Uri? by mutableStateOf(null)
      private set 

  var compressedBitmap: Bitmap? by mutableStateOf(null)
      private set 

  var workId: UUID? by mutableStateOf(null)
      private set

      fun updateUncompressUri(uri: Uri?) {
        uncompressedUri = uri
      }  

      fun updateCompressedBitmap(bmp: Bitmap?) {
        compressedBitmap = bmp 
      }      

      fun updateWorkId(uuid: UUID?) {
        workId = uuid
      }
}