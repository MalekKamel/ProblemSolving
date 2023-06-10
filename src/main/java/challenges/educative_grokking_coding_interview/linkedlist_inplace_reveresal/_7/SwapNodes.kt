package challenges.educative_grokking_coding_interview.linkedlist_inplace_reveresal._7

import challenges.educative_grokking_coding_interview.LinkedListNode

/**
Given a singly linked list, swap every two adjacent nodes of the linked list. After the swap, return the head of the linked list.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/m2O9Y45BqWr
 */

object SwapNodes {
    private fun swapPairs(head: LinkedListNode?): LinkedListNode? {
        if (head?.next == null) {
            return head
        }
        val newHead = head.next
        head.next = swapPairs(newHead?.next)
        newHead?.next = head
        return newHead
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val head = LinkedListNode(1)
        head.next = LinkedListNode(2)
        head.next!!.next = LinkedListNode(3)
        head.next!!.next!!.next = LinkedListNode(4)
        val swapped = swapPairs(head)
        var curr = swapped
        while (curr != null) {
            print("${curr.next}")
            curr = curr.next
        }
        // Output: 2 1 4 3
    }
}