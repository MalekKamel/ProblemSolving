package challenges.cracking_coding_interview.object_oriented_design.minesweeper

import java.util.*

class Game(private val rows: Int, private val columns: Int, private val bombs: Int) {
    enum class GameState {
        WON, LOST, RUNNING
    }

    private var board: Board? = null
    private var state: GameState
    fun initialize(): Boolean {
        return if (board == null) {
            board = Board(rows, columns, bombs)
            board!!.printBoard(true)
            true
        } else {
            println("Game has already been initialized.")
            false
        }
    }

    fun start(): Boolean {
        if (board == null) {
            initialize()
        }
        return playGame()
    }

    fun printGameState() {
        if (state == GameState.LOST) {
            board!!.printBoard(true)
            println("FAIL")
        } else if (state == GameState.WON) {
            board!!.printBoard(true)
            println("WIN")
        } else {
            println("Number remaining: " + board!!.numRemaining)
            board!!.printBoard(false)
        }
    }

    private fun playGame(): Boolean {
        val scanner = Scanner(System.`in`)
        printGameState()
        while (state == GameState.RUNNING) {
            val input = scanner.nextLine()
            if (input == "exit") {
                scanner.close()
                return false
            }
            val play: UserPlay = UserPlay.Companion.fromString(input) ?: continue
            val result = board!!.playFlip(play)
            if (result!!.successfulMove()) {
                state = result.resultingState
            } else {
                println("Could not flip cell (" + play.row + "," + play.column + ").")
            }
            printGameState()
        }
        scanner.close()
        return true
    }

    init {
        state = GameState.RUNNING
    }
}