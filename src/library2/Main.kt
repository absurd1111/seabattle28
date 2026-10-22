package library2

fun main() {
    val catalog = mutableListOf(
        Book("Мастер и Маргарита", "Булгаков", 1967, Genre.FICTION, BookStatus.AVAILABLE),
        Book("Война и мир", "Толстой", 1869, Genre.FICTION, BookStatus.AVAILABLE),
        Book("Преступление и наказание", "Достоевский", 1866, Genre.FICTION, BookStatus.BORROWED),
        Book("Мертвые души", "Гоголь", 1842, Genre.FICTION, BookStatus.BORROWED),
        Book("1984", "Оруэлл", 1949, Genre.FICTION, BookStatus.RESERVED),
        Book("Хоббит", "Толкин", 1937, Genre.FANTASY, BookStatus.AVAILABLE),
        Book("Гарри Поттер", "Роулинг", 1997, Genre.FANTASY, BookStatus.AVAILABLE),
        Book("Шерлок Холмс", "Конан Дойл", 1892, Genre.DETECTIVE, BookStatus.AVAILABLE),
        Book("Убийство в Восточном экспрессе", "Кристи", 1934, Genre.DETECTIVE, BookStatus.ARCHIVED),
        Book("Краткая история времени", "Хокинг", 1988, Genre.SCIENTIFIC, BookStatus.AVAILABLE),
        Book("Происхождение видов", "Дарвин", 1859, Genre.SCIENTIFIC, BookStatus.ARCHIVED),
        Book("Стив Джобс", "Айзексон", 2011, Genre.BIOGRAPHY, BookStatus.BORROWED),
        Book("Дневник Анны Франк", "Франк", 1947, Genre.BIOGRAPHY, BookStatus.LOST),
        Book("Маленький принц", "Сент-Экзюпери", 1943, Genre.CHILDREN, BookStatus.AVAILABLE),
        Book("Алиса в Стране чудес", "Кэрролл", 1865, Genre.CHILDREN, BookStatus.RESERVED)
    )

    println("=== Часть 1. Каталог ===")
    println("№\tНазвание\tАвтор\tГод\tЖанр\tСтатус")
    catalog.forEachIndexed {
        index, book -> book.printBook(index + 1)
    }
    println("\n")



    println("=== Часть 2. Статистика ===")
    var availableCount = 0
    var borrowedCount = 0
    var lostCount = 0
    var reservedCount = 0
    var archivedCount = 0

    for (book in catalog) {
        when (book.status) {
            BookStatus.AVAILABLE -> availableCount++
            BookStatus.BORROWED -> borrowedCount++
            BookStatus.LOST -> lostCount++
            BookStatus.RESERVED -> reservedCount++
            BookStatus.ARCHIVED -> archivedCount++
        }
    }
    println("Доступно: $availableCount")
    println("Выдано: $borrowedCount")
    println("Потеряно: $lostCount")
    println("Зарезервировано: $reservedCount")
    println("В архиве: $archivedCount")

    println("\nПо жанрам:")

    val booksByGenre = catalog.groupBy {
        it.genre
    }
    for ((genre, books) in booksByGenre) {
        println("${genre.name}: ${books.size}")
    }

    val oldestBook = catalog.minByOrNull { it.year }
    val newestBook = catalog.maxByOrNull { it.year }
    println("\nСамая старая книга: ${oldestBook?.name} (${oldestBook?.year})")
    println("Самая новая книга: ${newestBook?.name} (${newestBook?.year})")


    val averageYear = catalog.sumOf { it.year } / catalog.size
    println("Средний год: $averageYear")
    println("\n")



    println("=== Часть 3. Фильтры ===")

    println("--- Доступные книги ---")
    val availableBooks = catalog.filter {
        it.status == BookStatus.AVAILABLE
    }
    availableBooks.forEach { println("${it.name} - ${it.author}") }

    println("\n--- Поиск по жанру ---")
    println("Введите жанр (например, FANTASY, DETECTIVE):")
    val inputGenre = readlnOrNull()?.uppercase() ?: ""
    val genreBooks = catalog.filter {
        it.genre.name == inputGenre
    }
    if (genreBooks.isEmpty()) {
        println("Книг такого жанра нет")
    }
    else {
        genreBooks.forEach { println("${it.name} - ${it.author}") }
    }

    println("\n--- Поиск по году ---")
    println("Введите год:")

    val inputYear = readlnOrNull()?.toIntOrNull() ?: 0
    val newBooks = catalog.filter { it.year > inputYear }
    if (newBooks.isEmpty()) {
        println("Книг после $inputYear года не найдено.")
    }
    else {
        newBooks.forEach { println("${it.name} (${it.year})") }
    }
    println("\n")


    println("=== Часть 4. Действия ===")
    fun getBookByIndex(indexFromUser: String): Book? {
        val idx = indexFromUser.toIntOrNull() ?: return null
        if (idx < 1 || idx > catalog.size) return null
        return catalog[idx - 1]
    }

    println("Введите номер книги, чтобы ВЫДАТЬ её:")
    val bookToBorrow = getBookByIndex(readlnOrNull() ?: "")
    if (bookToBorrow != null) {
        if (bookToBorrow.status == BookStatus.AVAILABLE) {
            bookToBorrow.status = BookStatus.BORROWED
            println("Книга '${bookToBorrow.name}' успешно выдана")
        }
        else {
            println("Нельзя выдать книгу. Текущий статус: ${bookToBorrow.status.displayName}")
        }
    }
    else {
        println("Книга с таким номером не найдена")
    }

    println("\nВведите номер книги, чтобы ВЕРНУТЬ её:")
    val bookToReturn = getBookByIndex(readlnOrNull() ?: "")
    if (bookToReturn != null) {
        if (bookToReturn.status == BookStatus.BORROWED) {
            bookToReturn.status = BookStatus.AVAILABLE
            println("Книга '${bookToReturn.name}' успешно возвращена.")
        }
        else {
            println("Нельзя вернуть книгу. Текущий статус: ${bookToReturn.status.displayName}")
        }
    } else
    {
        println("Книга с таким номером не найдена.")
    }

    println("\nВведите номер книги, чтобы ПОТЕРЯТЬ её:")
    val bookToLose = getBookByIndex(readlnOrNull() ?: "")
    if (bookToLose != null) {
        bookToLose.status = BookStatus.LOST
        println("Книга '${bookToLose.name}' теперь числится потерянной.")
    }
    else {
        println("Книга с таким номером не найдена")
    }


    println("\nВведите номер книги, чтобы ЗАРЕЗЕРВИРОВАТЬ её:")
    val bookToReserve = getBookByIndex(readlnOrNull() ?: "")
    if (bookToReserve != null) {
        bookToReserve.status = BookStatus.RESERVED
        println("Книга '${bookToReserve.name}' зарезервирована.")
    }
    else {
        println("Книга с таким номером не найдена")
    }
    println("\n")

    println("=== Часть 5. Отчёт ===")
    println("Всего книг: ${catalog.size}")
    println("\nПо статусам:")
    val booksByStatus = catalog.groupBy { it.status }
    for (status in BookStatus.values()) {
        val count = booksByStatus[status]?.size ?: 0
        val percent = (count * 100) / catalog.size
        println("${status.name}: $count ($percent%)")
    }

    println("\nПо жанрам:")
    for (genre in Genre.values()) {
        val count = booksByGenre[genre]?.size ?: 0
        val percent = (count * 100) / catalog.size
        println("${genre.name}: $count ($percent%)")
    }
    println("\nСамая старая книга: ${oldestBook?.name} (${oldestBook?.year})")
    println("Самая новая книга: ${newestBook?.name} (${newestBook?.year})")
    println("Средний год: $averageYear")
}