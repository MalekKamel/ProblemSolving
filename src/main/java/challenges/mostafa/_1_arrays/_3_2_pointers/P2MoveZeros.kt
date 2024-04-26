package challenges.mostafa._1_arrays._3_2_pointers

/**
Given an integer array nums, move all 0's to the end of it while maintaining the relative
order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.

Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
Example 2:

Input: nums = [0]
Output: [0]

Constraints:

1 <= nums.length <= 104 -231 <= nums[i] <= 231 - 1

Follow up: Could you minimize the total number of operations done?

https://leetcode.com/problems/move-zeroes/description/
 */

internal object P2MoveZeros {

    private fun moveZeros(nums: IntArray): IntArray {
        var nonZerCount = 0

        for (i in nums.indices) {
            if (nums[i] == 0) continue
            val temp = nums[nonZerCount]
            nums[nonZerCount] = nums[i]
            nums[i] = temp
            nonZerCount++
        }

        return nums
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var array = intArrayOf(0, 1, 0, 3, 12)

        println("1st")
        println(moveZeros(array).contentToString())

        println("2nd")
        array = intArrayOf(0, 1, 0, 3, 12, 0, 8)
        println(moveZeros(array).contentToString())

        println("3rd")
        array = intArrayOf(0, 0, 0, 0, 0)
        println(moveZeros(array).contentToString())

        println("4th")
        array = intArrayOf(1, 2, 3, 4, 5)
        println(moveZeros(array).contentToString())

        println("5th")
        array = intArrayOf(0, 0, 0, 1, 2, 3)
        println(moveZeros(array).contentToString())
    }
}