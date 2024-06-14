package challenges.mostafa._6_linkedlist

/**
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
1->4->5,
1->3->4,
2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []

Constraints:

k == lists.length
0 <= k <= 104
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 10^4.

https://leetcode.com/problems/merge-k-sorted-lists/description/
 */

internal object P7MergeKSortedLists {
    class ListNode(var `val`: Int = 0, var next: ListNode? = null)

    private fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) return null

        // Merge lists using divide and conquer approach
        return mergeKListsHelper(lists, 0, lists.size - 1)
    }

    private fun mergeKListsHelper(lists: Array<ListNode?>, start: Int, end: Int): ListNode? {
        if (start == end) return lists[start]

        val mid = start + (end - start) / 2
        val left = mergeKListsHelper(lists, start, mid)
        val right = mergeKListsHelper(lists, mid + 1, end)

        return mergeTwoLists(left, right)
    }

    private fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        val dummy = ListNode(0)
        var current = dummy

        var p1 = l1
        var p2 = l2

        while (p1 != null && p2 != null) {
            if (p1.`val` < p2.`val`) {
                current.next = p1
                p1 = p1.next
            } else {
                current.next = p2
                p2 = p2.next
            }
            current = current.next!!
        }

        if (p1 != null) current.next = p1
        if (p2 != null) current.next = p2

        return dummy.next
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        val list1 = ListNode(1, ListNode(4, ListNode(5)))
        val list2 = ListNode(1, ListNode(3, ListNode(4)))
        val list3 = ListNode(2, ListNode(6))
        val lists: Array<ListNode?> = arrayOf(list1, list2, list3)
        val result1 = mergeKLists(lists)
        printList(result1) // Output: 1 1 2 3 4 4 5 6

        // Example 2
        val result2 = mergeKLists(arrayOf())
        printList(result2) // Output: (empty)

        // Example 3
        val result3 = mergeKLists(arrayOf(null))
        printList(result3) // Output: (empty)
    }

    private fun printList(head: ListNode?) {
        var current = head
        while (current != null) {
            print("${current.`val`} ")
            current = current.next
        }
        println()
    }
}