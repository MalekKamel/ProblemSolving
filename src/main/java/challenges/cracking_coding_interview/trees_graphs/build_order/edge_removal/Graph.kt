package challenges.cracking_coding_interview.trees_graphs.build_order.edge_removal

class Graph {
    val nodes: MutableList<Project> = ArrayList()
    private val map: HashMap<String, Project> = HashMap()

    fun getOrCreateNode(name: String): Project {
        if (!map.containsKey(name)) {
            val node = Project(name)
            nodes.add(node)
            map[name] = node
        }
        return map[name]!!
    }

    fun addEdge(startName: String, endName: String) {
        val start = getOrCreateNode(startName)
        val end = getOrCreateNode(endName)
        start.addNeighbor(end)
    }
}