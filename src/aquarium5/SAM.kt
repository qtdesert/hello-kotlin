package aquarium5

// SAM: Single Abstract Method

interface Runnable {
    fun run()
}

interface Callable<T> {
    fun call(): T
}

fun example() {
    val runnable = object: Runnable {
        override fun run() {
            println("I'm a runnable")
        }
    }
    JavaRun.runNow(runnable)

    // Or, much more simple - in Kotlin we can pass a lambda in place of a SAM
    // Kotlin will take care making the right kind of object for us.
    // Note: didn't work, had to add a function in the Java class
    JavaRun.runNow {
        println("Passing a lambda as a runnable")
    }
}
