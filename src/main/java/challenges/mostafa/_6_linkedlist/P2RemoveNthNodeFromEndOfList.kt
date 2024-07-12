package challenges.mostafa._6_linkedlist

/**
Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:

Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]

Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz

Follow up: Could you do this in one pass?

https://leetcode.com/problems/remove-nth-node-from-end-of-list/submissions/1284006370/
 */

internal object P2RemoveNthNodeFromEndOfList {

    class ListNode(var `val`: Int = 0, var next: ListNode? = null) {
        override fun toString(): String {
            return `val`.toString()
        }
    }

    private fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null) return null

        // Use two pointers, fast and slow, to traverse the list
        var fast = head
        var slow = head

        // Move the fast pointer n nodes ahead
        for (i in 0 until n) {
            fast = fast?.next
        }

        // If the fast pointer is null, it means we need to remove the head node
        if (fast == null) return head.next

        // Move both pointers until the fast pointer reaches the end
        while (fast?.next != null) {
            fast = fast.next
            slow = slow?.next
        }

        // Remove the nth node from the end
        slow?.next = slow?.next?.next

        return head
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        var head = ListNode(1)
        head.next = ListNode(2)
        head.next?.next = ListNode(3)
        head.next?.next?.next = ListNode(4)
        head.next?.next?.next?.next = ListNode(5)
        println(removeNthFromEnd(head, 2)) // Output: [1,2,3,5]

        // Example 2
        head = ListNode(1)

        // Example 3
        head = ListNode(1)
        head.next = ListNode(2)
        println(removeNthFromEnd(head, 1)) // Output: [1]
    }
}