package challenges.cracking_coding_interview.moderate.q4_tic_tac_toe

import challenges.util.AssortedMethods

object QuestionG {
    fun hasWon(board: Array<Array<Piece?>>): Piece? {
        if (board.size != board[0].size) return Piece.Empty
        val size = board.size

        /* Create list of things to check. */
        val instructions = ArrayList<Check>()
        for (i in board.indices) {
            instructions.add(Check(0, i, 1, 0))
            instructions.add(Check(i, 0, 0, 1))
        }
        instructions.add(Check(0, 0, 1, 1))
        instructions.add(Check(0, size - 1, 1, -1))

        /* Check them. */for (instr in instructions) {
            val winner = hasWon(board, instr)
            if (winner != Piece.Empty) {
                return winner
            }
        }
        return Piece.Empty
    }

    fun hasWon(board: Array<Array<Piece?>>, instr: Check): Piece? {
        val first = board[instr.row][instr.column]
        while (instr.inBounds(board.size)) {
            if (board[instr.row][instr.column] != first) {
                return Piece.Empty
            }
            instr.increment()
        }
        return first
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val N = 3
        val boardT: Array<IntArray> = AssortedMethods.randomMatrix(N, N, 0, 2)
        val board = Array(N) { arrayOfNulls<Piece>(N) }
        for (i in 0 until N) {
            for (j in 0 until N) {
                val x = boardT[i][j]
                board[i][j] = Tester.convertIntToPiece(x)
            }
        }
        val p1 = hasWon(board)
        println(p1)
        AssortedMethods.printMatrix(boardT)
    }

    class Check(var row: Int, var column: Int, private val rowIncrement: Int, private val columnIncrement: Int) {
        fun increment() {
            row += rowIncrement
            column += columnIncrement
        }

        fun inBounds(size: Int): Boolean {
            return row >= 0 && column >= 0 && row < size && column < size
        }
    }
}