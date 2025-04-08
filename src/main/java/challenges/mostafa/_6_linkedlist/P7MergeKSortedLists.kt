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
     * 1. Problem Explanation
     *
     * The problem remains the same: given an array of k sorted linked lists, merge them into a single
     * sorted linked list.
     *
     * 2. Pattern Identification and Rationale
     *
     * The identified pattern here is Divide and Conquer.
     *
     * Divide: The main mergeKLists function recursively divides the array of k linked lists into smaller
     * subproblems. It splits the array into two halves until it reaches the base case where each subproblem
     * contains either one list or an empty list (which is already "merged").
     * Conquer: The mergeKListsHelper function recursively merges the sorted linked lists obtained from
     * the divided subproblems. The base case of the recursion is when start == end, meaning there's only
     * one list, which is returned directly.
     * Combine: The mergeTwoLists function takes two sorted linked lists and merges them into a single
     * sorted linked list. This is the core combining step.
     *
     * Rationale for Suitability:
     *
     * Efficiency: Divide and conquer can lead to a more balanced merging process. Instead of repeatedly
     * comparing against all k lists (as implicitly done in a naive iterative approach or a simple min-heap),
     * it merges pairs of lists, gradually reducing the number of lists to merge.
     * Leverages mergeTwoLists: Since merging two sorted linked lists is a well-understood and efficient
     * operation (O(m+n) where m and n are the lengths of the two lists), this approach effectively utilizes
     * this sub-problem solver.
     *
     * 3. Solution Breakdown
     *
     * mergeKLists(lists: Array<ListNode?>): ListNode?:
     * Handles the base case: If the input array of lists is empty, it returns null.
     * Calls the recursive helper function mergeKListsHelper to start the divide and conquer process on
     * the entire array of lists (from index 0 to the last index).
     *
     * mergeKListsHelper(lists: Array<ListNode?>, start: Int, end: Int): ListNode?:
     * Base Case: If start == end, it means we have a single list (or an empty slot), so we return that
     * list directly.
     * Divide: Calculates the middle index mid to split the current range of lists into two halves.
     * Conquer: Recursively calls mergeKListsHelper for the left half (start to mid) and the right
     * half (mid + 1 to end) to get the merged sorted lists for each half.
     * Combine: Calls the mergeTwoLists function to merge the two sorted lists returned from the recursive calls.
     *
     * mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode?:
     * Creates a dummy node to simplify the merging process.
     * Iterates through both input lists (l1 and l2), comparing the values of the current nodes.
     * Appends the smaller node to the next of the current node in the merged list and moves the pointer
     * of the corresponding list forward.
     * Moves the current pointer to the newly added node.
     * After one of the lists is exhausted, appends any remaining nodes from the other list to the end
     * of the merged list.
     * Returns the next of the dummy node, which is the head of the merged sorted list.
     *
     * 4. Time Complexity
     * Let k be the number of linked lists and n be the total number of nodes across all linked lists.
     *
     * The mergeTwoLists operation takes O(m+p) time, where m and p are the lengths of the two lists being merged.
     * In the divide and conquer approach, we are essentially merging pairs of lists in a binary tree structure.
     * At the first level, we have k/2 merges of lists with an average total length of n/k. Each merge
     * takes roughly O(n/k) time, totaling O(n/2).
     * At the second level, we have k/4 merges of lists with an average total length of 2n/k. Each merge
     * takes roughly O(2n/k) time, totaling O(n/2).
     * This continues for log k levels.
     * Therefore, the overall time complexity of this divide and conquer approach is O(n log k).
     */

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
    avoiding potential integer overflow issues and maintaining precision.

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
        // Handle edge cases first
        when {
            l1 == null -> return l2
            l2 == null -> return l1
        }

        val dummy = ListNode(0)
        var current = dummy
        var first = l1
        var second = l2

        while (first != null && second != null) {
            when {
                first.`val` <= second.`val` -> {
                    current.next = first
                    first = first.next
                }
                else -> {
                    current.next = second
                    second = second.next
                }
            }
            current = current.next!!
        }

        // Attach remaining nodes
        current.next = first ?: second

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