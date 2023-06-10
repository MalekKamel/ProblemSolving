package challenges.educative_grokking_coding_interview.linkedlist_inplace_reveresal._6

import challenges.educative_grokking_coding_interview.LinkedList
import challenges.educative_grokking_coding_interview.LinkedListNode
import challenges.educative_grokking_coding_interview.PrintList.printListWithForwardArrow
import challenges.util.PrintHyphens


/**
You’re given a linked list. Your task is to reverse all of the nodes that are present in the groups with an even number of nodes in them. The nodes in the linked list are sequentially assigned to non-empty groups whose lengths form the sequence of the natural numbers
(1, 2, 3, 4...)
(1,2,3,4...).
The length of a group is the number of nodes assigned to it. In other words:

The 11st

node is assigned to the first group.

The 22nd

and 33rd

nodes are assigned to the second group.
The 44th, 55th, and 66th nodes are assigned to the third group and so on.

You have to return the head of the modified linked list.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/q2ln2wzlrBk
 */

object ReverseNodes {

    private fun reverseEvenLengthGroups(head: LinkedListNode?): LinkedListNode? {
        var prev: LinkedListNode? = head // Node immediately before the current group
        var node: LinkedListNode?
        var reverse: LinkedListNode?
        var nodeNext: LinkedListNode?
        var curr: LinkedListNode?
        var prevNext: LinkedListNode?
        var l =
            2 // The head doesn't need to be reversed since it's a group of one node, so starts with length 2
        var n = 0
        while (prev?.next != null) {
            node = prev
            n = 0
            for (i in 0 until l) {
                if (node?.next == null) break
                n += 1
                node = node.next
            }
            if (n % 2 != 0) // odd length
                prev = node else {
                reverse = node?.next
                curr = prev.next
                for (j in 0 until n) {
                    nodeNext = curr?.next
                    curr?.next = reverse
                    reverse = curr
                    curr = nodeNext
                }
                prevNext = prev.next
                prev.next = node
                prev = prevNext
            }
            l += 1
        }
        return head
    }

    @JvmStatic
    fun main(args: Array<String>) {
        LinkedList()
        val inputList = listOf(1, 2, 3, 4)
        val inputList1 = listOf(10, 11, 12, 13, 14)
        val inputList2 = listOf(15)
        val inputList3 = listOf(16, 17)
        val inputLinkList1 = LinkedList()
        inputLinkList1.createLinkedList(inputList.toIntArray())
        val inputLinkList2 = LinkedList()
        inputLinkList2.createLinkedList(inputList1.toIntArray())
        val inputLinkList3 = LinkedList()
        inputLinkList3.createLinkedList(inputList2.toIntArray())
        val inputLinkList4 = LinkedList()
        inputLinkList4.createLinkedList(inputList3.toIntArray())
        val listHeads: List<LinkedListNode> = listOf(
            inputLinkList1.head!!,
            inputLinkList2.head!!,
            inputLinkList3.head!!,
            inputLinkList4.head!!
        )
        for (i in listHeads.indices) {
            println("${i + 1}.\tIf we reverse the even length groups of the linked list:")
            printListWithForwardArrow(listHeads[i])
            println("\n\n\twe will get: \t")
            printListWithForwardArrow(reverseEvenLengthGroups(listHeads[i]))
            println("\n")
            println(PrintHyphens.repeat("-", 100))
        }
    }
}