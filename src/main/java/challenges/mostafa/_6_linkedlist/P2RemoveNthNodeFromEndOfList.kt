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

    /**
     * Problem Explanation
     *
     * The problem asks us to take a singly linked list and an integer n as input. Our goal is to
     * remove the nth node from the end of the linked list and return the head of the modified list.
     * For example, if the list is 1 -> 2 -> 3 -> 4 -> 5 and n=2, we need to remove the second to last
     * node (which is 4), resulting in the list 1 -> 2 -> 3 -> 5.
     *
     * Pattern Identification and Rationale
     *
     * The most efficient approach to solve this problem in a single pass involves using the two-pointer
     * technique. This pattern is suitable because it allows us to maintain a relative distance between
     * two pointers as we traverse the linked list. By carefully positioning these pointers, we can
     * identify the node to be removed and its preceding node in a single traversal.
     *
     * The advantage of the two-pointer approach here is its efficiency. It allows us to solve the problem
     * in linear time complexity, O(L), where L is the length of the linked list, and with constant
     * space complexity, O(1), as we only need to keep track of a few pointers.
     *
     * Solution Breakdown
     *
     * Here's a breakdown of the steps involved in the two-pointer solution:
     *
     * Handle Edge Case: If the list has only one node and n=1, we need to return an empty list.
     *
     * Initialize Two Pointers: Create two pointers, let's call them slow and fast, and initialize
     * both to the head of the linked list.
     *
     * Move the fast pointer: Move the fast pointer n steps ahead. Now, the fast pointer is n nodes
     * ahead of the slow pointer.
     *
     * Handle Removal of Head: If, after moving fast n steps, fast becomes null, it means that the nth
     *   node from the end is actually the head of the list. In this case, we simply return the next
     *   node of the head as the new head.
     *
     * Move Both Pointers: Now, move both the slow and fast pointers one step at a time until the fast
     * pointer reaches the end of the list (i.e., fast becomes null). Since fast was initially n steps
     * ahead, when fast reaches the end, slow will be pointing to the node just before the nth
     *  node from the end.
     *
     * Remove the Nth Node: Once fast reaches the end, the node to be removed is the one after the slow
     * pointer. We can remove this node by updating the next pointer of the slow node to point to the node
     * after the one to be removed (slow.next = slow.next?.next).
     *
     * Return Head: Finally, return the original head of the linked list (which might have been updated in step 4).
     *
     * Time Complexity
     *
     * The time complexity of this solution is O(L), where L is the number of nodes in the linked list.
     * This is because we traverse the list at most twice (once with the fast pointer initially and then
     * once with both slow and fast pointers). In the single-pass optimized solution, we traverse the list
     * exactly once.
     */
    private fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head?.next == null || n == 1) {
            return null
        }

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
        head.next = ListNode(2)
        println(removeNthFromEnd(head, 1)) // Output: [1]
    }
}