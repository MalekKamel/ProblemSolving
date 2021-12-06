package challenges.cracking_coding_interview.object_oriented_design.othello

object Question {
    @JvmStatic
    fun main(args: Array<String>) {
        Game.board.initialize()
        Game.board.printBoard()
        while (!Automator.isOver && Automator.playRandom()) {
        }
        Automator.printScores()
    }
}