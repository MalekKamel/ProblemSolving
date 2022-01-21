package challenges.cracking_coding_interview.moderate.q4_tic_tac_toe

import challenges.util.AssortedMethods

object QuestionC {

    fun hasWon(board: Array<Array<Piece>>): Piece {
        for (i in board.indices) {
            /* Check Rows */
            if (hasWinner(board[i][0], board[i][1], board[i][2])) {
                return board[i][0]
            }

            /* Check Columns */
            if (hasWinner(board[0][i], board[1][i], board[2][i])) {
                return board[0][i]
            }
        }

        /* Check Diagonal */if (hasWinner(board[0][0], board[1][1], board[2][2])) {
            return board[0][0]
        }
        return if (hasWinner(board[0][2], board[1][1], board[2][0])) {
            board[0][2]
        } else Piece.Empty
    }

    private fun hasWinner(p1: Piece, p2: Piece, p3: Piece): Boolean {
        return if (p1 == Piece.Empty) {
            false
        } else p1 == p2 && p2 == p3
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val N = 3
        val boardT: Array<IntArray> = AssortedMethods.randomMatrix(N, N, 0, 2)
        val board = Array(N) {
            Array(N) {
                Piece.Empty
            }
        }
        for (i in 0 until N) {
            for (j in 0 until N) {
                val x = boardT[i][j]
                board[i][j] = Piece.from(x)
            }
        }
        val p1 = hasWon(board)
        println(p1)
        AssortedMethods.printMatrix(boardT)
    }
}