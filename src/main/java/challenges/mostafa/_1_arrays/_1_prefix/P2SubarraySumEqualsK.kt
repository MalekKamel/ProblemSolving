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

https://leetcode.com/problems/subarray-sum-equals-k/description/
 */

internal object P2SubarraySumEqualsK {

    // o(n^3)
    private fun subarraySum(nums: IntArray, k: Int): Int {
        var res = 0
        for (start in nums.indices) {
            for (end in start until nums.size) {
                var sum = 0
                for (idx in start..end)
                    sum += nums[idx]
                if (sum == k) res++
            }
        }
        return res
    }

    // o(n^2)
    private fun subarraySum2(nums: IntArray, k: Int): Int {
        var res = 0
        val prefixSum = Prefix.prefixSum(nums)
        for (start in nums.indices) {
            for (end in start until nums.size) {
                val sum = Prefix.rangeSum(start, end, prefixSum)
                if (sum == k) res++
            }
        }
        return res
    }

    // o(n)
    private fun subarraySum3(nums: IntArray, k: Int): Int {
        var res = 0
        var prefixSum = 0
        val prefixMap = hashMapOf(Pair(0, 1))

        for (i in nums.indices) {
            prefixSum += nums[i]
            if (prefixMap.contains(prefixSum - k))
                res += prefixMap[prefixSum - k]!!
            prefixMap[prefixSum] = prefixMap.getOrDefault(prefixSum, 0) + 1
        }

        return res
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 1, 1) // 2
        val k1 = 2

        val array2 = intArrayOf(1, 2, 3) // 2
        val k2 = 3

        val array3 = intArrayOf(3, 4, 7, 2, -3, 1, 4, 2) // 4
        val k3 = 7

        println("First")
        println(subarraySum(array, k1))
        println(subarraySum(array2, k2))
        println(subarraySum(array3, k3))

        println("Second")
        println(subarraySum2(array, k1))
        println(subarraySum2(array2, k2))
        println(subarraySum2(array3, k3))

        println("Third")
        println(subarraySum3(array, k1))
        println(subarraySum3(array2, k2))
        println(subarraySum3(array3, k3))
    }

}