package aquarium5

import aquarium.num

data class Fish (var name: String)

fun main(args: Array<String>) {
    fishExamples()
}

fun fishExamples() {
    val fish = Fish("splashy")

    with (fish.name) {
        println(capitalize())
    }
    // equivalent to
    myWith(fish.name) {
        println(capitalize())
    }

    println(fish.run { name }) // run returns the result of the block function

    println(fish.apply {}) // apply returns the object it's applied to (in this case fish object)

    val fish2 = Fish(name = "splashy").apply { name = "Sharky" }
    println(fish2.name)

    println(fish.let { it.name.capitalize() } // let returns a copy of the changed object
            .let { it + "fish" }
            .let { it.length }
            .let { it + 31 })

    // Returns only numbers that are divisible by 3 using higher-order function
    val numbers = listOf<Int>(1,2,3,4,5,6,7,8,9,0)
    print(numbers.filterBy { isDivisibleBy3(it) })
}

fun myWith(name: String, block: String.() -> Unit) {
    name.block()
}

fun List<Int>.filterBy( block: (Int) -> Boolean ): List<Int> {
    val result = mutableListOf<Int>()
    this.forEach {
        if (block(it)) result.add(it)
    }
    return result
}

fun isDivisibleBy3(number: Int) = number.rem(3) == 0
