package challenges.educative_grokking_coding_interview.linkedlist_inplace_reveresal._3

import challenges.educative_grokking_coding_interview.LinkedList
import challenges.educative_grokking_coding_interview.LinkedListNode
import challenges.educative_grokking_coding_interview.PrintList.printListWithForwardArrow
import challenges.util.PrintHyphens


/**
Given a singly linked list with n
nodes and two positions, left and right, the objective is to reverse the nodes of the list from left to right. Return the modified list.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/gx9zZnEEM7r
 */

object ReverseLinkedList {
    // Assume that the linked list has left to right nodes.
    // Reverse left to right nodes of the given linked list.
    fun reverse(head: LinkedListNode, left: Int, right: Int): LinkedListNode? {
        var right = right
        var prev: LinkedListNode? = null
        var curr: LinkedListNode? = head
        while (right >= left) {
            val next: LinkedListNode? = curr?.next
            curr?.next = prev
            prev = curr
            curr = next
            right--
        }
        return prev // Returns the head of the reversed list
    }

    // Reverses the sublist between left and right in the linked list
    fun reverseBetween(head: LinkedListNode?, left: Int, right: Int): LinkedListNode? {
        var curr: LinkedListNode? = head
        var lpn: LinkedListNode? = null // Previous node before the sublist
        var right_n: LinkedListNode? = null // Node after the sublist
        var reverse_head: LinkedListNode? = null // Head of the reversed sublist
        var count = 1
        while (count < left && curr != null) {
            lpn = curr
            curr = curr.next
            count++
        }
        if (curr != null) {
            var rpn: LinkedListNode? = curr
            while (count <= right && rpn != null) {
                right_n = rpn
                rpn = right_n.next
                count++
            }
            if (right_n != null) {
                reverse_head = reverse(curr, left, right)
            }
            if (lpn != null) {
                lpn.next = reverse_head // Connects the previous node to the reversed sublist
            }
            if (rpn != null) {
                var tmp: LinkedListNode? = reverse_head
                while (tmp?.next != null) {
                    tmp = tmp.next
                }
                // Connects the last node of the reversed sublist to the next node after the sublist
                tmp?.next = rpn
            }
        }
        return if (lpn != null) {
            head // Returns the original head if there are nodes before the sublist
        } else {
            reverse_head // Returns the head of the reversed sublist if there are no nodes before the sublist
        }
    }

    // Driver Code
    @JvmStatic
    fun main(args: Array<String>) {
        val input = arrayOf(
            intArrayOf(1, 2, 3, 4, 5, 6, 7),
            intArrayOf(6, 9, 3, 10, 7, 4, 6),
            intArrayOf(6, 9, 3, 4),
            intArrayOf(6, 2, 3, 6, 9),
            intArrayOf(6, 2)
        )
        val left = intArrayOf(1, 3, 2, 1, 1)
        val right = intArrayOf(5, 6, 4, 3, 2)
        for (i in input.indices) {
            print(i + 1)
            val list = LinkedList()
            list.createLinkedList(input[i])
            print(".\tOriginal linked list is:  ")
            printListWithForwardArrow(list.head)
            print("\tReversed linked list is:  ")
            printListWithForwardArrow(
                reverseBetween(
                    list.head,
                    left[i], right[i]
                )
            )
            println(PrintHyphens.repeat("-", 100))
        }
    }
}