package challenges.cracking_coding_interview.trees_graphs.build_order.dfs

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
        val start: Project = getOrCreateNode(startName)
        val end: Project = getOrCreateNode(endName)
        start.addNeighbor(end)
    }
}