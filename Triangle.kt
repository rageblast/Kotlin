package com.example.design_start
import kotlin.math.sqrt

class Triangle( val a: Double, val b: Double, val c: Double) : Shape("Triangle")  {
    init {
        println("$name created with a = $a, b = $b, and c = $c")
    }

    override fun area() = sqrt((perimeter() / 2) * (perimeter() / 2 - a) * (perimeter() / 2 - b) * (perimeter() / 2 - c) )

    override fun perimeter() = a + b + c
}