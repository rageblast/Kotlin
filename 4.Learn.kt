package com.example.design_start

// all classes in kotlin is final by default
// we need to make it open to make it inheritable
// if you don't want it inheritable make it sealed
//sealed class Vehicle {

// Interface -> Extend the functionality of class
// it may have body for the methods or may not
// the class which implements it may override it
interface  Drivable{
 val maxSpeed: Double
 fun drive(): String
 fun brake(){
     println("The drivable is braking")
 }
}

open class Vehicle {

}

open class Cars(override val maxSpeed: Double, val name: String, val brand: String): Drivable{
   // To make the property to be override we can make it open
    open var range: Double = 0.0

    fun extendRange(amount: Double){
        if(amount > 0) range += amount
    }

    // we need to have drive method we already have one
    // but it does not match the criteria
    // because it has parameter but inside interface it doesn't have parameter

    override fun drive(): String {
        return "Override method of interface"
    }

    open fun drive(distance: Double){
        println("Drove for $distance KM")
    }
}

class ElectricCar( maxSpeed: Double, name: String, brand: String, batteryLife: Double): Cars(maxSpeed, name, brand){
    var chargerType = "Type1"

    override var range = batteryLife * 6

    override fun drive(distance: Double){
        println("Drove for $distance KM on electricity")
    }

    // Now we can have two drive method and it can be accessed by object
    // it can be confused so don't do it
//    fun drive(){
//        println("Drove for $range KM on electricity")
//    }

    override fun drive(): String{
       return "Drove for $range KM on electricity"
    }

    override fun brake() {
        // why super because it does not implement brake in cars
        // it uses from interface
        super.brake()
        println("brake inside of electric car")
    }
}

// Abstract class

// the variable or function with abstract
// is used as abstract and its sub-class has to implement it

abstract class Mammal(private val name: String, private val origin: String, private val weight: Double) {

    // abstract property
    abstract var maxSpeed: Double

    // abstract Methods
    abstract fun run()
    abstract fun breath()

    //Non Abstract Method
    fun displayDetails() {
        println("Name: $name, Origin: $origin, Weight: $weight" + "Max Speed: $maxSpeed")
    }
}

class Human(name: String, origin: String, weight: Double, override var maxSpeed: Double): Mammal(name, origin, weight){
    override fun run() {
     println("Runs on two legs")
    }

    override fun breath() {
        println("Breath through mouth or nose")
    }
}

fun typecasting() {
 val stringList: List<String> = listOf("Denis", "Frank")

 val mixedTypeList: List<Any> = listOf("Denis", 31, 5.5)

    for(value in mixedTypeList) {
        if(value is Int){
            println("Int")
        } else if (value is String){
            println("String")
        } else if(value is Double){
            println("Double")
        } else {
            println("UnKnown Type")
        }
    }

    for(value in mixedTypeList) {
        when(value) {
            is Int -> println("Int")
            is Double -> println("String")
            is String -> println("Double")
            else ->  println("UnKnown Type")
        }
    }

    // SMART CAST
    val obj1: Any = "I have a dream"
    if(obj1 !is String){
        println("Not a String")
    } else {
        // obj is automatically cast to a String in this scope
        println("Found a String of length ${obj1.length}")
    }

    // Explicit (unsafe) Casting using the "as" keyword - can go wrong
    val str1: String = obj1 as String
    println(str1.length)

    val obj2: Any = 1337
    val str2: String = obj2 as String
    println(str2)

    // Explicit (Safe) casting using the "as?" keyword
    val obj3: Any = 1337
    val str3: String? = obj3 as? String // Works
    println(str3) // Prints null
}

fun main() {
    var myCar = Cars(100.00, "A3", "Audi")
    var myECar = ElectricCar(100.00, "S-Model","Tesla", 85.0)
//     myECar.hashCode()

    // We never wrote any methods inside the class but we can
    // access hashCode because we have class called any
    // this any class property is inherited to every class

    myECar.chargerType = "Tesla 2"
    myECar.extendRange(200.0)
    myECar.drive()

    myCar.brake()
    myECar.brake()

    // Polymorphism
    myCar.drive(200.0)
    myECar.drive(200.0)

val human = Human("Denis", "Russia", 70.0, 28.0)

    human.run()
    human.breath()
}

// Interface cannot hold state
// Multiple Interfaces can pass to class (Drivable, non-Drivable)
// but one abstract class can be pass to class
// interface cannot have constructor