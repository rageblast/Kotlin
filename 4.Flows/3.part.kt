State Flow vs Shared Flow:

state Flow:
It's like live data without life cycle awareness.

So our activity can't really detect when or rather the state flow 
can't really detect when the activity goes in the the background.

Other than that it's actully the same as live data so it will notify all of its 
observers or rather collectors when we're dealing with flows.

If there is a new chnage and it will exactly keep one single value.

Because of above behaviour this also makes it a so called hot flow.

Cold Flow:
If there is no collectors and then it won't do anything.

Hot Flow:
if there is no collectors it will do something.

Example:
Assign new value to state flow and change it even if there are no collectors.

class MainViewModel: ViewModel() {
  val countDownFlow = flow<Int> {
    val startingValue = 5

    var currentValue = StartingValue 

    emit(StartingValue)

    while(currentValue > 0) {
      delay(1000L)
      currentValue--
      emit(currentValue)
    }
  }

  // UI State

  private val _stateFlow = MutableStateFlow(0)

  val stateFlow = StateFlow.asStateFlow() // Immutable value as our mainActivity collects it.

  init {
    collectFlow()
  }

  // If we rotate our device our UT State got lost because our activity 
  // get's recreated. So to store it we use this viewmodel and it won't 
  // get lost.

  fun incrementCounter() {
    _stateFlow.value += 1
  }

  private fun CollectFlow() {
    val flow = flow {
      delay(250L)
      emit("Appetizer")
      delay(1000L)
      emit("Main Dish")
      delay(100L)
      emit("Dessert")
    }

    ViewModelScope.launch {
      flow
      .onEach {
        println("Flow: $it is delivered")
      }
      .collectLatest {
       println("Flow: Now Eating $it")
       delay(1500L)
       println("Flow: Finished eating $it")
      }
    }
  }
}

// MainActivity:

class MainActivity : ComponentActivity()
{
  override fun onCreate( SavedInstanceState : Bundle? ) {
    
    Super.onCreate(SavedInstanceState)

     SetContent {
      KotlinFlowsTheme {
        val viewModel = ViewModel<MainViewModel>()

        // Don't recommeded StateFlow using compose because it 
        // already comes with state so we don't need the Collected
        // state function, Sometimes we can get stateflow from the 
        // library so we use Collect State Function.

        val time = viewModel.countDownFlow.CollectAsState(initial = 10)

        val count = ViewModel.stateFlow.CollectAsState(Initial = 0);

        Box(Modifier = Modifier.FillMaxSize()) {
          Button(onClick = {
            ViewModel.incrementCounter()
          }) {
            Text( text = "Counter : ${count.value} ")
          }
        }
      }
     }
  }
}

Using XML: How we collect state in the Main Activity:

resuable function: (write it below MainActivity)

fun<T> ComponentActivity.CollectLatestLifecycleFlow(flow<T>, collect: suspend(T) -> Unit) {
  lifecycleScope.launch {
    repeatOnLifeCycle(Lifecycle.State.Stater)
    {
      flow.collectLatest(collect)
    }
  }
}

class MainActivity: ComponentActivity()
{
  private val ViewModel: MainViewModel by viewModels()

  override fun onCreate(SavedInstanceState: Bundle?) {
    super.onCreate(SavedInstanceState)

    CollectLatestLifecycleFlow(viewModel: StateFlow) {
      binding.tvCounter.text = number.toString()
    }
  }
}

Shared Flow:

-> It is used to send one time events 

Two type emission:

1.State emission 

-> It is persist one

Ex:
When we rotate our screen then the state flow will fire off again
and notify all his collectors.

2. One Time Event Emission:

-> Below the _stateFlow write the below code in the View Model 

Private val _SharedFlow = MutableSharedFlow<Int>

val SharedFlow = _SharedFlow.asSharedFlow()

-> We don't have flow emitters like first what to emit and 
-> Second what value to emit like countDownFlow.

-> We are going to send event using shared Flow

Example:
When user login success we need to tell our navigator to go other 
page and we don't wan't this to trigger when we rotate so we use 
sharedflow.

Inside init create a corountine scope:

init {
   collectFlow()
   // Insead of above use squarenumber(3)
   // This won't collect it 
   // This will work if it is cached

   viewModelScope.launch {
    SharedFlow.collect {
      delay(2000L)
      println("First Flow: The received number is $it")
    }
   }

   viewModelScope.launch {
    SharedFlow.collect {
      delay(3000L)
      println("Second Flow: The received number is $it")
    }
   }

   squarenumber(3) -> This will emit the event and above will collect it 

}

fun squarenumber(number: Int) {
  ViewModelScope.launch {
    _SharedFlow.emit(number * number)
    // The above one will suspend the corountine untill all the 
    // sharedflow collectors process it.
  }
}

private val _SharedFlow = MutableSharedFlow<Int>(replay = 5)

replay = 5 -> This will cache five emissions in the flow and when there 
are new collectors will receive these emissions that are actullay cached.



