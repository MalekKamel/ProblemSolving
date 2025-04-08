package challenges.mostafa._7_binarytree

import java.util.LinkedList
import java.util.Queue

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
     * 1. Problem Explanation
     * The problem asks us to find the nodes visible from the right side of a binary tree.
     * When we look from the right, for each level of the tree, we will only be able to see
     * the rightmost node. We need to return these visible nodes in a top-to-bottom order.

     * 2. Pattern Identification and Rationale
     * The problem requires us to traverse the tree level by level to identify the rightmost
     * node at each level. Breadth-First Search (BFS) or Level Order Traversal is a suitable
     * algorithm for this. BFS allows us to visit all nodes at the same level before moving
     * to the next level. By keeping track of the nodes at each level, we can easily identify
     * the last (rightmost) node encountered at that level.

     * 3. Solution Breakdown
     * Step 1: Initialize an empty list to store the right side view nodes.
     * Step 2: Handle the base case where the root is null. In this case, the right side view is empty.
     * Step 3: Use a queue to perform BFS. Add the root node to the queue.
     * Step 4: While the queue is not empty, process all nodes at the current level.
     *    a. Get the number of nodes at the current level.
     *    b. Iterate through all the nodes at the current level.
     *    c. For each node, dequeue it.
     *    d. If it's the last node at the current level (i.e., the last one dequeued), add its value to the result list.
     *    e. Enqueue the left child (if it exists).
     *    f. Enqueue the right child (if it exists).
     *    g. Repeat steps b-f for each node in the current level.
     * Step 5: Return the list of right side view nodes.
     *
     * 4. Time Complexity
     * The time complexity of this solution is O(N), where N is the number of nodes in the binary tree.
     * This is because each node is visited and processed exactly once during the BFS traversal.
     */
    private fun rightSideView(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        if (root == null) {
            return result
        }

        val queue: Queue<TreeNode> = LinkedList()
        queue.offer(root)

        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            for (i in 0 until levelSize) {
                val node = queue.poll()
                if (i == levelSize - 1) {
                    result.add(node.`val`)
                }
                if (node.left != null) {
                    queue.offer(node.left)
                }
                if (node.right != null) {
                    queue.offer(node.right)
                }
            }
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