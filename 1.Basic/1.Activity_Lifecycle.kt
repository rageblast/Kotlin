Activity -> container for one or multiple screens in your app
Its where user interact with our app so it is called as Activity

// What activity do
// 1. Check currently active on the screen 
// 2. If its in the background 
// 3. servers as entry point in our app 
// ex: when user comes to our from app from another app 

// we have one activity per screen in the past 
// then we had fragment where we use one activity 
// we can have many fragment screen that we will bundle it together
// and show which screen when a button is clicked in one activity 

// now we have jetpack compose which means we will have one activity for an app 

// MainActivity -> uses as an entry point of our application

// like react we have lifecycle in android for an activity 

// onCreate -> mount phase where we initialize the variable

// onStart -> it will start the activity but user can't see it 

// onResume -> now user can start seeing the app and they can interact with it 

// it will stay in this state until the user moves out from it 

// when a dialog opens up or move on from one activity to another 

// the current activity will be in onPause state but they will be in memory

// so when we come back it will help us to start the activity again it will go back to onResume state 

// onStop and onPause state 

onPause -> the activity will there u can see it when dialog opens up it still there in the background u can see some part of it 

onStop -> when whole activity closes it will be in stop state we can't see it in background also

when user comes back to the previous activity -> from onStop -> it will go to oRestart -> and it will go to onStart phase again the same step

onDestory() -> 1. when our android os wants to destory our activity to get space

2. when we change the global configuration when we rotate our phone we need to rebuild our app

3. languague change configuration

-> it will start form start -> like onCreate

Example:

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }

    override fun onStart() {
        super.onStart()
    }
}

If we have very important data to save when user leaves the app we do it onPause()