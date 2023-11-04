A nullable string (String?) cannot be cast to non nullable string (String), This trows an exception

fun main(args: Array<String>) {
  val obj:Any? = null
  val str:String = obj as String
  print(str)
}

// O/P: error

Trying to cast an integer value of the Any Type into a string type leads to a ClassCastException

val obj: Any = 123

val str:String = obj as String 

// Source and target varaible needs to be a nullable for casting to work:

fun test(args: Array<String>){
  val obj: Any? = "String unsafe cast"
  val str: String?
  println(str)
}

// O/P: No Error  

Safe Cast: as?

as? provide a safe cast operation to safely cast to a type 

it returns a null if casting is not possible rather than throwing an ClassCastException exception 

fun test2(args: Array<String>){
   val location: Any = "Kotlin"
   val safeString: String? = location as? String 
   val safeInt: Int? = location as? Int 
   println(safeString)
   println(safeInt)
}

// Output: Kotlin  Null