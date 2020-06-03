package aquarium

const val rocks = 3

val number = 5 // assigned value can be determined during program execution
const val num = 5 // assigned value always determined at compile time

fun complexFunctionCall() {}
val result = complexFunctionCall() // can assign method return to a val but not to const val

const val CONSTANT = "top-level constant"

object Constants {
    const val CONSTANT2 = "object constant"
}

val foo = Constants.CONSTANT2

class MyClass {

    companion object {
        const val CONSTANT3 = "constant inside companion"
    }
}
