package challenges.mostafa._5_queue

import java.util.PriorityQueue

/**
Given an integer array nums and an integer k, return the kth largest element in the array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.
Can you solve it without sorting?

Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4

Constraints:

1 <= k <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4

https://leetcode.com/problems/kth-largest-element-in-an-array/description/
 */

internal object P1KthLargestElementInArray {

    /**
    The time complexity of the solution using a max-heap is O(n log k), where n is the length
    of the input array nums.

    Here's the breakdown of the time complexity:

    Constructing the max-heap: We iterate through the nums array and add each element to
    the max-heap. Adding an element to a heap takes O(log k) time, as we need to maintain
    the heap property. We do this n times, so the total time for this step is O(n log k).
    Returning the kth largest element: Once the max-heap is constructed, we can simply
    return the root of the heap, which is the kth largest element. This operation takes O(1) time.
    Therefore, the overall time complexity of the solution is O(n log k).

    This is because the dominant part of the algorithm is the heap construction, which
    takes O(n log k) time.

    The space complexity is O(k), as we only need to store the k largest elements in the max-heap.
     */
    private fun findKthLargest(nums: IntArray, k: Int): Int {
        val minHeap = PriorityQueue<Int>()

        for (num in nums) {
            minHeap.offer(num)
            if (minHeap.size > k) minHeap.poll()
        }

        return minHeap.peek()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        val nums1 = intArrayOf(3, 2, 1, 5, 6, 4)
        val k1 = 2
        println(findKthLargest(nums1, k1)) // Output: 5

        // Example 2
        val nums2 = intArrayOf(3, 2, 3, 1, 2, 4, 5, 5, 6)
        val k2 = 4
        println(findKthLargest(nums2, k2)) // Output: 4
    }
}