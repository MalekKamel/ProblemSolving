package challenges.cracking_coding_interview.object_oriented_design.othello

import challenges.util.AssortedMethods.randomIntInRange

/* A helper class to automate this game. This is just used for testing purposes. */
object Automator {
    private lateinit var players: Array<Player?>
    private var lastPlayer: Player? = null
    var remainingMoves = ArrayList<Location>()
    fun initialize(ps: Array<Player?>) {
        players = ps
        lastPlayer = players[1]
    }

    fun shuffle() {
        for (i in remainingMoves.indices) {
            val t = randomIntInRange(i, remainingMoves.size - 1)
            val other = remainingMoves[t]
            val current = remainingMoves[i]
            remainingMoves[t] = current
            remainingMoves[i] = other
        }
    }

    fun removeLocation(r: Int, c: Int) {
        for (i in remainingMoves.indices) {
            val loc = remainingMoves[i]
            if (loc.isSameAs(r, c)) {
                remainingMoves.removeAt(i)
            }
        }
    }

    fun getLocation(index: Int): Location {
        return remainingMoves[index]
    }

    fun playRandom(): Boolean {
        val board: Board = Game.board
        shuffle()
        lastPlayer = if (lastPlayer === players[0]) players[1] else players[0]
        val color = lastPlayer!!.color.toString()
        for (i in remainingMoves.indices) {
            val loc = remainingMoves[i]
            val success = lastPlayer!!.playPiece(loc.row, loc.column)
            if (success) {
                println("Success: " + color + " move at (" + loc.row + ", " + loc.column + ")")
                board.printBoard()
                printScores()
                return true
            }
        }
        println("Game over. No moves found for $color")
        return false
    }

    val isOver: Boolean
        get() = players[0]!!.score == 0 || players[1]!!.score == 0

    fun printScores() {
        println(
            "Score: " + players[0]!!.color.toString() + ": " + players[0]!!.score + ", " + players[1]!!.color
                .toString() + ": " + players[1]!!.score
        )
    }

    init {
        for (i in 0..9) {
            for (j in 0..9) {
                val loc = Location(i, j)
                remainingMoves.add(loc)
            }
        }
    }
}