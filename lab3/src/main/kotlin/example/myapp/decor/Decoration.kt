package main.kotlin.example.myapp.decor

data class Decoration(val rocks: String) {
}

data class Decoration2(val rocks: String, val wood: String, val diver: String){
}

enum class Color(val rgb: Int) { // enums are similars to singletones => it can be only one enum's instance
    RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF);
}

fun makeDecorations() {
    val decoration1 = Decoration("granite")
    println(decoration1)

    val decoration2 = Decoration("slate")
    println(decoration2)

    val decoration3 = Decoration("slate")
    println(decoration3)

    println (decoration1.equals(decoration2)) // equals() == "==". if u want to use "==" like in java, u should use "==="
    println (decoration3.equals(decoration2))

    //======================================
    val d5 = Decoration2("crystal", "wood", "diver")
    println(d5)

// Assign all properties to variables.
    val (rock, wood, diver) = d5 // if we don't need one or more properties -> val (rock, _, diver) = d5
    println(rock)
    println(wood)
    println(diver)
}

// companion object
class Choice {
    companion object {
        var name: String = "lyric"
        fun showDescription(name:String) = println("My favorite $name")
    }
}
