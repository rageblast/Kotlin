package com.example.design_start

import java.lang.IllegalArgumentException

fun main() {
    // creating an instance or object
    var denis = Person("Denis", "Rack")
    denis.stateHobby()
    denis.hobby = "skateboard"
    denis.stateHobby()
    var john = Person()
    // using secondary constructor
    var denis1 = Person("Denis", "Rack", 31)
    denis1.stateAge()
    // overwriting age manually
    denis1.age = 32
    denis1.stateAge()
    val iphone = MobilePhone("iOS", "Apple", "iPhone 12")
    val galaxyS20 = MobilePhone("Android","Samsung", "Galaxy S20")
    val mateXS = MobilePhone("Android", "Huawei", "Mate X S")
    myFunction(5)

    // getter and setter
    var myCar = Car()

    // getting the value
    println("Maxspeed is ${myCar.maxSpeed}")
    // setting the value
    myCar.maxSpeed = 200
    // Throws error BECAUSE PRIVATE
    //    myCar.myModel = "e3"

    val user1 = User(1, "Denis")
    val name1 = user1.name
    println(name1)
    user1.name = "Michael"

    val user2 = User(1, "Michael")

    // we can compare two data classes
    // returns true if both values are same
    // if not it will return false
    println(user1 == user2)

    // it has toString method automatically called with string
    // we don't need to use toString
    println("User Details: $user1")

    // like immutable
    // we can use data class
    // it won't change the value it will take and update the value and give it to us
    val updatedUser = user1.copy(name="Denis put")
    println(user1)
    println(updatedUser)

    println(updatedUser.component1()) // prints id
    println(updatedUser.component2()) // prints name

  // destructor like js
    val (id, name) = updatedUser
    println("id=$id, name=$name")
}

// Primary Constructor

//class Person constructor(firstname: String = "John", lastname: String = "Doe"){}

// or

class Person (firstname: String = "John", lastname: String = "Doe"){
 // Member Variables - Properties
    var age : Int? = null
    var hobby : String = "Watch Netflix"

    // we need to initialize first to use the constructor value
    var firstname : String? = null

    // Once a object is created we will initialize the below method
    init {
        this.firstname = firstname
        println(
            "Initialized $firstname and $lastname"
        )
    }

    // Member Secondary Constructor
    // this refer to person
    // this(firstname, lastname) -> refer to primary one
    // this.age -> refer to member variable
    constructor(firstname: String, lastname: String, age: Int): this(firstname, lastname){
        this.age = age
        // like the initializer above
        // once an object is created first primary init will trigger
        // second the secondary constructor is trigger
        // if secondary constructor is used
        println(
            "Initialized $firstname and $lastname and $age"
        )
    }

    // Member functions - Methods
    fun stateHobby(){
        println("$firstname\'s hobby is $hobby")
    }

    fun stateAge(){
        println("My age is $age")
    }
}

class MobilePhone(osName: String, brand: String, model: String){

    private var battery = 30

    init {
        println("The phone $model from $brand uses $osName as its Operating System")
    }

    fun chargeBattery(chargedBy: Int){
        println("Battery was at $battery and is at ${battery+chargedBy} now")
        battery += chargedBy
    }
}

// Scope
// this a is a parameter
fun myFunction(a: Int){
    // a is a variable
    var a = a
    // check using this one also a = 5
    var b = 7
    // above is accessible only in this function
    // var is printed
    println("a is $a")
}

class Car(){
    // when we create var or val we need to initialize it
    // but we can initialize it later using the below code
    lateinit var owner : String

    var maxSpeed: Int = 250
    // default the below code is generated for the above one
    // we don't need to create one
    //    get() = field(refer to maxspeed)
    //        set(value){
    //            field = value
    //        }

    //  Backing Field (field)
// Backing field helps you refer to the property
// inside the getter and setter methods.
// This is required because if you use the property
// directly inside the getter or setter then you’ll
// run into a recursive call which will generate
// a StackOverflowError.

    // we can customize the getter and setter if we needed
    set(value){
        field = if(value > 0) value else throw IllegalArgumentException("Maxspeed cannot be less than 0")
    }

    val myBrand: String = "BMW"
        // Custom Getter
        get(){
            return field.toLowerCase()
        }

    // Making the setter private available only within the class
    var myModel : String = "MS"
    private set

    init {
        this.myModel = "M3"
        this.owner = "Frank"
    }
}

// Data Class cannot be abstract, open or sealed
// primary constructor should at-least have one parameter
// store data in the format we want

data class User(val id: Long, var name:String)