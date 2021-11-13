package challenges.cracking_coding_interview.trees_graphs.introduction

import challenges.util.TreeNode


object Traversals {
    private fun visit(node: TreeNode?) {
        if (node == null) return
        println(node.data)
    }

    private fun inOrderTraversal(node: TreeNode?) {
        if (node == null) return
        inOrderTraversal(node.left)
        visit(node)
        inOrderTraversal(node.right)
    }

    fun preOrderTraversal(node: TreeNode?) {
        if (node == null) return
        visit(node)
        inOrderTraversal(node.left)
        inOrderTraversal(node.right)
    }

    fun postOrderTraversal(node: TreeNode?) {
        if (node != null) {
            inOrderTraversal(node.left)
            inOrderTraversal(node.right)
            visit(node)
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        // We needed this code for other files, so check out the code in the library
        val root: TreeNode? = TreeNode.createMinimalBST(array)
        inOrderTraversal(root)
    }
}