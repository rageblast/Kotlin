Broadcast and receivers -> the event it receives the system will silently handle it 
not open an activity

Its a system-wide events that our app can consume and it can be send by our android system 
or also with other apps or also by our apps 

Our app will receiver that broadcast using the broadcast receiver and trigger that broadcast.

Ex:
when our android system is booted once success boot the android system will send the broadcast 
to all the apps that are registerd to receive this specific boot completed broadcast and these apps will react to it.

Ex: like synchronizing data.

Ex: 2
Music Player app when a music is played and suddenly we received a phone call
our phone calling app will send a broadcast to our music player app
it will react to that broadcast and it will pause the music and allow us to attend the call 

How we react to broadcast that our android system would sent:
Ex: when airplane mode turned on and off how should our app should work 

// Broadcast Receiver is Nothing but a class 

 Class Name AirplaneModeReceiver -> which inherits from BroadcastReceiver
 when we receive any broadcast the onReceive method will get triggered
 in that broadcast we will send intent which will hold the intention of that Broadcast
 now this intent is passed to many apps 

 Ex:
 when an airplane mode is changed the android system will send intent to all the apps 

 class AirplaneModeReceiver: BroadcastReceiver() {

  override fun onReceive(context: Context?, intent: Intent?) {
    // Checking the action 
    if(intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
      // Know check whether the airplane mode is turned on or off 
      val isTurnedOn = Settings.Global.getInt(
        context?.contentResolver,
        Settings.Global.AIRPLANE_MODE_ON
      ) != 0
      println("Is airplane mode enabled? $isTurnedOn")

      // google it and check -> Settings.Global.getInt(context?.contentResolver, Settings.Global.AIRPLANE_MODE_ON) != 0
    }
  }
 } 

// Then we need to register our receiver to receive what kind of intents it has to respond

class MainActivity : ComponentActivity() {

  private val airPlaneModeReceiver = AirPlaneModeReceiver()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.OnCreate(savedInstanceState)
    registerReceiver(
      airPlaneModeReceiver,
      IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
    )
  }

  // Then we need to unregister this receiver when it is not needed
  override fun onDestory() {
    super.OnDestory()
    unregisterReceiver(airPlaneModeReceiver)
  }
}


// The above receiver we configured is a dynamic receiver
// when our app is open it will work and when our app is closed it won't work 

// Static receiver is launched whether is app is active or not 

// There are lot of restrictions on static receivers 
// there are few exceptions that allow us to use static receiver like boot completion process

// Because it increases battery usage more because when android system is booted it will trigger the 
// apps that will receive this broadcast and do some process in that app 

// If we want to set-up a static receiver we need to go to the manifest and do it 
above </appliction>

<receiver android:name=".AirplaneModeReceiver">
<intent-filter>
// same process in the intent filter refer above chapter
</intent-filter>
</receiver>

// Here the AirplaneModeReceiver won't work because for static we have certain methods only like boot etc.
// so check that and use it for now AirplaneModeReceiver can be used as dynamic.


Now send broadcast from our app to other app 
App 1:
// Button(onClick = {
  sendBroadcast(
    Intent("TEST_ACTION")
    "TEST_ACTION" -> custom action name
    we can add extra to this intent as data 
    we can specify package name if we want to use the static receiver to all the apps 
    that want this intents 
  )
})

App 2:
class TestReceiver: BroadcastReceiver() {

  override fun onReceive(p0: Content?, intent: Intent?) {
    if(intent?.action == "TEST_ACTION") {
      println("Received test intent!")
    }
  }
}


class MainActivity : ComponentActivity() {

  private val airPlaneModeReceiver = AirPlaneModeReceiver()
  private val testReceiver = TestReceiver()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.OnCreate(savedInstanceState)
    registerReceiver(
      airPlaneModeReceiver,
      IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
    )
    registerReceiver(
      testReceiver,
      IntentFilter("TEST_ACTION")
    )
    setContent {
      BroadcastsTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        )
      }
    }
  }

  override fun onDestory() {
    super.OnDestory()
    unregisterReceiver(airPlaneModeReceiver)
    unregisterReceiver(testReceiver)
  }
}