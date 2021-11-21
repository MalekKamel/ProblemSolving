package challenges.cracking_coding_interview.trees_graphs.bst_sequences

import challenges.util.TreeNode
import java.util.*


object BstSequences {

    private fun weaveLists(
        first: LinkedList<Int>,
        second: LinkedList<Int>,
        results: ArrayList<LinkedList<Int>>,
        prefix: LinkedList<Int?>
    ) {
        /* One list is empty. Add the remainder to [a cloned] prefix and
		 * store result. */
        if (first.size == 0 || second.size == 0) {
            val result = prefix.clone() as LinkedList<Int>
            result.addAll(first)
            result.addAll(second)
            results.add(result)
            return
        }

        /* Recurse with head of first added to the prefix. Removing the
		 * head will damage first, so we’ll need to put it back where we
		 * found it afterwards. */
        val headFirst = first.removeFirst()
        prefix.addLast(headFirst)
        weaveLists(first, second, results, prefix)
        prefix.removeLast()
        first.addFirst(headFirst)

        /* Do the same thing with second, damaging and then restoring
		 * the list.*/
        val headSecond = second.removeFirst()
        prefix.addLast(headSecond)
        weaveLists(first, second, results, prefix)
        prefix.removeLast()
        second.addFirst(headSecond)
    }

    private fun allSequences(node: TreeNode?): ArrayList<LinkedList<Int>> {
        val result = ArrayList<LinkedList<Int>>()
        if (node == null) {
            result.add(LinkedList())
            return result
        }
        val prefix = LinkedList<Int?>()
        prefix.add(node.data)

        /* Recurse on left and right subtrees. */
        val leftSeq = allSequences(node.left)
        val rightSeq = allSequences(node.right)

        /* Weave together each list from the left and right sides. */for (left in leftSeq) {
            for (right in rightSeq) {
                val weaved = ArrayList<LinkedList<Int>>()
                weaveLists(left, right, weaved, prefix)
                result.addAll(weaved)
            }
        }
        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val node = TreeNode(100)
        val array = intArrayOf(100, 50, 20, 75, 150, 120, 170)
        for (a in array) {
            node.insertInOrder(a)
        }
        val allSeq = allSequences(node)
        for (list in allSeq) {
            println(list)
        }
        println(allSeq.size)
    }

}