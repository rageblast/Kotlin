// its a bridge between our android app and android operating system
// when our app needs to communicate with other system components or other apps 

// getting images or connecting to database or preferences

// open other apps that opens text file we need to pass context

// its a middle man thats helps in many ways

// activity context and application context -> two type

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle){
    super.onCreate(savedInstanceState)
    // Activity Context
    val myContext: Context = this (activity destories its context also destories)
  }
}

class MyApplication: Application() {

// application can have lifecycle methods
  override fun onCreate() {
    super.onCreate()
    // this will long life time context than main activity 
    val myContext: Context = this (when application destories his context will also destories)
  }
}

this context will have memory leak so use it cautiously 

class myViewModel: ViewModel() {

  var context: Context? = null

}

// whenever the below activity gets destory the above view model won't 
// it will destory only when app destory 
// so it will still have the context of the below activity 
// which is a memory leak 

class MainActivity: ComponentActivity() {

  private val viewModel by viewmodels<MyViewModel>()

  override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
    
    //to access application context 
    this.applicationContext
    
    viewModel.context = this 
  }

}

// so don't store it outside activity if using activity context 

// application context may produce leak because the application context life time is more than activity 