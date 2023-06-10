package challenges.educative_grokking_coding_interview.fast_slow_pointers._2

import challenges.educative_grokking_coding_interview.LinkedList
import challenges.educative_grokking_coding_interview.LinkedListNode
import challenges.educative_grokking_coding_interview.PrintList

/**
Check whether or not a linked list contains a cycle. If a cycle exists, return TRUE.
Otherwise, return FALSE. The cycle means that at least one node can be reached
again by traversing the next pointer.
 */

object CycleDetection {
    private fun detectCycle(head: LinkedListNode?): Boolean {
        if (head == null) {
            return false
        }
        // Initialize two pointers, slow and fast, to the head of the linked list
        var slow = head
        var fast = head

        // Run the loop until we reach the end of the
        // linked list or find a cycle
        while (fast?.next != null) {
            // Move the slow pointer one step at a time
            slow = slow?.next
            // Move the fast pointer two steps at a time
            fast = fast.next?.next

            // If there is a cycle, the slow and fast pointers will meet
            if (slow == fast) {
                return true
            }
        }
        // If we reach the end of the linked list and haven't found a cycle, return False
        return false
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val input = arrayOf(
            intArrayOf(2, 4, 6, 8, 10, 12),
            intArrayOf(1, 3, 5, 7, 9, 11),
            intArrayOf(0, 1, 2, 3, 4, 6),
            intArrayOf(3, 4, 7, 9, 11, 17),
            intArrayOf(5, 1, 4, 9, 2, 3)
        )
        val pos = intArrayOf(0, -1, 1, -1, 2)
        for (i in input.indices) {
            val list = LinkedList()
            list.createLinkedList(input[i])
            print("${i + 1}" + ".\tInput:")
            print("\t")
            if (pos[i] == -1) {
                PrintList.printListWithForwardArrow(list.head)
            } else {
                PrintList.printListWithForwardArrowLoop(list.head)
            }
            println(
                """
	pos: ${pos[i]}"""
            )
            if (pos[i] != -1) {
                val length: Int = list.getLength(list.head)
                val lastNode: LinkedListNode? = list.getNode(list.head, length - 1)
                lastNode?.next = list.getNode(list.head, pos[i])
            }
            println(
                """
	Detected Cycle =  ${detectCycle(list.head)}"""
            )
            println(String(CharArray(100)).replace('\u0000', '-'))
        }
    }
}