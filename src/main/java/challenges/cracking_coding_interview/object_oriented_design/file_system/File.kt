package challenges.cracking_coding_interview.object_oriented_design.file_system

class File(n: String, p: Directory?, private val size: Int) : Entry(n, p) {
    var contents: String? = null
    override fun size(): Int {
        return size
    }
}