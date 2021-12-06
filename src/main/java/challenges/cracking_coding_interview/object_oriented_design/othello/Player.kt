package challenges.cracking_coding_interview.object_oriented_design.othello

class Player(val color: Color) {
    val score: Int
        get() = Game.board.getScoreForColor(color)

    fun playPiece(row: Int, column: Int): Boolean {
        return Game.board.placeColor(row, column, color)
    }
}