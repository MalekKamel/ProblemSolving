package challenges.cracking_coding_interview.object_oriented_design.othello

class Location(val row: Int, val column: Int) {
    fun isSameAs(r: Int, c: Int): Boolean {
        return row == r && column == c
    }
}