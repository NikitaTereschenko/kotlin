fun main() {
    // basic function
    fun printHello() {
        println("Hello World")
    }
    printHello()

    // conversion
    val i : Int = 6
    val b1 = i.toByte()
    println(b1)

    val b2 : Byte = 1
    //val i1 : Int = b2 // conversion ERROR
    //val i2 : String = b2 // conversion ERROR
    //val i3 : Double = b2 // conversion ERROR
    val i4 : Int = b2.toInt()
    val i5 : String = b2.toString()
    val i6 : Double = b2.toDouble()

    val oneMillion = 1_000_000
    var socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E // -1253794
    val bytes = 0b11010010_01101001_10010100_10010010 // -764832622
    println(hexBytes.toInt())

    // var - mutable, val - immutable
    var fish = 1
    fish = 2
    val aquarium = 1
    //aquarium = 2 // ERROR because val is immutable

    // we cant change a type of variables that has been assigned by me or compiler
    var turtle: Int = 12
    var lakes: Double = 2.5

    // $ - string template
    var numberOfFish = 5
    var numberOfPlants = 12
    println("I have $numberOfFish fish" + " and $numberOfPlants plants")
    println("I have ${numberOfFish + numberOfPlants} fish and plants")

    val numberOfTurtles = 50
    val numberOfTrees = 23
    if (numberOfTurtles > numberOfTrees) {
        println("Good ratio!")
    } else {
        println("Unhealthy ratio")
    }

    val ducks = 50
    if (ducks in 1..100) {
        println(ducks)
    }

    if (numberOfTurtles == 0) {
        println("Empty tank")
    } else if (numberOfTurtles < 40) {
        println("Got turtles!")
    } else {
        println("That's a lot of turtles!")
    }

    when (numberOfTurtles) {
        0  -> println("Empty tank")
        in 1..39 -> println("Got turtles!")
        else -> println("That's a lot of turtles!")
    }

    // by default val cant be bull
    // var rocks: Int = null // null can not be a value of non-null type int
    var marbles: Int? = null // question operator indicates that a variable can be null

    // test for null with ? operator
    var fishFoodTreats = 6
    if (fishFoodTreats != null) {
        fishFoodTreats = fishFoodTreats.dec()
    }

    /*fishFoodTreats = 6
    fishFoodTreats = fishFoodTreats?.dec()*/ // cant run without !! operator (converts any value to a non-null type ant throws an exception when type == null)

    fishFoodTreats = 6
    fishFoodTreats = fishFoodTreats?.dec()!!

    fishFoodTreats = fishFoodTreats?.dec() ?: 0 // if fishFoodTreats is not null, decrement and use it; otherwise use the value after the ?:, which is 0

    // ?: - Elvis operator, provides an alternative value if var is null

    val s : String? = /*null*/ "hulumulu";
    val len = s!!.length   // throws NullPointerException if s is null

    // Arrays, lists and loops
    // LISTS can be mutable and immutable
    val school = listOf("mackerel", "trout", "halibut") // cant be changed
    println(school)

    val myList = mutableListOf("tuna", "salmon", "shark") // can be changed
    myList.remove("shark")
    println(myList)
    // ARRAYS can be only immutable: u cant change array size or elems
    val university = arrayOf("shark", "salmon", "minnow")
    println(java.util.Arrays.toString(university))

    val mix = arrayOf("fish", 2) // arrayOf = mix types, intArrayOf/doubleArrayOf... - one type for all elems
    val numbers = intArrayOf(1,2,3)

    // easy combine 2 arrays
    val numbers2 = intArrayOf(1,2,3)
    val numbers3 = intArrayOf(4,5,6)
    val foo2 = numbers3 + numbers2
    println(foo2[5])

    // array elems can be a lists and list elems can be an arrays
    val someNumbers = intArrayOf(1, 2, 3)
    val oceans = listOf("Atlantic", "Pacific")
    val oddList = listOf(someNumbers, oceans, "salmon")
    println(oddList)

    // we can initialize array with code
    val array = Array (5) { it * 3 }
    println(java.util.Arrays.toString(array))

    val highSchool = arrayOf("shark", "salmon", "minnow")
    for (element in highSchool) {
        print(element + " ")
    }

    // loop for index and elem at the same time
    for ((index, element) in school.withIndex()) {
        println("Item at $index is $element\n")
    }

    // ranges
    for (i in 1..5) println(i)
    for (i in 5 downTo 1) println(i)
    for (i in 3..6 step 2) println(i)
    for (i in 'd'..'g') println(i)

    // while, do while
    var bubbles = 0
    while (bubbles < 50) {
        bubbles++
    }
    println("$bubbles bubbles in the water\n")

    do {
        bubbles--
    } while (bubbles > 50)
    println("$bubbles bubbles in the water\n")

    repeat(2) {
        println("A fish is swimming")
    }
}