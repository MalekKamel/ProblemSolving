package challenges.cracking_coding_interview.first_common_ancestor

import challenges.util.TreeNode

/**
 * Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree.
 * Avoid storing additional nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 */
object QuestionE {
    class Result(n: TreeNode?, isAnc: Boolean) {
        var node: TreeNode?
        var isAncestor: Boolean

        init {
            node = n
            isAncestor = isAnc
        }
    }

    private fun commonAncestorHelper(root: TreeNode?, p: TreeNode, q: TreeNode): Result {
        if (root == null) {
            return Result(null, false)
        }
        if (root === p && root === q) {
            return Result(root, true)
        }
        val rx = commonAncestorHelper(root.left, p, q)
        if (rx.isAncestor) { // Found common ancestor
            return rx
        }
        val ry = commonAncestorHelper(root.right, p, q)
        if (ry.isAncestor) { // Found common ancestor
            return ry
        }
        return if (rx.node != null && ry.node != null) {
            Result(root, true) // This is the common ancestor
        } else if (root === p || root === q) {
            /* If we�re currently at p or q, and we also found one of those
                  * nodes in a subtree, then this is truly an ancestor and the
                  * flag should be true. */
            val isAncestor = rx.node != null || ry.node != null
            Result(root, isAncestor)
        } else {
            Result(if (rx.node != null) rx.node else ry.node, false)
        }
    }

    fun commonAncestor(root: TreeNode?, p: TreeNode, q: TreeNode): TreeNode? {
        val r = commonAncestorHelper(root, p, q)
        return if (r.isAncestor) {
            r.node
        } else null
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val root: TreeNode = TreeNode.createMinimalBST(array) ?: return
        val n3: TreeNode = root.find(10)!!
        val n7: TreeNode = root.find(6)!!
        val ancestor: TreeNode? = commonAncestor(root, n3, n7)
        if (ancestor != null) {
            println(ancestor.data)
        } else {
            println("null")
        }
    }

}