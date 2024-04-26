package challenges.mostafa._1_arrays.ad_hoc

/**
Given an array nums containing n distinct numbers in the range [0, n], return the only number
in the range that is missing from the array.



Example 1:

Input: nums = [3,0,1]
Output: 2
Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is
the missing number in the range since it does not appear in nums.

Example 2:

Input: nums = [0,1]
Output: 2
Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is
the missing number in the range since it does not appear in nums.

Example 3:

Input: nums = [9,6,4,2,3,5,7,0,1]
Output: 8
Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is
the missing number in the range since it does not appear in nums.

https://leetcode.com/problems/missing-number/description/
 */

internal object P1MissingNumber {

    private fun missingNumber(nums: IntArray): Int {
        val n = nums.size
        var sum = n * (n + 1) / 2

        for (num in nums)
            sum -= num
        return sum
    }
    private fun missingNumber2(nums: IntArray): Int {
        var sum = nums.size
        for ((i, num) in nums.withIndex()) {
            sum = (sum + i) - num
        }
        return sum
    }

    private fun missingNumber3(nums: IntArray): Int {
        var missingNumber = nums.size // Initialize missingNumber with n
        for (i in nums.indices) {
            missingNumber = missingNumber xor i xor nums[i]
        }
        return missingNumber
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var array = intArrayOf(3,0,1)

        println("1st")
        println(missingNumber(array))
        println(missingNumber2(array))
        println(missingNumber3(array))

        println("2nd")
        array = intArrayOf(0,1)
        println(missingNumber(array))
        println(missingNumber2(array))
        println(missingNumber3(array))

        println("3rd")
        array = intArrayOf(9,6,4,2,3,5,7,0,1)
        println(missingNumber(array))
        println(missingNumber2(array))
        println(missingNumber3(array))
    }
}