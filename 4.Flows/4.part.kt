Combine, Zip, Merge:

Post.kt:
data class Post (
  val imageUrl: String? = null,
  val username: String? = null,
  val description: String? = null
)

ProfileState.kt:

data class ProfileState (
  val profilePicUrl: String? = null,
  val username: String? = null,
  val description: String? = null,
  val posts: List<Post> = emptyList()
)

User.kt:
data class User (
  val username: String? = null,
  val description: String? = null,
  val profilePicUrl: String? = null
)

class MainViewModel: viewModel() {
  private val isAuthenticated = MutableStateFlow(true)
  private val user = MutableStateFlow<User?>(null)
  private val posts = MutableStateFlow<emptyList<Post>()>
  private val _profileState = MutableStateFlow<ProfileState?>(null)

  val profileState = _profileState.asStateFlow()

  // When new post (or) profile data changes

  init {
    If user (or) post changes -> we can use combine
    if either of this changes update profile state:

    user.combine(posts) { user, posts -> 
    _profileState.value = profileState.Value?.copy(
      profilePicUrl = user?.profilePicUrl,
      username = user?.username,
      description = user?.description,
      posts = posts
    )
    }.launchIn(viewModelScope)

    above and below are same so use anyone

    viewModelScope.launch {
      user.Combine(posts) {
        user, posts -> 
    _profileState.value = profileState.Value?.copy(
      profilePicUrl = user?.profilePicUrl,
      username = user?.username,
      description = user?.description,
      posts = posts
    )
      }.collect()

      // We need to combine the .launchIn(viewModelScope) flow with 
      the authenticated flow.

      Combine -> Returns another flow.

      Change the init 

      init {
         isAuthenticated.combine(user) { isAuthenticated, user ->
          if(isAuthenticated) user else null 
         }.combine(posts){ user, posts -> 
            user?.let {
              _profileState.value = profileState.value?.copy(
                    profilePicUrl = user?.profilePicUrl,
                    username = user?.username,
                    description = user?.description,
                    posts = posts
              )
            }
         }.launchIn(viewModelScope)
      }
    }
  }
}

val numberString by MutableStateof("")
private set 

private val flow1 = (1..10)asFlow().onEach{
  delay(1000L)
}
private val flow2 = (10..20)asFlow().onEach{
  delay(1000L)
}

Zip:
Triggers when Both state emission Changes 

Combine:
Trigger when anyone of the changes

flow1.zip(flow2) { number1, number2 -> 
 numberString += "($number1, $number2)\n"
}.launchIn(viewModelScope)

class MainActivity: ComponentActivity() {
  override fun onCreate(SavedInstanceState: Bundle?) {
    setContent {
      CombineZipMergeTheme {
        val viewModel = viewModel<MainViewModel>()
        Text(text = viewModel.numberString)
      }
    }
  }
}

merge:
Merge multiple flows into one flow 

merge(flow1, flow2).onEach {
  numberString += "$it\n"
}.launchIn(viewModelScope)