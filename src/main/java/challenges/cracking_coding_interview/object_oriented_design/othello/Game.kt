package challenges.cracking_coding_interview.object_oriented_design.othello

object Game {
    private val players: Array<Player?>
    val board: Board
    private val ROWS = 10
    private val COLUMNS = 10


    init {
        board = Board(ROWS, COLUMNS)
        players = arrayOfNulls(2)
        players[0] = Player(Color.Black)
        players[1] = Player(Color.White)
        Automator.initialize(players) // used for testing
    }
}