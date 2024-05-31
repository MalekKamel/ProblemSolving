package challenges.mostafa._4_stack

import java.util.Stack

/**
Given an array A of integers, return the number of non-empty continuous subarrays that
satisfy the following condition:
The leftmost element of the subarray is not larger than other elements in the subarray.


Example 1:

Input: [1,4,2,5,3]
Output: 11
Explanation: There are 11 valid subarrays: [1],[4],[2],[5],[3],[1,4],[2,5],[1,4,2],[2,5,3],[1,4,2,5],[1,4,2,5,3].

Example 2:

Input: [3,2,1]
Output: 3
Explanation: The 3 valid subarrays are: [3],[2],[1].

Example 3:

Input: [2,2,2]
Output: 6
Explanation: There are 6 valid subarrays: [2],[2],[2],[2,2],[2,2],[2,2,2].

Note:

1 <= A.length <= 50000
0 <= A[i] <= 100000

https://leetcode.ca/all/1063.html
 */

internal object P4NumberOfValidSubarrays {

    private fun numSubarrayWithMaxElement(A: IntArray): Int {
        var count = 0
        val stack = Stack<Int>()

        for (i in A.indices) {
            while (stack.isNotEmpty() && A[stack.peek()] > A[i]) {
                stack.pop()
            }
            stack.push(i)
            count += stack.size
        }

        return count
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val arrays = arrayOf(
            intArrayOf(1,4,2,5,3),
            intArrayOf(3, 2, 1),
            intArrayOf(2, 2, 2)
        )
        for (a in arrays) {
            val result = numSubarrayWithMaxElement(a)
            println("Number of subarrays with maximum element: $result")
        }
    }
}