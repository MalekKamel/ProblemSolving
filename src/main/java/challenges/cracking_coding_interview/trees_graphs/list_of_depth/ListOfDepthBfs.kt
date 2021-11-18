package challenges.cracking_coding_interview.trees_graphs.list_of_depth

import challenges.util.AssortedMethods
import challenges.util.TreeNode
import java.util.*


/**
 * Given a binary tree, design an algorithm which creates a linked list of all the nodes at
 * each depth (e.g., if you have a tree with depth D, you'll have D linked lists).
 */

object ListOfDepthBfs {

    private fun createLevelLinkedList(root: TreeNode?): ArrayList<LinkedList<TreeNode>> {
        val result: ArrayList<LinkedList<TreeNode>> = ArrayList<LinkedList<TreeNode>>()

        /* "Visit" the root */
        var current: LinkedList<TreeNode> = LinkedList<TreeNode>()
        if (root != null) {
            current.add(root)
        }
        while (!current.isEmpty()) {
            result.add(current) // Add previous level
            val parents: LinkedList<TreeNode> = current // Go to next level
            current = LinkedList<TreeNode>()
            for (parent in parents) {
                /* Visit the children */
                if (parent.left != null) {
                    current.add(parent.left!!)
                }
                if (parent.right != null) {
                    current.add(parent.right!!)
                }
            }
        }
        return result
    }

    private fun printResult(result: ArrayList<LinkedList<TreeNode>>) {
        for ((depth, entry) in result.withIndex()) {
            val i: Iterator<TreeNode> = entry.listIterator()
            print("Link list at depth $depth:")
            while (i.hasNext()) {
                print(" " + i.next().data)
            }
            println()
        }
    }


    @JvmStatic
    fun main(args: Array<String>) {
        val nodesFlattened = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val root: TreeNode = AssortedMethods.createTreeFromArray(nodesFlattened) ?: return
        val list: ArrayList<LinkedList<TreeNode>> = createLevelLinkedList(root)
        printResult(list)
    }

}