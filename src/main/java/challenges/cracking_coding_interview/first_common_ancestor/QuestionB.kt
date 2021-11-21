package challenges.cracking_coding_interview.first_common_ancestor

import challenges.util.TreeNode
import kotlin.math.abs

/**
 * Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree.
 * Avoid storing additional nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 */
object QuestionB {
    fun commonAncestor(p: TreeNode?, q: TreeNode?): TreeNode? {
        val delta = depth(p) - depth(q) // get difference in depths
        var first: TreeNode? = if (delta > 0) q else p // get shallower node
        var second: TreeNode? = if (delta > 0) p else q // get deeper node
        second = goUpBy(second, abs(delta)) // move shallower node to depth of deeper
        while (first !== second && first != null && second != null) {
            first = first.parent
            second = second.parent
        }
        return if (first == null || second == null) null else first
    }

    private fun goUpBy(node: TreeNode?, delta: Int): TreeNode? {
        var node: TreeNode? = node
        var delta = delta
        while (delta > 0 && node != null) {
            node = node.parent
            delta--
        }
        return node
    }

    private fun depth(node: TreeNode?): Int {
        var node: TreeNode? = node
        var depth = 0
        while (node != null) {
            node = node.parent
            depth++
        }
        return depth
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val root: TreeNode = TreeNode.createMinimalBST(array) ?: return
        val n3: TreeNode = root.find(3)!!
        val n7: TreeNode = root.find(7)!!
        val ancestor: TreeNode? = commonAncestor(n3, n7)
        println(ancestor?.data)
    }
}