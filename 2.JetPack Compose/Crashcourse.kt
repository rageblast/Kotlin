1.Declarative way of design

@composable
fun Greeting(name: String) {
// we can use states when it changes it will change the name
// like react
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@composable
fun DefaultPreview() {
  JetpackComposeCrashCourseTheme {
    Greeting("Rock") // we only pass value to parameter
    // previously we are using R.getviewbyid and view binding
    // view.name = "Rock"
    // its been replaced by the above
  }
}

// to check what are all the parameters we can pass in that function
// style the texts

  Text(
    text = "Hello $name!",
    Ctrl + p -> check parameter
    fontsize = 30.sp
  )

// Modifiers used to modify our views or composable
// common UI design we use for all the composable function like background color etc
// it can be used to all composable
// it will work in sequence
// check below code
  Text(
      text = "Hello $name!",
      Modifiers = modifier
                .background(Color.red)
                .padding(16.dp)
                .background(Color.Green)
  )

  // to overcome z-index
  // we use layout to overcome it 
  fun Greeting(name: String) {
  column(
    horizontalArrangement = Arrangement.End,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier
    .fillMaxSize()  // fill all the space you can get full screen
    .fillMaxWidth() // fill only full width of the screen
    .size(400.dp) // it's like square 400 width and height
  ) {
Text(text = "Hello $name!")
Text(text = "Some other text")
  }
}

like column in the upper example we have row
Row(
  horizontalArrangement = Arrangement.End,
  modifier = Modifier.size(400.dp)
) {
  Text(
    text = "Hello $name"
    color = Color.Blue
  )
  Text(
    text = "Some other text"
    color = Color.Blue 
    fontsize = 30.sp
  )
}

// Box layout ->place views top of each other which is a default layout

// moves the whole component -> contentAligment = Aignment.Center
// moves only one component ->   modifier = Modifier.align(Alignment.BottomEnd)

// use anyone

Box(
  modifier = Modifier.size(400.dp),
  contentAligment = Aignment.Center
) {
  Text(
    text = "Hello $name!",
    color = Color.Blue,
    fontsize = 30.sp,
    modifier = Modifier.align(Alignment.BottomEnd)
  )
  Text(
    text = "Some other text",
    color = Color.Blue,
    fontSize = 30.sp
  )
}

Images

fun Greeting(name: String) {
  // Painter -> is like paint the image into our screen
  Image(
    painter = painterResource(id = R.drawable.ic_launcher_foreground),
    contentDescription = null,
    modifier = Modifier.background(Color.Black)
  )

// material ui has so many default icon so we use the below code
// Icon.Default.Add
  Icon(
    imageVector = Icon.Default.Add,
    contentDescription = null
  )
}

// Conditional Rendering and Loop Rendering 
if(name.length > 5) {
    Icon(
    imageVector = Icon.Default.Add,
    contentDescription = null
  )
}

Column {
  for(i in 1 <..< 10) {
    Icon(
    imageVector = Icon.Default.Add,
    contentDescription = null
  )
  }
}

// Recycler view is modified as LazyColumn and LazyRow
// which shows what should renderon the screen

fun Greeting(name: String) {
  LazyColumn(modifier = Modifier.fillMaxSize()) {
    items(10) { i ->
  Icon(
    imageVector = Icon.Default.Add,
    contentDescription = null,
    modifier = Modifier.size(100.dp)
  )
    }
  }

  // same as column we have rows
  LazyRow(modifier = Modifier.fillMaxSize()) {
    items(10) { i ->
  Icon(
    imageVector = Icon.Default.Add,
    contentDescription = null,
    modifier = Modifier.size(100.dp)
  )
    }
  }
}


// States 
// Like React it will change

// It will re-render the component which are all using that states

// recomposition
// And it won't re-draw all the components it will re-draw only the components that these states is used 

// And we use remember because when state changes it runs the whole function from top to bottom so when it runs from top to bottom 
// it set the value to default one so avoid it remember the last value changed we use remember 

// to access value of the state we use count.value to avoid it we can use "by" we can use count only 

// we use mutableStateOf to decalre it as state 

setContent {
  var count by remember {
    mutableStateOf(0)
  }
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalArrangement = Alignment.CenterHorizontally
  ) {
    Text(
      text = count.toString(),
      fontSize = 30.sp
    )
    Button(onClick = {
      count++
    }){
      // You can use Image and text also
       Text(text = "Click me!") 
    }
  }
}


// Example:
var name by remember {
  mutableStateOf(")
}

var names by remember {
  mutableStateOf(listOf<String>())
}

Column(
  modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
) {
  Row(
    modifier = Modifier.fillMaxWidth()
  ) {
     OutlinedTextField(
      value = name,
      onValueChange = { text ->
             name = text
      },
      modifier = Modifier.weight(1f)
      )
      spacer(modifier = Modifier.width(16.dp))
      Button(onClick = {
         if(name.isNotBlank()) {
          names = names + name
          name = ""
         }
      }) {
        Text(text = "Add")
      }
  } 
}

LazyColumn {
  items(names) { currentName ->
     Text(
      text = currentName,
      modifier = Modifier
           .fillMaxWidth()
           .padding(16.dp)
      )
      Divider()
  }
}

// to make it as reusable ui component

@composable
fun NameList(
  names: List<String>,
  modifier: Modifier = Modifier // we can change ui from the params from someother functions
) {
  LazyColumn(modifier) {
     items(names) { currentName ->
       Text(
        text = currentName,
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
       )
       Divider()
     }
  }
}

NameList(names = names)

// use dripple design pattern