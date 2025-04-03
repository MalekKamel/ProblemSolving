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
    /**
    The solution checks if the hashmap prefixMap contains the key prefixSum - k.
    This is because if there exists a subarray with sum prefixSum - k, then the subarray
    with sum prefixSum (ending at the current index i) will have a sum of k.
    If the key prefixSum - k exists in the hashmap, the count of such
    subarrays (stored in the value of the hashmap) is added to the res variable.

    The key aspects of this approach are:

    1. **Prefix Sum**: The solution maintains a running prefix sum, which allows it to efficiently
    compute the sum of any subarray by subtracting the prefix sum at the start of the subarray
    from the prefix sum at the end of the subarray.

    2. **Hashmap**: The hashmap `prefixMap` is used to keep track of the count of each prefix
    sum encountered so far. This allows the solution to quickly check if there exists a subarray
    with sum `prefixSum - k`, and update the result accordingly.

    3. **Time Complexity**: The time complexity of this solution is O(n), where n is the length
    of the input array `nums`. This is because the solution iterates through the array once, and
    the hashmap operations (lookup and update) have an average time complexity of O(1).

    4. **Space Complexity**: The space complexity is O(n), as the hashmap can store up to n unique
    prefix sums in the worst case (when all elements in the array are distinct).

    Overall, this solution is efficient and uses a clever combination of prefix sum and hashmap
    to solve the problem in linear time and space.
     */
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

        val array4 = intArrayOf(1, 2, 1, 2, 1) // 4
        val k4 = 3

        println("First")
        println(subarraySum(array, k1))
        println(subarraySum(array2, k2))
        println(subarraySum(array3, k3))
        println(subarraySum(array4, k4))

        println("Second")
        println(subarraySum2(array, k1))
        println(subarraySum2(array2, k2))
        println(subarraySum2(array3, k3))
        println(subarraySum2(array4, k4))

        println("Third")
        println(subarraySum3(array, k1))
        println(subarraySum3(array2, k2))
        println(subarraySum3(array3, k3))
        println(subarraySum3(array4, k4))

        println("Fourth")
        println(subarraySum3(array, k1))
        println(subarraySum3(array2, k2))
        println(subarraySum3(array3, k3))
        println(subarraySum3(array4, k4))
    }

}