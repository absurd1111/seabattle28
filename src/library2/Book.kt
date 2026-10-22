package library2

class Book(
    val name: String,
    val author: String,
    val year: Int,
    val genre: Genre,
    var status: BookStatus
) {
    fun printBook(index: Int) {
        println("$index\t$name\t$author\t$year\t${genre.name}\t${status.name}")
    }
}