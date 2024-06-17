package challenges.mostafa._7_binarytree

/**
Given the root of a binary tree, return the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
This path may or may not pass through the root.
The length of a path between two nodes is represented by the number of edges between them.

Example 1:

Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
Example 2:

Input: root = [1,2]
Output: 1

Constraints:

The number of nodes in the tree is in the range [1, 10^4].
-100 <= Node.val <= 100

https://leetcode.com/problems/diameter-of-binary-tree/description/
 */

internal object P1DiameterOfBinaryTree {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    class Solution {
        private var diameter = 0

        fun diameterOfBinaryTree(root: TreeNode?): Int {
            diameter = 0
            dfs(root)
            return diameter
        }

        private fun dfs(node: TreeNode?): Int {
            if (node == null) return 0

            val leftHeight = dfs(node.left)
            val rightHeight = dfs(node.right)

            // Update the diameter if the path through the current node is larger
            diameter = maxOf(diameter, leftHeight + rightHeight)

            // Return the height of the current node
            return maxOf(leftHeight, rightHeight) + 1
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        val root1 = TreeNode(1)
        root1.left = TreeNode(2)
        root1.right = TreeNode(3)
        root1.left?.left = TreeNode(4)
        root1.left?.right = TreeNode(5)
        println(Solution().diameterOfBinaryTree(root1)) // Output: 3

        // Example 2
        val root2 = TreeNode(1)
        root2.left = TreeNode(2)
        println(Solution().diameterOfBinaryTree(root2)) // Output: 1
    }
}