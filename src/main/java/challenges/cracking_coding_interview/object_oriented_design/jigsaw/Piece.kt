package challenges.cracking_coding_interview.object_oriented_design.jigsaw

class Piece(edgeList: Array<Edge?>) {
    private var edges: HashMap<Orientation, Edge> = HashMap<Orientation, Edge>()

    /* Set this edge in the appropriate orientation, rotating the piece as necessary. */
    fun setEdgeAsOrientation(edge: Edge?, orientation: Orientation?) {
        val currentOrientation = getOrientation(edge)
        rotateEdgesBy(orientation!!.ordinal - currentOrientation!!.ordinal)
    }

    /* Return the current orientation of the edge. */
    private fun getOrientation(edge: Edge?): Orientation? {
        for ((key, value) in edges) {
            if (value === edge) {
                return key
            }
        }
        return null
    }

    /* Rotate edges by "numberRotations". */
    fun rotateEdgesBy(numberRotations: Int) {
        var numberRotations = numberRotations
        val orientations = Orientation.values()
        val rotated: HashMap<Orientation, Edge> = HashMap<Orientation, Edge>()
        numberRotations %= NUMBER_OF_EDGES
        if (numberRotations < 0) numberRotations += NUMBER_OF_EDGES
        for (i in orientations.indices) {
            val oldOrientation = orientations[(i - numberRotations + NUMBER_OF_EDGES) % NUMBER_OF_EDGES]
            val newOrientation = orientations[i]
            rotated[newOrientation] = edges[oldOrientation]!!
        }
        edges = rotated
    }

    /* Check if this piece is a corner piece. */
    val isCorner: Boolean
        get() {
            val orientations = Orientation.values()
            for (i in orientations.indices) {
                val current: Shape = edges[orientations[i]]!!.shape
                val next: Shape = edges[orientations[(i + 1) % NUMBER_OF_EDGES]]!!.shape
                if (current === Shape.FLAT && next === Shape.FLAT) {
                    return true
                }
            }
            return false
        }

    /* Check if this piece has a border edge. */
    val isBorder: Boolean
        get() {
            val orientations = Orientation.values()
            for (i in orientations.indices) {
                if (edges[orientations[i]]?.shape === Shape.FLAT) {
                    return true
                }
            }
            return false
        }

    /* Get edge at this orientation. */
    fun getEdgeWithOrientation(orientation: Orientation): Edge {
        return edges[orientation]!!
    }

    /* Return the edge that matches targetEdge. Returns null if there is no match. */
    fun getMatchingEdge(targetEdge: Edge): Edge? {
        for (e in edges.values) {
            if (targetEdge.fitsWith(e)) {
                return e
            }
        }
        return null
    }

    override fun toString(): String {
        val sb = StringBuilder()
        val orientations = Orientation.values()
        for (o in orientations) {
            sb.append(edges[o].toString().toString() + ",")
        }
        return "[$sb]"
    }

    companion object {
        private const val NUMBER_OF_EDGES = 4
    }

    init {
        val orientations = Orientation.values()
        for (i in edgeList.indices) {
            val edge: Edge = edgeList[i]!!
            edge.parentPiece = this
            edges[orientations[i]] = edge
        }
    }
}