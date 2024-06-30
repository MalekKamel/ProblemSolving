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

3 <= nums.length <= 10^4 -1000 <= nums[i] <= 1000

https://leetcode.com/problems/maximum-product-of-three-numbers/submissions/1250718050/
 */

internal object P7MaxProduct {

    /**
    The time complexity is O(n log n).

    Here's the breakdown:

    nums.sort(): Sorting the input array nums has a time complexity of O(n log n), as it uses
    a comparison-based sorting algorithm (e.g., quicksort, merge sort).
    Retrieving the maximum 3 elements and the minimum 2 elements from the sorted array:
    Accessing the last 3 elements and the first 2 elements of the sorted array takes
    constant time O(1).
    The overall time complexity of the maximumProduct function is dominated by the sorting step,
    which is O(n log n).
     */
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

    /**
    The time complexity is O(n log k), where n is the length of the input array
    nums and k is the size of the heaps (2 for the min heap and 3 for the max heap).

    The code uses two priority queues (heaps) - a max heap and a min heap. It iterates through
    the input array nums and adds each element to both the max heap and the min heap. However,
    it also ensures that the size of the heaps does not exceed 3 and 2, respectively,
    by removing the smallest elements from the max heap and the largest elements from the min heap.

    The time complexity for adding an element to a heap is O(log k), where k is the size of
    the heap. Since the code adds each element to both the max heap and the min heap, the overall
    time complexity for this part is O(2n log k) = O(n log k).

    Finally, the code retrieves the top 3 elements from the max heap and the top 2 elements from
    the min heap, which takes O(log k) time for each operation. This adds an additional
    O(5 log k) = O(log k) to the overall time complexity.

    Therefore, the total time complexity of the provided code is O(n log k + log k) = O(n log k).
     */
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