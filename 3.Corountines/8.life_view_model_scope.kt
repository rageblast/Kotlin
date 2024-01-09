GlobalScope -> starts a corountine that lives as long as the application does.

We can use other dependencies to use local and view model scope.

def arch_version = '2.2.0-alpha01'
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$arch_version"
implementation "android.lifecycle:lifecycle-runtime-ktx:$arch_version"

btnStartActivity.setOnClickListener {
  GlobalScope.launch {
    while(true) {
      delay(1000L)
      Log.d(TAG, "Still running...")
    }
  }
  GlobalScope.launch{
    delay(5000L)
    Intent(this@MainActivity(Inside corountine we cannot use this), SecondActivity::class.java).also {
      startActivity(it)
      finish()
    }
  }
}

// Define the above code in onCreate 
Both GlobalScope will run at same time 
first one is a infinite loop and second one is movining to other activity 

after 5s the second one will get triggered and move to second activity
but the first one don't get destoryed because it is defined inside a GlobalScope
it will get destoryed only when the application is destoryed.

To solve it we use the below one.

  lifecycleScope.launch {
    while(true) {
      delay(1000L)
      Log.d(TAG, "Still running...")
    }
  }

  GlobalScope.launch{
    delay(5000L)
    Intent(this@MainActivity(Inside corountine we cannot use this), SecondActivity::class.java).also {
      startActivity(it)
      finish()
    }
  }

  lifecycleScope -> attach the corountine to the lifecycle of the activity
  all the corountine attachec to this lifecycle scope will also get destoryed

  finish() -> onDestroy is called so the lifecycleScope will check this activity and do according to it 
  here onDestory means destroy the corountine.

  viewModelScope -> its same as lifecycleScope only thing it will keep our corountine
  alive as long as our view model is alive.


