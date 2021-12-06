package challenges.cracking_coding_interview.object_oriented_design.jukebox

import challenges.cracking_coding_interview.object_oriented_design.jukebox.Playlist
import challenges.cracking_coding_interview.object_oriented_design.jukebox.CD
import challenges.cracking_coding_interview.object_oriented_design.jukebox.Song
import challenges.cracking_coding_interview.object_oriented_design.jukebox.CDPlayer
import challenges.cracking_coding_interview.object_oriented_design.jukebox.SongSelector

class User(var name: String, var iD: Long) {
    val user: User
        get() = this

    companion object {
        fun addUser(name: String, iD: Long): User {
            return User(name, iD)
        }
    }
}