package challenges.cracking_coding_interview.trees_graphs

import challenges.util.TreeNode

object Successor {
    private fun inorderSucc(n: TreeNode?): TreeNode? {
        if (n == null) return null

        // Found right children -> return left most node of right subtree
        if (n.parent == null || n.right != null) {
            return leftMostChild(n.right!!)
        }

        var q: TreeNode = n
        var x: TreeNode? = q.parent
        // Go up until we're on left instead of right
        while (x != null && x.left != q) {
            q = x
            x = x.parent
        }
        return x
    }

    private fun leftMostChild(_n: TreeNode): TreeNode {
        var n: TreeNode = _n
        while (n.left != null) {
            n = n.left!!
        }
        return n
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val root: TreeNode = TreeNode.createMinimalBST(array) ?: return
        for (i in array.indices) {
            val node: TreeNode = root.find(array[i])!!
            val next: TreeNode? = inorderSucc(node)
            if (next != null) {
                println(node.data.toString() + "->" + next.data)
            } else {
                println(node.data.toString() + "->" + null)
            }
        }
    }

}