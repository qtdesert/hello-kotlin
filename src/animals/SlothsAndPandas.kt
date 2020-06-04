// 1- https://medium.com/kotlin-thursdays/introduction-to-kotlin-generics-9d18d3719e1d
// 2- https://medium.com/kotlin-thursdays/introduction-to-kotlin-generics-part-2-9428963bb96b
// 3- https://medium.com/kotlin-thursdays/introduction-to-kotlin-generics-reified-generic-parameters-7643f53ba513
package animals

import kotlin.random.Random
import kotlin.reflect.KFunction1

sealed class Mammal(val name: String) {
    fun eat() {}
    fun sleep() {}
    fun swim() {
        println("${name.toUpperCase()} CAN SWIM")
    }
    open fun relief() {}
}

data class Sloth(val slothName: String,
                 val isTwoFingered: Boolean,
                 val slothWeight: Int) : Mammal(slothName) {
    override fun relief() {
        val oldWeight = slothWeight
        val weightShed = Random.nextInt(0, slothWeight / 3)
        val newWeight = slothWeight - weightShed
        println("${slothName.toUpperCase()} FINALLY WENT THIS WEEK")
        println("\tOld weight: $oldWeight \t|\t New weight: $newWeight")
    }
}

data class Panda(val pandaName: String) : Mammal(pandaName)

data class Manatee(val manateeName: String) : Mammal(manateeName)

fun Mammal.vertebraeCount(): Int {
    return when (this) {
        is Manatee -> 6
        is Sloth -> 10
        else -> 7
    }
}

fun Mammal.knownSpeciesCount(): Int {
    return when (this) {
        is Sloth -> 6
        is Panda -> 2
        is Manatee -> 3
    }
}

fun Mammal.isEndangered(): Boolean {
    return when (this) {
        is Sloth -> true
        is Panda -> true
        is Manatee -> false
    }
}

fun mammalFactCheck(mammal: Mammal, factCheck: KFunction1<Mammal, Int>): Int {
    return factCheck(mammal)
}

fun slothActivity(sloth: Sloth, action: Unit) {
    sloth.run { action }
}

fun feedCrew(crew: List<Mammal>) {
    crew.forEach {
        it.eat()
        println(it.name + " ate some rad leaves!")
    }
}

fun <T: Mammal> printAnimalResultFiltered(
        clazz: Class<T>,
        list: List<Mammal>,
        factCheck: Mammal.() -> Int
): List<Mammal> {
    if (list.isNotEmpty()) {
        list.forEach {
            if (clazz.isInstance(it) ) {
                println("-- ${it.javaClass.name} - ${it.factCheck()}")
            }
        }
    }
    return list
}

// Inline reified version (much better)
inline fun <reified T: Mammal> printAnimalResultFiltered(
        list: List<Mammal>,
        factCheck: Mammal.() -> Int
): List<Mammal> {
    if (list.isNotEmpty()) {
        list.filterIsInstance<T>().forEach {
            println("-- ${it.javaClass.name} - ${it.factCheck()}")
        }
    }
    return list
}

// And now, as a List extension function:
fun <T: Mammal> List<Mammal>.printAnimalResultsExtensionFiltered(
        clazz: Class<T>,
        factCheck: Mammal.() -> Int
): List<Mammal> {
    if (this.isNotEmpty()) {
        this.filter { clazz.isInstance(it) }
                .forEach {
                    println("${it.javaClass.name} - ${it.factCheck()}")
                }
    }
    return this
}

// And, best of all, the reified version of the extension function:
inline fun <reified T: Mammal> List<Mammal>.printAnimalResultsExtensionFiltered(
        factCheck: Mammal.() -> Int
): List<Mammal> {
    if (this.isNotEmpty()) {
        this.filterIsInstance<T>()
                .forEach {
                    println("${it.javaClass.name} - ${it.factCheck()}")
                }
    }
    return this
}

fun main() {
    val sloth: Sloth

    val slothCrew = listOf(
            Sloth("Jerry", false, 15),
            Sloth("Bae", true, 12),
            Sloth("Alex", false, 15)
    )

    feedCrew(slothCrew)

    val pandaCrew = listOf(
            Panda("Tegan"),
            Panda("Peggy")
    )

    feedCrew(pandaCrew)

    slothCrew.forEach {
        slothActivity(it, it.swim())
        slothActivity(it, it.relief())
    }

    val crewCrewCrew = listOf(
            Sloth("Jerry", false, 15),
            Panda("Tegan"),
            Manatee("Manny")
    )

//    crewCrewCrew.filterIsInstance<Sloth>().forEach {
//        mammalFactCheck(it, Mammal::vertebraeCount)
//        mammalFactCheck(it, Mammal::knownSpeciesCount)
////        mammalFactCheck(it, Mammal::isEndangered) // Doesn't work: Be careful of your types
//        println("-- ${it.javaClass.name} - ${mammalFactCheck(it, Mammal::knownSpeciesCount)}")
//    }
    // Instead we do:
    printAnimalResultFiltered(Sloth::class.java, crewCrewCrew, Mammal::knownSpeciesCount)
    // And even better (with inline and reified):
    printAnimalResultFiltered<Sloth>(crewCrewCrew, Mammal::knownSpeciesCount)

    // And even, even better, with the extension function:
    println("\nSpecies count with list extension function:")
    crewCrewCrew.printAnimalResultsExtensionFiltered(Sloth::class.java, Mammal::knownSpeciesCount)
    // And the reified version (the best of all):
    println("\nSpecies count with list extension function reified:")
    crewCrewCrew.printAnimalResultsExtensionFiltered<Sloth>(Mammal::knownSpeciesCount)

    val compareByNames = Comparator { a: Mammal, b: Mammal ->
        a.name.first().toInt() - b.name.first().toInt()
    }

    crewCrewCrew.sortedWith(compareByNames).forEach(::println)

    val slothList: List<Sloth> = listOf()
    val slothList2 = listOf<Sloth>()
}
