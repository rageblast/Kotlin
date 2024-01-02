Its like an envelope where we send one data from one activity to another

Explicit Intent -> are targeted towards one specific app specified in that intent own app
-> launch other activity of other apps

// Create a new activity called second activity 

// use setContent -> set content 

// When New Activity, Permissions are created we need to put it inside Manifest 

// Add it to Manifest when new activity is created
// add the second activity

setContent {
  IntentsAndIntentFiltersTheme {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Button(onClick = {
      // our own activity 
      // so use packageContext parameter
      // also -> using this we can stop the .methodone.methodtwo
      // we can specify the methods inside an object 
      Intent(applicationContext, SecondActivity::class.java).also {
        startActivity(it)
      }
      }) {
        Text(text = "Click me")
      }
    }
  }
}


// To open other apps we need to know their package name
Intent(Intent.ACTION_MAIN).also {
  it.`package` = "com.google.android.youtube"
  // To find the package name we can use ADB Android Package Bridge
  // using the emulator it prints the package name inside the emulator

  // go to terminal 
  adb shell
  pm list packages 

  // shows all the packages 
  
  // search for youtube use grep
  pm list packages | grep youtube 

// check if the package is present or not
try {
startActivity(it)
} catch(e: ActivityNotFoundException) {
  e.printStackTrace
}
  
}

Intent.ACTION_MAIN -> where we use action params and it says open the main activity of other apps 


Implicit -> We will say the action only and android will go and fetch what are all the 
apps that can perform this actions will list it out and finally we will select the app we want

Intent.ACTION_SEND -> its just a string so we can use our own also

val intent = Intent(Intent.ACTION_SEND).apply {
  type = "text/plain"  (mime type should be specified)
 // passing parameter
 putExtra(Intent.EXTRA_EMAIL, arrayof("test@test.com"))
 putExtra(Intent.EXTRA_SUBJECT, "This is my Subject")
 putExtra(Intent.EXTRA_TEXT, "Content of the email")
}

// to get that extra -> use intent.getStringExtra("Specify the extras name")

// check if there is any app that satisfies this intent 
if(intent.resolveActivity(packageManager) != null) {
 // if satisfied start that activity 
 startActivity(intent)
}

resolveActivity won't work because android made slight changes like
if an intent wants to open an email app to send email but not to access the photo galary app 

so to do it we have to use "queries" in Manifest

<queries>
<intent>
// app that satisfies below action will open
<action android:name="android.intent.action.SEND"/>
<data android:mimeType="text/plain" />
</intent>
</queries>


// If i build an email app that opens up and wants go get this intent details ie. get the put extra details in it
// we use intent filters 

Create a new intent-filter inside manifest and register our app to accept all images

<intent-filter>
// action
<action android:name="android.intent.action.SEND" />
// what category it has to receieve
<category android:name="android.intent.category.DEFAULT" />
// mime type 
<data android:mimeType="image/*"/>
</intent-filter>

// if we do like this it will open a new instance of our app
// if we want to use the current instance of our app running

we can use -> android.launchMode="singleTop" inside manifest

// the above one will do what it will trigger onNewIntent method 
// so we need to access it in our activity

override fun onNewIntent(intent: Intent?) {
  super.onNewIntent(intent)
  val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
       // Checking if the SDK is new version or not 
       intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
  } else {
       intent?.getParcelableExtra(Intent.EXTRA_STREAM)
  }
}

// when we select our app to open the image 
// we will get the data from the intent
// using that intent we will parse that extra so that we need to know what extra is coming whether it is a string or image 
// it will be a uri 

-> check -> Intent.EXTRA_STREAM, Uri::class.java

// create a viewModel to update the image that we received

class ImageViewModel: ViewModel() {

  var uri: Uri? by mutableStateOf(null)
  private set 

  fun updateUri(uri: Uri?) {
    this.uri = uri
  }
}

// create an instance of the viewmodel inside the main activity

// then add the dependency implementation "io.coil-kt:coil-compose:2.4.0" -> for image loader
// it will read the uri and convert it into byte
// if we don't use it will be hard

class mainActivity : ComponentActivity() {
private val viewModel by viewModels<ImageViewModel>()

setContent {
  IntentsAndIntentFiltersTheme {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      viewModel.uri?.let {
        // since we use the above library we can use the below AsyncImage
        AsyncImage(
          model = viewModel.uri,
          contentDescription = null
        )
      }
      Button(onClick = {})
    }
  }
override fun onNewIntent(intent: Intent?) {
  super.onNewIntent(intent)
  val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
       // Checking if the SDK is new version or not 
       intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
  } else {
       intent?.getParcelableExtra(Intent.EXTRA_STREAM)
  }

  viewModel.updateUri(uri)
}
}

}