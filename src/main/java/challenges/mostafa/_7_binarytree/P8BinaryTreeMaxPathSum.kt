package challenges.mostafa._7_binarytree

/**
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence
has an edge connecting them. A node can only appear in the sequence at most once. Note that
the path does not need to pass through the root.
The path sum of a path is the sum of the node's values in the path.
Given the root of a binary tree, return the maximum path sum of any non-empty path.

Example 1:

Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

Example 2:

Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

Constraints:

The number of nodes in the tree is in the range [1, 3 * 10^4].
-1000 <= Node.val <= 1000

https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 */

internal object P8BinaryTreeMaxPathSum {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null

        override fun toString(): String {
            return `val`.toString()
        }
    }

    private fun maxPathSum(root: TreeNode?): Int {
        var maxSum = Int.MIN_VALUE

        fun maxPathSumUtil(node: TreeNode?): Int {
            if (node == null) return 0

            val leftMaxSum = maxPathSumUtil(node.left)
            val rightMaxSum = maxPathSumUtil(node.right)

            val currentMaxSum = node.`val` + maxOf(0, leftMaxSum) + maxOf(0, rightMaxSum)
            maxSum = maxOf(maxSum, currentMaxSum)

            return currentMaxSum
        }

        maxPathSumUtil(root)
        return maxSum
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Create the binary tree from the examples
        val root1 = TreeNode(1)
        root1.left = TreeNode(2)
        root1.right = TreeNode(3)
        println(maxPathSum(root1)) // Output: 6

        val root2 = TreeNode(-10)
        root2.left = TreeNode(9)
        root2.right = TreeNode(20)
        root2.right?.left = TreeNode(15)
        root2.right?.right = TreeNode(7)
        println(maxPathSum(root2)) // Output: 42
    }
}