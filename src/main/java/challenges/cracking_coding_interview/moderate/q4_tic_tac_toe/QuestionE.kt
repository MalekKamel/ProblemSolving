package challenges.cracking_coding_interview.moderate.q4_tic_tac_toe

import challenges.util.AssortedMethods

object QuestionE {
    fun hasWon(board: Array<Array<Piece>>): Piece {
        val size = board.size
        if (board[0].size != size) return Piece.Empty
        var first: Piece

        /* Check rows. */
        for (i in 0 until size) {
            first = board[i][0]
            if (first == Piece.Empty) continue
            for (j in 1 until size) {
                if (board[i][j] != first) {
                    break
                } else if (j == size - 1) {
                    return first
                }
            }
        }

        /* Check columns. */
        for (i in 0 until size) {
            first = board[0][i]
            if (first == Piece.Empty) continue
            for (j in 1 until size) {
                if (board[j][i] != first) {
                    break
                } else if (j == size - 1) {
                    return first
                }
            }
        }

        /* Check diagonals. */
        first = board[0][0]
        if (first != Piece.Empty) {
            for (i in 1 until size) {
                if (board[i][i] != first) {
                    break
                } else if (i == size - 1) {
                    return first
                }
            }
        }
        first = board[0][size - 1]
        if (first != Piece.Empty) {
            for (i in 1 until size) {
                if (board[i][size - i - 1] != first) {
                    break
                } else if (i == size - 1) {
                    return first
                }
            }
        }
        return Piece.Empty
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