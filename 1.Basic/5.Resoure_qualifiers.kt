Resources -> Non-code tings like pictures, vector graphics and localizied strings
text -> transalted to different languages
we can have different variant of the resources in different devices

res is called as resource 

under res we have 4 folders default

res -> drawable, mipmap, values, xml - we can have more folders inside of instance
drawable -> pictures, svg, jpg

to access the resources we have two methods
1. when we are inside a activity it already access to context so we can call resources 
2. but when we create a class that don't have access to activity we need to call it using applicationcontext.resources

every single picturs is ids in drawable

To work like instances 

val drawable = resources.getDrawable(R.drawable.kermit, null(resource theme))

To show it inside images
Image(
  painter = painterResource(id = R.drawable.kermit),
  contentDescription = null
)

v24 inside drawable
-> we call it as qualifier
-> this specific resource is used for specific resource configuration
-> Your android device has to run on api 24 level

qualifier are used in more ways like showing responsive stuff and many more 
-> right click on the drawable folder and select drawable resource folder 
-> give file name to it -> select the available qualifiers -> country code is 49 means it will work for germany only
-> Night Mode -> only works if the theme is dark 
-> we have many options to it -> explore it like we can have responsive qualifiers also

Now when we go to projects and select res and see the drawable folder
we see the qualifiers are arranged in different folders it segregates every configuration in different folders 

mipmap (Icon App) -> stores the different icon size of our android app 
-> for different device size

ic_launcher -> hdpi, mdpi etc are qualifiers 

Values -> color.xml -> give color code -> and then using qualifiers we can separate it for night mode also.

create a file color.xml for night mode 

values -> String.xml -> for german country we can do it qualifiers 

create a file string.xml for germany country


