package com.example.design_start

import android.graphics.drawable.shapes.Shape

// Nested Class -> created inside another class
// It is default static -> so its member and member function can be accessed without creating an object of the class
// Nested classes cannot access the data members of outer classes

class OuterClass {
    private var name:String ="Mr X"
    // outer class

    class NestedClass {
        var description: String = "code inside nested class"
        private var id: Int = 101
        fun foo(){
            // print("name is ${name}") // cannont access the outer class member

            println("Id is ${id}")
        }
    }
}

// Inner class
// created inside an another class
// It cannot be declared inside interfaces or non-inner nested classes
// It is able to access members of its outer class even it is private
// it keeps a reference to an object of its outer class

class OuterClass1 {
    private var name:String = "Mr X"
    inner class InnerClass {
        var description: String = "code inside inner class"
        private var id: Int = 101
        fun foo(){
            println("name is ${name}") // access the private outer class member
            println("Id is ${id}")
        }
    }
}

// Lambda
// pass function as parameter to other functions

fun main() {
    var list = (1..20).toList()

    println(list)

    // in js array.filter -> here list.filter (lambda)
    // it refer to list item
    list = list.filter { it % 2 == 0 }
     println(list)

// val circle1 = Circle(5.0)
//var shapes = listOf(circle1, circle2)
//shapes = shapes.customFilter{ it.area() > 20.0 }.sortedBy{ it.area() }
// for(shape in shapes) {
//   println("${shape.name}: Area = ${shape.area()}")
// }

    // Two param
//    shapes = shapes.customFilter { shape, string ->
//        shape.area() > 20.0
//    }

// var list1 = (1..10).toList()
// val sum = list.customSum{ it % 2 == 1 }
// 1 + 3 + 5 + 7 + 9 = 25
// println("The sum is: $sum")

 println(OuterClass.NestedClass().description)

 var obj = OuterClass.NestedClass() // object creation
 obj.foo()

  println(OuterClass1().InnerClass().description) // Accessing Property
  var obj1 = OuterClass1().InnerClass() // object creation

  obj.foo() // access member function
}

// lambda expression

val sum: (Int, Int) -> Int = { a: Int, b: Int -> a + b }

val sum2 = {a: Int, b: Int -> println(a + b)}

// creating our own lambda function

// List<Shape> -> list which has shape
// List<Shape>.customFilter -> that has a function name customFilter
// It receives a function has a parameter -> filterFunction
// That function has a parameter called shape which returns boolean
// then our customFilter returns list of shape

// function with one param
//fun List<Shape>.customFilter(filterFunction: (Shape) -> (Boolean)) : List<Shape> {
//    val resultList = mutableListOf<Shape>()
//    // this refer to List<Shape>
//    for(shape in this){
//        if(filterFunction(shape)){
//            resultList.add(shape)
//        }
//    }
//    return resultList
//}

//Function with two param
fun List<Shape>.customFilter(filterFunction: (Shape, String) -> (Boolean)) : List<Shape> {
    val resultList = mutableListOf<Shape>()
    // this refer to List<Shape>
    for(shape in this){
        if(filterFunction(shape, "Hello")){
            resultList.add(shape)
        }
    }
    return resultList
}

// Public Modifier
public class Example{
    public fun hello(){}
}
class Demo{}

public fun hello2(){}

fun demo() {}

public val x = 5

val y = 10

// Not accessible within another file
private class Example2 {
    // accessible within this class
    private val x = 1

    private fun doSomething3() {}
}

// Internal modifier makes the field visible only inside the module in which it is implemented

internal class Example3{
    internal val x = 5

    internal fun getValue(){

    }
}

internal val t = 10

//  In Kotlin all classes are final by default, so they can't be inherited by default

// Protected

open class Base {
    protected val i = 0
    // overriding
    protected open val j = 8
}

class Derived : Base() {
    fun getValue(): Int {
        return i
    }
    override val j = 10
}

// Visibility Modifier

open class Base2() {
    var a = 1 // public by default
    private var b = 2 // Private to Base class
    protected open val c = 3 // Visible to the Base and the Derived class
    internal val d = 4 // Visible inside the same module
    protected fun e() {} // Visible to the Base and the Derived class
}

class Derived3: Base2() {
    // a, c, d, and e() of the Base class are visible
    // b is not visible
    override  val c = 9
}

fun test(args: Array<String>) {
    val base = Base2()
    // base.a and base.d are visible
    // base.b, base.c and base.e() are not visible

    val derived = Derived3()
    // derived.c is not visible
}