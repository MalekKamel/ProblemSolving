package challenges.mostafa._7_binarytree

/**
Given the roots of two binary trees root and subRoot, return true if there is a subtree of root
with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this
node's descendants. The tree tree could also be considered as a subtree of itself.

Example 1:

Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true

Example 2:

Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false

Constraints:

The number of nodes in the root tree is in the range [1, 2000].
The number of nodes in the subRoot tree is in the range [1, 1000].
-10^4 <= root.val <= 10^4
-10^4 <= subRoot.val <= 10^4

https://leetcode.com/problems/subtree-of-another-tree/description/
 */

internal object P4SubtreeOfAnotherTree {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null

        override fun toString(): String {
            return `val`.toString()
        }
    }

    private fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        val rootString = parenthesize(root)
        val subRootString = parenthesize(subRoot)
        return rootString.contains(subRootString)
    }

    private fun parenthesize(node: TreeNode?): String {
        if (node == null) return "null"
        return "(${node.`val`} ${parenthesize(node.left)} ${parenthesize(node.right)})"
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        val root1 = TreeNode(3)
        root1.left = TreeNode(4)
        root1.right = TreeNode(5)
        root1.left?.left = TreeNode(1)
        root1.left?.right = TreeNode(2)

        val subRoot1 = TreeNode(4)
        subRoot1.left = TreeNode(1)
        subRoot1.right = TreeNode(2)

        println(isSubtree(root1, subRoot1)) // Output: true

        // Example 2
        val root2 = TreeNode(3)
        root2.left = TreeNode(4)
        root2.right = TreeNode(5)
        root2.left?.left = TreeNode(1)
        root2.left?.right = TreeNode(2)
        root2.left?.right?.left = TreeNode(0)

        val subRoot2 = TreeNode(4)
        subRoot2.left = TreeNode(1)
        subRoot2.right = TreeNode(2)

        println(isSubtree(root2, subRoot2)) // Output: false
    }
}