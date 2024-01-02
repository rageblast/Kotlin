     ----- UI ACTIONS ---->           ---- UPDATES MODEL ---------->  
VIEW                        VIEW MODEL                                MODEL
     <---- NOTIFIES UI -----           <----- Notifies VIEW MODEL ---

1. VIEW MODEL is a bridge between VIEW and MODEL 

VIEW -> UI DATA that users can see 
MODEL -> DATA that is given to UI TO seen by the user 

Example : 1 
if we have a date format that is 16 digit but we want to see it in normal format like 16 dec 2023
the model will give data of date of 16 digit to view model and it will convert it and send it to view 

Example : 2
if we want to store any data we will send the data from view to the model using view model and then reterieve the data from model
using view model and show it in view 

Example : 3
if view triggers any events like "Click","Swipe" etc the view model will change the ui and the view but it won't go to model 

// state changes also be traggered by view model -> per screen we have one view model 

// Create a view model by creating a class 

// if we have file or data source we can use constructor
// in the below view model 

class ContactsViewModel {

  var backgroundColor by mutableStateOf(Color.White)
  private set 

  fun changeBackgroundColor() {
    backgroundColor = Color.Red
  }
}

class MainActivity : ComponentActivity() {
  
  private val viewModel = ContactsViewModel()

      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Button(onClick ={
               viewModel.changeBackgroundColor
            }) {
                Text(text = "Change color")
            }
        }
     
      }
}

// configuration changes like screen rotation and language changes and theme changes will trigger the app to start from first
// the changes made in the activity may gone it will restore back to first how the app looks
// since view model will also reset 

// so to stop it google gives us an method of view model that out lives this configuration and life cycle changes with configuration changes 

// to use google view model do like below

class ContactsViewModel: viewModel() {}

// this will get destory only when we use back stack to exit from the current activity to previous activity
// because current activity using this view model 

// inside MainActivity we can use it like this 

step: 1
private val viewModel by viewModels<ContactsViewModel>()

// some times the above is not supported that case we use it like below

to use it we need the dependency to add it we will use build.gradle(:app)

dependency{
  implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1"
}

setContent {
  val viewModel = viewModel<ContactsViewModel>()
}

// if we pass constructor in the view model
// if we don't pass value to constructor the app will crash 
// to solve it we need view model factory

class ContactsViewModel(
  val helloWorld: String
): ViewModel() {

}

factory model:

val viewModel = viewModel<ContactsViewModel>(
  // instance of factory
  factory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return ContactsViewModel(
        hellowWorld = "Hello world!"
      ) as T // casting this as T
    }
  }
)