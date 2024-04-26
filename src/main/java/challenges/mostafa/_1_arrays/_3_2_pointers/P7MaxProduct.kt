package challenges.mostafa._1_arrays._3_2_pointers

import java.util.Collections
import java.util.PriorityQueue

/**
Given an integer array nums, find three numbers whose product is maximum and return
the maximum product.

Example 1:

Input: nums = [1,2,3]
Output: 6
Example 2:

Input: nums = [1,2,3,4]
Output: 24
Example 3:

Input: nums = [-1,-2,-3]
Output: -6

Constraints:

3 <= nums.length <= 104 -1000 <= nums[i] <= 1000

https://leetcode.com/problems/maximum-product-of-three-numbers/submissions/1250718050/
 */

internal object P7MaxProduct {

    private fun maximumProduct(nums: IntArray): Int {
        nums.sort()
        val n = nums.size

        val max1 = nums[n - 1]
        val max2 = nums[n - 2]
        val max3 = nums[n - 3]

        val min1 = nums[0]
        val min2 = nums[1]

        return maxOf(max1 * max2 * max3, min1 * min2 * max1)
    }

    private fun maximumProduct2(nums: IntArray): Int {
        val maxHeap = PriorityQueue<Int>()
        val minHeap = PriorityQueue<Int>(Collections.reverseOrder())

        for (num in nums) {
            maxHeap.offer(num)
            minHeap.offer(num)

            if (minHeap.size > 2) minHeap.poll()
            if (maxHeap.size > 3) maxHeap.poll()
        }

        val max1 = maxHeap.poll()
        val max2 = maxHeap.poll()
        val max3 = maxHeap.poll()

        val min1 = minHeap.poll()
        val min2 = minHeap.poll()

        return maxOf(max1 * max2 * max3, min1 * min2 * max1)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var array = intArrayOf(1,2,3)

        println("1st")
        println(maximumProduct(array))
        println(maximumProduct2(array))

        println("2nd")
        array = intArrayOf(1,2,3,4)
        println(maximumProduct(array))
        println(maximumProduct2(array))

        println("3rd")
        array = intArrayOf(-1,-2,-3)
        println(maximumProduct(array))
        println(maximumProduct2(array))
    }
}