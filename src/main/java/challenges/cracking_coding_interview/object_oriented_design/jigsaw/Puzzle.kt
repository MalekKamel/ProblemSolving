package challenges.cracking_coding_interview.object_oriented_design.jigsaw

import java.util.*

class Puzzle(private val size: Int, /* Remaining pieces left to put away. */private val pieces: LinkedList<Piece?>) {
    var currentSolution: Array<Array<Piece?>>? = null
        private set

    /* Group pieces into border pieces (including corners) and inside pieces. */
    fun groupPieces(
        cornerPieces: LinkedList<Piece?>,
        borderPieces: LinkedList<Piece?>,
        insidePieces: LinkedList<Piece?>
    ) {
        for (p in pieces) {
            if (p!!.isCorner) {
                cornerPieces.add(p)
            } else if (p.isBorder) {
                borderPieces.add(p)
            } else {
                insidePieces.add(p)
            }
        }
    }

    /* Orient this corner piece so that its flat edges are on the top and left. */
    fun orientTopLeftCorner(piece: Piece?) {
        if (!piece!!.isCorner) return
        val orientations = Orientation.values()
        for (i in orientations.indices) {
            val current: Edge? = piece.getEdgeWithOrientation(
                orientations[i]
            )
            val next: Edge? = piece.getEdgeWithOrientation(
                orientations[(i + 1) % orientations.size]
            )
            if (current!!.shape === Shape.FLAT && next!!.shape === Shape.FLAT) {
                piece.setEdgeAsOrientation(current, Orientation.LEFT)
                return
            }
        }
    }

    /* Bounds check. Check if this index is on a border (0 or size - 1) */
    fun isBorderIndex(location: Int): Boolean {
        return location == 0 || location == size - 1
    }

    /* Given a list of pieces, check if any have an edge that matches this piece. Return the edge*/
    private fun getMatchingEdge(targetEdge: Edge, pieces: LinkedList<Piece?>): Edge? {
        for (piece in pieces) {
            val matchingEdge: Edge? = piece!!.getMatchingEdge(targetEdge)
            if (matchingEdge != null) {
                return matchingEdge
            }
        }
        return null
    }

    /* Put the edge/piece into the solution, turn it appropriately, and remove from list. */
    private fun setEdgeInSolution(
        pieces: LinkedList<Piece?>,
        edge: Edge,
        row: Int,
        column: Int,
        orientation: Orientation?
    ) {
        val piece = edge.parentPiece
        piece!!.setEdgeAsOrientation(edge, orientation)
        pieces.remove(piece)
        currentSolution!![row][column] = piece
    }

    /* Return the list where a piece with this index would be found. */
    private fun getPieceListToSearch(
        cornerPieces: LinkedList<Piece?>,
        borderPieces: LinkedList<Piece?>,
        insidePieces: LinkedList<Piece?>,
        row: Int,
        column: Int
    ): LinkedList<Piece?> {
        return if (isBorderIndex(row) && isBorderIndex(column)) {
            cornerPieces
        } else if (isBorderIndex(row) || isBorderIndex(column)) {
            borderPieces
        } else {
            insidePieces
        }
    }

    /* Find the matching piece within piecesToSearch and insert it at row, column. */
    private fun fitNextEdge(piecesToSearch: LinkedList<Piece?>, row: Int, column: Int): Boolean {
        if (row == 0 && column == 0) {
            val p = piecesToSearch.remove()
            orientTopLeftCorner(p)
            currentSolution!![0][0] = p
        } else {
            /* Get the right edge and list to match. */
            val pieceToMatch = if (column == 0) currentSolution!![row - 1][0] else currentSolution!![row][column - 1]
            val orientationToMatch = if (column == 0) Orientation.BOTTOM else Orientation.RIGHT
            val edgeToMatch: Edge = pieceToMatch!!.getEdgeWithOrientation(orientationToMatch)

            /* Get matching edge. */
            val edge = getMatchingEdge(edgeToMatch, piecesToSearch) ?: return false
            // Can't solve
            val orientation = orientationToMatch.opposite
            setEdgeInSolution(piecesToSearch, edge, row, column, orientation)
        }
        return true
    }

    fun solve(): Boolean {
        /* Group pieces. */
        val cornerPieces = LinkedList<Piece?>()
        val borderPieces = LinkedList<Piece?>()
        val insidePieces = LinkedList<Piece?>()
        groupPieces(cornerPieces, borderPieces, insidePieces)

        /* Walk through puzzle, finding the piece that joins the previous one. */currentSolution = Array(
            size
        ) { arrayOfNulls(size) }
        for (row in 0 until size) {
            for (column in 0 until size) {
                val piecesToSearch = getPieceListToSearch(cornerPieces, borderPieces, insidePieces, row, column)
                if (!fitNextEdge(piecesToSearch, row, column)) {
                    return false
                }
            }
        }
        return true
    }
}