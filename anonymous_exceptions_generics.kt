package com.example.design_start

import java.lang.NumberFormatException

// anonymous class
fun main() {

    val a = 3.0
    val b = 4.0
    val height = 2.0

    // to create an anonymous class we need to
    // refer that class if it is an abstract class
    // we need to make it an object because we can't make instant with it
    // and then we need to create the methods for it inside the object and select it using ctrl + o

    val parallelogram = object : Shape("Parallelogram", a, b, height) {

        init {
            println("Parallelogram created with a = $a, b = $b and height = $height")
            println("The area is ${area()}")
            println("The perimeter is ${perimeter()}")
        }

        override fun area(): Double {
            return a * height
        }

        override fun perimeter(): Double {
             return 2 * a + 2 * b
        }

        fun isRectangle(): Boolean = height == b
    }

    println("Is the parallelogram a rectangle? ${parallelogram.isRectangle()}")
    exception()

    val division = try {
        divide(5.0, 0.0)
    } catch(e: DivisionByZeroException) {
        0.0
    }

    val circle1 = Circle(5.0)
    val triangle = Triangle(4.0, 4.0, 4.0)
    val rectangle = Rectangle(6.0)

    // ctrl + p
    var integers = (1..10).toList().customFilter { it > 5 }

    var shapes = listOf<Shape>(circle1, triangle, rectangle)
    // use ctrl + p inside customFilter{ } check the value to be passed
    shapes = shapes.customFilter { it.area() > 20.0 }
    for(shape in shapes) {
        println("${shape.name}: Area = ${shape.area()}")
    }
}

fun exception() {
    println("Please enter a number: ")
    val input = try {

    } catch(e: NumberFormatException) {
        // NumberFormatException -> inherits from Exception
        // so we can use Exception -> to capture all the exception error
        0
    } finally {
        println("This is from the finally block")
    }

    println("You entered: $input")
}

// creating our own exception

class DivisionByZeroException : Exception("You cannot divide by zero. Please choose a different number");

fun divide(a: Double, b: Double): Double {
    if(b == 0.0) {
        throw DivisionByZeroException()
    }
    return a / b
}

// Generics are basically type parameter
// Type Parameters so we can pass the type of an object
// to function or class

// we can use the fun for every list type we want not only shape

// Expendable function with generics Any
fun <T> List<T>.customFilter(filterFunction: (T) -> (Boolean)): List<T> {
    val resultList = mutableListOf<T>()

    // To rename all the "item" mentioned
    // click shift + f16 -> type the name and press enter
    for(item in this) {
        if(filterFunction(item)) {
            resultList.add(item)
        }
    }
    return resultList
}

// Generics for Number
//fun <T : Number> List<T>.customFilter(filterFunction: (T) -> (Boolean)): List<T> {
//    val resultList = mutableListOf<T>()
//
//    // To rename all the "item" mentioned
//    // click shift + f16 -> type the name and press enter
//    for(item in this) {
//        if(filterFunction(item)) {
//            resultList.add(item)
//        }
//    }
//    return resultList
//}

// Class

class CustomTriple<A : Any, B : Any, C : Any>(
    var first: A,
    var second: B,
    var thrid: C
){
    fun printTypes() {
        println("The type of first is ${first::class}")
        println("The type of second is ${second::class}")
        println("The type of third is ${thrid::class}")
    }
}