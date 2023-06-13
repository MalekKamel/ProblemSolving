package challenges.educative_grokking_coding_interview.fast_slow_pointers._5

import challenges.util.PrintHyphens
import java.util.*


/**
Given an array of integers, nums, find a duplicate number such that the array contains n+1
elements, and each integer is in the range [1,n] inclusive.
There is only one repeated number in nums. Find and return that number.
Note: You cannot modify the given array nums. You have to solve the problem using only constant extra space.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/g7Pq3G7NK06
 */

object FindDuplicate {
    fun findDuplicate(nums: IntArray): Int {
        // Intialize the fast and slow pointers and make them point the first
        // element of the array
        var fast = nums[0]
        var slow = nums[0]
        // PART #1
        // Traverse in array until the intersection point is found
        while (true) {
            // Move the slow pointer using the nums[slow] flow
            slow = nums[slow]
            // Move the fast pointer two times fast as the slow pointer using the
            // nums[nums[fast]] flow
            fast = nums[nums[fast]]
            // Break the loop when slow pointer becomes equal to the fast pointer, i.e.,
            // if the intersection is found
            if (slow == fast) {
                break
            }
        }
        // PART #2
        // Make the slow pointer point the starting position of an array again, i.e.,
        // start the slow pointer from starting position
        slow = nums[0]
        // Traverse the array until the slow pointer becomes equal to the
        // fast pointer
        while (slow != fast) {
            // Move the slow pointer using the nums[slow] flow
            slow = nums[slow]
            // Move the fast pointer slower than before, i.e., move the fast pointer
            // using the nums[fast] flow
            fast = nums[fast]
        }
        // Return the fast pointer as it points the duplicate number of the array
        return fast
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val nums = arrayOf(
            intArrayOf(1, 3, 2, 3, 5, 4),
            intArrayOf(2, 4, 5, 4, 1, 3),
            intArrayOf(1, 6, 3, 5, 1, 2, 7, 4),
            intArrayOf(1, 2, 2, 4, 3),
            intArrayOf(3, 1, 3, 5, 6, 4, 2)
        )
        for (i in nums.indices) {
            print(i + 1)
            println(".\tnums = " + Arrays.toString(nums[i]))
            println("\tDuplicate number = " + findDuplicate(nums[i]))
            println(PrintHyphens.repeat("-", 100))
        }
    }
}