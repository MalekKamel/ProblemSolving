package challenges.cracking_coding_interview.object_oriented_design.jukebox

import java.util.*

class Playlist(private val song: Song, private val queue: Queue<Song>) {
    val nextSongToPlay: Song
        get() = queue.peek()

    fun queueUpSong(s: Song) {
        queue.add(s)
    }
}