package com.example.design_start

fun main(){
    val myArray = arrayOf(4, 5, 8, 9 ,10, 2, 3)
    var max = myArray[0]
    for(item in myArray) {
        if(item > max) {
            max = item
        }
    }
    println(max)

    // list value cannot be changed on run time
    val array = arrayOf(1, 2, 3)
    val list = listOf(1, 2, 3)

    // we can use mutable list
    // we can use mutable list to add value at run time
    // we can use mutable list to change value
    val list2 = mutableListOf(1, 2, 3)

    array[0] = 3
    // error below
//    list[0] = 3

    // no error
    list2[0] = 3

    // we can increase size in list but we cannot do it with array
    list2.add(4)
    println(list2)
    list2.remove(2)
    println(list2)

    // array has limitation of methods than list

     val list3 = mutableListOf<Int>()

    for(i in 1..10) {
        // user inputs
        val x = readlnOrNull()?.toInt()
        if(x != null) {
            list3.add(x)
        }
    }
    print(list3)

    val age = readlnOrNull()?.toInt()
    val x = 5

    when(age) {
        in 0..5 -> {
            println("You're a young kid")
            println("Another line")
        }
        in 6..17 -> println("You're a teenager")
        18 -> if(x == 6) {
            println("Finally, you're 18")
        }
        19, 20 -> println("you're a young adult")
        in 21..65 -> println("You're an adult")
        else -> println("You're really old")
    }

    val list4 = mutableListOf<Int>()
    println("Please enter 5 numbers:")
    for(i in 1..5) {
        val x = readlnOrNull()?.toInt()
        if(x != null) {
            list4.add(x)
        }
    }

    println("The 5 numbers in reverse order are:")
    for(i in list4.size - 1 downTo 0) {
        println(list4[i])
    }

    //fibonacci
    val list5 = mutableListOf(0 , 1)
    println("Enter a number n > 1:")
    val x1 = readlnOrNull()?.toInt()
    if(x1 != null) {
        for(i in 2..x1-1){
            list5.add(list5[i-2] + list5[i-1])
        }
    }
    println(list5)
}