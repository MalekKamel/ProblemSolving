package challenges.cracking_coding_interview.object_oriented_design.file_system

abstract class Entry(var name: String, protected var parent: Directory?) {
    var creationTime: Long
        protected set
    var lastUpdatedTime: Long = 0
        protected set
    var lastAccessedTime: Long = 0
        protected set

    fun delete(): Boolean {
        return if (parent == null) {
            false
        } else parent!!.deleteEntry(this)
    }

    abstract fun size(): Int
    val fullPath: String
        get() = if (parent == null) {
            name
        } else {
            parent?.fullPath + "/" + name
        }

    fun changeName(n: String) {
        name = n
    }

    init {
        creationTime = System.currentTimeMillis()
    }
}