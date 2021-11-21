package challenges.cracking_coding_interview.first_common_ancestor

import challenges.util.TreeNode

/**
 * Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree.
 * Avoid storing additional nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 */
object QuestionA {
    fun commonAncestor(p: TreeNode, q: TreeNode): TreeNode? {
        if (p === q) return p
        var ancestor: TreeNode? = p
        while (ancestor != null) {
            if (isOnPath(ancestor, q)) {
                return ancestor
            }
            ancestor = ancestor.parent
        }
        return null
    }

    private fun isOnPath(ancestor: TreeNode, node: TreeNode?): Boolean {
        var node: TreeNode? = node
        while (node !== ancestor && node != null) {
            node = node.parent
        }
        return node === ancestor
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val root: TreeNode = TreeNode.createMinimalBST(array) ?: return
        val n3: TreeNode = root.find(8)!!
        val n7: TreeNode = root.find(8)!!
        val ancestor: TreeNode? = commonAncestor(n3, n7)
        println(ancestor?.data)
    }
}