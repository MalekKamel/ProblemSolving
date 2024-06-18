package challenges.mostafa._7_binarytree

/**
For a binary tree T, we can define a flip operation as follows: choose any node, and swap
the left and right child subtrees.

A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y
after some number of flip operations.

Given the roots of two binary trees root1 and root2, return true if the two trees are flip
equivalent or false otherwise.

Example 1:

Flipped Trees Diagram
Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
Output: true
Explanation: We flipped at nodes with values 1, 3, and 5.

Example 2:

Input: root1 = [], root2 = []
Output: true

Example 3:

Input: root1 = [], root2 = [1]
Output: false

Constraints:

The number of nodes in each tree is in the range [0, 100].
Each tree will have unique node values in the range [0, 99].
 */

internal object P5FlipEquivalentBinaryTrees {

    class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    private fun flipEquiv(root1: TreeNode?, root2: TreeNode?): Boolean {
        return parenthesizeCanonical(root1) == parenthesizeCanonical(root2)
    }

    private fun parenthesizeCanonical(root: TreeNode?): String {
        if (root == null) return ""

        val left = parenthesizeCanonical(root.left)
        val right = parenthesizeCanonical(root.right)

        return if (left < right) {
            "(${root.`val`}$left$right)"
        } else {
            "(${root.`val`}$right$left)"
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
        root1.right?.left = TreeNode(6)
        root1.left?.right?.left = TreeNode(7)
        root1.left?.right?.right = TreeNode(8)

        val root2 = TreeNode(1)
        root2.left = TreeNode(3)
        root2.right = TreeNode(2)
        root2.left?.right = TreeNode(6)
        root2.right?.left = TreeNode(4)
        root2.right?.right = TreeNode(5)
        root2.right?.right?.left = TreeNode(8)
        root2.right?.right?.right = TreeNode(7)

        println(flipEquiv(root1, root2)) // true

        // Example 2
        println(flipEquiv(null, null)) // true

        // Example 3
        println(flipEquiv(null, TreeNode(1))) // false
    }
}