package challenges.cracking_coding_interview.trees_graphs.build_order.dfs


class Project(val name: String) {
    enum class State {
        COMPLETE, PARTIAL, BLANK
    }

    val children = ArrayList<Project>()
    private val map = HashMap<String, Project>()
    var state = State.BLANK

    fun addNeighbor(node: Project) {
        if (!map.containsKey(node.name)) {
            children.add(node)
            map[node.name] = node
        }
    }
}