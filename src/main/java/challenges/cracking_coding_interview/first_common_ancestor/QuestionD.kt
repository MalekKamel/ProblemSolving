package challenges.cracking_coding_interview.first_common_ancestor

import challenges.util.TreeNode

/**
 * Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree.
 * Avoid storing additional nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 */
object QuestionD {

    fun commonAncestor(root: TreeNode?, p: TreeNode, q: TreeNode): TreeNode? {
        return if (!covers(root, p) || !covers(root, q)) { // Error check - one node is not in tree
            null
        } else ancestorHelper(root, p, q)
    }

    private fun ancestorHelper(root: TreeNode?, p: TreeNode, q: TreeNode): TreeNode? {
        if (root == null || root === p || root === q) {
            return root
        }
        val pIsOnLeft = covers(root.left, p)
        val qIsOnLeft = covers(root.left, q)
        if (pIsOnLeft != qIsOnLeft) { // Nodes are on different side
            return root
        }
        val childSide: TreeNode? = if (pIsOnLeft) root.left else root.right
        return ancestorHelper(childSide, p, q)
    }

    private fun covers(root: TreeNode?, p: TreeNode): Boolean {
        if (root == null) return false
        return if (root === p) true else covers(root.left, p) || covers(root.right, p)
    }


    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val root: TreeNode = TreeNode.createMinimalBST(array) ?: return
        val n3: TreeNode = root.find(1)!!
        val n7: TreeNode = root.find(7)!!
        val ancestor: TreeNode? = commonAncestor(root, n3, n7)
        println(ancestor?.data)
    }

}