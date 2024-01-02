Run app in the Background -> services does it -> it is an android component -> no ui 

Two services Normal and Foreground services 

Normal -> user won't know what is happening in background
Foreground -> users will know what is happening and they can control it 

Normal -> These cannot be guaranted to run indefinitely because 
android system needs the memory it will kill it.

Foreground -> It will run definitely so android thinks users wants it so we can take up 
other memory so we will leave this one.

create service class:

class RunningService : Service() {
  // Inherits from service 
  
  // Need to tell what kind of service it is 
  override fun onBind(po: Intent?) : IBinder? {
    // it will return bound service IBinder
    // it will have one active instance -> multiple component connect to this single instance
    // creates a stream to communicate with that service and receives callback from that service
    return null -> nothing is bind to this service like js bind
  }

  // Define commands that this activity send to this service 
  // when we will launch and stop this service 

  // when an android component send an intent to this runnig service we use the below method 
  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
      when(intent?.action) {
        Actions.START.toString() -> start()
        Actions.Stop.toString() -> stopSelf()
      }

      // stopSelf() -> it stops the service
      // if we need to free up anything wee need to write stop() method 

      private fun start() {
        // we need to create notification channel to show notification 
        // channel -> Category for specify set of notification 
        // example :
        // insta app has s set of notifications that are related to 
        // receiving a message from friend of yours
        // category for someone like your post or interacted with your post 
        
        // In the settings of your Android app the user can decide whether they 
        // want to see notifications for specific channel or not.
        // user can toggle the notification on or off


       val notification = Notification.Builder(this (services has context also so we can run onDestroy, onCreate functions), "running_channel"(channel))
              .setSmallIcon(R.drawable.ic_launcher_foreground)
              .setContentTitle("Run is active")
              .setContentText("Elapsed time: 00:50")
       startForeground(1(unique identifier for this foreground it should not be zero), notification)
      }

    return super.onStartCommand(intent, flags, startId)
  } 
 
 enum class Actions {
  START, STOP
 }
}


// We need to create a channel called "running channel"
// when our app typically launched we do it inside our application class 
// on create method of the application class it will run once when our application boots up 
// first time when our instance of our app closes and re-open again we will run it 

// Notification channels are introducted after oreo so to check if it is oreo or bigger

1. Give same id as in the Notification Builder 
2. Give name for the notifications that our user can see 
3. Will give sounds for the notification using notification Manager based on the value 

Showing Notification is not allowed to do by our app so we need to 
use the android operating system which provides 
a service for it we need to use it.

The app services is not save as service we create 
service we create runs on background and app service is a functions provided by our android system 

class RunningApp: Application() {

  override fun onCreate() {
    super.onCreate()
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(
        id: "running_channel",
        name: "Running Notifications",
        NotificationManager.IMPORTANCE_HIGH
      )
      val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
      notificationManager.createNotificationChannel(channel)
    }
  }
}

// when we create a broadcast receiver, Services, Activity we need to register it inside manifest.

We need to define inside application 
<application>
<activity></activity>
<service android:name=".RunningService" /> // this will receive all type of intents
// if it need to receive only specific intent use we need to define intent filter for it 
</application>

// for notification we need to ask permissions for it 
// because it is sensitive

<uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>
<uses-permission android:name="android.permission.POST_NOTIFICATION"/>

// ask for the permission
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if(Build.VERSION.SDK_INT >= build.VERSION_CODES.TIRAMISU) {
      ActivityCompat.requestPermissions(
        this,
        arrayOf(Manifest.permission.POST_NOTIFICATION), // Specify the permissions we want in an array
        0 // request code for that request so we may know how the user reacted
      )
    }
    setContent {
      ForegroundServicesTheme {
        // A surface container using the 'background' color from the theme
       Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
       ) {
        Button(onClick = {
          Intent(applicationContext, RunningService::class.java).also {
            it.action = RunningService.Actions.START.toString()
            startService(it) // it will deliver the intent to our service
          }
        }) {
          Text("Start run")
        }
        Button(onClick = {
          Intent(applicationContext, RunningService::class.java).also {
            it.action = RunningService.Actions.STOP.toString()
            startService(it) // it will deliver the intent to our service
            // we have start Foreground services also it will do the same as above
          }
        }) {
          Text("Stop run")
        }
       }
      }
    }
  }
}

// we need to register our application class inside our manifest because it is also
// an android component 

<application
android:name=".RunningApp"
></application>


Finally we have foreground types that we want to specifically track
on the background so user may know
<service android:name=".RunningService" android:foregroundServiceType="camera,phoneall,etc"/>

// don't put all of your source code inside your services because
// your services is running your app is also running 
// so put what the service wants only in the service not all  
// and create separate class for all of it and use it in the services 