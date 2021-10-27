package challenges.cracking_coding_interview.linked_list.kth_to_last

import challenges.data_structure.LinkedListNode
import challenges.util.AssortedMethods


/*
 * Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
 */
object KthToLastB {
    class Index {
        var value = 0
    }

    private fun kthToLast(head: LinkedListNode?, k: Int): LinkedListNode? {
        val idx = Index()
        return kthToLast(head, k, idx)
    }

    private fun kthToLast(head: LinkedListNode?, k: Int, idx: Index): LinkedListNode? {
        if (head == null)
            return null
        val node = kthToLast(head.next, k, idx)
        idx.value = idx.value + 1
        return if (idx.value == k) {
            head
        } else node
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(0, 1, 2, 3, 4, 5, 6)
        val head: LinkedListNode = AssortedMethods.createLinkedListFromArray(array)
        for (i in 0..array.size + 1) {
            val node = kthToLast(head, i)
            val nodeValue = if (node == null) "null" else "" + node.data
            println("$i: $nodeValue")
        }
    }
}