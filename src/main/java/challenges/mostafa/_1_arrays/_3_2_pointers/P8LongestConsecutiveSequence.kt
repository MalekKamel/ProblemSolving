package challenges.mostafa._1_arrays._3_2_pointers

/**
Given an unsorted array of integers nums, return the length of the longest consecutive
elements sequence.

You must write an algorithm that runs in O(n) time.

Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9


Constraints:

0 <= nums.length <= 105 -109 <= nums[i] <= 109

https://leetcode.com/problems/longest-consecutive-sequence/
 */

internal object P8LongestConsecutiveSequence {

    private fun longestConsecutive(nums: IntArray): Int {
        val numSet = nums.toMutableSet()
        var maxLength = 0

        for (num in nums) {
            // If it's not the first element in the sequence, we should skip because there's
            // another longer sequence than this
            if (num - 1 in numSet) continue

            var currentLength = 0
            var currentNum = num
            while (currentNum in numSet) {
                currentNum++
                currentLength++
            }
            maxLength = maxOf(maxLength, currentLength)
        }

        return maxLength
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var array = intArrayOf(100, 4, 200, 1, 3, 2)

        println("1st")
        println(longestConsecutive(array))

        println("2nd")
        array = intArrayOf(0, 3, 7, 2, 5, 8, 4, 6, 0, 1)
        println(longestConsecutive(array))
    }
}