package challenges.cracking_coding_interview.object_oriented_design.othello

class Piece(var color: Color) {
    fun flip() {
        color = if (color == Color.Black) {
            Color.White
        } else {
            Color.Black
        }
    }
}