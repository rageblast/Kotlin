package com.example.design_start

fun main(){
    val myCircle1 = Circle(5.0)

    myCircle1.changeName("Peter")
    println("The name of the circle is ${myCircle1.name}")

    val myTriangle = Triangle(3.0, 3.0, 3.0)
    myTriangle.changeName("Susan")
    println("The name of the triangle is ${myTriangle.name}")

    val rect = Rectangle(5.0)
    val circle = Circle(3.0)
    val triangle = Triangle(7.0, 7.0, 7.0)

    val maxAreaRectandCircle = maxArea(rect, circle)
    val maxAreaRectCircleTriangle = maxArea(rect, circle, triangle)
    println("The maximum area of the rectangle and the circle is $maxAreaRectandCircle")
    println("The maximum area of the rectangle, the circle and the triangle is $maxAreaRectCircleTriangle")

    // we didn't create instance
    // we are accessing value using companion
    val circle2 = Circle.randomCircle()
}

fun maxArea(shape1: Shape, shape2: Shape): Double {
    val areaShape1 = shape1.area()
    val areaShape2 = shape2.area()
    return if(areaShape1 > areaShape2) areaShape1 else areaShape2
}

fun maxArea(shape1: Shape, shape2: Shape, shape3: Shape): Double {
    val maxAreaShape1Shape2 = maxArea(shape1, shape2)
    val areaShape3 = shape3.area()
    return if(maxAreaShape1Shape2 > areaShape3) maxAreaShape1Shape2 else areaShape3
    // or
//    return maxArea(maxArea(shape1, shape2), shape3)
}