package challenges.mostafa._6_linkedlist

/**
Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again
by continuously following the next pointer. Internally, pos is used to denote the index of
the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

Example 2:
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.

Example 3:
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.

Constraints:

The number of the nodes in the list is in the range [0, 10^4].
-10^5 <= Node.val <= 10^5
pos is -1 or a valid index in the linked-list.


Follow up: Can you solve it using O(1) (i.e. constant) memory?

https://leetcode.com/problems/linked-list-cycle/description/
 */

internal object P4LinkedListCycle {

    /**
    The time complexity of this solution is O(n), where n is the number of nodes in the linked list.
    The slow and fast pointers will meet within the first loop through the cycle if a cycle exists.
    If there is no cycle, the fast pointer will reach the end of the list in roughly n/2 steps.

    The space complexity is O(1) since we only use a constant amount of extra space for the slow
    and fast pointers, regardless of the size of the linked list.
     */
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    private fun hasCycle(head: ListNode?): Boolean {
        if (head?.next == null) return false

        var slow = head
        var fast = head.next

        while (slow != fast) {
            // fast?.next == null: This checks if the fast pointer is at the last node. If it is,
            // and there's no cycle, the next step for fast would be null, and the loop would terminate
            // in the next iteration.
            //
            // fast.next?.next == null: This checks if the fast pointer is at the second-to-last node.
            // If it is, the next step for fast would be the last node, and the step after that would be null.
            // This condition is important because fast moves two steps at a time. If there's no cycle,
            // fast will eventually reach a point where it (or the node after it) is null.
            //
            // In either of these cases, we know there's no cycle, so we return false.
            if (fast?.next == null || fast.next?.next == null) return false
            slow = slow?.next
            fast = fast.next?.next
        }

        // If the while loop terminates because slow == fast, it means that the fast pointer has
        // eventually caught up to the slow pointer. This can only happen if there is a cycle in
        // the linked list. Therefore, in this case, the function returns true.
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        val node1 = ListNode(3)
        val node2 = ListNode(2)
        val node3 = ListNode(0)
        val node4 = ListNode(-4)
        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node2
        println(hasCycle(node1)) // Output: true

        // Example 2
        val node5 = ListNode(1)
        val node6 = ListNode(2)
        node5.next = node6
        node6.next = node5
        println(hasCycle(node5)) // Output: true

        // Example 3
        val node7 = ListNode(1)
        println(hasCycle(node7)) // Output: false
    }
}