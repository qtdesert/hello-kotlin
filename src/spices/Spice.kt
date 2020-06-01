package spices

fun main(args: Array<String>) {
    delegate()
}

fun delegate() {
    val curry = Curry("spicy yellow curry", "spicy")
    println(curry.color)

    fun makeSpicyCurry() = Curry("Spicy curry", spiciness = "spicy")
}

abstract class Spice(val name: String, val spiciness: String = "mild", color: SpiceColor): SpiceColor by color {
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

    abstract fun prepareSpice()
}

interface Grinder {
    fun grind()
}

interface SpiceColor {
    val color: String
}

object YellowSpiceColor: SpiceColor {
    override val color = "yellow"
}

class Curry(name: String, spiciness: String, color: SpiceColor = YellowSpiceColor):
        Spice(name, spiciness, color),
        Grinder {

    override fun prepareSpice() {
        println("Start preparing $spiciness $name")
        grind()
    }

    override fun grind() {
        println("Grinding $spiciness $name")
    }
}
