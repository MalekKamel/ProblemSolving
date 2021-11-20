package challenges.cracking_coding_interview.trees_graphs.build_order.edge_removal

object Question {

    private fun buildOrderWrapper(
        projects: Array<String>,
        dependencies: Array<Array<String>>
    ): Array<String?> {
        val buildOrder = findBuildOrder(projects, dependencies) ?: return emptyArray()
        return convertToStringList(buildOrder)
    }

    private fun findBuildOrder(projects: Array<String>, dependencies: Array<Array<String>>): Array<Project?>? {
        val graph = buildGraph(projects, dependencies)
        return orderProjects(graph.nodes)
    }

    /*
     * Build the graph, adding the edge (a, b) if b is dependent on a.
     * Assumes a pair is listed in “build order”. The pair (a, b) in
     * dependencies indicates that b depends on a and a must be built
     * before b.
     * */
    private fun buildGraph(projects: Array<String>, dependencies: Array<Array<String>>): Graph {
        val graph = Graph()
        for (project in projects) {
            graph.getOrCreateNode(project)
        }
        for (dependency in dependencies) {
            val first = dependency[0]
            val second = dependency[1]
            graph.addEdge(first, second)
        }
        return graph
    }

    private fun orderProjects(projects: List<Project>): Array<Project?>? {
        val order = arrayOfNulls<Project>(projects.size)

        /* Add “roots” to the build order first.*/
        var endOfList = addNonDependent(order, projects, 0)
        var toBeProcessed = 0
        while (toBeProcessed < order.size) {
            /* We have a circular dependency since there are no remaining
             * projects with zero dependencies.
             */
            val current = order[toBeProcessed] ?: return null

            /* Remove myself as a dependency. */
            val children = current.children
            for (child in children) {
                child.decrementDependencies()
            }

            /* Add children that have no one depending on them. */
            endOfList = addNonDependent(order, children, endOfList)
            toBeProcessed++
        }
        return order
    }

    /* A helper function to insert projects with zero dependencies
     * into the order array, starting at index offset.
     * */
    private fun addNonDependent(order: Array<Project?>, projects: List<Project>, _offset: Int): Int {
        var offset = _offset
        for (project in projects) {
            if (project.numberDependencies == 0) {
                order[offset] = project
                offset++
            }
        }
        return offset
    }

    private fun convertToStringList(projects: Array<Project?>): Array<String?> {
        val buildOrder = arrayOfNulls<String>(projects.size)
        for (i in projects.indices) {
            buildOrder[i] = projects[i]!!.name
        }
        return buildOrder
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val projects = arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j")
        val dependencies = arrayOf(
            arrayOf("a", "b"),
            arrayOf("b", "c"),
            arrayOf("a", "c"),
            arrayOf("d", "e"),
            arrayOf("b", "d"),
            arrayOf("e", "f"),
            arrayOf("a", "f"),
            arrayOf("h", "i"),
            arrayOf("h", "j"),
            arrayOf("i", "j"),
            arrayOf("g", "j")
        )
        val buildOrder = buildOrderWrapper(projects, dependencies)
        if (buildOrder.isEmpty()) {
            println("Circular Dependency.")
        } else {
            for (s in buildOrder) {
                println(s)
            }
        }
    }

}

