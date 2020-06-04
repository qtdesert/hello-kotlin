import kotlin.random.Random

const val MAX_NUMBER_BOOKS = 20

object Constants {
    const val BASE_URL_OTHER = "http://www.turtlecare.net/"
}

open class Book(val title: String, val author: String, val year: Int, var pages: Int) {

    companion object {
        val BASE_URL = "http://www.turtlecare.net/"
    }

    private var currentPage = 1

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

    open fun readPage() {
        currentPage++
    }
}

class eBook(title: String, author: String, year: Int, pages: Int, val format: String = "text") :
        Book(title, author, year, pages) {

    private var wordCount = 0

    override fun readPage() {
        wordCount += 250
    }
}

fun Book.weight() = pages * 1.5

fun Book.tornPages(torn: Int) = if (pages >= torn) pages -= torn else pages = 0

class Puppy(private val name: String) {
    fun playWithBook(book: Book) {
        println("$name plays with ${book.title} book.")
        book.tornPages(Random.nextInt(book.pages + 1))
    }
}

fun main(args: Array<String>) {

    val romeoAndJuliet = Book("Romeo and Juliet", "William Shakespeare", 1597, 649)
    val bookTitleAuthor = romeoAndJuliet.getTitleAuthor()
    val bookTitleAuthorYear = romeoAndJuliet.getTitleAuthorYear()

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

    val newBook = Book("The Shining", "Stephen King", 1979, 649)
    println("There are ${newBook.pages} pages in the Shining.")
    val puppy = Puppy("Kiddo")

    while (newBook.pages > 0) {
        puppy.playWithBook(newBook)
        println("Pages left in ${newBook.title}: ${newBook.pages}")
    }
    println("Sad puppy, no more pages in ${newBook.title}. ")
}
