// https://www.raywenderlich.com/3634394-kotlin-generics-tutorial-getting-started
package animals

enum class FurColor(val color: String) {
    BLACK("black"), BROWN("brown"), WHITE("white"), RED("red")
}
enum class EyesColor(val color: String) {
    GREEN("green"), YELLOW("yellow"), BLUE("blue")
}
enum class FeatherColor(val color: String) {
    BLACK("black"), BROWN("brown"), RED("red")
}

open class Animal(open val id: Int, open val name: String)

data class Dog(override val id: Int, override val name: String, val furColor: FurColor) : Animal(id, name)

data class Cat(override val id: Int, override val name: String, val eyesColor: EyesColor) : Animal(id, name)

open class Bird(override val id: Int,
                override val name: String,
                open val featherColor: FeatherColor,
                open val maxSpeed: Float) :
        Animal(id, name)

class Eagle(override val id: Int,
            override val name: String,
            override val featherColor: FeatherColor,
            override val maxSpeed: Float) :
        Bird(id, name, featherColor, maxSpeed)

class Cage0(var dog: Dog, val size: Double)

val dog = Dog(0, "Doglin", FurColor.BLACK)
val cage = Cage0(dog, 6.0)

class Cage<T: Animal>(var animal: T, val size: Double)

val dog2: Dog = Dog(1, "Stu", FurColor.BROWN)
val cat: Cat = Cat(4, "Peter", EyesColor.GREEN)
val cageDog: Cage<Dog> = Cage(dog, 6.0)
val cageCat: Cage<Cat> = Cage(cat, 3.0)
//val cageString: Cage<String> = Cage("This cage hosts a String?", 0.1) // nope

var cageAnimal: Cage<Animal> = TODO()
var cageDog2: Cage<Dog> = Cage(dog2, 3.2)
fun x() {
//    cageAnimal = cageDog2 // error, Kotlin is preventing our code from potential future errors
}

class CovariantCage<out T : Animal>(private val t: T?) {
    fun getId(): Int? = t?.id
    fun getName(): String? = t?.name
    fun getContentType(): T? = t?.let { t } ?: run { null }
    fun printAnimalInfo(): String = "Animal ${t?.id} is called ${t?.name}"
}

val dog3: Dog = Dog(1, "Bob", FurColor.RED)
var cageDog3: CovariantCage<Dog> = CovariantCage(dog3)
var cageAnimal2: CovariantCage<Animal> = cageDog3   // thanks to being 'out'

class ContravariantCage<in T : Bird>(private var t: T?) {
    fun getId(): Int? = t?.id
    fun getName(): String? = t?.name
    fun setContentType(t: T) { this.t = t }
    fun printAnimalInfo(): String = "Animal ${t?.id} is called ${t?.name}"
}


// Type Projection

class AnimalCage<out T : Animal>(val animal: T, val size: Double)

fun examine(cageItem: AnimalCage<out Animal>) {
    val animal: Animal = cageItem.animal
    println(animal)
}

val bird: Bird = Eagle(7, "Piti", FeatherColor.BROWN, maxSpeed = 75.0f)
val animal: Animal = Dog(1, "Joe", FurColor.BROWN)
val animalCage: AnimalCage<Animal> = AnimalCage(animal, 3.1)
val animalCage2: AnimalCage<Bird> = AnimalCage(bird, 0.9)
fun y() {
    examine(animalCage)
    examine(animalCage2)   // 'out' provides type-safety so that this statement is valid
}

// Generics in Real Scenarios

interface Repository<S : AnimalCage<Animal>> {
    fun registerCage(cage: S): Unit
}

class AnimalRepository : Repository<AnimalCage<Animal>> {
    override fun registerCage(cage: AnimalCage<Animal>) {
        println("registering cage for: ${cage.animal.name}")
    }
}

class BirdRepository: Repository<AnimalCage<Bird>> {
    override fun registerCage(cage: AnimalCage<Bird>) {
        println("registering cage for: ${cage.animal.name}")
    }
}

// Cannot create this function
// Use out T in classes and methods that will not modify T, but produce or use it as an output type
//fun sampleFun(t: T) {
//    println("dummy behavior")
//}


// Collections
var list0: MutableList<out Animal>
val list1: MutableList<Dog> = mutableListOf(dog2)
fun z() { list0 = list1 }
