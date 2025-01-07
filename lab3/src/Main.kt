package main.kotlin.example.myapp

import main.kotlin.example.myapp.decor.*

object Constants {
    const val CONSTANT2 = "object constant"
}

fun buildAquarium() {
    val myAquarium = Aquarium()
    myAquarium.printSize()
    myAquarium.height = 60
    myAquarium.printSize()
    println("------")
    var aquarium1 = Aquarium() // default values
    aquarium1.printSize()
    println("------")
    var aquarium2 = Aquarium(40) // change width
    aquarium2.printSize()
    println("------")
    var aquarium3 = Aquarium(50, 70) // change width and height
    aquarium3.printSize()
    println("------")
    var aquarium4 = Aquarium(30, 50, 80) // change width, height and length
    aquarium4.printSize()
    println("------")

    val aquarium6 = Aquarium(numberOfFish = 29)
    aquarium6.printSize()
    //println("Volume: ${aquarium6.width * aquarium6.length * aquarium6.height / 1000} liters")

    aquarium6.volume = 70
    aquarium6.printSize()

    println("------")
    val aquarium7 = Aquarium(length = 25, width = 25, height = 40)
    aquarium7.printSize()

    println("------")
    val myTower = TowerTank(diameter = 25, height = 40)
    myTower.printSize()
}

fun makeFish() {
    val shark = Shark()
    val pleco = Plecostomus()

    println("Shark: ${shark.color}")
    shark.eat()
    println("Plecostomus: ${pleco.color}")
    pleco.eat()
}

enum class Direction(val degrees: Int) {
    NORTH(0), SOUTH(180), EAST(90), WEST(270)
}

fun part2() {
    println(Choice.name)
    Choice.showDescription("pick")
    Choice.showDescription("selection")

    // pairs
    val equipment = "fish net" to "catching fish"
    println("${equipment.first} used for ${equipment.second}")

    val numbers = Triple(6, 9, 42)
    println(numbers.toString())
    println(numbers.toList())

    val equipment2 = ("fish net" to "catching fish") to "equipment"
    println("${equipment2.first} is ${equipment2.second}\n")
    println("${equipment2.first.second}")

    //destructuring
    val equipment3 = "fish net" to "catching fish"
    val (tool, use) = equipment3
    println("$tool is used for $use")

    val numbers2 = Triple(6, 9, 42)
    val (n1, n2, n3) = numbers2
    println("$n1 $n2 $n3")

    val list = listOf(1, 5, 3, 4)
    println(list.sum())

    //val list2 = listOf("a", "bbb", "cc")
    //println(list2.sum()) // ERROR, list dont know how to sum strings
    //but we can specify how to sum it
    val list2 = listOf("a", "bbb", "cc")
    println(list2.sumBy { it.length }) // sumBy is deprecated
    //println(list2.sumOf { list2.indexOf(it)})

    println()
    val list3 = listOf("a", "bbb", "cc")
    for (s in list3.listIterator()) {
        println("$s ")
    }
    println()

    val scientific = hashMapOf("guppy" to "poecilia reticulata", "catfish" to "corydoras", "zebra fish" to "danio rerio" )
    println(scientific.get("guppy"))
    println(scientific.get("zebra fish"))
    println(scientific.get("swordtail"))
    println(scientific.getOrDefault("swordtail", "sorry, I don't know"))
    println(scientific.getOrElse("swordtail") {"sorry, I don't know"})

    /* constants
    val value1 = complexFunctionCall() // OK -> can be called because val declares at execute time
    const val CONSTANT1 = complexFunctionCall() // NOT ok -> cant be called because const val declares at compile time
     */
    val foo = Constants.CONSTANT2

    // extension functions

    /*fun String.hasSpaces(): Boolean {
        val found = this.indexOf(' ')
        // also valid: this.indexOf(" ")
        // returns positive number index in String or -1 if not found
        return found != -1
    }*/

    fun String.hasSpaces() = indexOf(" ") != -1


    val plant = GreenLeafyPlant(size = 10)
    plant.print()
    println("\n")
    val aquariumPlant: AquariumPlant = plant
    aquariumPlant.print()  // what will it print?
    // the type is resolved at compile time
    println(aquariumPlant.isGreen)

    val plant2: AquariumPlant? = null
    plant2.pull()

}

fun main() {
    //buildAquarium()
    //makeFish()
    //makeDecorations()

    //println(Direction.EAST.name)
    //println(Direction.EAST.ordinal)
    //println(Direction.EAST.degrees)

    // PART 2
    // companion object of a class == static in JAVA
    part2()

}

class MyClass {
    companion object {
        const val CONSTANT3 = "constant in companion"
    }
}