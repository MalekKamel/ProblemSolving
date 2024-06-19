package challenges.mostafa._7_binarytree

/**
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
two nodes p and q as the lowest node in T that has both p and q as descendants
(where we allow a node to be a descendant of itself).”

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1

Constraints:

The number of nodes in the tree is in the range [2, 10^5].
-10^9 <= Node.val <= 10^9
All Node.val are unique.
p != q
p and q will exist in the tree.

https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 */

internal object P9LowestCommonAncestorOfBinaryTree {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null

        override fun toString(): String {
            return `val`.toString()
        }
    }

    private fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || root == p || root == q) return root

        val left = lowestCommonAncestor(root.left, p, q)
        val right = lowestCommonAncestor(root.right, p, q)

        if (left != null && right != null) return root

        return left ?: right
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        val root1 = TreeNode(3)
        root1.left = TreeNode(5)
        root1.right = TreeNode(1)
        root1.left?.left = TreeNode(6)
        root1.left?.right = TreeNode(2)
        root1.left?.right?.left = TreeNode(7)
        root1.left?.right?.right = TreeNode(4)
        root1.right?.left = TreeNode(0)
        root1.right?.right = TreeNode(8)
        println(lowestCommonAncestor(root1, root1.left, root1.right)?.`val`) // Output: 3


    }
}
