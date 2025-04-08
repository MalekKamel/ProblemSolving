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
     * Time Complexity
     * The time complexity of this solution is O(N log k), where N is the number of elements in the input
     * array `nums`,
     * and k is the given integer. This is because we iterate through each of the N elements, and for
     * each element,
     * we perform at most one insertion and one deletion operation on the min-heap, both of which
     * take O(log k) time since the heap size is at most k.

     * The space complexity is O(k) because we maintain a min-heap of size k to store the k largest
     * elements.
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

        // Example 3
        val nums3 = intArrayOf(4, 2, 1, 3)
        val k3 = 2
        println(findKthLargest(nums3, k3)) // Output: 4
    }
}