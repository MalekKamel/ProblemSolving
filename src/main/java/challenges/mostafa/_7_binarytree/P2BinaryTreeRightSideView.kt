package challenges.mostafa._7_binarytree

/**
Given the root of a binary tree, imagine yourself standing on the right side of it, return
the values of the nodes you can see ordered from top to bottom.


Example 1:

Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]
Example 2:

Input: root = [1,null,3]
Output: [1,3]
Example 3:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100

https://leetcode.com/problems/binary-tree-right-side-view/description/
 */

internal object P2BinaryTreeRightSideView {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    private fun rightSideView(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        if (root == null) return result

        val queue: MutableList<TreeNode> = mutableListOf()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            var rightmostValue = 0

            for (i in 0 until levelSize) {
                val node = queue.removeAt(0)
                rightmostValue = node.`val`

                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }

            result.add(rightmostValue)
        }

        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        val root1 = TreeNode(1).apply {
            left = TreeNode(2).apply {
                right = TreeNode(5)
            }
            right = TreeNode(3).apply {
                right = TreeNode(4)
            }
        }
        println(rightSideView(root1)) // Output: [1, 3, 4]

    }
}