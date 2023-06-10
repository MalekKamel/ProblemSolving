package challenges.educative_grokking_coding_interview.linkedlist_inplace_reveresal._5

import challenges.educative_grokking_coding_interview.LinkedList
import challenges.educative_grokking_coding_interview.LinkedListNode
import challenges.educative_grokking_coding_interview.PrintList.printListWithForwardArrow
import challenges.util.PrintHyphens


/**
Given the linked list and an integer, k, return the head of the linked list after swapping the values of the kth
node from the beginning and the kth node from the end of the linked list.
https://www.educative.io/courses/grokking-coding-interview-patterns-java/RLYlOPvWk5w
 */

object SwapNodes {
    private fun swap(node1: LinkedListNode?, node2: LinkedListNode?) {
        val temp: Int? = node1?.data
        node1?.data = node2?.data ?: 0
        node2?.data = temp ?: 0
    }

    private fun swapNodes(head: LinkedListNode?, k: Int): LinkedListNode? {
        if (head == null) {
            return null
        }
        var count = 0

        // front and end pointers will be used to track the kth node from
        // the start and end of the linked list, respectively
        var front: LinkedListNode? = null
        var end: LinkedListNode? = null
        var curr: LinkedListNode? = head
        while (curr != null) {
            count += 1
            // if end is not null, it means the kth node from the beginning has
            // been found, we can start moving the end pointer to find the
            // kth node from the end of the linked list
            if (end != null) {
                end = end.next
            }
            // if the count has become equal to k, it means the curr is
            // pointing the kth node at the begining of the linked list
            if (count == k) {
                front = curr
                end = head
            }
            curr = curr.next
        }
        // swap the values of two nodes
        swap(front, end)
        return head
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val input = arrayOf(
            intArrayOf(1, 2, 3, 4, 5, 6, 7),
            intArrayOf(6, 9, 3, 10, 7, 4, 6),
            intArrayOf(6, 9, 3, 4),
            intArrayOf(6, 2, 3, 6, 9),
            intArrayOf(6, 2)
        )
        val k = intArrayOf(
            2, 3, 2, 3, 1
        )
        for (i in input.indices) {
            print(i + 1)
            val list = LinkedList()
            list.createLinkedList(input[i])
            print(".\tOriginal linked list is: ")
            printListWithForwardArrow(list.head)
            println("\tk: " + k[i])
            print("\tLinked list with swapped values: ")
            printListWithForwardArrow(swapNodes(list.head, k[i]))
            println(PrintHyphens.repeat("-", 100))
        }
    }
}