package challenges.cracking_coding_interview.object_oriented_design.jukebox

import challenges.cracking_coding_interview.object_oriented_design.jukebox.Playlist
import challenges.cracking_coding_interview.object_oriented_design.jukebox.CD
import challenges.cracking_coding_interview.object_oriented_design.jukebox.Song
import challenges.cracking_coding_interview.object_oriented_design.jukebox.CDPlayer
import challenges.cracking_coding_interview.object_oriented_design.jukebox.SongSelector

class CDPlayer {
    var playlist: Playlist? = null
    var cD: CD? = null

    constructor(p: Playlist?) {
        playlist = p
    }

    constructor(c: CD?, p: Playlist?) {
        playlist = p
        cD = c
    }

    constructor(c: CD?) {
        cD = c
    }

    fun playSong(s: Song?) {}
}