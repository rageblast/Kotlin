Side Effects -> Something that escapes the scope of a composable function

example:

class MainActivity:ActivityCompat {
  private var i = 0 // this is not binded to compose 
  // it goes outside the scope of jetpack compose

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      var text by remember {
        mutableStateOf("")
      }
      EffectHandlersTheme {
        Button(onClick = { text += "#"}) {
          // this block of code will recompose and we don't how often it will recompose
          // since it is a incrementor we leave it if it is a network request 
          // what will happen think

          // we need to avoid non-composed things in compose code 
          // we can avoid it using effect handlers
          i++
          Text(text = text)
        }
      }
    }
  }
}

1. LaunchedEffect handlers:
// Example : 1
LaunchedEffect -> its safe to use it inside composable because it itself a composable.

// the key can be a state 
// whenever the state changes it will destory the old running
// function inside LaunchedEffect and triggers the new one (i.e) rerun the function again
EffectHandlersTheme {
  LaunchedEffect(key1 = text) {
    delay(1000L)
    println("The text is $text")
  }
}

// Example: 2

// using the below view model we can send events using shared flows

class LaunchedEffectViewModel: ViewModel() {

  private val _sharedFlow = MutableSharedFlow<ScreenEvent>()
  val sharedFlow = _sharedFlow.asSharedFlow()

  init {
    viewModelScope.launch {
      _sharedFlow.emit(ScreenEvents.ShowSnackbar("Hello World!"))
    }
  }

// if we want use Snackbar or navigate to different screens 
// we can send these events from the view model to the ui
// see the above _sharedFlow.

  sealed class ScreenEvents {
    data class ShowSnackbar(val message: String): ScreenEvents()
    data class Navigate(val route: String): ScreenEvents()
  }
}

// we need to collect the flow (sharedFlow) in our composable

@Composable 
fun LaunchedEffectFlowDemo(
  viewModel: LaunchedEffectViewModel
) {

  // the below will run only one time when the "LaunchedEffectFlowDemo"
  // called for the first time and if recompose happens it won't run.
  key1 = true // -> make it run one time 

  LaunchedEffect(key1 = true) {
    viewModel.sharedFlow.collect { event ->
     when(event) {
      is LaunchedEffectViewModel.ScreenEvents.ShowSnackbar -> {

      }
      is LaunchedEffectViewModel.ScreenEvents.Navigate -> {

      }
     }
    }
  } 
}

// Example: 3
Animation

@Composable
fun LaunchedEffectAnimation(
  counter: Int
) {

  val animatable = remember {
    Animatable(0f)
  }

// whenever counter changes it will destory the old function 
// and rerun the function with new value 
  LaunchedEffect(key1 = counter) {
    animatable.animateTo(counter.toFloat())
  }
}

2. rememberCoroutineScope:

rememberCoroutineScope() // -> Its also a composable function
we will get reference to coroutine scope that is aware of the composition hear 
as soon as this composable "RememberCorountineScopeDemo()" Leaves the composition.
all the coroutine in the "scope" will get canceled

Important thing is use this only in callbacks

callbacks -> onClick, OnChange

rarely if we want to launch a independent coroutine block in launch effect
we can do it but strongly recommended is View Model Scope. 

fun RememberCorountineScopeDemo() {
  val scope = rememberCoroutineScope()
  Button(onClick = {
    scope.launch {
      delay(1000L)
      println("Hello World!")
    }
  }) {

  }
}

3. rememberUpdatedState 

RememberUpdatedStateDemo(
  onTimeout: () -> Unit
) {
  
  val updatedOnTimeout by rememberUpdatedState(newValue = onTimeout)

  LaunchedEffect(key1: true) {
    delay(3000L)
    updatedOnTimeout()
    // onTimeout()
  }
}

the above RememberUpdatedStateDemo -> called with new onTimeout function
the LaunchedEffect block won't consider the new one because it is trigger with the old one.
and key here is true and it will trigger only once.

But we don't want the LaunchedEffect block to be re-launched when the timer chnages which
would then extend the duration of our splash screen because of that it will not override this on timeout.

  val updatedOnTimeout by rememberUpdatedState(newValue = onTimeout)

  The above line will change the value of "updatedOnTimeout" whenever a new onTimeout function called 
  but won't relaunch the LaunchedEffect block the total delay is 3s only 
  but will update the value.

  4.Disposable Effect

// Check Observer in android.

  fun DisposableEffectDemo() {
    val lifecycleOwner = LocalLifecycleOwner.current // check what it is
    val observer = lifecycleEventObserver{ _, event -> 
      if(event == lifecycle.Event.ON_PAUSE) {
        println("On pause called")
      }
    }
    
    lifecycleOwner.lifecycle.addObserver(observer)

    onDispose {
      lifecycleOwner.lifecycle.removeObserver(observer)
    }
  }

  the Problem:
  1. observer will be called for every recomposition
  2. if we use LaunchedEffect we cannot close the observer once the composer finish the composition.

  onDispose:
  when the composable leaves the composition this method onDispose will be called.
  in that we will remove the observer.

  Typically if we need any cleanup composable we use DisposableEffect


5. SideEffect

It is called whenever the composable is successfully recomposed

fun SideEffectDemo(nonComposableCounter: Int) {
  
  // nonComposableCounter -> lets say it's an api value 
  // which is treated not as state. so whatever may be the reason.
  // you actually need to treat it like a state.
  // maybe we need to update someother code with this "nonComposableCounter"
  // whenever our composable recomposes then we use side effect. 

  SideEffect {
    println("Called after every successful recomposition")
  }
}

6. produceState : it returns a coroutine scope where we can use suspend function.
puropse of it to produce a state that changes over time.

fun produceStateDemo(countUpTo: Int): State<Int> {
  return produceState(initialValue = 0) {
    while(value < countUpTo) {
      delay(1000L)
      value++
    }
  }
}


7. DerivedStateof

fun DerivedStateOfDemo() {

  var counter by remember {
    mutableStateOf(0)
  }

  val counterText = "The counter is $counter"

  // when counter changes it will do the concatination
  "The counter is" + counter 
  // everytime on Click it will re-compute.

  // so if you have some kind of complex thing that depends on states
  // that changes over time then you want to use derived state off

  val counterText by derivedStateOf {
    "The counter is $counter"
  }

  // When it actually when counter text is accessed the first 
  // time it will compute this string so it will simply cache the 
  // string that it actually concatenated here so for the first call 
  // it will be simply the counter is zero and that string will now be cached 
  // so the result of that computation now when the text here accesses this counter 
  // text it will get the cached version now instead so it won't recompute this 
  // concatenation which is really cool and really helpful
  // if you actually very complex thing here to do that depends on states
  // now if actually one of these states that you use in this block changes 
  // like this counter lets say we now increase this by one then this derived set 
  // off function will recompute that string will then reconcatenate it and it will 
  // automatically notify all these different composables that use that counter text  
  // like in our case the text one here below code.

  // They will actually have the new change but as long as the counter is not increased
  // any composables that access this counter text will access the 
  // cached version and not it actually won't recompute the string 
  // every single time here which is yeah very simple example won't 
  // be noticeable with a simple string but if you have some kind of
  // complex thing you want to do that requires some states to be computed
  // then definitely use derived state off

  Button(onClick = { counter++ }) {
    Text(text = counterText)
  }
}

8. ShapshotFlow

if we have a flow we can use collect as state functions to get the flow in form
of a compose state 

ShapshotFlow is exactly opposite so we can actually take a compose state and construct 
the flow out of that emits values whenever the composed state changes

we want to have a flow for whatever reason that emits the snack bar 
message whenever it actually changes then we can actually use snapshot flow 

// compose state need as a flow 
fun SnapshotFlowDemo() {
  val scaffoldState = rememberScaffoldState()
  LaunchedEffect(key1 = scaffoldState) {
    snapshotFlow { scaffoldState.snackbarHostState }
    .mapNotNull { it.currentSnackbarData?.message }
    .distinctUntilChanged()
    .collect { message -> 
      println("A snackbar with message $message was shown")
    }
  }
}