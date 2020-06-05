enum class Direction {
    NORTH, SOUTH, EAST, WEST, START, END
}

class Game {
    private var path = mutableListOf(Direction.START)
    private val north = { path.move(Direction.NORTH) }
    private val south = { path.move(Direction.SOUTH) }
    private val east = { path.move(Direction.EAST) }
    private val west = { path.move(Direction.WEST) }
    private val end = {
        path.add(Direction.END)
        println("Game Over: $path")
        path.clear()
        false
    }

    private fun MutableList<Direction>.move(direction: Direction): Boolean {
        this.add(direction)
        println("Moving $direction")
        return true
    }

    private fun move(where: () -> Boolean) {
        where.invoke()
    }

    fun makeMove(command: String?) {
        when (command) {
            "n" -> move(north)
            "s" -> move(south)
            "e" -> move(east)
            "w" -> move(west)
            else -> move(end)
        }
    }
}

fun main() {
    val game = Game()
    while (true) {
        print("Enter a direction: n/s/e/w:")
        game.makeMove(readLine())
    }
}
