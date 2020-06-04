package aquarium.generics

fun main(args: Array<String>) {
    genericExample()
}

open class WaterSupply(var needsProcessed: Boolean)

class TapWater : WaterSupply(true) {
    fun addChemicalCleaners() = apply { needsProcessed = false }
}

class FishStoreWater : WaterSupply(false)

class LakeWater : WaterSupply(true) {
    fun filter() = apply { needsProcessed = false }
}

class Aquarium<out T: WaterSupply>(val waterSupply: T) {

    fun addWater() {
        check(!waterSupply.needsProcessed) { "water supply needs processed" }
        println("adding water from $waterSupply")
    }

    fun addWater2(cleaner: Cleaner<T>) {
        if(!waterSupply.needsProcessed) {
            cleaner.clean(waterSupply)
        }
        println("adding water from $waterSupply")
    }

    // here is how to declare a generic method
//    inline fun <reified R: WaterSupply> hasWaterSupplyOfType() = waterSupply is R
}

// better to declare it as an extension function as it is not really part of the aquarium
inline fun <reified R: WaterSupply> Aquarium<*>.hasWaterSupplyOfType() = waterSupply is R

interface Cleaner<in T: WaterSupply> {
    fun clean(waterSupply: T)
}

class TapWaterCleaner : Cleaner<TapWater> {
    override fun clean(waterSupply: TapWater) {
        waterSupply.addChemicalCleaners()
    }
}

fun addItemTo(aquarium: Aquarium<WaterSupply>) = println("item added")

fun <T: WaterSupply> isWaterClean(aquarium: Aquarium<T>) {
    println("aquarium water is clean: ${aquarium.waterSupply.needsProcessed}")
}

inline fun <reified T: WaterSupply> WaterSupply.isOfType() = this is T

fun genericExample() {
    val aquarium = Aquarium(TapWater())
    aquarium.waterSupply.addChemicalCleaners()

//    val aquarium2 = Aquarium("string") // Impossible
//    val aquarium3 = Aquarium(null) // Impossible

    val aquarium4 = Aquarium(LakeWater())
    aquarium4.waterSupply.filter()
    aquarium4.addWater()

    val aquarium5 = Aquarium(TapWater())

    addItemTo(aquarium5)

    val cleaner = TapWaterCleaner()
    aquarium5.addWater2(cleaner)

    isWaterClean(aquarium5)

    aquarium5.hasWaterSupplyOfType<TapWater>() // true
    aquarium5.waterSupply.isOfType<LakeWater>() // false
}
