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

    /**
     *  Problem Explanation
     * The problem asks us to find the middle node of a given singly linked list. If the list has
     * an odd number of nodes, there will be a single middle node. If the list has an even number
     * of nodes, there will be two middle nodes, and we need to return the second one. We are given
     * the head of the linked list as input and need to return the middle node itself (which includes all subsequent nodes in the list).
     *
     * 2. Pattern Identification and Rationale
     * The most efficient and straightforward pattern to solve this problem is the "two pointers" approach,
     * often referred to as the "fast and slow pointer" method.
     *
     * Rationale:
     *
     * Efficiency: This approach allows us to find the middle node in a single pass through the linked list.
     * We avoid the need to first count the number of nodes and then traverse again to the middle.
     * Direct Traversal: By using two pointers moving at different speeds, we can simultaneously reach
     * the end of the list (with the fast pointer) and the middle of the list (with the slow pointer).
     * Handling Odd and Even Lengths: The relative speeds of the pointers naturally handle both odd
     * and even length lists, ensuring we land on the correct middle node (or the second middle node in the even case).
     *
     * Advantages:
     *
     * Time Complexity: O(n) - We traverse the list at most once.
     * Space Complexity: O(1) - We only use a constant amount of extra space for the two pointers.
     * 3. Solution Breakdown
     *
     * Here's how the fast and slow pointer approach works:
     *
     * Initialization:
     *
     * Create two pointers, slow and fast, and initialize both to the head of the linked list.
     * Iteration:
     *
     * Iterate through the linked list as long as the fast pointer is not null and fast.next is not null.
     * In each iteration:
     * Move the slow pointer one step forward (slow = slow.next).
     * Move the fast pointer two steps forward (fast = fast.next.next).
     * Middle Node Identification:
     *
     * When the loop terminates, the fast pointer will have reached the end of the list (or the node
     * just before the end in the case of an odd length list). At this point, the slow pointer will
     * be pointing to the middle node (or the first of the two middle nodes in an even length list).
     * Since the problem asks for the second middle node in the even case, the slow pointer will
     * naturally be at the correct position.
     * Return:
     *
     * Return the slow pointer. This pointer now points to the middle node (or the second middle node).
     * Logic behind each step:
     *
     * Initialization: Starting both pointers at the head ensures they begin their traversal from
     * the same starting point.
     * Iteration with different speeds: The fast pointer moves twice as fast as the slow pointer.
     * This means that for every one step the slow pointer takes, the fast pointer takes two steps.
     * Termination Condition: The loop continues as long as the fast pointer and the node after it are valid.
     * This condition ensures that the fast pointer reaches the end (or just before the end), allowing
     * the slow pointer to reach the middle.
     * Middle Node Location: When the fast pointer reaches the end, the slow pointer will have traversed
     * half the distance. In an odd length list (e.g., 1->2->3->4->5), when fast reaches 5 (and fast.next is null),
     * slow will be at 3. In an even length list (e.g., 1->2->3->4->5->6), when fast reaches 6 (and fast.next is null),
     * slow will be at 4, which is the second middle node.
     *
     * 4. Time Complexity
     * The time complexity of this solution is O(n), where n is the number of nodes in the linked list.
     * This is because, in the worst case, the fast pointer traverses the entire list, and the slow
     * pointer traverses approximately half the list. Since the number of operations is directly proportional
     * to the length of the list, the time complexity is linear.
     */
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