package challenges.cracking_coding_interview.trees_graphs.route_between_nodes

import java.util.*


/**
 * Given a directed graph, design an algorithm to find out
 * whether there is a route between two nodes.
 */


object Question {

    @JvmStatic
    fun main(a: Array<String>) {
        val g = createNewGraph()
        val n: Array<Node?> = g.nodes
        val start = n[3]
        val end = n[5]
        println(search(g, start, end))
    }

    private fun createNewGraph(): Graph {
        val g = Graph()
        val temp = arrayOfNulls<Node>(6)
        temp[0] = Node("a", 3)
        temp[1] = Node("b", 0)
        temp[2] = Node("c", 0)
        temp[3] = Node("d", 1)
        temp[4] = Node("e", 1)
        temp[5] = Node("f", 0)
        temp[0]!!.addAdjacent(temp[1])
        temp[0]!!.addAdjacent(temp[2])
        temp[0]!!.addAdjacent(temp[3])
        temp[3]!!.addAdjacent(temp[4])
        temp[4]!!.addAdjacent(temp[5])
        for (i in 0..5) {
            g.addNode(temp[i])
        }
        return g
    }

    private fun search(g: Graph, start: Node?, end: Node?): Boolean {
        val q = LinkedList<Node>()

        for (u in g.nodes) {
            u!!.state = State.Unvisited
        }

        start!!.state = State.Visiting
        q.add(start)

        var u: Node?
        while (!q.isEmpty()) {
            u = q.removeFirst()
            val isFound = search(u, end, q)
            if (isFound) return true
        }
        return false
    }

    private fun search(_u: Node?, end: Node?, q: LinkedList<Node>): Boolean {
        val u = _u ?: return false

        for (v in u.adjacent) {
            when (v!!.state) {
                State.Visiting, State.Visited -> return false
                State.Unvisited -> {
                    if (v == end) return true

                    v.state = State.Visiting
                    q.add(v)
                }
            }
        }
        u.state = State.Visited
        return false
    }

    enum class State {
        Unvisited,
        Visiting,
        Visited
    }
}