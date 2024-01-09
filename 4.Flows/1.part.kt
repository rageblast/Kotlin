Reactive Programming ->

It's all about being notified about changes in your code and 
sending them through a pipeline that potentially modifies them 

A flow is a coroutine that can emit multiple values over a period 
of time.

A single suspend function will return single value 
when we reach that point the function is over 

If we want something like countdown timer emits value every single second
we can't do it with the suspend function of coroutine
so we use flow 

create a view model class:

class MainViewModel: ViewModel() {

// Below one is a Normal flow which is a code flow 
   val countDownFlow = flow<Int(type of the value that we emit over time)>{
     val startingValue = 10
     var currentValue = startingValue
     // showing in the ui by notify it 
     emit(startingValue)
     while(currentValue > 0) {
      delay(100L)
      currentValue--
      // showing in the ui by notify it 
      emit(currentValue)
     }
   }

   // Notify if any changes made in this flow
   // we call it as collect the flows
   
   init {
    collectFlow()
   }

   private fun collectFlow() {
    // we want it in corountine 
    // so we use the below method 
    viewModelScope.launch {
      // when we call emit we will trigger the below one 
      countDownFlow.collect { time -> 
          println("The current time is $time")
      }
    }

    collect and collectlatest -> they both will get triggered everytime the emit is triggered 
    collectlatest -> if a block of code still running not finished then a new emit comes it will
    leave the unfinished block and trigger new block.
   }
}

// Activity 
class  MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      KotlinFlowsTheme {
        val viewModel = viewModel<MainViewModel>()
        // we will create state because it will redraw the compose 
        val time = viewModel.countDownFlow.collectAsState(initial = 10)
        Box(modifier = Modifier.fillMaxSize()) {
          Text(
            text = time.value.toString(),
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.Center)
          )
        } 
      }
    }
  }
}