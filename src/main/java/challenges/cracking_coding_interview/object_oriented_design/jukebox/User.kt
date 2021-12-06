package challenges.cracking_coding_interview.object_oriented_design.jukebox

class User(var name: String, var iD: Long) {
    val user: User
        get() = this

    companion object {
        fun addUser(name: String, iD: Long): User {
            return User(name, iD)
        }
    }
}