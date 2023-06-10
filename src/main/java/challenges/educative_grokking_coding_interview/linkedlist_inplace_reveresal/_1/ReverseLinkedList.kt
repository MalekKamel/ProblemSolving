package challenges.educative_grokking_coding_interview.linkedlist_inplace_reveresal._1

import challenges.educative_grokking_coding_interview.LinkedList
import challenges.educative_grokking_coding_interview.LinkedListNode
import challenges.educative_grokking_coding_interview.PrintList.printListWithForwardArrow

/**
Given a singly linked list with n nodes and two positions, left and right, the objective
is to reverse the nodes of the list from left to right. Return the modified list.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/3jNq515Ppv4
 */
object ReverseLinkedList {
    private fun reverse(head: LinkedListNode?): LinkedListNode? {
        // initialize prev and next pointer to NULL
        var head: LinkedListNode? = head
        var prev: LinkedListNode? = null
        var next: LinkedListNode? = null
        // set current pointer to the head node
        var curr: LinkedListNode? = head
        // while the current pointer is not NULL
        while (curr != null) {
            // set the next pointer to the next node in the list
            next = curr.next
            // reverse the current node's pointer to point to the previous node
            curr.next = prev
            // set the previous pointer to the current node
            prev = curr
            // move the current pointer to the next node
            curr = next
        }
        // set the head pointer to the last node, which is the new first node after reversal
        head = prev
        // return the new head pointer
        return head
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val input = arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(1, 2, 3, 4, 5, 6),
            intArrayOf(3, 2, 1),
            intArrayOf(10),
            intArrayOf(1, 2)
        )
        for (i in input.indices) {
            val inputLinkedList = LinkedList()
            inputLinkedList.createLinkedList(input[i])
            print((i + 1).toString() + ".\tInput linked list: ")
            printListWithForwardArrow(inputLinkedList.head)
            print("\n\tReversed linked list: ")
            printListWithForwardArrow(reverse(inputLinkedList.head))
            println()
            println(String(CharArray(100)).replace('\u0000', '-'))
        }
    }
}