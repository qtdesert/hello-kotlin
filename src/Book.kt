const val MAX_NUMBER_BOOKS = 20

object Constants {
    const val BASE_URL_OTHER = "http://www.turtlecare.net/"
}

class Book(val title: String, val author: String, val year: Int) {

    companion object {
        val BASE_URL = "http://www.turtlecare.net/"
    }

    fun getTitleAuthor(): Pair<String, String> {
        return (title to author)
    }

    fun getTitleAuthorYear(): Triple<String, String, Int> {
        return Triple(title, author, year)
    }

    fun canBorrow(hasBooks: Int): Boolean {
        return (hasBooks < MAX_NUMBER_BOOKS)
    }

    fun printUrl() {
        println("$BASE_URL$title.html")
    }
}

fun main(args: Array<String>) {

    val book = Book("Romeo and Juliet", "William Shakespeare", 1597)
    val bookTitleAuthor = book.getTitleAuthor()
    val bookTitleAuthorYear = book.getTitleAuthorYear()

    println("Here is your book ${bookTitleAuthor.first} by ${bookTitleAuthor.second}")

    println("Here is your book ${bookTitleAuthorYear.first} " +
            "by ${bookTitleAuthorYear.second} written in ${bookTitleAuthorYear.third}")

    val allBooks = setOf("Titus Andronicus", "Julius Caesar", "Hamlet", "Macbeth")
    val library = mapOf("William Shakespeare" to allBooks)
    println(library.any { it.value.contains("Hamlet") })

    val moreBooks = mutableMapOf<String, String>("Wilhelm Tell" to "Schiller")
    moreBooks.getOrPut("Jungle Book") { "Kipling" }
    moreBooks.getOrPut("Hamlet") { "Shakespeare" }
    println(moreBooks)
}
