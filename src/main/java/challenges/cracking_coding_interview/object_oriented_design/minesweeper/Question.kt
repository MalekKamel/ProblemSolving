package challenges.cracking_coding_interview.object_oriented_design.minesweeper

import java.util.LinkedList
import challenges.cracking_coding_interview.object_oriented_design.minesweeper.UserPlay
import challenges.cracking_coding_interview.object_oriented_design.minesweeper.UserPlayResult
import challenges.cracking_coding_interview.object_oriented_design.minesweeper.Game.GameState
import kotlin.jvm.JvmStatic
import java.lang.NumberFormatException

object Question {
    @JvmStatic
    fun main(args: Array<String>) {
        val game = Game(7, 7, 3)
        game.initialize()
        game.start()
    }
}