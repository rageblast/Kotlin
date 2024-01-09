  Suspend Functions are executed inside an another suspend function 
  or inside a corountines.

  After onCreate Method create two Suspend functions

  GlobalScope.launch {
    val networkCallAnswer = doNetworkCall() // first will stop the second so result will print after 6s
    // so first will influence the second one.
    // because they executed inside the same corountine.
    val networkCallAnswer2 = doNetworkCall2()
    Log.d(TAG, networkCallAnswer)
    Log.d(TAG, networkCallAnswer2)
  }

  suspend fun doNetworkCall() : String {
    delay(3000L)
    return "This is the answer"
  }

    suspend fun doNetworkCall2() : String {
    delay(3000L)
    return "This is the answer"
  }