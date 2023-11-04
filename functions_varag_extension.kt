package com.example.design_start

fun main() {
    val list = listOf<Int>(1, 2, 3)
    // Vararg
    val array = intArrayOf(10, 20, 25)
    val max = getMax(1, 2, 3, 4, 5, 3, *array, 4 , 6)
    println("The maximum is $max")
    // Named Parameter
    searchFor("How to become a good programmer")
    searchFor(searchEngine = "Bing", search = "How to become a good programmer")

    // Named Space
    println("Please enter a number")
    // below one is Int
    val input = readlnOrNull()?.toInt()

    if(input != null) {
        if(input.isPrime()) {
            println("$input is a prime number")
        } else {
            println("$input is not a prime number")
        }
    }

    val list2 = listOf<Int>(1, 2, 3, 4)
    println("${list2.product()}")
}

// one line return
fun multiply(a: Int, b: Int) = a * b
fun printFirstItem(list: List<Int>) = println(list[0])

// Normal return type
fun printPow(base: Int, exponent: Int): Int {
    var result = 1
    for(i in 1..exponent) {
        result *= base
    }
    return result
}

// VARARG
fun getMax(vararg numbers: Int): Int {
    var max = numbers[0]
    for(number in numbers) {
        if(number > max) {
            max = number
        }
    }
    return max
}

// Default
fun searchFor(search: String, searchEngine: String = "Google") {
    println("Searching for '$search' on $searchEngine")
}


// Adding methods to the Int, String, Boolean, etc
// extension
fun Int.isPrime(): Boolean {
    for(i in 2 until this - 1) {
        if(this % i == 0) {
            return false
        }
    }
    return true
}

fun List<Int>.product(): Int {
    var result = 1
    for(value in this) {
        result *= value
    }
    return result
}