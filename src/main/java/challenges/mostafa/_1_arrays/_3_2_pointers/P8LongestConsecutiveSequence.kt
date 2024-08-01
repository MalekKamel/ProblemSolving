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

0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9

https://leetcode.com/problems/longest-consecutive-sequence/
 */

internal object P8LongestConsecutiveSequence {

    /**
    The time complexity of the problem is O(n), where n is the length of the input array nums.

    Here's the breakdown of the time complexity:

    Creating the Set: Converting the input array nums to a MutableSet takes O(n) time, as we need
    to iterate through the entire array to create the set.
    Iterating through the array: The outer loop iterates through each element in the nums array,
    which takes O(n) time.
    Checking for the first element in the sequence: The if (num - 1 in numSet) continue statement
    takes constant time O(1) to check if the previous element is in the set.
    Finding the length of the consecutive sequence: The inner while loop continues as long as
    the current number is in the set. In the worst case, the loop will iterate through the entire
    range of consecutive numbers, which can be up to n elements. Therefore, the time complexity
    of this part is O(n).
    Updating the maximum length: The maxOf(maxLength, currentLength) operation takes constant
    time O(1).
    Combining all the time complexities, the overall time complexity of the longestConsecutive
    function is O(n), as the most expensive operations are the creation of the set and the inner
    while loop, both of which are linear in the size of the input array.
     */
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