package main.kotlin.example.myapp

interface FishAction  {
    fun eat()
}
interface FishColor {
    val color: String
}

object GoldColor : FishColor { // singletone => only one instance can be
    override val color = "gold"
}

/*abstract class AquariumFish:FishAction {
    abstract val color: String
    override fun eat() = println("yum")
}*/

class Shark : FishAction, FishColor/*AquariumFish()*/ {
    override val color = "grey"
    override fun eat() {
        println("hunt and eat fish")
    }
}

class Plecostomus(fishColor: FishColor = GoldColor) : FishAction by PrintingFishAction("eat algae"), FishColor by fishColor /*AquariumFish() */ {

}

class PrintingFishAction(val food: String) : FishAction {
    override fun eat() {
        println(food)
    }
}

/*
class AquariumPlant(val color: String, private val size: Int)

fun AquariumPlant.isRed() = color == "red"    // OK
//fun AquariumPlant.isBig() = size > 50         // gives error
*/

open class AquariumPlant(val color: String, private val size: Int)

class GreenLeafyPlant(size: Int) : AquariumPlant("green", size)

fun AquariumPlant.print() = println("AquariumPlant")
fun GreenLeafyPlant.print() = println("GreenLeafyPlant")

val AquariumPlant.isGreen: Boolean
    get() = color == "green"

fun AquariumPlant?.pull() {
    this?.apply {
        println("removing $this")
    }
}