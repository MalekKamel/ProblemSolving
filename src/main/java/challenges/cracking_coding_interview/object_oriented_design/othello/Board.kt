package challenges.cracking_coding_interview.object_oriented_design.othello

class Board(rows: Int, columns: Int) {
    private var blackCount = 0
    private var whiteCount = 0
    private val board: Array<Array<Piece?>>
    fun initialize() {
        /* initial board has a grid like the following in the center:
		 *     WB
		 *     BW
		 */
        val middleRow = board.size / 2
        val middleColumn: Int = board[middleRow].size / 2
        board[middleRow][middleColumn] = Piece(Color.White)
        board[middleRow + 1][middleColumn] = Piece(Color.Black)
        board[middleRow + 1][middleColumn + 1] = Piece(Color.White)
        board[middleRow][middleColumn + 1] = Piece(Color.Black)
        blackCount = 2
        whiteCount = 2
    }

    fun placeColor(row: Int, column: Int, color: Color): Boolean {
        if (board[row][column] != null) {
            return false
        }

        /* attempt to flip each of the four directions */
        val results = IntArray(4)
        results[0] = flipSection(row - 1, column, color, Direction.up)
        results[1] = flipSection(row + 1, column, color, Direction.down)
        results[2] = flipSection(row, column + 1, color, Direction.right)
        results[3] = flipSection(row, column - 1, color, Direction.left)

        /* compute how many pieces were flipped */
        var flipped = 0
        for (result in results) {
            if (result > 0) {
                flipped += result
            }
        }

        /* if nothing was flipped, then it's an invalid move */if (flipped < 0) {
            return false
        }

        /* flip the piece, and update the score */board[row][column] = Piece(color)
        updateScore(color, flipped + 1)
        return true
    }

    private fun flipSection(row: Int, column: Int, color: Color, d: Direction): Int {
        /* Compute the delta for the row and the column. At all times, only the row or the column
		 * will have a delta, since we're only moving in one direction at a time.
		 */
        var r = 0
        var c = 0
        when (d) {
            Direction.up -> r = -1
            Direction.down -> r = 1
            Direction.left -> c = -1
            Direction.right -> c = 1
        }

        /* If out of bounds, or nothing to flip, return an error (-1) */if (row < 0 || row >= board.size || column < 0 || column >= board[row].size || board[row][column] == null) {
            return -1
        }

        /* Found same color - return nothing flipped */if (board[row][column]!!.color == color) {
            return 0
        }

        /* Recursively flip the remainder of the row. If -1 is returned, then we know we hit the boundary
		 * of the row (or a null piece) before we found our own color, so there's nothing to flip. Return
		 * the error code.
		 */
        val flipped = flipSection(row + r, column + c, color, d)
        if (flipped < 0) {
            return -1
        }

        /* flip our own color */board[row][column]!!.flip()
        return flipped + 1
    }

    fun getScoreForColor(c: Color): Int {
        return if (c == Color.Black) {
            blackCount
        } else {
            whiteCount
        }
    }

    fun updateScore(newColor: Color, newPieces: Int) {
        /* If we added x pieces of a color, then we actually removed x - 1 pieces of the other
		 * color. The -1 is because one of the new pieces was the just-placed one.
		 */
        if (newColor == Color.Black) {
            whiteCount -= newPieces - 1
            blackCount += newPieces
        } else {
            blackCount -= newPieces - 1
            whiteCount += newPieces
        }
    }

    fun printBoard() {
        for (r in board.indices) {
            for (c in 0 until board[r].size) {
                if (board[r][c] == null) {
                    print("_")
                } else if (board[r][c]!!.color == Color.White) {
                    print("W")
                } else {
                    print("B")
                }
            }
            println()
        }
    }

    init {
        board = Array(rows) { arrayOfNulls(columns) }
    }
}