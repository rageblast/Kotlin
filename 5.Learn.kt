package com.example.design_start

// Collection is a group of data of same data type
// or different data type

// IntArray - Integer
// BooleanArray - Boolean
// DoubleArray - Double
// ByteArray - Byte
// LongArray - Long
// ShortArray - Short
// FloatArray - Float

// List is more like array but with more helper methods
//  and size can be easily increased when needed

// Set ensures that there are no duplicate values in a group

// Map is arranged in key value pair pattern

// Reading and writing to collections

// Mutable List - ArrayList, arrayListOf, mutableListOf
// Mutable Set - mutableSetOf, hashSetOf
// Mutable Map - HashMap, hashMapOf, mutableMapOf

// We can use this to tell which element can be publicly modifiable
// and which are private

fun main(){
// val numbers: IntArray = intArrayOf(1,2,3,4,5,6)

// val numbers = intArrayOf(1,2,3,4,5,6)

    val numbers = arrayOf(1,2,3,4,5,6)
    val doublenumbers = arrayOf(1.0,2.0,3.0,4.0,5.0,6.0)

    // why we do like below because it will print the
    // result if we don't do it like the below
    // we use print(numbers) -> it will give us memory location address
    print(numbers.contentToString())
    for(element in numbers){
        print(element)
    }

    // won't change array it will display
    for(elements in numbers){
        print("${elements + 2}")
    }

    print("initial values ${numbers.contentToString()}")
    // change value in index
    numbers[0] = 6
    print("\n final values ${numbers.contentToString()}")

    val days = arrayOf("Sun", "Mon", "Tues")

    val fruits = arrayOf(Fruit("Apple", 2.5), Fruit("Grape", 2.6))
    print(fruits.contentToString())

    for(index in fruits.indices){
        print("${fruits[index].name} is in index $index")
    }

    for(fruit in fruits){
        print("${fruit.name}")
    }

    val mix = arrayOf("Sun", "Mon", "Tues",1,2,3,Fruit("Apple", 2.5))
//   so we can have mix so we specify what array it is IntArrayof() -> which hold only integer

    // List hold single data types, classes, custom objects
    // they can be resized to grow when new values are added
    // they have mutable -> read and write and immutable -> read only

    val months = listOf("Jan", "Feb", "Mar")
    val anyTypes = listOf(1,2,3,true,false,"String")
    print(anyTypes.size)
    print(months[1])

    for(month in months){
        println(month)
    }

    // default list immutable
    // to add it we need to make it mutable
    // months value won't change
    // it will add the value to additional month
    // we can add value to it
    val additionalMonths = months.toMutableList()
    val newMonths = arrayOf("April", "May", "June")
    additionalMonths.addAll(newMonths)
    additionalMonths.add("July")
    print(additionalMonths)

    val dayss = mutableListOf<String>("Mon", "Tue", "Wed")
    dayss.add("Thu")
     dayss[2] = "Sunday"
//    dayss.removeAt(1)

    val removeList = mutableListOf<String>("Mon", "Wed")
    dayss.removeAll(removeList)
    print(dayss)

    // Sets won't allow duplicate data
    // its unordered

    val fruit = setOf<String>("Orange", "Apple", "Mango", "Grape", "Apple", "Orange")
    // omits the duplicate
    print(fruit.size)
    print(fruit.toSortedSet())

    val newFruits = fruit.toMutableList()
    newFruits.add("Water Melon")
    newFruits.add("Pear")
    print(newFruits.elementAt(4))

    // Maps holds value in the style of key value pairs
    // keys are unique

    val daysOfTheWeek = mapOf( 1 to "Monday", 2 to "Tuesday", 3 to "Wednesday")

    for(key in daysOfTheWeek.keys){
        print("$key is to ${daysOfTheWeek[key]}")
    }

    val fruitsMap = mapOf("Favorite" to Fruit("Grape", 2.5), "Ok" to Fruit("Orange", 2.6))

    val newDaysOfWeek = daysOfTheWeek.toMutableMap()
    newDaysOfWeek[4] = "Thursday"
    newDaysOfWeek[5] = "Friday"

    print(newDaysOfWeek.toSortedMap())

    // Array List -> create a dynamic array
    // it has read and write functionality
    // follows sequence insertion order
    // ArrayList is non synchronized and it may contain duplicate elements

    // Empty arraylist
    // ArrayList<E>()

    // Capacity
    // ArrayList(capacity: Int)

    // Element Collection
//    ArrayList(elements: Collection<E>)

// functions of ArrayList

//-> open fun add(element:E): Boolean -> add element to the collection

//-> open fun clear() -> remove all elements from collection

//-> open fun get(index: Int): E -> return elements at specified index

//-> open fun remove(element: E): Boolean -> remove a single instance of the specific element from current collection, if it is available

 // -> many more functions explore it

    fun main(){
        val arrayList = ArrayList<String>()
        arrayList.add("One")
        arrayList.add("Two")
        println("...Print ArrayList....")
        for(i in arrayList){
            print(i)
        }

        val itr = arrayList.iterator()

        while(itr.hasNext()) {
            println(itr.next())
        }

        println("Size of arrayList ="+ arrayList.size)

        println(arrayList.get(1))

        val arrayList2: ArrayList<String> = ArrayList<String>(5)
        var list: MutableList<String> = mutableListOf<String>()

        list.add("One")
        list.add("Two")

        arrayList.addAll(list)

        println("...Print ArrayList...")

        val myArrayList: ArrayList<Double> = ArrayList()
        myArrayList.add(13.212312)
        myArrayList.add(23.151232)
        myArrayList.add(32.651553)
        myArrayList.add(16.223817)
        myArrayList.add(18.523999)
        var total = 0.0
        for (i in myArrayList){
            total += i
        }
        var average = total / myArrayList.size
        println("Avarage is " + average)

    }
}

data class Fruit(val name: String, val price: Double)


