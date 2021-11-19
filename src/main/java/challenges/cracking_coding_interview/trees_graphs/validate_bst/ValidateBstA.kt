package challenges.cracking_coding_interview.trees_graphs.validate_bst

import challenges.util.TreeNode

object ValidateBstA {
    var lastPrinted: Int? = null

    private fun checkBST(node: TreeNode?): Boolean {
        return checkBST(node, true)
    }

    // Allow "equal" value only for left child. This validates the BST property.
    private fun checkBST(n: TreeNode?, isLeft: Boolean): Boolean {
        if (n == null) return true

        // Check / recurse left
        if (!checkBST(n.left, true)) return false

        // Check current
        if (!check(n, isLeft)) return false

        lastPrinted = n.data

        // Check / recurse right
        return checkBST(n.right, false)
    }

    private fun check(n: TreeNode, isLeft: Boolean): Boolean {
        val lastPrinted = lastPrinted ?: return true
        if (isLeft) {
            // left child "is allowed" be equal to parent.
            if (n.data < lastPrinted) return false
            return true
        }
        // Right child "is not allowed" be equal to parent.
        if (n.data <= lastPrinted) return false
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(Int.MIN_VALUE, Int.MAX_VALUE - 2, Int.MAX_VALUE - 1, Int.MAX_VALUE)
        val node: TreeNode? = TreeNode.createMinimalBST(array)
        //node.left.data = 5;
        //node.left.right.data = 3;
        println(checkBST(node))
        test()
    }

    private fun test() {
        var node: TreeNode
        println("test cases for equals condition.")

        /* Expect true: for left child: node.data <= last_printed.
   2
  / \
 /   \
 2   3
      \
      4
		*/
        val array2 = intArrayOf(1, 2, 3, 4)
        node = TreeNode.createMinimalBST(array2) ?: return
        node.left?.data = 2
        node.print()
        lastPrinted = null
        var condition: Boolean = checkBST(node)
        println("should be true: $condition")

        /* Expect false: for right child: node.data <= last_printed.
   2
  / \
 /   \
 1   2
      \
      4
		 */
        val array3 = intArrayOf(1, 2, 3, 4)
        node = TreeNode.createMinimalBST(array3) ?: return
        node.right?.data = 2
        node.print()
        lastPrinted = null
        condition = checkBST(node)
        println("should be false: $condition")
    }
}