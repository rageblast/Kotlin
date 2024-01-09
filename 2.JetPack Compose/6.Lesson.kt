Lists :
if we limited items like 10 we can use columns and make it scrollable
if we have more items we can use lazy columns 

setContent {
  val scrollState = rememberScrollState() //check why
  // because column default won't have scrollable option
  // if we use this we can get where we scrolled do something
  Column(
    modifier = Modifier.verticalScroll(scrollState)
  ) {
    for(i in 1..50) {
      Text(
        text = "Item $i",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
                   .fillMaxWidth()
                   .padding(vertical = 24.dp)
      )
    }
  }
}

// Lazy Column : default scrollable
// when we scroll to the position other items will get loaded
// we don't use for loop in lazy loading because it has inbuilt loop 
// functionality
setContent {
   LazyColumn {
      items(5000) {
        Text(
          text = "Item $it",
          fontSize = 24.sp,
          fontWeight = FontWeight.Bold,
          textAlign = TextAlign.Center,
          modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 24.dp)
        )
      }
   }
}

items(5000) {} -> like for loop 
itemsIndexed() {} -> like for each loop 

setContent {
   LazyColumn {
      itemsIndexed(
        listOf("This", "is", "Jetpack", "Compose")
      ) { index, string ->
        Text(
          text = string,
          fontSize = 24.sp,
          fontWeight = FontWeight.Bold,
          textAlign = TextAlign.Center,
          modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 24.dp)
        )
      }
   }
}