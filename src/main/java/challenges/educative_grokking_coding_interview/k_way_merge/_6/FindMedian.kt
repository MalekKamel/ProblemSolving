package challenges.educative_grokking_coding_interview.k_way_merge._6

import java.util.*

/**
You’re given two sorted integer arrays, nums1 and nums2, of size m and n, respectively.
Your task is to return the median of the two sorted arrays.

The overall run time complexity should be O(log(m+n)).

https://www.educative.io/courses/grokking-coding-interview-patterns-java/m218mw0LVO3
 */

object FindMedian {

    private fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val minHeap = PriorityQueue<Int>()
        var i = 0
        var j = 0

        // Merge both arrays into the min heap
        while (i < nums1.size || j < nums2.size) {
            if (i < nums1.size && (j >= nums2.size || nums1[i] <= nums2[j])) {
                minHeap.offer(nums1[i])
                i++
            } else {
                minHeap.offer(nums2[j])
                j++
            }
        }

        // Get the median from the min heap
        val size = nums1.size + nums2.size
        val median = if (size % 2 == 0) {
            (minHeap.poll().toDouble() + minHeap.poll().toDouble()) / 2.0
        } else {
            minHeap.poll().toDouble()
        }

        return median
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val nums1 = intArrayOf(1, 3)
        val nums2 = intArrayOf(2, 4, 6)
        val median = findMedianSortedArrays(nums1, nums2)
        println("The median of [${nums1.joinToString()}] and [${nums2.joinToString()}] is: $median")
    }
}