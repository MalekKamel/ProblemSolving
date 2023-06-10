package challenges.educative_grokking_coding_interview.linkedlist_inplace_reveresal._2

import challenges.educative_grokking_coding_interview.LinkedList
import challenges.educative_grokking_coding_interview.LinkedListNode
import challenges.educative_grokking_coding_interview.PrintList.printListWithForwardArrow
import challenges.educative_grokking_coding_interview.fast_slow_pointers._6.LinkedListReversal


/**
The task is to reverse the nodes in groups of k
in a given linked list, where k
is a positive integer, and at most the length of the linked list. If any remaining nodes are not part of a group of k
, they should remain in their original order.
It is not allowed to change the values of the nodes in the linked list. Only the order of the nodes can be modified.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/YMPXpl4JX30
 */

object Reorder {
    private fun reverseKGroups(head: LinkedListNode?, k: Int): LinkedListNode? {
        val dummy = LinkedListNode(0)
        dummy.next = head
        var ptr: LinkedListNode? = dummy
        while (ptr != null) {
            var tracker = ptr
            for (i in 0 until k) {
                if (tracker == null) {
                    break
                }
                tracker = tracker.next
            }
            if (tracker == null) {
                break
            }
            val updatedNodes: Array<LinkedListNode?> =
                LinkedListReversal.reverseLinkedList(ptr.next, k)
            val previous = updatedNodes[0]
            val current = updatedNodes[1]
            val lastNodeOfReversedGroup = ptr.next
            lastNodeOfReversedGroup!!.next = current
            ptr.next = previous
            ptr = lastNodeOfReversedGroup
        }
        return dummy.next
    }

    // Driver code
    @JvmStatic
    fun main(args: Array<String>) {
        val inputList: List<List<Int>> = ArrayList(
            listOf(
                listOf(1, 2, 3, 4, 5, 6, 7, 8),
                listOf(3, 4, 5, 6, 2, 8, 7, 7),
                listOf(1, 2, 3, 4, 5),
                listOf(1, 2, 3, 4, 5, 6, 7),
                listOf(1)
            )
        )
        val k: List<Int> = ArrayList(listOf(3, 2, 1, 7, 1))
        for (i in inputList.indices) {
            val inputLinkedList = LinkedList()
            inputLinkedList.createLinkedList(inputList[i].toIntArray())
            print((i + 1).toString() + ".\tLinked list: ")
            printListWithForwardArrow(inputLinkedList.head)
            println()
            print("\n\tReversed linked list: ")
            val result = reverseKGroups(inputLinkedList.head, k[i])
            printListWithForwardArrow(result)
            println()
            val hyphens = String(CharArray(100)).replace('\u0000', '-')
            println(hyphens)
        }
    }
}