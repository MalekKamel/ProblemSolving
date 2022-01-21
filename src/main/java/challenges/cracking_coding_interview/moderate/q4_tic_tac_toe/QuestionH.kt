package challenges.cracking_coding_interview.moderate.q4_tic_tac_toe

import challenges.util.AssortedMethods

object QuestionH {
    fun hasWon(board: Array<Array<Piece?>>): Piece? {
        if (board.size != board[0].size) return Piece.Empty
        val size = board.size
        val instructions = ArrayList<PositionIterator>()
        for (i in board.indices) {
            instructions.add(PositionIterator(Position(0, i), 1, 0, size))
            instructions.add(PositionIterator(Position(i, 0), 0, 1, size))
        }
        instructions.add(PositionIterator(Position(0, 0), 1, 1, size))
        instructions.add(PositionIterator(Position(0, size - 1), 1, -1, size))
        for (iterator in instructions) {
            val winner = hasWon(board, iterator)
            if (winner != Piece.Empty) {
                return winner
            }
        }
        return Piece.Empty
    }

    fun hasWon(board: Array<Array<Piece?>>, iterator: PositionIterator): Piece? {
        val firstPosition = iterator.next()
        val first = board[firstPosition.row][firstPosition.column]
        while (iterator.hasNext()) {
            val position = iterator.next()
            if (board[position.row][position.column] != first) {
                return Piece.Empty
            }
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