package challenges.cracking_coding_interview.object_oriented_design.minesweeper

import java.util.LinkedList
import challenges.cracking_coding_interview.object_oriented_design.minesweeper.UserPlay
import challenges.cracking_coding_interview.object_oriented_design.minesweeper.UserPlayResult
import challenges.cracking_coding_interview.object_oriented_design.minesweeper.Game.GameState
import kotlin.jvm.JvmStatic
import java.lang.NumberFormatException

class UserPlayResult(private val successful: Boolean, val resultingState: GameState) {
    fun successfulMove(): Boolean {
        return successful
    }
}