package challenges.cracking_coding_interview.object_oriented_design.online_book_reader

class OnlineReaderSystem {
    val library: Library
    val userManager: UserManager
    val display: Display
    var activeBook: Book? = null
        set(book) {
            display.displayBook(book)
            field = book
        }
    var activeUser: User? = null
        set(user) {
            field = user
            display.displayUser(user)
        }

    init {
        userManager = UserManager()
        library = Library()
        display = Display()
    }
}