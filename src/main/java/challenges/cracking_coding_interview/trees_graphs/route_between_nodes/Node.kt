package challenges.cracking_coding_interview.trees_graphs.route_between_nodes

class Node(val vertex: String, adjacentLength: Int) {
    val adjacent: Array<Node?> = arrayOfNulls(adjacentLength)
    var adjacentCount = 0
    var state: Question.State = Question.State.Unvisited

    fun addAdjacent(x: Node?) {
        if (adjacentCount >= adjacent.size) {
            return
        }
        adjacent[adjacentCount] = x
        adjacentCount++
    }
}