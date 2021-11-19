package challenges.cracking_coding_interview.trees_graphs.validate_bst

import challenges.util.AssortedMethods
import challenges.util.AssortedMethods.randomIntInRange
import challenges.util.TreeNode


object ValidateBstB {
    private fun checkBST(n: TreeNode?, min: Int?, max: Int?): Boolean {
        if (n == null) return true

        if (min != null && n.data <= min || max != null && n.data > max) {
            return false
        }
        return !(!checkBST(n.left, min, n.data) || !checkBST(n.right, n.data, max))
    }

    private fun checkBST(n: TreeNode?): Boolean {
        return checkBST(n, null, null)
    }

    private fun checkBSTAlternate(n: TreeNode): Boolean {
        return checkBSTAlternate(n, IntWrapper(0), IntWrapper(0))
    }

    private fun checkBSTAlternate(n: TreeNode, min: IntWrapper, max: IntWrapper): Boolean {
        /* An alternate, less clean approach. This is not provided in the book, but is used to test the other method. */
        if (n.left == null) {
            min.data = n.data
        } else {
            val leftMin = IntWrapper(0)
            val leftMax = IntWrapper(0)
            if (!checkBSTAlternate(n.left!!, leftMin, leftMax)) {
                return false
            }
            if (leftMax.data > n.data) {
                return false
            }
            min.data = leftMin.data
        }
        if (n.right == null) {
            max.data = n.data
        } else {
            val rightMin = IntWrapper(0)
            val rightMax = IntWrapper(0)
            if (!checkBSTAlternate(n.right!!, rightMin, rightMax)) {
                return false
            }
            if (rightMin.data <= n.data) {
                return false
            }
            max.data = rightMax.data
        }
        return true
    }

    /* Create a tree that may or may not be a BST */
    private fun createTestTree(): TreeNode {
        /* Create a random BST */
        val head: TreeNode = AssortedMethods.randomBST(10, -10, 10)

        /* Insert an element into the BST and potentially ruin the BST property */
        var node: TreeNode? = head
        do {
            val n = randomIntInRange(-10, 10)
            val rand = randomIntInRange(0, 5)
            if (rand == 0) {
                node?.data = n
            } else if (rand == 1) {
                node = node?.left
            } else if (rand == 2) {
                node = node?.right
            } else if (rand == 3 || rand == 4) {
                break
            }
        } while (node != null)
        return head
    }

    @JvmStatic
    fun main(args: Array<String>) {
        /* Simple test -- create one */
        val array = intArrayOf(Int.MIN_VALUE, 3, 5, 6, 10, 13, 15, Int.MAX_VALUE)
        val node: TreeNode = TreeNode.createMinimalBST(array) ?: return
        //node.left.data = 6; // "ruin" the BST property by changing one of the elements
        node.print()
        val isBst = checkBST(node)
        println("isBst: $isBst")

        // More elaborate test -- creates 100 trees (some BST, some not)
        // and compares the outputs of various methods.
      /*
        for (i in 0..99) {
            val head = createTestTree()

            // Compare results
            val isBst1 = checkBST(head)
            val isBst2 = checkBSTAlternate(head)
            if (isBst1 != isBst2) {
                println("*********************** ERROR *******************")
                head.print()
                break
            } else {
                println("$isBst1 | $isBst2")
                head.print()
            }
        }
       */
    }

}