package challenges.cracking_coding_interview.trees_graphs.check_balanced

import challenges.util.AssortedMethods.randomIntInRange
import challenges.util.TreeNode
import kotlin.math.abs


object CheckBalancedBrute {

    private fun getHeight(root: TreeNode?): Int {
        return if (root == null) {
            -1
        } else getHeight(root.left).coerceAtLeast(getHeight(root.right)) + 1
    }

    private fun isBalanced(root: TreeNode?): Boolean {
        if (root == null)
            return true

        val heightDiff = getHeight(root.left) - getHeight(root.right)
        return if (abs(heightDiff) > 1) {
            false
        } else {
            isBalanced(root.left) && isBalanced(root.right)
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Create balanced tree
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val root: TreeNode? = TreeNode.createMinimalBST(array)
        println("Root? " + root?.data)
        println("Is balanced? " + isBalanced(root))

        // Could be balanced, actually, but it's very unlikely...
        val unbalanced = TreeNode(10)
        for (i in 0..9) {
            unbalanced.insertInOrder(randomIntInRange(0, 100))
        }
        println("Root? " + unbalanced.data)
        println("Is balanced? " + isBalanced(unbalanced))
    }

}