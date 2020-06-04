package buildings

fun main(args: Array<String>) {
    Building("The Woodenstone", Wood()).build()
    Building("Brickstone", Brick()).build()
}

open class BaseBuildingMaterial {
    open val numberNeeded: Int = 1
}

class Wood : BaseBuildingMaterial() {
    override val numberNeeded: Int = 4
}

class Brick : BaseBuildingMaterial() {
    override val numberNeeded: Int = 8
}

class Building<T: BaseBuildingMaterial>(private val name: String, private val buildingMaterial: T) {

    private val baseMaterialsNeeded = 100
    var actualMaterialsNeeded = baseMaterialsNeeded * buildingMaterial.numberNeeded

    fun build() {
        println("$actualMaterialsNeeded units of ${buildingMaterial::class.simpleName?.toLowerCase()} " +
                "are required to build $name building.")
    }
}
