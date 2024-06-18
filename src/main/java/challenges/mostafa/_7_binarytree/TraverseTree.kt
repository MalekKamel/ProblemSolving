package challenges.mostafa._7_binarytree

/**

 */

internal object TraverseTree {

    // Define a node class for the binary tree
    data class TreeNode(
        var value: Int,
        var left: TreeNode? = null,
        var right: TreeNode? = null
    )

    // In-order traversal
    private fun inorderTraversal(root: TreeNode?) {
        if (root == null) return

        inorderTraversal(root.left)
        print("${root.value} ")
        inorderTraversal(root.right)
    }

    private fun reverseInorderTraversal(root: TreeNode?) {
        if (root == null) return

        reverseInorderTraversal(root.right)
        print("${root.value} ")
        reverseInorderTraversal(root.left)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(1)
        root.left = TreeNode(2)
        root.right = TreeNode(3)
        root.left?.left = TreeNode(4)
        root.left?.right = TreeNode(5)

        println("In-order traversal:")
        inorderTraversal(root)
        println("\nReverse in-order traversal:")
        reverseInorderTraversal(root)
    }
}