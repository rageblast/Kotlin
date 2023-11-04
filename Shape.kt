package com.example.design_start

abstract class Shape( var name: String) {

    constructor(name: String, vararg dimension: Double) : this(name)

    init {
        println("I am the super class!")
    }

    abstract fun area(): Double

    abstract fun perimeter(): Double

    fun changeName(newName: String) {
        name = newName
    }
}