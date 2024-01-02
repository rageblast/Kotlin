Our app need to provide content 

Example:
If we have a contacts list app
we will store all the contacts content's

And user opens other app and wants to show the content's of the contact 
in this app.

so the contact list app will exposes a content list provider provides the resource
that we want like database where we can filter it and do other stuff's.

In Calendar app to provide all the events using content providers.

Most used content provider in android is media store where we can access audio, video etc. 

// To access specific content Provider resources we use ContentResolver

val projection = arrayOf( // Inside we have many information in that we take two details
  MediaStore.Images.Media._ID,
  MediaStore.Images.Media.DISPLAY_NAME
)

// then Select only latest photos
// getting date in milli seconds
val millisYesterday = Calendar.getInstance().apply {
  add(Calendar.DAY_OF_YEAR, -1)
}.timeInMillis
val selection = "${MediaStore.Images.Media.DATE_TAKEN} >= ?"
// Because of Sql Injection we are using "?"
// To pass value to ? -> we do it like below and we can have multiple 
// question marks and pass the value in arrays
val selectionArgs = arrayOf(millisYesterday.toString())

// sort the order
val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"

contentResolver.query(
  MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // Image uri 
  projection,
  selection,
  selectionArgs,
  sortOrder
)?.use { cursor -> // the cursor will iterate through the result
// gives us the necessary information.
val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLY_NAME)

// Convert it to image data type
val images = mutableListOf<Image>()
while(cursor.moveToNext()) { // this will return true if there is next image
val id = cursor.getLong(idColumn)
val name = cursor.getString(nameColumn)
val uri = ContentUris.withAppendedId( // getting the content uri of one specific image
  MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
  id
)

images.add(Image(id, name, uri)) // display the result we use view Model(ImageviewModel)
}
viewModel.updateImages(images)
}

data class Image(
  val id: Long,
  val name: String,
  val uri: Uri
)

create a seperate file 
 class ImageViewModel: ViewModel() {
  
  var images by mutableStateOf(emptyList<Image>())
  private set

  fun updateImages(images: List<Image>) {
    this.images = images
  }
 }

 on the top of the activity class use this view model 

 private val viewModel by viewModel<ImageViewModel>()

 then showing the image 

 setContent {
  ContentProvidersTheme {
    LazyColumn(
      modifier = Modifier.fillMaxSize()
    ) {
      items(viewModel.images) {
        Column(
          modifier = Modifier.fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
           AsyncImage(
            model = image.uri,
            contentDescription = null
           )
           Text(text = image.name)
        }
      }
    }
  }
 }

 To access external storage the user need to give access to the files 
 we need to put it inside permissions

 <uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />

 inside on create 

// check if it is using latest api level 33 and above if 
// not check check for other methods.

 ActivityCompat.requestPermissions(
  this,
  arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
  0
 )

 // to create our own content provider 
 // check more info about it 
 // here little is explained

 class MyContentProvider: ContentProvider() {

  // click ctrl/cmd + i 
  // to see the methods 
 }

 then we need to register the content provider inside the manifest 