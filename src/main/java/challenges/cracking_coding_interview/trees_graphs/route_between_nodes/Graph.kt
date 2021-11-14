package challenges.cracking_coding_interview.trees_graphs.route_between_nodes

class Graph {
    private val vertices: Array<Node?> = arrayOfNulls(MAX_VERTICES)
    var count: Int = 0

    fun addNode(x: Node?) {
        if (count < vertices.size) {
            vertices[count] = x
            count++
        } else {
            print("Graph full")
        }
    }

    val nodes: Array<Node?>
        get() = vertices

    companion object {
        var MAX_VERTICES = 6
    }

}