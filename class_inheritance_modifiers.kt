//package com.example.design_start
//import kotlin.math.sqrt
//
//// Create each class on an individual file called class
//class Rectangle(
//    private val a: Double,
//    val b: Double
//) : Shape("Rectangle")  {
//  init {
//      println("Rectangle created with a = $a and b = $b")
//  }
//
//    fun area() = a * b
//
//    fun perimeter() = 2 * a + 2 * b
//}
//
//// we can make our constructor private or protected
//// class Circle private constructor( val radius: Double )
//class Circle(
//    val radius: Double
//) : Shape("Circle") {
//private val pi = 3.141592
//
//init {
//    println("Circle created with radius = $radius")
//}
//
//fun area() = radius * radius * pi
//
//fun perimeter() = 2 * radius * pi
//}
//
//class Triangle( val a: Double, val b: Double, val c: Double) : Shape("Triangle")  {
//    init {
//        println("$name created with a = $a, b = $b, and c = $c")
//    }
//
//    fun area() = sqrt((perimeter() / 2) * (perimeter() / 2 - a) * (perimeter() / 2 - b) * (perimeter() / 2 - c) )
//
//    fun perimeter() = a + b + c
//}
//
//open class Shape( var name: String) {
//    init {
//        println("I am the super class!")
//    }
//
//    fun changeName(newName: String) {
//        name = newName
//    }
//}