package challenges.cracking_coding_interview.object_oriented_design.online_book_reader

class UserManager {
    private val users: HashMap<Int, User>? = null
    fun addUser(id: Int, details: String, accountType: Int): User? {
        if (users!!.containsKey(id)) {
            return null
        }
        val user = User(id, details, accountType)
        users[id] = user
        return user
    }

    fun remove(u: User): Boolean {
        return remove(u.iD)
    }

    fun remove(id: Int): Boolean {
        if (!users!!.containsKey(id)) {
            return false
        }
        users.remove(id)
        return true
    }

    fun find(id: Int): User? {
        return users!![id]
    }
}