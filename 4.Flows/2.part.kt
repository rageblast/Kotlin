Flow Operators:
What happens when an emission of a flow.

Class MainViewModel: ViewModel() {
val countDownFlow = flow<Int>{
  val startingValue = 10
  var currentValue = startingValue
  emit(startingValue)

  while(currentValue > 0) {
    delay(1000L)
    currentValue--
    emit(currentValue)
  }
}

  init {
    collectFlow()
  }

  private fun collectFlow() {
    viewModelScope.launch {
       countDownFlow.collectlatest { time ->
        delay(1500L)
        println("The current time is $time")
       }
    }
  }
}

Flow Operators:

Example: 1
countDownFlow
(operator).filter{ time ->
    time % 2 == 0
}
(operator).collect { time -> 
   println("The current time is $time")
}

Example: 2
countDownFlow
(operator).filter{ time ->
  time % 2 == 0
}
(operator).map{ time ->
  time * time
}
(operator).onEach{ time -> 
  println(time)
}
(operator).collect { time -> 
  println("The current time is $time")
}

Other way launching a flow

countDownFlow.onEach {
  println(it)
}.launchIn(viewModelScope)

above is equal to below 

viewModelScope.launch {
  countDownFlow.collect{}
}

Terminal Flow Operator:
Because these will terminate the flow.

The whole results of a flow all emissions together and then do something with these.

Example: 1
viewModelScope.launch {
  val count = countDownFlow
  (operator).filter{ time ->
  time % 2 == 0
}
(operator).map{ time ->
  time * time
}
(operator).onEach{ time -> 
  println(time)
}
(Terminal operator).count { 
  it % 2 == 0
}

println("The Count is $count")
}

Example: 2
viewModelScope.launch {
  val reduceResult = countDownFlow
  (Terminal operator).reduce{ accumulator, value ->
     accumulator + value
  }
  println("The Count is $reduceResult")

  // reduceResult will run for two emission 10 (emit(currentValue)) & 9(emit(startingValue))
}

add value to accumulator as startingValue

val reduceResult = countDownFlow
   .fold(100) { accumulator , value -> 
  accumulator + value
   }
   println("The count is $reduceResult")

Flattering Operators:

Example:
[ [1,2] [1,2,3] ]
[ [1, 2, 1, 2, 3] ]

In Flow we have like this 

private fun collectFlow() {
  val flow1 = flow {
    emit(1)
    delay(500L)
    emit(2)
  }
}

viewModelScope.launch {
  flow1.flatMapConcat { Value ->
     flow {
       emit(Value + 1)
       delay(500L)
       emit(Value + 2)
     }
  }.collect{ value ->
    println("The Value is $ value")
  }
}

val flow1 = (1..5)asflow -> Which emits value Immediately

viewModelScope.launch {
  flow1.flatMapConcat { id ->
     getRecipeById(id) -> It will make API call to with 5 Number from above flow 
     and combine the result result and give it to collect.
  }.collect { value ->
     println("The value is $value")
  }
}

flatMapConcat:
1 -> getRecipeById(id)
above finished call the below 
2 -> getRecipeById(id)

flatMapMerge:
This one will trigger everything at the same line.

flatMapLatest { id ->
  getRecipeById(id)
}

if id -> 2 finishes before
id -> 1 it will drop the the id 1 and forward the result of id 2 to the collect block.

What to do with our emission (i.e) How to Collect it.

Private fun collectFlow() {
  val flow = flow {
    delay(250L)
    emit("Appetizer")
    delay(1000L)
    emit("Main Dish")
    delay(100L)
    emit("Dessert")
  }
}

viewModelScope.launch {
  flow.onEach {
    println("Flow: $it is delivered")
  }.collect {
    println("Flow: Now eating $it")
    delay(1500L) -> This delay will delay the flow emission also means 250L -> Called First -> Then it will Print First Line delay 1500L & Print second line then call the 1000 L process repeats.
    println("Flow: Finished eating $it")
  }
}

Strategies we can use to avoid above.

1. Buffer:
 flow
 .onEach {
    println("Flow: it is delivered.")
 }
 .Buffer() -> It Makes our Flow run in different Corountine and below collect in different corountine and result will be fast.
 .Collect{
  println("Flow: Now eating $it")
 }

2.Conflate()
If there are two emissions from the flow that we can't collect yet
and then when we finish it we will directly go to the latest emission.

So we will basically drop all emissions that came before and completely leave it.

3.Collectlatest()
Considers only latest emissions.