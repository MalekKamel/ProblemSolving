package challenges.cracking_coding_interview.linked_list.kth_to_last

import challenges.data_structure.LinkedListNode
import challenges.util.AssortedMethods


/*
 * Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
 */
object KthToLastA {
    private fun printKthToLast(head: LinkedListNode?, k: Int): Int {
        if (head == null) {
            return 0
        }
        val index = printKthToLast(head.next, k) + 1
        if (index == k) {
            println(k.toString() + "th to last node is " + head.data)
        }
        return index
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(0, 1, 2, 3, 4, 5, 6)
        val head: LinkedListNode = AssortedMethods.createLinkedListFromArray(array)
        for (i in 0..array.size + 1) {
            printKthToLast(head, i)
        }
    }
}