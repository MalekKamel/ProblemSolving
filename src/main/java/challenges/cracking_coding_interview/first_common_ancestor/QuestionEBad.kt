package challenges.cracking_coding_interview.first_common_ancestor

import challenges.util.TreeNode

/**
 * Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree.
 * Avoid storing additional nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 */
object QuestionEBad {

    private fun commonAncestorBad(root: TreeNode?, p: TreeNode, q: TreeNode): TreeNode? {
        if (root == null) {
            return null
        }
        if (root == p && root == q) {
            return root
        }
        val x: TreeNode? = commonAncestorBad(root.left, p, q)
        if (x != null && x !== p && x !== q) { // Found common ancestor
            return x
        }
        val y: TreeNode? = commonAncestorBad(root.right, p, q)
        if (y != null && y !== p && y !== q) {
            return y
        }
        return if (x != null && y != null) {
            root // This is the common ancestor
        } else if (root == p || root == q) {
            root
        } else {
            x ?: y
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val root: TreeNode = TreeNode.createMinimalBST(array) ?: return
        val n3: TreeNode = root.find(9)!!
        val n7 = TreeNode(6) //root.find(10);
        val ancestor: TreeNode? = commonAncestorBad(root, n3, n7)
        if (ancestor != null) {
            println(ancestor.data)
        } else {
            println("null")
        }
    }

}