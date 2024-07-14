package challenges.mostafa._7_binarytree

/**

 */

internal object TraverseTree {

    class Node(var value: Int) {
        var left: Node? = null
        var right: Node? = null
    }

    // Inorder Traversal
    private fun inorderTraversal(root: Node?) {
        if (root == null) return
        inorderTraversal(root.left)
        print("${root.value} ")
        inorderTraversal(root.right)
    }

    // Preorder Traversal
    private fun preorderTraversal(root: Node?) {
        if (root == null) return
        print("${root.value} ")
        preorderTraversal(root.left)
        preorderTraversal(root.right)
    }

    // Postorder Traversal
    private fun postorderTraversal(root: Node?) {
        if (root == null) return
        postorderTraversal(root.left)
        postorderTraversal(root.right)
        print("${root.value} ")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Create a Binary Search Tree
        val root = Node(4)
        root.left = Node(2)
        root.right = Node(6)
        root.left?.left = Node(1)
        root.left?.right = Node(3)
        root.right?.left = Node(5)
        root.right?.right = Node(7)

        // Traverse the Binary Search Tree
        println("Inorder Traversal:")
        inorderTraversal(root) // Output: 1 2 3 4 5 6 7
        println("\nPreorder Traversal:")
        preorderTraversal(root) // Output: 4 2 1 3 6 5 7
        println("\nPostorder Traversal:")
        postorderTraversal(root) // Output: 1 3 2 5 7 6 4
    }

}