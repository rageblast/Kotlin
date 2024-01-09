// First Include Corountine 

Build.Gradle:
def coroutines_version = "1.2.1"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"

Every corountine should start in a corountine scope

  Inside OnCreate create a corountine 
  GlobalScope -> untill our app lives this corountine works
  But the corountine will get stopped once it is finished.
  GlobalScope will launch the corountine inside another thread. 

 put val TAG = "MainActivity" -> above onCreate method 

  GlobalScope.launch {
    delay(3000L) // Like Sleep functions in thread corountine has its own sleep function which is delay
    delay -> will block the current corountine not the whole thread
    Log.d(TAG, "Coroutine says hello from thread ${Thread.currentThread().name}")
  } 

  Log.d(TAG, "Hello from thread ${Thread.currentThread().name}")

If Main thread finishes its work all the other threads and Corountines will get cancelled
Even though if they started in another thread and are started asynchronously 