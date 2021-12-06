package challenges.cracking_coding_interview.object_oriented_design.online_book_reader

class Display {
    private var activeBook: Book? = null
    private var activeUser: User? = null
    private var pageNumber = 0
    fun displayUser(user: User?) {
        activeUser = user
        refreshUsername()
    }

    fun displayBook(book: Book?) {
        pageNumber = 0
        activeBook = book
        refreshTitle()
        refreshDetails()
        refreshPage()
    }

    fun refreshUsername() {
        /* updates username display */
    }

    fun refreshTitle() {
        /* updates title display */
    }

    fun refreshDetails() {
        /* updates details display */
    }

    fun refreshPage() {
        /* updated page display */
    }

    fun turnPageForward() {
        pageNumber++
        refreshPage()
    }

    fun turnPageBackward() {
        pageNumber--
        refreshPage()
    }
}