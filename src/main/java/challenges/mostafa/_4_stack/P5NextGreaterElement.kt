package challenges.mostafa._4_stack

import java.util.Stack

/**
Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order
next in the array, which means you could search circularly to find its next greater number.
If it doesn't exist, return -1 for this number.


Example 1:

Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number.
The second 1's next greater number needs to search circularly, which is also 2.

Example 2:

Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]

Constraints:

1 <= nums.length <= 104
-109 <= nums[i] <= 109

https://leetcode.com/problems/next-greater-element-ii/description/
 */

internal object P5NextGreaterElement {

    private fun nextGreaterElements(nums: IntArray): IntArray {
        val n = nums.size
        val stack = Stack<Int>()
        val result = IntArray(n) { -1 }

        for (i in 0 until 2 * n) {
            val index = i % n
            while (stack.isNotEmpty() && nums[stack.peek()] < nums[index]) {
                result[stack.pop()] = nums[index]
            }
            stack.push(index)
        }

        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val nums = intArrayOf(1, 2, 3, 4, 3)
        val result = nextGreaterElements(nums)
        println(result.contentToString()) // Output: [2, 3, 4, -1, 4]
    }
}