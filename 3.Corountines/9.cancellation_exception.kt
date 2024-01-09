lifecycleScope.launch {
  try {
    launch {
      throw Exception()
    }
  } catch (e: Exception) {
    println("Caught exception $e")
  }
}

The above one didn't caught the exceptions.

Why we need to know how coroutine scope works

Explanation:

1.Outside we have more scope like lifecycleScope, viewModelScope and Our own custom scope.
2.Using that we will launch a coroutine using that scope lifecycleScope.launch{}
3.Inside it we can have child coroutines using launch{} function.
4.Both lifecycleScope.launch{}  and child coroutine launch{} run independent of each other

Exceptions:

lifecycleScope.launch {
  try {
    launch {
      launch {
      throw Exception() // when we get exception if it is not handled it propagate up to first launch it is in it 
      // and then it will move to above launch and it will move to above lifecycleScope.launch
      // if it is not handled it will crash
      }
    }
  } catch (e: Exception) {
    println("Caught exception $e")
  }
}

launch{} -> is used to do something we don't except it to give result 
async -> will return result 

Execption will get caught on await of async

But in the below example make the system crash if exception is not handled why 
but we used async. 

lifecycleScope.launch {
  val string = async {
    delay(500L)
    throw Exception("error")
    "Result"
  }
}

Answer is launch when we run the code async will throw exception
and it will propogate to the top which is the launch{} and in it is 
not handled so it will through error.

But if we turn the launch into async and run the code it won't
make the system crash.

lifecycleScope.async {
  val string = async {
    delay(500L)
    throw Exception("error")
    "Result"
  }
}

Because if it throws error the async block will propagate up it to the 
parent async block and it will leave it up to the user to what to do it (i.e) await

we can use coroutine exception handlers to handle exception
it should pass to parent.
it won't run for cancelation exception but it will run for uncaught exceptions 

val handler = CoroutineExceptionHandler { _(which coroutine trown the exception), throwable ->
  println("Caught exception: $throwable")
 }

 lifecycleScope.launch(handler(this will get the exceptions)) {
  throw Exception("Error")
 }

 lifecycleScope.launch(handler) {
  launch() {
  throw Exception("Error")
  }
 }

 Build our own corountine scope:
Dispatchers.Main + handler: combining context 
 CoroutineScope(Dispatchers.Main + handler).launch {
     launch {
      delay(300L)
      throw Exception("Coroutine 1 failed")
     }
     launch {
      delay(400L)
      println("Coroutine 2 finished")
     }
 }

 Both launch gets run independently but the first launch 
 only got caught but the second didn't why?
 first affects second because we used CoroutineScope

 Because if one corountine child function fails whether we handle exception or not 
 it will cancels all the other child coroutine 

Exception -> propogation remember

but we can avoid one coroutine fails others should run using supervisorScope
 CoroutineScope(Dispatchers.Main + handler).launch {
  supervisorScope {
     launch {
      delay(300L)
      throw Exception("Coroutine 1 failed")
     }
      launch {
      delay(400L)
      println("Coroutine 2 finished")
     }
  }
 }

 but if we want other coroutine not to run if one fail we can use CoroutineScope
    CoroutineScope(Dispatchers.Main + handler).launch {
  coroutineScope {
     launch {
      delay(300L)
      throw Exception("Coroutine 1 failed")
     }
      launch {
      delay(400L)
      println("Coroutine 2 finished")
     }
  }
 }

 group the corountine and use it.

 lifecycleScope.launch {
  val job = launch {
    try {
      delay(500L)
    } catch(e: Exception) {
      e.printStackTrace()
    }
    println("Coroutine 1 finished")
  }
  delay(300L)
  job.cancel()
 }

 we have delayed the lifecycleScope.launch for 3s and canceled the job 
 but it still prints "Coroutine 1 finished" inside the job which should print after 5s

 when coroutine gets canceled if it has suspend function here it has delay(500L)
 will throw cancellation exception error but it is executed inside a try catch block
 this exception get caught by it and print it.

 so the outer coroutine scope which is lifecycleScope.launch {} won't know child got canceled
 thats why it prints "Coroutine 1 finished"

 solution 1:
 Put different exception to be caught not all 

  lifecycleScope.launch {
  val job = launch {
    try {
      delay(500L)
    } catch(e: HttpException) {
      e.printStackTrace()
    }
    println("Coroutine 1 finished")
  }
  delay(300L)
  job.cancel()
 }

 solution 2
 in the all exception check if it is cancellation exception throw back to parent
 
 lifecycleScope.launch {
  val job = launch {
    try {
      delay(500L)
    } catch(e: Exception) {
      if(e is CancellationException) {
        throw e // this will propagate up to parent and tell
      }
      e.printStackTrace()
    }
    println("Coroutine 1 finished")
  }
  delay(300L)
  job.cancel()
 }