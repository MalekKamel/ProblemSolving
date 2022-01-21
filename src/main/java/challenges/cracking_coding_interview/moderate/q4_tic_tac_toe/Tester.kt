package challenges.cracking_coding_interview.moderate.q4_tic_tac_toe

import challenges.util.AssortedMethods.printMatrix
import challenges.util.AssortedMethods.randomMatrix

object Tester {
    fun convertIntToPiece(i: Int): Piece {
        return if (i == 1) {
            Piece.Blue
        } else if (i == 2) {
            Piece.Red
        } else {
            Piece.Empty
        }
    }

    fun hasWonB(board: Array<Array<Piece?>>): Piece? {
        for (i in board.indices) {
            for (j in board[0].indices) {
                val winner = QuestionB.hasWon(board, i, j)
                if (winner != Piece.Empty) {
                    return winner
                }
            }
        }
        return Piece.Empty
    }

    @JvmStatic
    fun main(args: Array<String>) {
        for (k in 0..99) {
            val N = 3
            val boardT = randomMatrix(N, N, 0, 2)
            val board = Array(N) { arrayOfNulls<Piece>(N) }
            for (i in 0 until N) {
                for (j in 0 until N) {
                    val x = boardT[i][j]
                    board[i][j] = convertIntToPiece(x)
                }
            }
            //AssortedMethods.printMatrix(board_t);
            val p1 = hasWonB(board)
            val p2 = QuestionC.hasWon(board)
            val p3 = QuestionD.hasWon(board)
            val p4 = QuestionE.hasWon(board)
            val p5 = QuestionF.hasWon(board)
            val p6 = QuestionG.hasWon(board)
            val p7 = QuestionH.hasWon(board)
            //System.out.println(p + " " + p2);
            if (p1 != p2 || p2 != p3 || p3 != p4 || p4 != p5 || p5 != p6 || p6 != p7) {
                println(p1.toString() + " " + p2 + " " + p3 + " " + p4 + " " + p5 + " " + p6 + " " + p7)
                printMatrix(boardT)
            }
        }
        for (k in 0..99) {
            val N = 4
            val board_t = randomMatrix(N, N, 0, 2)
            val board = Array(N) { arrayOfNulls<Piece>(N) }
            for (i in 0 until N) {
                for (j in 0 until N) {
                    val x = board_t[i][j]
                    board[i][j] = convertIntToPiece(x)
                }
            }
            //AssortedMethods.printMatrix(board_t);
            val p3 = hasWonB(board)
            val p4 = QuestionE.hasWon(board)
            val p5 = QuestionF.hasWon(board)
            val p6 = QuestionG.hasWon(board)
            val p7 = QuestionH.hasWon(board)
            //System.out.println(p + " " + p2);
            if (p3 != p4 || p4 != p5 || p5 != p6 || p6 != p7) {
                println(p3.toString() + " " + p4 + " " + p5 + " " + p6 + " " + p7)
                printMatrix(board_t)
            }
        }
    }
}