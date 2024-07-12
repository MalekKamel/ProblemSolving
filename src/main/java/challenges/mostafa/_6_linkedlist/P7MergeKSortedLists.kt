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

    /**
    The sequence of computing the equation `val mid = start + (end - start) / 2` on a computer
    can be broken down as follows:

    1. **Evaluate the Subtraction**: The first step is to compute `end - start`. This will give
    the length of the range or interval.

    2. **Perform the Division**: Next, the result of `end - start` is divided by 2. This division
    operation will perform integer division, which means the result will be rounded down to the nearest integer.

    3. **Add the Offset to the Start**: Finally, the result of the division `(end - start) / 2`
    is added to the `start` value to determine the middle index `mid`.

    Here's the step-by-step breakdown:

    ```
    start = 0
    end = 10

    Step 1: Compute end - start
    end - start = 10 - 0 = 10

    Step 2: Perform the division
    (end - start) / 2 = 10 / 2 = 5 (integer division)

    Step 3: Add the offset to the start
    start + (end - start) / 2 = 0 + 5 = 5

    Therefore, the final value of mid is 5.
    ```

    The key points to remember are:

    1. The subtraction `end - start` is performed first to get the length of the range.
    2. The length is then divided by 2 to get the offset from the `start` value.
    3. The offset is added to the `start` value to obtain the middle index `mid`.

    This sequence of operations ensures that the middle index is calculated correctly,
    avoiding potential integer overflow issues and maintaining precision, as discussed in
    the previous response.


    The approach of using `start + (end - start) / 2` to calculate the middle index (`mid`) is
    a common and efficient way to find the middle of a range or interval. Here's why this approach

    works well:

    1. **Avoiding Overflow**: The expression `start + (end - start) / 2` is preferred over
    the simpler `(start + end) / 2` because it helps avoid potential integer overflow issues.
    When `start` and `end` are large numbers, the sum `start + end` could exceed the maximum
    integer value, leading to incorrect results. By subtracting `start` from `end` first, and then
    dividing by 2, the intermediate values are smaller and less likely to cause an overflow.

    2. **Maintaining Precision**: The division operation `(end - start) / 2` ensures that
    the result is properly rounded down to the nearest integer, which is important when working
    with integer indices or positions. The simpler `(start + end) / 2` approach could potentially
    result in a non-integer value, which would not be a valid index.

    3. **Simplicity and Efficiency**: The expression `start + (end - start) / 2`
    is a straightforward and concise way to calculate the middle index. It involves only
    basic arithmetic operations, making it efficient and easy to understand and implement.
    This approach works well in various scenarios, such as:

    - **Binary Search**: When implementing a binary search algorithm, the middle index is

    calculated using this formula to quickly narrow down the search range.
    - **Array/List Manipulation**: When working with arrays or lists, finding the middle index is

    often necessary for tasks like splitting the data in half, merging sorted halves, or traversing

    the data structure efficiently.
    - **Tree/Graph Algorithms**: In tree or graph data structures, calculating the middle point

    or node is important for various traversal and partitioning algorithms.
    In summary, the `start + (end - start) / 2` approach to finding the middle index is a simple,

    efficient, and overflow-safe method that is widely used in various computer science algorithms
    and data structures.
     */
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