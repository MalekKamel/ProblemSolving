package challenges.cracking_coding_interview.object_oriented_design.jigsaw

import java.util.*

/**
 * Jigsaw: Implement an NxN jigsaw puzzle. Design the data structures and explain an algorithm to solve the puzzle.
 * You can assume that you have a fitsWith method which, when passed two puzzle edges,
 * returns true if the two edges belong together.
 */
object Question {
    fun createRandomEdge(code: String): Edge {
        val random = Random()
        var type = Shape.INNER
        if (random.nextBoolean()) {
            type = Shape.OUTER
        }
        return Edge(type, code)
    }

    fun createEdges(
        puzzle: Array<Array<Piece?>>,
        column: Int,
        row: Int
    ): Array<Edge?> {
        val key = "$column:$row:"
        /* Get left edge */
        val left =
            if (column == 0) Edge(
                Shape.FLAT,
                key + "h|e"
            ) else puzzle[row][column - 1]!!
                .getEdgeWithOrientation(Orientation.RIGHT)
                ._createMatchingEdge()

        /* Get top edge */
        val top =
            if (row == 0) Edge(
                Shape.FLAT,
                key + "v|e"
            ) else puzzle[row - 1][column]!!
                .getEdgeWithOrientation(Orientation.BOTTOM)
                ._createMatchingEdge()

        /* Get right edge */
        val right =
            if (column == puzzle[row].size - 1) Edge(
                Shape.FLAT,
                key + "h|e"
            ) else createRandomEdge(key + "h")

        /* Get bottom edge */
        val bottom =
            if (row == puzzle.size - 1) Edge(
                Shape.FLAT,
                key + "v|e"
            ) else createRandomEdge(key + "v")
        return arrayOf(left, top, right, bottom)
    }

    fun initializePuzzle(size: Int): LinkedList<Piece?> {
        /* Create completed puzzle. */
        val puzzle = Array(size) { arrayOfNulls<Piece>(size) }
        for (row in 0 until size) {
            for (column in 0 until size) {
                val edges = createEdges(puzzle, column, row)
                puzzle[row][column] = Piece(edges)
            }
        }

        /* Shuffle and rotate pieces. */
        val pieces = LinkedList<Piece?>()
        val r = Random()
        for (row in 0 until size) {
            for (column in 0 until size) {
                val rotations = r.nextInt(4)
                val piece = puzzle[row][column]
                piece!!.rotateEdgesBy(rotations)
                val index = if (pieces.size == 0) 0 else r.nextInt(pieces.size)
                pieces.add(index, piece)
            }
        }
        return pieces
    }

    fun solutionToString(solution: Array<Array<Piece?>>?): String {
        if (solution == null) return ""
        val sb = StringBuilder()
        for (h in solution.indices) {
            for (w in 0 until solution[h].size) {
                val p = solution[h][w]
                if (p == null) {
                    sb.append("null")
                } else {
                    sb.append(p.toString())
                }
            }
            sb.append("\n")
        }
        return sb.toString()
    }

    /* Used for testing. Check if puzzle is solved. */
    fun validate(solution: Array<Array<Piece?>>?): Boolean {
        if (solution == null) return false
        for (r in solution.indices) {
            for (c in 0 until solution[r]!!.size) {
                val piece = solution[r]!![c] ?: return false
                if (c > 0) { /* match left */
                    val left = solution[r]!![c - 1]
                    if (!left!!.getEdgeWithOrientation(Orientation.RIGHT).fitsWith(
                            piece.getEdgeWithOrientation(
                                Orientation.LEFT
                            )
                        )
                    ) {
                        return false
                    }
                }
                if (c < solution[r]!!.size - 1) { /* match right */
                    val right = solution[r][c + 1]
                    if (!right!!.getEdgeWithOrientation(Orientation.LEFT).fitsWith(
                            piece.getEdgeWithOrientation(
                                Orientation.RIGHT
                            )
                        )
                    ) {
                        return false
                    }
                }
                if (r > 0) { /* match top */
                    val top = solution[r - 1][c]
                    if (!top!!.getEdgeWithOrientation(Orientation.BOTTOM).fitsWith(
                            piece.getEdgeWithOrientation(
                                Orientation.TOP
                            )
                        )
                    ) {
                        return false
                    }
                }
                if (r < solution.size - 1) { /* match bottom */
                    val bottom = solution[r + 1][c]
                    if (!bottom!!.getEdgeWithOrientation(Orientation.TOP).fitsWith(
                            piece.getEdgeWithOrientation(
                                Orientation.BOTTOM
                            )
                        )
                    ) {
                        return false
                    }
                }
            }
        }
        return true
    }

    fun testSize(size: Int): Boolean {
        val pieces = initializePuzzle(size)
        val puzzle = Puzzle(size, pieces)
        puzzle.solve()
        val solution = puzzle.currentSolution
        println(solutionToString(solution))
        val result = validate(solution)
        println(result)
        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        for (size in 1..9) {
            if (!testSize(size)) {
                println("ERROR: $size")
            }
        }
    }
}