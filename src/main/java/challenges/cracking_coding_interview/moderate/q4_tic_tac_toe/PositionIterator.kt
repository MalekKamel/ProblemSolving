package challenges.cracking_coding_interview.moderate.q4_tic_tac_toe

class PositionIterator(
    p: Position,
    private val rowIncrement: Int,
    private val colIncrement: Int,
    private val size: Int
) : MutableIterator<Position> {
    private var current: Position

    init {
        current = Position(p.row - rowIncrement, p.column - colIncrement)
    }

    override fun hasNext(): Boolean {
        return current.row + rowIncrement < size && current.column + colIncrement < size
    }

    override fun next(): Position {
        current = Position(current.row + rowIncrement, current.column + colIncrement)
        return current
    }

    override fun remove() {
    }
}