package challenges.cracking_coding_interview.trees_graphs.list_of_depth

import challenges.util.AssortedMethods
import challenges.util.TreeNode
import java.util.*


/**
 * Given a binary tree, design an algorithm which creates a linked list of all the nodes at
 * each depth (e.g., if you have a tree with depth D, you'll have D linked lists).
 */


object ListOfDepthDfs {

    private fun createLevelLinkedList(
        root: TreeNode?,
        lists: ArrayList<LinkedList<TreeNode>>,
        level: Int
    ) {
        if (root == null) return
        val list: LinkedList<TreeNode>?
        if (lists.size == level) { // Level not contained in list
            list = LinkedList<TreeNode>()
            /* Levels are always traversed in order. So, if this is the first time we've visited level i,
			 * we must have seen levels 0 through i - 1. We can therefore safely add the level at the end. */
            lists.add(list)
        } else {
            list = lists[level]
        }
        list.add(root)
        createLevelLinkedList(root.left, lists, level + 1)
        createLevelLinkedList(root.right, lists, level + 1)
    }

    private fun createLevelLinkedList(root: TreeNode?): ArrayList<LinkedList<TreeNode>> {
        val lists: ArrayList<LinkedList<TreeNode>> = ArrayList<LinkedList<TreeNode>>()
        createLevelLinkedList(root, lists, 0)
        return lists
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