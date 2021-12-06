package challenges.cracking_coding_interview.object_oriented_design.file_system

class Directory(n: String, p: Directory?) : Entry(n, p) {
    protected var contents: ArrayList<Entry> = ArrayList()
    override fun size(): Int {
        var size = 0
        for (e in contents) {
            size += e.size()
        }
        return size
    }

    fun numberOfFiles(): Int {
        var count = 0
        for (e in contents) {
            if (e is Directory) {
                count++ // Directory counts as a file
                count += e.numberOfFiles()
            } else if (e is File) {
                count++
            }
        }
        return count
    }

    fun deleteEntry(entry: Entry): Boolean {
        return contents.remove(entry)
    }

    fun addEntry(entry: Entry) {
        contents.add(entry)
    }

}