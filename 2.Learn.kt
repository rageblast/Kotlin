package com.example.design_start

fun main(){
    // getting values in the console
    var userInput = readlnOrNull()
    print("user inputed a string of $userInput")

    val myArray = arrayOf("hello", "guys", "whats up?")
    print(myArray[1])

    println("Please enter your age:")
    val age = readlnOrNull()?.toInt()

    if(age != null) {
    if(age >= 0 && age < 18){
        println("You are an adult")
    }
    }

}