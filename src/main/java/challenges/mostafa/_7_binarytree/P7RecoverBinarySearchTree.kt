package challenges.mostafa._7_binarytree

/**
You are given the root of a binary search tree (BST), where the values of exactly two nodes
of the tree were swapped by mistake. Recover the tree without changing its structure.

Example 1:

Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.

Example 2:

Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes
the BST valid.

Constraints:

The number of nodes in the tree is in the range [2, 1000].
-231 <= Node.val <= 231 - 1

Follow up: A solution using O(n) space is pretty straight-forward. Could you devise
a constant O(1) space solution?

https://leetcode.com/problems/recover-binary-search-tree/description/
 */

internal object P7RecoverBinarySearchTree {
    class TreeNode(var value: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null

        override fun toString(): String {
            return value.toString()
        }
    }

    private var first: TreeNode? = null
    private var second: TreeNode? = null
    private var prev: TreeNode? = null

    /**
     * 1. Problem Explanation
     * The core of the problem is that we have a Binary Search Tree (BST) where exactly two nodes have
     * had their values swapped. Our task is to identify these two misplaced nodes and swap their values
     * back to restore the BST property. Remember, in a BST, for any given node, all nodes in its left
     * subtree must have values smaller than the node's value, and all nodes in its right subtree must
     * have values greater than the node's value. The tree structure itself remains unchanged, only
     * the values within two nodes are incorrect.
     *
     * 2. Pattern Identification and Rationale
     * The fundamental property of a BST that we can leverage here is the in-order traversal. An in-order
     * traversal of a valid BST yields a sequence of nodes with values in ascending order. If two nodes
     * are swapped, this ascending order will be disrupted at exactly two places (or one place if the swapped
     * nodes are adjacent in the in-order traversal).
     *
     * Therefore, the core idea is to perform an in-order traversal of the given BST and identify the nodes
     * that violate this ascending order. Once we locate these misplaced nodes, we can simply swap their
     * values to restore the BST property.
     *
     * This approach naturally leads to an algorithm based on tree traversal, specifically in-order traversal.
     *
     * Advantages of using in-order traversal:
     *
     * Ordered Sequence: It provides a linear sequence of the tree's nodes in sorted order
     * (if the tree is a valid BST). This makes identifying the swapped nodes straightforward by looking
     * for inversions in the sequence.
     * Simplicity: In-order traversal is a well-understood and relatively easy-to-implement tree traversal
     * technique.
     *
     * 3. Solution Breakdown
     * Here's a breakdown of the steps to solve this problem:
     *
     * Perform In-order Traversal: Traverse the BST in-order. During the traversal, we need to keep track
     * of the previously visited node to compare its value with the current node's value.
     *
     * Identify the Misplaced Nodes: As we traverse, we look for violations of the BST
     * property (i.e., a node whose value is smaller than the value of the immediately preceding node
     * in the in-order sequence). We need to identify two such nodes (let's call them first and second).
     *
     * The first time we find such a violation (previous node's value > current node's value), the previous
     * node is our first misplaced node (first).
     * If we find a second violation later in the traversal, the current node is our second misplaced node (second).
     * If the two misplaced nodes are adjacent in the in-order traversal, we will only record the first violation,
     * and the current node at that point will be our second misplaced node.
     * Swap the Values: Once we have identified the two misplaced nodes (first and second), we simply
     * swap their val attributes. This corrects the BST.
     *
     * Handle Edge Cases: Consider the case where the two swapped nodes are adjacent in the in-order traversal.
     * In this scenario, the first violation will identify the first of the swapped nodes, and the second node
     * involved in the swap will be the current node at the time of the first violation.
     *
     * 4. Time Complexity
     * The time complexity of the in-order traversal is O(n), where n is the number of nodes in
     * the BST, as we visit each node exactly once. Identifying the misplaced nodes happens during this
     * traversal, so it doesn't add to the overall time complexity. Swapping the values takes constant time, O(1).
     * Therefore, the overall time complexity of this solution is O(n).
     */

    /**
     * Why we don't stop the in-order traversal as soon as we find the second misplaced node?
     *
     * The core issue is that we need to reliably identify both of the swapped nodes to correct the BST.
     * Consider these scenarios:
     *
     * Non-Adjacent Swapped Nodes: If the two swapped nodes are not adjacent in the in-order sequence,
     * we will encounter two distinct violations of the BST property during the traversal.
     *
     * The first time we find a node whose value is smaller than the previous node's value, the previous
     * node is one of the misplaced nodes (we store it as first).
     * The second time we find such a violation, the current node is the other misplaced node (we store
     * it as second).
     * Adjacent Swapped Nodes: If the two swapped nodes are adjacent in the in-order sequence, we will
     * only encounter one instance where the current node's value is smaller than the previous node's value.
     *
     * At this point, the previous node is one of the swapped nodes (we store it as first).
     * The current node at this point is the other swapped node. We need to continue the traversal so
     * that this current node is eventually assigned to second. If we stopped after the first violation,
     * second might remain null, and we wouldn't have both nodes to swap.
     * Why continuing is necessary:
     *
     * Ensuring both nodes are identified: We need to find both nodes involved in the swap to correct
     * the tree. Stopping after the first discrepancy might leave us with only one of the misplaced nodes,
     * especially in the adjacent swap case.
     * Correctly assigning second: The logic in the inorderTraversal function relies on updating second
     * whenever a violation is found. Even if first has been set, the second violation (or the current
     * node at the time of the first violation in the adjacent case) needs to be captured by second.
     * In essence, the traversal needs to continue to ensure that second correctly points to the second
     * node that is out of order in the in-order sequence. This second out-of-order node might be the current
     * node of the second violation or the current node of the first violation if the swapped nodes are adjacent.
     *
     * By continuing the traversal after the first violation, we guarantee that by the end of the in-order
     * traversal, first will hold one of the misplaced nodes (the one that appears earlier in the correct
     * in-order sequence), and second will hold the other misplaced node (the one that appears later in
     * the correct in-order sequence). Then, swapping their values correctly restores the BST.
     */
    private fun recoverTree(root: TreeNode?) {
        inorderTraversal(root)
        if (first != null && second != null) {
            val temp = first!!.value
            first!!.value = second!!.value
            second!!.value = temp
        }
    }

    private fun inorderTraversal(node: TreeNode?) {
        if (node == null) return

        inorderTraversal(node.left)

        if (prev != null && node.value < prev!!.value) {
            if (first == null) {
                first = prev
            }
            second = node
        }
        prev = node

        inorderTraversal(node.right)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        val root1 = TreeNode(1)
        root1.left = TreeNode(3)
        root1.left?.right = TreeNode(2)

        println("Example 1 - Before recovery:")
        printInOrder(root1)
        recoverTree(root1)
        println()
        println("Example 1 - After recovery:")
        printInOrder(root1)
        println()

        // Example 2
        val root2 = TreeNode(3)
        root2.left = TreeNode(1)
        root2.right = TreeNode(4)
        root2.right?.left = TreeNode(2)

        println("Example 2 - Before recovery:")
        printInOrder(root2)
        recoverTree(root2)
        println()
        println("Example 2 - After recovery:")
        printInOrder(root2)
    }

    private fun printInOrder(root: TreeNode?) {
        if (root == null) return
        printInOrder(root.left)
        print("${root.value} ")
        printInOrder(root.right)
    }
}