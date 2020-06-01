package aquarium

fun main(args: Array<String>) {
    delegate()
}

fun delegate() {
    val pleco = Plecostomus()
    println("Pleco has color ${pleco.color} and eats:")
    pleco.eat()

    val clown = Clownfish()
    println("Clownfish has color ${clown.color} and eats:")
    clown.eat()

    val shark = Shark()
    println("Shark has color ${shark.color} and eats:")
    shark.eat()
}

interface FishAction {
    fun eat()
}

interface FishColor {
    val color: String
}

class Clownfish(fishColor: FishColor = OrangeColor):
        FishAction by PrintingFishAction("plankton"),
        FishColor by fishColor

class Shark(fishColor: FishColor = GreyColor):
        FishAction by PrintingFishAction("fishes"),
        FishColor by fishColor

class Plecostomus(fishColor: FishColor = GoldColor):
        FishAction by PrintingFishAction("a lot of algae"),
        FishColor by fishColor

object GoldColor: FishColor {
    override val color = "gold"
}

object RedColor: FishColor {
    override val color = "red"
}

object OrangeColor: FishColor {
    override val color = "orange"
}

object GreyColor: FishColor {
    override val color = "grey"
}

class PrintingFishAction(val food: String): FishAction {
    override fun eat() {
        println(food)
    }
}
