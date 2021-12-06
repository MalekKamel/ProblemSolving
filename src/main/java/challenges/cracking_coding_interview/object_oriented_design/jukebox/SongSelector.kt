package challenges.cracking_coding_interview.object_oriented_design.jukebox

class SongSelector(var currentSong: Song) {
    fun setSong(s: Song) {
        currentSong = s
    }
}