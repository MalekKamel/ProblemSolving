package challenges.cracking_coding_interview.object_oriented_design.minesweeper

import java.util.LinkedList
import challenges.cracking_coding_interview.object_oriented_design.minesweeper.UserPlay
import challenges.cracking_coding_interview.object_oriented_design.minesweeper.UserPlayResult
import challenges.cracking_coding_interview.object_oriented_design.minesweeper.Game.GameState
import kotlin.jvm.JvmStatic
import java.lang.NumberFormatException

class Cell(var row: Int, var column: Int) {
    private var isBomb = false
    private var number = 0
    var isExposed = false
        private set
    var isGuess = false
        private set

    fun setRowAndColumn(r: Int, c: Int) {
        row = r
        column = c
    }

    fun setBomb(bomb: Boolean) {
        isBomb = bomb
        number = -1
    }

    fun incrementNumber() {
        number++
    }

    fun isBomb(): Boolean {
        return isBomb
    }

    val isBlank: Boolean
        get() = number == 0

    fun flip(): Boolean {
        isExposed = true
        return !isBomb
    }

    fun toggleGuess(): Boolean {
        if (!isExposed) {
            isGuess = !isGuess
        }
        return isGuess
    }

    override fun toString(): String {
        return undersideState
    }

    val surfaceState: String
        get() = if (isExposed) {
            undersideState
        } else if (isGuess) {
            "B "
        } else {
            "? "
        }
    val undersideState: String
        get() = if (isBomb) {
            "* "
        } else if (number > 0) {
            Integer.toString(number) + " "
        } else {
            "  "
        }
}