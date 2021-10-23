package challenges.cracking_coding_interview.linked_list.kth_to_last

import challenges.data_structure.LinkedListNode
import challenges.util.AssortedMethods


/*
 * Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
 */
object KthToLastC {
    private fun nthToLast(head: LinkedListNode?, k: Int): LinkedListNode? {
        var p1 = head
        var p2 = head

        /* Move p1 k nodes into the list.*/for (i in 0 until k) {
            if (p1 == null) return null // Out of bounds
            p1 = p1.next
        }

        /* Move them at the same pace. When p1 hits the end,
		 * p2 will be at the right element. */while (p1 != null) {
            p1 = p1.next
            p2 = p2!!.next
        }
        return p2
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(0, 1, 2, 3)
        val head: LinkedListNode = AssortedMethods.createLinkedListFromArray(array)
        for (i in 0..array.size + 1) {
            val node = nthToLast(head, i)
            val nodeValue = if (node == null) "null" else "" + node.data
            println("$i: $nodeValue")
        }
    }
}