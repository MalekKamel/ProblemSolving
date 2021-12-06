package challenges.cracking_coding_interview.object_oriented_design.jigsaw

class Edge(shape: Shape, code: String) {
    val shape: Shape
    private val code // used to mock how pieces would fit together.
            : String
    /* Get the parent piece. *//* Set parent piece. */  var parentPiece: Piece? = null
    fun _createMatchingEdge(): Edge? {
        return if (shape === Shape.FLAT) null else Edge(
            shape.opposite!!,
            code
        )
    }

    /* Check if this edge fits into the other one. */
    fun fitsWith(edge: Edge): Boolean {
        return edge.code == code
    }

    override fun toString(): String {
        return code
    }

    init {
        this.shape = shape
        this.code = code
    }
}