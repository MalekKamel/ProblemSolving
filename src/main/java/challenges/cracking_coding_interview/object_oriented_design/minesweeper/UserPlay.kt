package challenges.cracking_coding_interview.object_oriented_design.minesweeper

import java.util.LinkedList
import challenges.cracking_coding_interview.object_oriented_design.minesweeper.UserPlay
import challenges.cracking_coding_interview.object_oriented_design.minesweeper.UserPlayResult
import challenges.cracking_coding_interview.object_oriented_design.minesweeper.Game.GameState
import kotlin.jvm.JvmStatic
import java.lang.NumberFormatException

class UserPlay private constructor(r: Int, c: Int, guess: Boolean) {
    var row = 0
    var column = 0
    val isGuess: Boolean
    val isMove: Boolean
        get() = !isMove

    companion object {
        fun fromString(input: String): UserPlay? {
            var input = input
            var isGuess = false
            if (input.length > 0 && input[0] == 'B') {
                isGuess = true
                input = input.substring(1)
            }
            if (!input.matches("\\d* \\d+".toRegex())) {
                return null
            }
            val parts = input.split(" ".toRegex()).toTypedArray()
            return try {
                val r = parts[0].toInt()
                val c = parts[1].toInt()
                UserPlay(r, c, isGuess)
            } catch (e: NumberFormatException) {
                null
            }
        }
    }

    init {
        row = r
        column = c
        isGuess = guess
    }
}