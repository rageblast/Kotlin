package com.example.design_start

class Rectangle(
    val a: Double,
    val b: Double
) : Shape("Rectangle")  {

    // Secondary Constructor
    // val, var -> used inside primary constructor only

    // here the this refer to primary constructor
    // here we create square rectangle so we pass the value of a to both
    constructor(a: Double): this(a, a)

    // we can create many constructor we want
    // we create rectangle with int value
    // finally we need to pass the value to primary constructor
    // every time when we create primary and secondary constructor
    constructor(a: Int, b: Int) : this(a.toDouble(), b.toDouble())

    init {
      println("Rectangle created with a = $a and b = $b")
  }

    override fun area() = a * b

    override fun perimeter() = 2 * a + 2 * b
}