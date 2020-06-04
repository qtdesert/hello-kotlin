package aquarium

fun String.hasSpaces() = this.find { it == ' ' } != null

fun extensionsExample() {
    "Does it have spaces?".hasSpaces() // true
}

open class AquariumPlant(val color: String, private val size: Int)
class GreenLeafyPlant(size: Int) : AquariumPlant("Green", size)

fun AquariumPlant.isRed() = color == "Red"

// fun AquariumPlant.isBig() = size > 50 // error, no access to private variable size

fun AquariumPlant.print() = println("AquariumPlant")
fun GreenLeafyPlant.print() = println("GreenLeafyPlant")

fun staticExample() {
    val plant = GreenLeafyPlant(size = 50)
    plant.print() // GreenLeafyPlant

    val aquariumPlant: AquariumPlant = plant
    aquariumPlant.print() // AquariumPlant
}

val AquariumPlant.isGreen: Boolean
    get() = color == "Green"

fun propertyExample() {
    val plant = AquariumPlant("Green", 50)
    plant.isGreen // true
}

// Make the class you're extending (receiver) nullable
// /!\ Then the this variable can be null (so test it)
fun AquariumPlant?.pull() {
    this?.apply {
        println("removing $this")
    }
}

fun nullableExample() {
    val plant: AquariumPlant? = null
    plant.pull() // ok
}
