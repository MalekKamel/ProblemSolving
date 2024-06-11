package challenges.mostafa._6_linkedlist

/**
Given the head of a linked list and a value x, partition it such that all nodes less than x come
before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example 1:
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
Example 2:

Input: head = [2,1], x = 2
Output: [1,2]


Constraints:

The number of nodes in the list is in the range [0, 200].
-100 <= Node.val <= 100
-200 <= x <= 200

https://leetcode.com/problems/partition-list/description/
 */

internal object P3PartitionList {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null

        override fun toString(): String {
            return `val`.toString()
        }
    }
    private fun partition(head: ListNode?, x: Int): ListNode? {
        val smallerHead = ListNode(0)
        val greaterHead = ListNode(0)
        var smallerTail = smallerHead
        var greaterTail = greaterHead
        var current = head

        while (current != null) {
            if (current.`val` < x) {
                smallerTail.next = current
                smallerTail = current
            } else {
                greaterTail.next = current
                greaterTail = current
            }
            current = current.next
        }

        smallerTail.next = greaterHead.next
        greaterTail.next = null

        return smallerHead.next
    }
    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        var head = ListNode(1)
        head.next = ListNode(4)
        head.next?.next = ListNode(3)
        head.next?.next?.next = ListNode(2)
        head.next?.next?.next?.next = ListNode(5)
        head.next?.next?.next?.next?.next = ListNode(2)
        val x1 = 3
        var result1 = partition(head, x1)
        printList(result1)
        // Output: [1,2,2,4,3,5]

        // Example 2
        head = ListNode(2)
        head.next = ListNode(1)
        val x2 = 2
        result1 = partition(head, x2)
        printList(result1)
        // Output: [1,2]
    }

    private fun printList(head: ListNode?) {
        var curr = head
        while (curr != null) {
            print("${curr.`val`} ")
            curr = curr.next
        }
        println()
    }
}