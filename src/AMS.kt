import java.util.Random

fun main(args: Array<String>) {
    println("Hello, ${args[0]}!")
    feedTheFish()
}

fun shouldChangeWater(day: String, temperature: Int = 22, dirty: Int = 20) : Boolean {
    return true
}

fun feedTheFish() {
    val day = randomDay()
    val food = fishFood(day)
    println("Today is $day and the fish eat $food")

    swim(50, speed="slow")

    if (shouldChangeWater(day)) {
        println("Change the water today")
    }
}

fun swim(time: Int, speed: String = "fast") {
    println("swimming $speed")
}

fun randomDay() : String {
    val week = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    return week[Random().nextInt(7)]
}

fun fishFood(day: String): String {
    return when (day) {
        "Monday" -> "flakes"
        "Wednesday" -> "redworms"
        "Thursday" -> "granules"
        "Friday" -> "mosquitoes"
        "Sunday" -> "plankton"
        else -> "fasting"
    }
}
