package challenges.cracking_coding_interview.moderate.q4_tic_tac_toe

import challenges.util.AssortedMethods

object QuestionB {
    fun hasWon(board: Array<Array<Piece?>>, row: Int, column: Int): Piece? {
        if (board.size != board[0].size) return Piece.Empty
        val piece = board[row][column]
        if (piece == Piece.Empty) return Piece.Empty
        if (hasWonRow(board, row) || hasWonColumn(board, column)) {
            return piece
        }
        if (row == column && hasWonDiagonal(board, 1)) {
            return piece
        }
        return if (row == board.size - column - 1 && hasWonDiagonal(
                board,
                -1
            )
        ) {
            piece
        } else Piece.Empty
    }

    fun hasWonRow(board: Array<Array<Piece?>>, row: Int): Boolean {
        for (c in 1 until board[row].size) {
            if (board[row][c] != board[row][0]) {
                return false
            }
        }
        return true
    }

    fun hasWonColumn(board: Array<Array<Piece?>>, column: Int): Boolean {
        for (r in 1 until board.size) {
            if (board[r][column] != board[0][column]) {
                return false
            }
        }
        return true
    }

    fun hasWonDiagonal(board: Array<Array<Piece?>>, direction: Int): Boolean {
        var row = 0
        var column = if (direction == 1) 0 else board.size - 1
        val first = board[0][column]
        for (i in board.indices) {
            if (board[row][column] != first) {
                return false
            }
            row += 1
            column += direction
        }
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val N = 3
        val boardT: Array<IntArray> = AssortedMethods.randomMatrix(N, N, 0, 2)
        boardT[1][1] = boardT[0][2]
        boardT[2][0] = boardT[0][2]
        val board = Array(N) { arrayOfNulls<Piece>(N) }
        for (i in 0 until N) {
            for (j in 0 until N) {
                val x = boardT[i][j]
                board[i][j] = Tester.convertIntToPiece(x)
            }
        }
        val p1 = hasWon(board, 0, 2)
        println(p1)
        AssortedMethods.printMatrix(boardT)
    }
}