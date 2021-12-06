package challenges.cracking_coding_interview.object_oriented_design.minesweeper

import challenges.cracking_coding_interview.object_oriented_design.minesweeper.Game.GameState

class UserPlayResult(private val successful: Boolean, val resultingState: GameState) {
    fun successfulMove(): Boolean {
        return successful
    }
}