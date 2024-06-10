package challenges.mostafa._6_linkedlist

/**
Given the head of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the second middle node.



Example 1:
Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.

Example 2:
Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.


Constraints:

The number of nodes in the list is in the range [1, 100].
1 <= Node.val <= 100

https://leetcode.com/problems/middle-of-the-linked-list/description/
 */

internal object P1MiddleOfTheLinkedList {

    /**
     * Example class for a singly-linked list node.
     */
    class ListNode(var `val`: Int = 0, var next: ListNode? = null)

    private fun middleNode(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        var slow = head
        var fast = head

        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        return slow
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        var head1 = ListNode(1)
        head1.next = ListNode(2)
        head1.next?.next = ListNode(3)
        head1.next?.next?.next = ListNode(4)
        head1.next?.next?.next?.next = ListNode(5)

        val middle1 = middleNode(head1)
        println("Example 1 Output: [${middle1?.`val`}, ${middle1?.next?.`val`}, ${middle1?.next?.next?.`val`}]")
        // Output: Example 1 Output: [3, 4, 5]

        // Example 2
        var head2 = ListNode(1)
        head2.next = ListNode(2)
        head2.next?.next = ListNode(3)
        head2.next?.next?.next = ListNode(4)
        head2.next?.next?.next?.next = ListNode(5)
        head2.next?.next?.next?.next?.next = ListNode(6)

        val middle2 = middleNode(head2)
        println("Example 2 Output: [${middle2?.`val`}, ${middle2?.next?.`val`}, ${middle2?.next?.next?.`val`}]")
        // Output: Example 2 Output: [4, 5, 6]
    }
}