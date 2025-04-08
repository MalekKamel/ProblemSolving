package challenges.mostafa._7_binarytree

import java.util.LinkedList
import java.util.Queue

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

    /**
     * BFS visits all nodes at the same level before moving to the next level. It uses a queue to keep
     * track of the nodes to visit. This is also known as level-order traversal.
     */
    private fun bfsOrLevelOrderTraversal(root: Node?) {
        if (root == null) {
            return
        }

        val queue: Queue<Node> = LinkedList()
        queue.offer(root) // Enqueue the root

        while (queue.isNotEmpty()) {
            val node = queue.poll() // Dequeue a node
            print("${node.value} ") // Visit the node

            if (node.left != null) {
                queue.offer(node.left) // Enqueue left child
            }
            if (node.right != null) {
                queue.offer(node.right) // Enqueue right child
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Create a Binary Search Tree
        var root = Node(4)
        root.left = Node(2)
        root.right = Node(6)
        root.left?.left = Node(1)
        root.left?.right = Node(3)
        root.right?.left = Node(5)
        root.right?.right = Node(7)

        /*
                         4
                      /     \
                     2       6
                    / \     / \
                   1    3  5    7
         */
        // Traverse the Binary Search Tree
        println("Inorder Traversal:")
        inorderTraversal(root) // Output: 1 2 3 4 5 6 7
        println("\nPreorder Traversal:")
        preorderTraversal(root) // Output: 4 2 1 3 6 5 7
        println("\nPostorder Traversal:")
        postorderTraversal(root) // Output: 1 3 2 5 7 6 4


        println("\nBFS or Level-order Traversal:")
        bfsOrLevelOrderTraversal(root) // Output: 4 2 6 1 3 5 7

        /*
                 1
              /     \
             2       3
            / \     / \
           4    5  6    7
 */
        root = Node(1)
        root.left = Node(2)
        root.right = Node(3)
        root.left?.left = Node(4)
        root.left?.right = Node(5)
        root.right?.left = Node(6)
        root.right?.right = Node(7)

        println("\nBFS or Level-order Traversal:")
        bfsOrLevelOrderTraversal(root) // Output: 1 2 3 4 5 6 7
    }

}