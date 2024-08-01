package challenges.mostafa._1_arrays._2_sliding_window

import kotlin.math.max

/**
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in
the array if you can flip at most k 0's.

Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
Example 2:

Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
0 <= k <= nums.length

https://leetcode.com/problems/max-consecutive-ones-iii/description/
 */

internal object P4MaxConsecutiveOnes {

    private fun longestOnes(nums: IntArray, k: Int): Int {
        var maxConsecutiveOnes = 0
        var left = 0
        var zerosCount = 0

        for (right in nums.indices) {
            if (nums[right] == 0) zerosCount++

            while (zerosCount > k) {
                if (nums[left] == 0) zerosCount--
                left++
            }
            maxConsecutiveOnes = maxOf(maxConsecutiveOnes, right - left + 1)
        }

        return maxConsecutiveOnes
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var array = intArrayOf(1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0)
        var k = 2

        println("First")
        println(longestOnes(array, k))

        println("Second")
        array = intArrayOf(0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1)
        k = 3
        println(longestOnes(array, k))
    }
}