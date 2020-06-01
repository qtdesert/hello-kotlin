package aquarium

fun main(args: Array<String>) {

    val mySimpleSpice = SimpleSpice()
    println("Name: ${mySimpleSpice.name}")
    println("Spiciness: ${mySimpleSpice.spiciness}")
    println("Heat: ${mySimpleSpice.heat}")

    println("Setting heat to 9")
    mySimpleSpice.heat = 9
    println("Name: ${mySimpleSpice.name}")
    println("Spiciness: ${mySimpleSpice.spiciness}")
    println("Heat: ${mySimpleSpice.heat}")


    val spices1 = listOf(
            Spice("curry", "mild"),
            Spice("pepper", "medium"),
            Spice("cayenne", "spicy"),
            Spice("ginger", "mild"),
            Spice("red curry", "medium"),
            Spice("green curry", "mild"),
            Spice("hot pepper", "extremely spicy")
    )

    val spice = Spice("cayenne", spiciness = "spicy")

    val spicelist = spices1.filter {it.heat < 5}

    fun makeSalt() = Spice("Salt")
}

class SimpleSpice {
    var name = "curry"
    var spiciness = "mild"

    var heat: Int
        get() = when(spiciness) {"mild" -> 5 "fucking spicy" -> 9 else -> 0 }
        set(value) { spiciness = when(value) { 5 -> "mild" 9 -> "fucking spicy" else -> "not spicy" } }
}

class Spice(val name: String, val spiciness: String = "mild") {

    var heat: Int
        get() {
            return when(spiciness) {
                "mild" -> 1
                "medium" -> 3
                "spicy" -> 5
                "very spicy" -> 7
                "crazy spicy" -> 10
                else -> 0
            }
        }
        set(value) {}

    init {
        println("Name: $name")
        println("Spiciness: $spiciness")
        println("Heat: $heat")
    }
}
