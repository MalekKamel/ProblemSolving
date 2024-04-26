package challenges.mostafa._1_arrays._1_prefix


/**
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.



Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2

 */

internal object Prefix {

    fun rangeSum(s: Int, e: Int, prefix: IntArray): Int {
        if (s == 0) return prefix[e]
        return prefix[e] - prefix[s - 1]
    }

    fun prefixSum(arr: IntArray): IntArray {
        val prefixSum = IntArray(arr.size)
        prefixSum[0] = arr[0]

        for (i in 1 until arr.size) {
            prefixSum[i] = prefixSum[i - 1] + arr[i]
        }

        return prefixSum
    }

}