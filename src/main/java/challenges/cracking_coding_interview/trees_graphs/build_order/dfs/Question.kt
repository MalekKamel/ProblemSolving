package challenges.cracking_coding_interview.trees_graphs.build_order.dfs

import java.util.*

object Question {

    private fun buildOrderWrapper(
        projects: Array<String>,
        dependencies: Array<Array<String>>
    ): Array<String>? {
        val buildOrder = findBuildOrder(projects, dependencies) ?: return null
        return convertToStringList(buildOrder)
    }

    private fun convertToStringList(projects: Stack<Project>): Array<String> {
        val buildOrder = Array(projects.size) {
            projects.pop().name
        }
        return buildOrder
    }

    private fun findBuildOrder(projects: Array<String>, dependencies: Array<Array<String>>): Stack<Project>? {
        val graph = buildGraph(projects, dependencies)
        return orderProjects(graph.nodes)
    }

    /* Build the graph, adding the edge (a, b) if b is dependent on a.
     * Assumes a pair is listed in “build order” (which is the reverse
     * of dependency order). The pair (a, b) in dependencies indicates
     * that b depends on a and a must be built before a.
     * */
    private fun buildGraph(projects: Array<String>?, dependencies: Array<Array<String>>): Graph {
        val graph = Graph()
        for (dependency in dependencies) {
            val first = dependency[0]
            val second = dependency[1]
            graph.addEdge(first, second)
        }
        return graph
    }

    private fun orderProjects(projects: List<Project>): Stack<Project>? {
        val stack: Stack<Project> = Stack()
        for (project in projects) {
            when (project.state) {
                Project.State.BLANK -> {
                    if (!doDFS(project, stack)) return null
                }
                Project.State.PARTIAL,
                Project.State.COMPLETE -> continue
            }
        }
        return stack
    }

    private fun doDFS(project: Project, stack: Stack<Project>): Boolean {
        when (project.state) {
            Project.State.PARTIAL -> return false
            Project.State.BLANK -> {
                project.state = Project.State.PARTIAL
                val children = project.children
                for (child in children) {
                    if (!doDFS(child, stack)) return false
                }
                project.state = Project.State.COMPLETE
                stack.push(project)
            }
            Project.State.COMPLETE -> {
                // Nothing
            }
        }
        return true
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
        if (buildOrder == null) {
            println("Circular Dependency.")
        } else {
            for (s in buildOrder) {
                println(s)
            }
        }
    }

}