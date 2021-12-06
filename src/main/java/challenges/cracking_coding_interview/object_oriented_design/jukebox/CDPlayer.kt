package challenges.cracking_coding_interview.object_oriented_design.jukebox

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