package challenges.cracking_coding_interview.moderate.q4_tic_tac_toe

import challenges.util.AssortedMethods

object QuestionD {
    fun hasWon(board: Array<Array<Piece>>): Piece {
        if (board[0][0] != Piece.Empty &&
            (hasWinner(board[0][0], board[0][1], board[0][2]) ||
                    hasWinner(board[0][0], board[1][0], board[2][0]))
        ) {
            return board[0][0]
        }
        if (board[2][2] != Piece.Empty &&
            (hasWinner(board[2][0], board[2][1], board[2][2]) ||
                    hasWinner(board[0][2], board[1][2], board[2][2]))
        ) {
            return board[2][2]
        }
        return if (board[1][1] != Piece.Empty &&
            (hasWinner(
                board[0][0],
                board[1][1],
                board[2][2]
            ) ||
                    hasWinner(
                        board[0][2],
                        board[1][1],
                        board[2][0]
                    ) ||
                    hasWinner(
                        board[1][0],
                        board[1][1],
                        board[1][2]
                    ) ||
                    hasWinner(
                        board[0][1],
                        board[1][1],
                        board[2][1]
                    ))
        ) {
            board[1][1]
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