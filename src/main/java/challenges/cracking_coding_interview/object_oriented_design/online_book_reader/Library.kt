package challenges.cracking_coding_interview.object_oriented_design.online_book_reader

import java.util.HashMap
import challenges.cracking_coding_interview.object_oriented_design.online_book_reader.Library
import challenges.cracking_coding_interview.object_oriented_design.online_book_reader.Display

class Library {
    private val books: HashMap<Int, Book>? = null
    fun addBook(id: Int, details: String): Book? {
        if (books!!.containsKey(id)) {
            return null
        }
        val book = Book(id, details)
        books[id] = book
        return book
    }

    fun remove(b: Book): Boolean {
        return remove(b.iD)
    }

    fun remove(id: Int): Boolean {
        if (!books!!.containsKey(id)) {
            return false
        }
        books.remove(id)
        return true
    }

    fun find(id: Int): Book? {
        return books!![id]
    }
}