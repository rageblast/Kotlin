Jobs -> whenever we start a corountine it returns a job 
which we can save it in a varaible.

val TAG = "MainActivity"

val job = GlobalScope.launch(Dispatchers.Default) {
    repeat(5) {
      Log.d(TAG, "Corountine is still working..")
      delay(1000L)
    }
}

job.join() // wait for it until it finish 
// the above one is a suspend function.
// so we used below.

runBlocking {
  job.join() // block our thread until it is finished.
  Log.d(TAG, "Main Thread is continuing..")
}

runBlocking {
  delay(2000L)
  job.cancel() // cancel our job.
  Log.d(TAG, "Main Thread is continuing..")
}

// In the above we are delaying the main thread by 2s at that time 
// job is working so it will print two times.

// but the jobs work for 5s so it won't run for other 3s.

// If we cancelled our job it will still works.

val job = GlobalScope.launch(Dispatchers.Default) {
  Log.d(TAG, "Starting long running calculation..")
  for(i in 30..40) {
    Log.d(TAG, "Result for i = $i: ${fib(i)}")
  }
  Log.d(TAG, "Ending long running calculations..")
}

runBlocking {
  delay(2000L)
  job.cancel()
  Log.d(Tag, "Canceled job!")
}

fun fib(n: Int): Long {
  return if(n == 0) 0
  else if(n == 1) 1
  else fib(n-1) + fib(n-2)
}

// Reason:
our corountine is busy with this line Log.d(TAG, "Result for i = $i: ${fib(i)}")
inside the loop

so it has no time for check the cancellation.

so it does not use suspend function so we use manual cancelation.

manual cancelation:
for (i in 30..40) {
  if(isActive) { // check if corountine is active or not
    Log.d(TAG, "Result for i = $i: ${fib(i)}")
  }
}

// we want to cancel the corountine because of time out.
// if we have a network call that takes more time we need to stop it.
// we can use the withTimeout function.

val job = GlobalScope.launch(Dispatchers.Default) {
  Log.d(TAG, "Starting long running calculation...")
  withTimeout(3000L) {
    for(i in 30..40) {
        Log.d(TAG, "Result for i = $i: ${fib(i)}")
    }
  }
}

Log.d(TAG, "Ending long running calculations..")

// we don't use the running block.