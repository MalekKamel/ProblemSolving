package challenges.cracking_coding_interview.trees_graphs.check_balanced

import challenges.util.TreeNode
import kotlin.math.abs

object CheckBalancedImproved {

    private fun checkHeight(root: TreeNode?): Int {
        if (root == null) {
            return -1
        }
        val leftHeight = checkHeight(root.left)
        if (leftHeight == Int.MIN_VALUE) return Int.MIN_VALUE // Propagate error up

        val rightHeight = checkHeight(root.right)
        if (rightHeight == Int.MIN_VALUE) return Int.MIN_VALUE // Propagate error up

        val heightDiff = leftHeight - rightHeight
        return if (abs(heightDiff) > 1) {
            Int.MIN_VALUE // Found error -> pass it back
        } else {
            leftHeight.coerceAtLeast(rightHeight) + 1
        }
    }

    private fun isBalanced(root: TreeNode?): Boolean {
        return checkHeight(root) != Int.MIN_VALUE
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Create balanced tree
        val array = intArrayOf(0, 1, 2, 3, 5, 6, 7, 8, 9, 10)
        val root: TreeNode? = TreeNode.createMinimalBST(array)
        println("Is balanced? " + isBalanced(root))
        root?.insertInOrder(4) // Add 4 to make it unbalanced
        println("Is balanced? " + isBalanced(root))
    }
}