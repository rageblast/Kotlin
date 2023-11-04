package com.example.design_start

fun main(){

    for(i in 1..10){
        print("Test")
    }

    for(i in 2..3){
        println(i)
    }

    for(i in 10 downTo(1) ){
        println(i)
    }

    for(i in 10 downTo(1) step(2)){
        println(i)
    }

    for( i in 1 until(10) ){
        println(i)
    }

    val x:Any = 38

    when(x) {
        is Int -> println("INT")
        !is Double -> println("Not Double")
        is String -> println("String")
        else -> println("Nothing")
    }

    val month = 3

    when(month) {
        in 1..3 -> println("Summer")
        in 4..6 -> println("Winter")
        in 7..9 -> println("Autumn")
        in 10..12 -> println("Spring")
        13 -> {
            println("Fail")
        }
    }

    var num = 33
    // increment after execution
    num++
    // increment before execution
    ++num
    for(i in 20 downTo(1) ){
        if(i/2 == 5){
//            break
            // skip the current execution move on to next
            continue
        }

        print(i)
    }
    val a:Byte = 10
    val b:Short = 10
    val c:Long = 100
    val d: Int = 10000

    print(add(d, d));

    var t:Int = 15
    do{
        print("$t")
        t++
    }while (b <= 10)
        print("do while loop")


    // it should have value
    var name: String = "Denis"
    // it can hold null value
    var nullablename: String? = "Denis"

    if(nullablename != null){
        print(nullablename.toLowerCase())
    }

    //    Both are same
    nullablename?.toLowerCase()

    // don't want to print values if null is present
    nullablename?.let {
        println(it.length) }

    // chain of nullable
    // val wifeAge: String? = User?.Wife?.Age

    // default value
    val name2 = nullablename ?: "Guest"

    // val wifeAge: String? = User?.Wife?.Age ?: 0

    // risky use it with carefully
    // ! -> true means false
    // !! -> true means true
    var nullable2 : String? = null
    nullable2!!.length
}


val word:String = "Text"
val character:Char = 'A'

val single_decimal: Float = 34.78F
val Double_decimal: Double = 36.90888

val test:Boolean = true

val interpolation = "testing $test ${test.toString()}"

var length = 0;
val const_length = 0;

fun add(input1: Int, input2: Int): Int {
    return input1 + input2
}

fun double_add(input1: Double, input2: Double): String {
    val value = input1 + input2;
    return value.toString();
}

fun humidity(): String {
    for(i in 1..1000){
        if(i == 9001) print("It's over")
    }

    var humidity = "humid"
    var humidity_level = 80

    while(humidity == "humid"){
        humidity_level-=5
        if(humidity_level < 60){
            humidity = "comfy"
            println("Comfy level reached")
        }
    }

    return "Humidity check"
}


