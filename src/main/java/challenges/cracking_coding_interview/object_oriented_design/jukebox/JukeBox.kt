package challenges.cracking_coding_interview.object_oriented_design.jukebox

import challenges.cracking_coding_interview.object_oriented_design.jukebox.Playlist
import challenges.cracking_coding_interview.object_oriented_design.jukebox.CD
import challenges.cracking_coding_interview.object_oriented_design.jukebox.Song
import challenges.cracking_coding_interview.object_oriented_design.jukebox.CDPlayer
import challenges.cracking_coding_interview.object_oriented_design.jukebox.SongSelector

class JukeBox(
    private val cdPlayer: CDPlayer, private var user: User, private val cdCollection: Set<CD>,
    private val ts: SongSelector
) {
    val currentSong: Song?
        get() = ts.currentSong

    fun setUser(u: User) {
        user = u
    }
}