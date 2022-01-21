package challenges.cracking_coding_interview.moderate.q4_tic_tac_toe

import challenges.util.AssortedMethods

object QuestionF {
    fun hasWon(board: Array<Array<Piece?>>): Piece? {
        var winner: Piece? = Piece.Empty

        /* Check rows. */for (i in board.indices) {
            winner = hasWon(board, i, 0, 0, 1)
            if (winner != Piece.Empty) {
                return winner
            }
        }

        /* Check columns. */for (i in board[0].indices) {
            winner = hasWon(board, 0, i, 1, 0)
            if (winner != Piece.Empty) {
                return winner
            }
        }

        /* Check top/left -> bottom/right diagonal. */winner = hasWon(board, 0, 0, 1, 1)
        return if (winner != Piece.Empty) {
            winner
        } else hasWon(
            board,
            0,
            board[0].size - 1,
            1,
            -1
        )

        /* Check top/right -> bottom/left diagonal. */
    }

    fun hasWon(board: Array<Array<Piece?>>, row: Int, col: Int, incrementRow: Int, incrementCol: Int): Piece? {
        var row = row
        var col = col
        val first = board[row][col]
        while (row < board.size && col < board[row].size) {
            if (board[row][col] != first) {
                return Piece.Empty
            }
            row += incrementRow
            col += incrementCol
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
}