RunBlocking -> starts a corountine in the main thread and also will block it.

GlobalScope.launch(Dispatchers.Main){
 delay(100L)
}

The difference between the two is runBlocking will block the main thread 
while the above one won't.

Below one will stop the UI Updates.

runBlocking {
delay(100L)
}

If we don't want the corountine behaivour but still want to call suspend
functions in your main thread

if we have a suspend function that we want to call from our main thread
we can use runBlocking.

If we dont care about asynchrounous and we can play around with coroutine 
to figure out how they actually work behind the scenes

when we start runBlocking it will use the main thread

Example:

val TAG = "MainActivity"

Log.d(TAG, "Before runBlocking")
runBlocking {
  Log.d(TAG, "Start of runBlocking")
  delay(5000L)
  Log.d(TAG, "End of runBlocking")
}
Log.d(TAG, "After runBlocking")

// Below and above or doing the same but one difference is 
// In the above we can call suspend functions below we can't.

runBlocking {

}
Log.d(TAG, "Start of runBlocking")
Thread.sleep(5000L)
Log.d(TAG, "End of runBlocking")
Log.d(TAG, "After runBlocking")

// the below one is a corountine scope 
// so we can launch other corountine by using the launch block
// we can't need GlobalScope because it is inside corountine scope
runBlocking {
launch { // this will run asynchrounous and it won't be blocked

}
}

Example:

// The two dispatcher won't get add up they will be executed at the sametime

Log.d(TAG, "Before runBlocking")
runBlocking {
  launch(Dispatchers.IO) {
    delay(3000L)
    Log.d(TAG, "Finished IO Coroutine 1")
  }
  launch(Dispatchers.IO) {
    delay(3000L)
    Log.d(TAG, "Finished IO Coroutine 2")
  }
  Log.d(TAG, "Start of runBlocking")
  delay(5000L)
  Log.d(TAG, "End of runBlocking")
}
Log.d(TAG, "After runBlocking")