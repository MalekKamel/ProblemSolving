package challenges.cracking_coding_interview.object_oriented_design.jukebox

import challenges.cracking_coding_interview.object_oriented_design.jukebox.Playlist
import challenges.cracking_coding_interview.object_oriented_design.jukebox.CD
import challenges.cracking_coding_interview.object_oriented_design.jukebox.Song
import challenges.cracking_coding_interview.object_oriented_design.jukebox.CDPlayer
import challenges.cracking_coding_interview.object_oriented_design.jukebox.SongSelector

class Song {
    private val songName: String? = null
    override fun toString(): String {
        return songName!!
    }
}