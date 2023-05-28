package challenges.educative_grokking_coding_interview.fast_slow_pointers._3

import challenges.educative_grokking_coding_interview.fast_slow_pointers.LinkedList
import challenges.educative_grokking_coding_interview.fast_slow_pointers.LinkedListNode
import challenges.educative_grokking_coding_interview.fast_slow_pointers.PrintList.printListWithForwardArrow
import challenges.util.PrintHyphens


/**
Given a singly linked list, return the middle node of the linked list.
If the number of nodes in the linked list is even, return the second middle node.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/Y5B6zlNOYlA
 */

internal object MiddleNode {
    fun middleNode(head: LinkedListNode?): LinkedListNode? {
        // Initially slow and fast pointers point to head
        var slow: LinkedListNode? = head
        var fast: LinkedListNode? = head
        // Traverse the linked list until fast reaches at the last node or NULL
        while (fast?.next != null) {
            // Move slow pointer one step ahead
            slow = slow?.next
            // Move fast pointer two step ahead
            fast = fast.next?.next //the fast pointer will jump 2 steps
        }
        // Return the slow pointer
        return slow
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
            print(i + 1)
            val list: LinkedList<Int> = LinkedList<Int>()
            list.createLinkedList(input[i])
            print(".\tInput linked list:  ")
            printListWithForwardArrow(list.head)
            print("\tMiddle of the linked list is:  ")
            println(middleNode(list.head)?.data)
            println(PrintHyphens.repeat("-", 100))
        }
    }
}