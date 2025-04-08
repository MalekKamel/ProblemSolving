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

        /**
         * 1. Problem Explanation
         * The problem asks us to find the longest path between any two nodes in a binary tree.
         * This path is measured by the number of edges it contains. The longest path might or
         * might not go through the root of the tree.

         * 2. Pattern Identification and Rationale
         * The problem can be efficiently solved using a recursive approach, specifically a
         * Depth-First Search (DFS) traversal. We can adapt the standard height calculation
         * of a binary tree to also keep track of the diameter.
         *
         * Rationale:
         * The diameter of a subtree rooted at a particular node can be one of three things:
         * 1. The diameter lies entirely in the left subtree.
         * 2. The diameter lies entirely in the right subtree.
         * 3. The diameter passes through the current node. In this case, the length of the
         *    diameter is the sum of the heights of the left and right subtrees plus 2 (for the
         *    edges connecting the current node to the deepest nodes in its left and right
         *    subtrees).
         *
         * By recursively calculating the height of each subtree, we can simultaneously update
         * the maximum diameter found so far. This approach avoids redundant traversals and
         * efficiently explores all possible paths in the tree.

         * 3. Solution Breakdown
         * Step 1: Define a recursive function (e.g., `height`) that takes a TreeNode as input.
         * Step 2: Base Case: If the current node is null, its height is 0.
         * Step 3: Recursively calculate the height of the left subtree (`leftHeight`).
         * Step 4: Recursively calculate the height of the right subtree (`rightHeight`).
         * Step 5: At the current node, the diameter that passes through this node is
         *         `leftHeight + rightHeight`. Update the global `diameter` variable if this
         *         value is greater than the current `diameter`.
         * Step 6: The height of the current node is the maximum of the left and right subtree
         *         heights plus 1 (for the edge connecting to its parent). Return this height.
         * Step 7: In the main `diameterOfBinaryTree` function, initialize the `diameter` to 0
         *         and call the `height` function on the root of the tree.
         * Step 8: After the `height` function completes, the `diameter` variable will hold the
         *         length of the longest path (number of edges), so return `diameter`.
         *
         * 4. Time Complexity
         * The `height` function visits each node in the binary tree exactly once. Therefore,
         * the time complexity of this solution is O(N), where N is the number of nodes in the tree.

         * 5. Efficient Implementation
         * The provided Kotlin code implements the recursive DFS approach as described above.
         * It uses a single traversal of the tree to calculate both the height of each subtree
         * and the diameter. The space complexity is O(H), where H is the height of the tree,
         * due to the recursive call stack. In the worst case (a skewed tree), H can be N,
         * resulting in O(N) space complexity. In the best case (a balanced tree), H is O(log N),
         * resulting in O(log N) space complexity.
         */
        /**
         * Here's why we add 1:
         *
         * Defining Height: The height of a node in a binary tree is defined as the number of edges
         * on the longest path from that node down to a leaf node.
         *
         * Recursive Calculation: When the height function is called on a node, it recursively gets
         * the heights of its left and right subtrees (leftHeight and rightHeight). These recursive
         * calls essentially give us the lengths of the longest paths from the left and right children
         * down to their respective leaf nodes.
         *
         * Connecting to the Parent: To find the height of the current node, we need to consider
         * the longest path from this node down to a leaf. This path will go through either the left
         * subtree or the right subtree (whichever is deeper).
         *
         * Counting the Edge: The + 1 accounts for the edge connecting the current node to its deepest
         * child (either in the left or right subtree). We've already found the length of the longest
         * path from that child downwards (either leftHeight or rightHeight), so adding 1 includes the edge
         * that brings us up to the current node.
         *
         * Analogy: Imagine you're counting the number of steps to the top of the tallest tower you can
         * reach from your current position. You look at the tallest tower reachable from your left, count
         * its steps, and do the same for the right. The height from your current position is the number
         * of steps to the taller of those towers plus one step to get from your current position to the base
         * of that tower.
         *
         * Base Case: Notice that the base case for the recursion is when node is null, in which case
         * we return 0. This is correct because a null node (representing the absence of a node) has
         * a height of 0 (no edges below it).
         *
         * Therefore, the + 1 ensures that the height function correctly returns the number of edges
         * in the longest path from the given node to a leaf node in its subtree. This height information
         * is then used to calculate the diameter of the tree.
         */
        fun diameterOfBinaryTree(root: TreeNode?): Int {
            diameter = 0
            height(root)
            return diameter
        }

        private fun height(node: TreeNode?): Int {
            if (node == null) return 0

            val leftHeight = height(node.left)
            val rightHeight = height(node.right)

            // The diameter passing through the current node is leftHeight + rightHeight
            diameter = maxOf(diameter, leftHeight + rightHeight)

            // The height of the current node is the maximum height of its subtrees + 1
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