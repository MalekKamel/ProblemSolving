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
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null

        override fun toString(): String {
            return `val`.toString()
        }
    }

    private fun recoverTree(root: TreeNode?) {
        var firstNode: TreeNode? = null
        var secondNode: TreeNode? = null
        var prevNode: TreeNode? = null

        // Perform in-order traversal and find the two misplaced nodes
        fun inorderTraversal(node: TreeNode?) {
            if (node == null) return

            inorderTraversal(node.left)

            if (prevNode != null && prevNode!!.`val` > node.`val`) {
                if (firstNode == null) firstNode = prevNode
                secondNode = node
            }
            prevNode = node

            inorderTraversal(node.right)
        }

        inorderTraversal(root)

        // Swap the values of the two misplaced nodes
        if (firstNode != null && secondNode != null) {
            val temp = firstNode!!.`val`
            firstNode!!.`val` = secondNode!!.`val`
            secondNode!!.`val` = temp
        }
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
        print("${root.`val`} ")
        printInOrder(root.right)
    }
}