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

    /**
    This can be calculated using the formula for the sum of an arithmetic series: n * (n + 1) / 2
     */
    private fun missingNumber(nums: IntArray): Int {
        val n = nums.size
        var sum = n * (n + 1) / 2

        for (num in nums)
            sum -= num
        return sum
    }

    /**
    The reason why the code uses sum + i in the loop is to calculate the expected sum of all
    the numbers from 0 to the size of the array.

    Let's say the array nums has a size of n. The expected sum of all the numbers from 0 to n is:

    0 + 1 + 2 + ... + (n-1) + n

    This can be calculated using the formula for the sum of an arithmetic series:

    n * (n + 1) / 2

    By iterating over the array and adding the index i to the sum variable, the code is essentially
    calculating this expected sum.

    For example, let's say the array nums has the values [0, 1, 3]. The size of the array is 3,
    so the expected sum is:

    0 + 1 + 2 = 3

    The code will calculate this as follows:

    Initialize sum = 3 (the size of the array)
    For the first element nums[0] = 0:
    sum = (sum + 0) - 0 = 3
    For the second element nums[1] = 1:
    sum = (sum + 1) - 1 = 3
    For the third element nums[2] = 3:
    sum = (sum + 2) - 3 = 2
    The final value of sum is 2, which is the missing number in the array.

    So, in summary, the sum + i part of the calculation is used to keep track of the expected
    sum of all the numbers from 0 to the size of the array, which is then used to find the
    missing number.
     */
    private fun missingNumber2(nums: IntArray): Int {
        var sum = nums.size
        for ((i, num) in nums.withIndex()) {
            sum = (sum + i) - num
        }
        return sum
    }

    /**
    The reason this works is that the XOR operation has the following properties:
    a ^ b ^ a = b
    a ^ b ^ b = a
    So, by XORing all the indices and all the elements in the nums array, the values that are
    present in the array will cancel each other out, leaving only the missing number.
     */
    private fun missingNumber3(nums: IntArray): Int {
        // The initial value of missingNumber is set to the size of the nums array. This is
        // because the missing number in the array will be a value between 0 and the size of the array (inclusive).
        var missingNumber = nums.size
        for (i in nums.indices) {
            missingNumber = missingNumber xor i xor nums[i]
        }
        return missingNumber
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var array = intArrayOf(3, 0, 1)

        println("1st")
        println(missingNumber(array))
        println(missingNumber2(array))
        println(missingNumber3(array))

        println("2nd")
        array = intArrayOf(0, 1)
        println(missingNumber(array))
        println(missingNumber2(array))
        println(missingNumber3(array))

        println("3rd")
        array = intArrayOf(9, 6, 4, 2, 3, 5, 7, 0, 1)
        println(missingNumber(array))
        println(missingNumber2(array))
        println(missingNumber3(array))
    }
}