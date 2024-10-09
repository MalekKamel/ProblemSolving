package challenges.mostafa._1_arrays._3_2_pointers

/**
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].

Notice that the order of the output and the order of the triplets does not matter.

Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.

Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.

Constraints:

3 <= nums.length <= 3000 -105 <= nums[i] <= 105

https://leetcode.com/problems/3sum/description/
 */

internal object P5ThreeSum {


    /**
    The time complexity of the threeSum function is O(n^2), where n is the length
    of the input array nums.

    Here's the breakdown of the time complexity:

    Sorting the array: nums.sort() has a time complexity of O(n log n).
    Iterating through the array: The outer loop iterates n - 2 times, where n is the length
    of the input array.
    Two-pointer approach: The inner while loop has a time complexity of O(n), as the left and
    right pointers move towards each other.
    Handling duplicates: The additional while loops to skip duplicates have a time complexity
    of O(n) in the worst case, where all elements are duplicates.
    The overall time complexity of the function is the combination of these steps:

    Sorting the array: O(n log n)
    Outer loop: O(n - 2), which is O(n)
    Inner loop: O(n)
    Handling duplicates: O(n)
    Therefore, the total time complexity of the threeSum function is O(n^2).

    The space complexity of the function is O(1), as the function only uses a constant amount of
    additional space to store the result list and the pointers.
     */
    private fun threeSum(nums: IntArray): List<List<Int>> {
        // Sort the array in ascending order
        nums.sort()

        val result = mutableListOf<List<Int>>()
        val n = nums.size

        for (i in 0 until n - 2) {
            // Skip duplicates
            if (i > 0 && nums[i] == nums[i - 1]) continue

            var left = i + 1
            var right = n - 1

            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]

                when {
                    sum == 0 -> {
                        result.add(listOf(nums[i], nums[left], nums[right]))

                        // Skip duplicates
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--
                        }

                        left++
                        right--
                    }

                    sum < 0 -> {
                        left++
                    }

                    else -> {
                        right--
                    }
                }
            }
        }

        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var array = intArrayOf(-1, 0, 1, 2, -1, -4)

        println("1st")
        println(threeSum(array))

        println("2nd")
        array = intArrayOf(0, 1, 1)
        println(threeSum(array))

        println("3nd")
        array = intArrayOf(0, 0, 0)
        println(threeSum(array))
    }
}