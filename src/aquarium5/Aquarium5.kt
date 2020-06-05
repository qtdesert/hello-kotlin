package aquarium5

data class Fish (var name: String)

fun main(args: Array<String>) {
    fishExamples()
}

fun fishExamples() {
    val fish = Fish("splashy")

    with (fish.name) {
        capitalize()
    }

    // equivalent to
    myWith(fish.name) {
        capitalize()
    }
    // and this is also equivalent to
    myWith(fish.name, object: Function1<String, Unit> {
        override fun invoke(name: String) {
            name.capitalize()
        }
    }) // the lambda expression is an instance of function interface, subtype of object
    // every time myWith is called an instance of the function is created.
    // To solve this, Kotlin let's us define myWith as inline: See function definition below

    // with inline, no object is created, and lambda body is inlined here
    fish.name.capitalize()

    println(fish.run { name }) // run returns the result of the block function

    println(fish.apply {}) // apply returns the object it's applied to (in this case fish object)

    val fish2 = Fish(name = "splashy").apply { name = "Sharky" }
    println(fish2.name)

    println(fish.let { it.name.capitalize() } // let returns a copy of the changed object
            .let { it + "fish" }
            .let { it.length }
            .let { it + 31 })

    // Practice (higher-order functions): Returns only numbers that are divisible by 3 using higher-order function
    val numbers = listOf<Int>(1,2,3,4,5,6,7,8,9,0)
    print(numbers.filterBy { isDivisibleBy3(it) })
}

fun myWith(name: String, block: String.() -> Unit) {
    name.block()
}

// Practice (higher-order functions)

fun List<Int>.filterBy( block: (Int) -> Boolean ): List<Int> {
    val result = mutableListOf<Int>()
    this.forEach {
        if (block(it)) result.add(it)
    }
    return result
}

fun isDivisibleBy3(number: Int) = number.rem(3) == 0

// inline version of myWith
inline fun myWithInline(name: String, block: String.() -> Unit) {
    name.block()
} // Every time myWithInline is called, it will transform the source code to inline - the function.
// Note: inlining large function does increase code size, best used for simple functions like myWith.
