To make two request at the same time

soluion: 1 don't do it

val TAG = "MainActivity";

GlobalScope.launch(Dispatchers.IO) {
  val time = measureTimeMillis {
    var answer1: String? = null
    var answer2: String? = null
    val job1 = launch { answer1 = networkCall1() }
    val job2 = launch { answer2 = networkCall2() }
    job1.join()
    job2.join()
    Log.d(TAG, "Answer1 is $answer1")
    Log.d(TAG, "Answer2 is $answer2")
  }
  Log.d(TAG, "Requests took $time ms.")
}

Solution: 2 => use this method
Async: it will return a deferred which can be used to get the calculation or of the netwok call 
async will start a new corountine.

It won't give us job.

GlobalScope.launch(Dispatchers.IO) {
  val time = measureTimeMillis {
    val answer1 = async { networkCall1() }
    val answer2 = async { networkCall2() }
    Log.d(TAG, "Answer1 is ${answer1.await()}")
    Log.d(TAG, "Answer2 is ${answer2.await()}")
  }
  Log.d(TAG, "Requests took $time ms.")
}

GlobalScope.async -> like this can also be used if we need to return asynchrounsly