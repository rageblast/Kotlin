package com.example.design_start

import kotlin.random.Random

class Circle(
    val radius: Double
) : Shape("Circle") {
//private val pi = 3.141592

    // lOOK THE ABOVE PI -> remember singleton Instance and Every Instance
    // to use Singleton Instance we use object and object in class also
    companion object {
        fun randomCircle(): Circle {
            val radius = Random.nextDouble(1.0, 10.0)
            return Circle(radius)
        }
    }

// every time when we create a constructor it should be unique
//    constructor(diameter: Double): this(diameter/2)

    init {
    println("Circle created with radius = $radius")
}

    override fun area() = radius * radius * ImportantNumbers.PI

    override fun perimeter() = 2 * radius * ImportantNumbers.PI
}