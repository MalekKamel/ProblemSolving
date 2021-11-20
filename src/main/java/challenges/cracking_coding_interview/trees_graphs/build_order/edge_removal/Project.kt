package challenges.cracking_coding_interview.trees_graphs.build_order.edge_removal


class Project(val name: String) {
    val children = ArrayList<Project>()
    private val map = HashMap<String, Project>()
    var numberDependencies = 0
        private set

    fun addNeighbor(node: Project) {
        if (map.containsKey(node.name)) return

        children.add(node)
        map[node.name] = node
        node.incrementDependencies()
    }

    private fun incrementDependencies() {
        numberDependencies++
    }

    fun decrementDependencies() {
        numberDependencies--
    }

}