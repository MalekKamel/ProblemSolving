package challenges.cracking_coding_interview.moderate.q4_tic_tac_toe

object QuestionA {
    fun convertBoardToInt(board: Array<Array<Piece>>): Int {
        var sum = 0
        for (i in board.indices) {
            for (j in board[i].indices) {
                val value = board[i][j].ordinal
                sum = sum * 3 + value
            }
        }
        return sum
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val board = arrayOf(
            arrayOf(Piece.Empty, Piece.Empty, Piece.Empty),
            arrayOf(Piece.Empty, Piece.Empty, Piece.Empty),
            arrayOf(Piece.Blue, Piece.Blue, Piece.Blue)
        )
        val v = convertBoardToInt(board)
        println(v)
    }
}