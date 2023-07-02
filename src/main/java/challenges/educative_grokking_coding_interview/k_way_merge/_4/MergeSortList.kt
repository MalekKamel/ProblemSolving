package challenges.educative_grokking_coding_interview.k_way_merge._4

import challenges.educative_grokking_coding_interview.LinkedList
import challenges.educative_grokking_coding_interview.LinkedListNode
import challenges.educative_grokking_coding_interview.PrintList.printListWithForwardArrow
import challenges.util.PrintHyphens
import java.util.*

/**
Given an array of k sorted linked lists, your task is to merge them into a single sorted list.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/NEYEqvwL8W6
 */
internal object MergeSortList {
    // helper function
    private fun merge2Lists(head1: LinkedListNode?, head2: LinkedListNode?): LinkedListNode? {
        var head1: LinkedListNode? = head1
        var head2: LinkedListNode? = head2
        val dummy = LinkedListNode(-1)
        var prev: LinkedListNode? = dummy // set prev pointer to dummy node
        // traverse over the lists until both or one of them becomes null
        while (head1 != null && head2 != null) {
            // if l1 value is<=  l2 value, add l1 node to the list
            if (head1.data <= head2.data) {
                prev?.next = head1
                head1 = head1.next
            } else {
                // if l1 value is greater than l2 value, add l2 node to the list
                prev?.next = head2
                head2 = head2.next
            }
            prev = prev?.next
        }
        if (head1 == null) prev?.next = head2 else prev?.next = head1
        return dummy.next
    }

    // Main function
    private fun mergeKLists(lists: List<LinkedList>): LinkedListNode? {
        val temp = 0
        return if (lists.isNotEmpty()) {
            var step = 1
            while (step < lists.size) {
                //temp = step;
                var i = 0
                while (i < lists.size - step) {
                    lists[i].head = merge2Lists(lists[i].head, lists[i + step].head)
                    i += step * 2
                }
                step *= 2
            }
            lists[0].head
        } else null
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val inputLists = listOf(
            listOf(listOf(21, 23, 42), listOf(1, 2, 4)),
            listOf(listOf(11, 41, 51), listOf(21, 23, 42)),
            listOf(listOf(2), listOf(1, 2, 4), listOf(25, 56, 66, 72)),
            listOf(
                listOf(11, 41, 51),
                listOf(2),
                listOf(2),
                listOf(2),
                listOf(1, 2, 4)
            ),
            listOf(
                listOf(10, 30),
                listOf(15, 25),
                listOf(1, 7),
                listOf(3, 9),
                listOf(100, 300),
                listOf(115, 125),
                listOf(10, 70),
                listOf(30, 90)
            )
        )
        for (i in inputLists.indices) {
            println((i + 1).toString() + ".\tInput lists:")
            val llList: MutableList<LinkedList> = ArrayList<LinkedList>()
            for (x in inputLists[i]) {
                val a = LinkedList()
                a.createLinkedList(x.toIntArray())
                llList.add(a)
                print("\t")
                printListWithForwardArrow(a.head)
                println()
            }
            print("\tMerged list: \n\t")
            printListWithForwardArrow(mergeKLists(llList))
            println(
                """
                    
                    ${PrintHyphens.repeat("-", 100)}
                    """.trimIndent()
            )
        }
    }
}