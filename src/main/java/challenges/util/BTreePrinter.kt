package challenges.util

import kotlin.math.max
import kotlin.math.pow


object BTreePrinter {
    fun printNode(root: TreeNode?) {
        val maxLevel = maxLevel<Comparable<*>>(root)
        printNodeInternal<Comparable<*>>(listOf(root), 1, maxLevel)
    }

    private fun <T : Comparable<*>?> printNodeInternal(nodes: List<TreeNode?>, level: Int, maxLevel: Int) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) return
        val floor = maxLevel - level
        val edgeLines = 2.0.pow(max(floor - 1, 0).toDouble()).toInt()
        val firstSpaces = 2.0.pow(floor.toDouble()).toInt() - 1
        val betweenSpaces = 2.0.pow((floor + 1).toDouble()).toInt() - 1
        printWhitespaces(firstSpaces)
        val newNodes: MutableList<TreeNode?> = ArrayList()
        for (node in nodes) {
            if (node != null) {
                print(node.data)
                newNodes.add(node.left)
                newNodes.add(node.right)
            } else {
                newNodes.add(null)
                newNodes.add(null)
                print(" ")
            }
            printWhitespaces(betweenSpaces)
        }
        println("")
        for (i in 1..edgeLines) {
            for (j in nodes.indices) {
                printWhitespaces(firstSpaces - i)
                if (nodes[j] == null) {
                    printWhitespaces(edgeLines + edgeLines + i + 1)
                    continue
                }
                if (nodes[j]!!.left != null) print("/") else printWhitespaces(1)
                printWhitespaces(i + i - 1)
                if (nodes[j]!!.right != null) print("\\") else printWhitespaces(1)
                printWhitespaces(edgeLines + edgeLines - i)
            }
            println("")
        }
        printNodeInternal<Comparable<*>>(newNodes, level + 1, maxLevel)
    }

    private fun printWhitespaces(count: Int) {
        for (i in 0 until count) print(" ")
    }

    private fun <T : Comparable<*>?> maxLevel(node: TreeNode?): Int {
        return if (node == null) 0 else max(
            maxLevel<Comparable<*>>(node.left),
            maxLevel<Comparable<*>>(node.right)
        ) + 1
    }

    private fun <T> isAllElementsNull(list: List<T>): Boolean {
        for (`object` in list) {
            if (`object` != null) return false
        }
        return true
    }
}