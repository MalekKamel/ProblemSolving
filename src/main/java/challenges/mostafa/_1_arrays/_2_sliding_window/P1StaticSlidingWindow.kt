package challenges.mostafa._1_arrays._2_sliding_window

import challenges.mostafa._1_arrays._1_prefix.Prefix
import kotlin.math.max

/**
Given an integer array of length N and K, find the maximum sum of K consecutive elements inside the array.
 */

internal object P1StaticSlidingWindow {


    private fun maxConsecutiveSum(nums: IntArray, k: Int): Int {
        var best = Int.MIN_VALUE

        for (i in 0..nums.size - k) {
            var currentSum = 0
            for (j in i until i + k) {
                currentSum += nums[j]
            }
            best = maxOf(best, currentSum)
        }
        return best
    }

    private fun maxConsecutiveSum2(nums: IntArray, k: Int): Int {
        var best = Int.MIN_VALUE

        val prefixSum = Prefix.prefixSum(nums)
        for (i in 0..nums.size - k) {
            val currentSum = Prefix.rangeSum(i, i + k - 1, prefixSum)
            best = maxOf(best, currentSum)
        }
        return best
    }

    private fun maxConsecutiveSum3(nums: IntArray, k: Int): Int {
        var windowSum = 0
        for (end in 0 until k)
            windowSum += nums[end]
        var best = windowSum

        for (end in k until nums.size) {
            windowSum += nums[end] - nums[end - k]
            best = maxOf(windowSum, best)
        }
        return best
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 3, 6, 4, 5, 3, 8, 2) // 28
        val k = 5

        println("First")
        println(maxConsecutiveSum(array, k))

        println("Second")
        println(maxConsecutiveSum2(array, k))

        println("Third")
        println(maxConsecutiveSum3(array, k))
    }
}