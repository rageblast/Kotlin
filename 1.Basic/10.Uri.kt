Uri(Unique resources Indentifier) -> Identify resources

Resources can be file and Res file and image, video etc

Its a path to a specific resource

There are four types of uri 

val uri = Uri(android.net).parse("") -> if we declare it like this it covers all the four types of uri
it won't say what type of uri is used from that four types 

1. Resource Uri -> Points to specific resource in our android app
Resource means something inside of our res folder

val uri = Uri.parse(""android.resource://(telling it is a resource uri)$packageName(telling from which package resource we are accessing the resource)/drawable/kermit")

// check content Resolver 
val kermitBytes = contentResolver.openInputStream(uri)?.use {
  it.readBytes()
} // returns byte array

println("Kermit size: ${kermitBytes?.size}")

2. File Uri -> Points to normal file path like windows to points to specific file 
Every app has its own private storage only our app can read it 

val file = File(java.io)(filesDir, "kermit.jpg"(file name))
// check the below code 
FileOutputStream(file).use {
  it.write(kermitBytes)
}
println(file.toUri()) 
gives us the file path of the image where it is stored

To access to our internal storage we use filesDir(where ever we have access to context we can use this)
its used inside activity and activity has access to context

if we give file path of our internal storage to other apps it won't be able to access it 
because it need to have permission to access it.

don't share it to other apps use it for internal purpose only

3.Content Uri: -> if we want to share the content to other apps we use this Uri
access the external storage and get the images u r app doesn't need permission 
thats how content Uri works 

Activity result launcher:
Launch a different activity and get a result out of that
here we want to launch the gallery activity and the result is just uri
pointing to the image that user picker

We have given temporary permission to access the file 

setContent{
  UrisTheme {
    val pickImage = rememberLauncherForActivityResult(
      contract = ActivityResultContracts.GetContent(),
      onResult = { contentUri -> 
        println(contentUri)
      }
    )
    Button(onClick = {
      pickImage.launch("image/*")
    }) {
      Text(text = "Pick image")
    }
  }
}

for the byte process refer above 

4. Data Uris -> already contains the content it encodes inside the uri itself
it's also often encoded as a base 64 string in this case it's just plain text

Example:
If we have a base64 encoded image and we want to encode it in our code 
we could do that with such a data uri 

recommended is put inside a drawable folder and use it and read it using resource uri 

val dataUri = Uri.parse("data:text/plain;charset=UTF-8,Hello%20World")