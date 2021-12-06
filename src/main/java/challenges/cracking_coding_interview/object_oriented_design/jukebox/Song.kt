package challenges.cracking_coding_interview.object_oriented_design.jukebox

class Song {
    private val songName: String? = null
    override fun toString(): String {
        return songName!!
    }
}