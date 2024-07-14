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

    /**
    Here's a breakdown of how the function works:

    1. Initialize an empty mutable list called `result` to store the right side view of the tree.
    2. If the input `root` is `null`, return the `result` list as is.
    3. Create a mutable list called `queue` and add the `root` node to it.
    4. Enter a loop that continues as long as the `queue` is not empty.
    5. Inside the loop:
    - Determine the size of the current level of the tree by storing the `queue.size` in the
    `levelSize` variable.
    - Initialize a `rightmostValue` variable to store the rightmost value of the current level.
    - Iterate through the nodes in the current level (`for (i in 0 until levelSize)`):
    - Remove the first node from the `queue` and store it in the `node` variable.
    - Update the `rightmostValue` to be the value of the current `node`.
    - If the `node` has a left child, add it to the `queue`.
    - If the `node` has a right child, add it to the `queue`.
    - After processing all the nodes in the current level, add the `rightmostValue` to the `result` list.
    6. Return the `result` list, which now contains the right side view of the tree.

    The key aspects of this algorithm are:
    - It uses a breadth-first search (BFS) approach, traversing the tree level by level.
    - For each level, it keeps track of the rightmost value, which represents the node that is
    visible from the right side of the tree.
    - The rightmost value is added to the `result` list after processing all the nodes in
    the current level.
    - The algorithm continues until all levels of the tree have been processed.

    This function can be useful in scenarios where you need to visualize the tree from the right
    side and obtain the values of the nodes that are visible from that perspective.
     */
    private fun rightSideView(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        if (root == null) return result

        val queue = mutableListOf<TreeNode>()
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