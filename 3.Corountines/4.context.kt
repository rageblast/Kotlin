It always starts in specific contexts
contexts will describes in which thread or corountine will be started

GlobalScope.launch -> won't give much of a control

// we can start corountine by passing dispatcher in launch function

Dispatchers.Main -> It will start the coroutine in the main thread
and that is useful if you need to do UI operations from within your corountine
because you can change only change the UI from the main thread.

Dispatchers.IO -> Which is useful in all kind of data operations such always
networking, writing to database or reading and writing to files.

Dispatchers.Default -> Choose if we are planning on doing complex and 
long running calculations that will block the main thread

Example:
if we need to sort a list of 10,000 elements then we should 
do that in the default dispatcher to not block our main thread and 
our thread UI.

Dispatchers.Unconfined -> if we start our corountine in a Unconfined
that causes a suspend function it will stay in the thread that the suspend
function resumed

other options inside launch function parameter:

we can start our new thread using newSingleThreadContext("MyThread") {}

GlobalScope.launch() {

}

usefulthing about corountine context is they can switch within corountine

Example:
if we want to make a network call which we cannot do from main thread.

we should do network request in our main thread we should only change UI in our main thread.

GlobalScope.launch(Dispatchers.IO) {
  val answer = doNetworkCall()
  Log.d(TAG,"${Thread.currentThread(),name}")
  // Below we need to change the context of main thread using 
 // withContext + Dispatchers.Main
  withContext(Dispatchers.Main) {
  Log.d(TAG,"${Thread.currentThread(),name}")
    tvDummy.text = answer
  }
}

suspend fun doNetworkCall(): String {
  delay(3000L);
  return "This is the answer";
}