package challenges.mostafa._1_arrays._3_2_pointers

/**
Given an array nums of n integers, return an array of all the unique
quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

Example 1:

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

Example 2:

Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]

Constraints:

1 <= nums.length <= 200 -109 <= nums[i] <= 109 -109 <= target <= 109

https://leetcode.com/problems/4sum
 */

internal object P6FourSum {

    private fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val n = nums.size

        nums.sort()

        for (i in 0 until n - 3) {

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue
            }

            for (j in i + 1 until n - 2) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue
                }

                var left = j + 1
                var right = n - 1

                while (left < right) {
                    val sum = nums[i] + nums[j] + nums[left] + nums[right]
                    if (sum >= Int.MAX_VALUE) return emptyList()

                    when {
                        sum == target -> {
                            result.add(listOf(nums[i], nums[j], nums[left], nums[right]))

                            while (left < right && nums[left] == nums[left + 1]) {
                                left++
                            }
                            while (left < right && nums[right] == nums[right - 1]) {
                                right--
                            }

                            left++
                            right--
                        }

                        sum < target -> left++
                        else -> right--
                    }
                }
            }
        }

        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var array = intArrayOf(1, 0, -1, 0, -2, 2)
        var target = 0

        println("1st")
        println(fourSum(array, target))

        println("2nd")
        array = intArrayOf(2, 2, 2, 2, 2)
        target = 8
        println(fourSum(array, target))
    }
}