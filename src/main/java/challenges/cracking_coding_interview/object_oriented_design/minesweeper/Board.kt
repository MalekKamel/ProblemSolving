package challenges.cracking_coding_interview.object_oriented_design.minesweeper

import challenges.cracking_coding_interview.object_oriented_design.minesweeper.Game.GameState
import java.util.*

class Board(private val nRows: Int, private val nColumns: Int, b: Int) {
    private var nBombs = 0
    private lateinit var cells: Array<Array<Cell?>>
    private lateinit var bombs: Array<Cell?>
    var numRemaining: Int
        private set

    private fun initializeBoard() {
        cells = Array(nRows) { arrayOfNulls(nColumns) }
        bombs = arrayOfNulls(nBombs)
        for (r in 0 until nRows) {
            for (c in 0 until nColumns) {
                cells[r][c] = Cell(r, c)
            }
        }
        for (i in 0 until nBombs) {
            val r = i / nColumns
            val c = (i - r * nColumns) % nColumns
            bombs[i] = cells[r][c]
            bombs[i]!!.setBomb(true)
        }
    }

    private fun shuffleBoard() {
        val nCells = nRows * nColumns
        val random = Random()
        for (index1 in 0 until nCells) {
            val index2 = index1 + random.nextInt(nCells - index1)
            if (index1 != index2) {
                /* Get cell at index1. */
                val row1 = index1 / nColumns
                val column1 = (index1 - row1 * nColumns) % nColumns
                val cell1 = cells[row1][column1]

                /* Get cell at index2. */
                val row2 = index2 / nColumns
                val column2 = (index2 - row2 * nColumns) % nColumns
                val cell2 = cells[row2][column2]

                /* Swap. */cells[row1][column1] = cell2
                cell2!!.setRowAndColumn(row1, column1)
                cells[row2][column2] = cell1
                cell1!!.setRowAndColumn(row2, column2)
            }
        }
    }

    private fun inBounds(row: Int, column: Int): Boolean {
        return row >= 0 && row < nRows && column >= 0 && column < nColumns
    }

    /* Set the cells around the bombs to the right number. Although 
	 * the bombs have been shuffled, the reference in the bombs array
	 * is still to same object. */
    private fun setNumberedCells() {
        val deltas = arrayOf(
            intArrayOf(-1, -1),
            intArrayOf(-1, 0),
            intArrayOf(-1, 1),
            intArrayOf(0, -1),
            intArrayOf(0, 1),
            intArrayOf(1, -1),
            intArrayOf(1, 0),
            intArrayOf(1, 1)
        )
        for (bomb in bombs) {
            val row = bomb!!.row
            val col = bomb.column
            for (delta in deltas) {
                val r = row + delta[0]
                val c = col + delta[1]
                if (inBounds(r, c)) {
                    cells[r][c]!!.incrementNumber()
                }
            }
        }
    }

    fun printBoard(showUnderside: Boolean) {
        println()
        print("   ")
        for (i in 0 until nColumns) {
            print("$i ")
        }
        println()
        for (i in 0 until nColumns) {
            print("--")
        }
        println()
        for (r in 0 until nRows) {
            print("$r| ")
            for (c in 0 until nColumns) {
                if (showUnderside) {
                    print(cells[r][c]!!.undersideState)
                } else {
                    print(cells[r][c]!!.surfaceState)
                }
            }
            println()
        }
    }

    private fun flipCell(cell: Cell?): Boolean {
        if (!cell!!.isExposed && !cell.isGuess) {
            cell.flip()
            numRemaining--
            return true
        }
        return false
    }

    fun expandBlank(cell: Cell?) {
        val deltas = arrayOf(
            intArrayOf(-1, -1),
            intArrayOf(-1, 0),
            intArrayOf(-1, 1),
            intArrayOf(0, -1),
            intArrayOf(0, 1),
            intArrayOf(1, -1),
            intArrayOf(1, 0),
            intArrayOf(1, 1)
        )
        val toExplore: Queue<Cell?> = LinkedList()
        toExplore.add(cell)
        while (!toExplore.isEmpty()) {
            val current = toExplore.remove()
            for (delta in deltas) {
                val r = current!!.row + delta[0]
                val c = current.column + delta[1]
                if (inBounds(r, c)) {
                    val neighbor = cells[r][c]
                    if (flipCell(neighbor) && neighbor!!.isBlank) {
                        toExplore.add(neighbor)
                    }
                }
            }
        }
    }

    fun playFlip(play: UserPlay): UserPlayResult {
        val cell = getCellAtLocation(play) ?: return UserPlayResult(false, GameState.RUNNING)
        if (play.isGuess) {
            val guessResult = cell.toggleGuess()
            return UserPlayResult(guessResult, GameState.RUNNING)
        }
        val result = flipCell(cell)
        if (cell.isBomb()) {
            return UserPlayResult(result, GameState.LOST)
        }
        if (cell.isBlank) {
            expandBlank(cell)
        }
        return if (numRemaining == 0) {
            UserPlayResult(result, GameState.WON)
        } else UserPlayResult(result, GameState.RUNNING)
    }

    fun getCellAtLocation(play: UserPlay): Cell? {
        val row = play.row
        val col = play.column
        return if (!inBounds(row, col)) {
            null
        } else cells[row][col]
    }

    init {
        nBombs = b
        initializeBoard()
        shuffleBoard()
        setNumberedCells()
        numRemaining = nRows * nColumns - nBombs
    }
}