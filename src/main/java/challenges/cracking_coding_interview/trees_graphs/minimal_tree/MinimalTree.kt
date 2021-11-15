package challenges.cracking_coding_interview.trees_graphs.minimal_tree

import challenges.cracking_coding_interview.trees_graphs.introduction.Traversals
import challenges.util.TreeNode

/**
 * Given a directed graph, design an algorithm to find out
 * whether there is a route between two nodes.
 */


object MinimalTree {

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        // We needed this code for other files, so check out the code in the library
        val root: TreeNode = TreeNode.createMinimalBST(array) ?: return
        Traversals.inOrderTraversal(root)
        println("Root? " + root.data)
        println("Created BST? " + root.isBST)
        println("Height: " + root.height())
    }
}