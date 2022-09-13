package challenges.grokking_coding_interview.sliding_window

/**
 * Given an array of positive numbers and a positive number ‘k,’
 * find the maximum sum of any contiguous subarray of size ‘k’.
 *
 * https://www.educative.io/courses/grokking-the-coding-interview/JPKr0kqLGNP
 */
internal object MaxSumSubArrayOfSizeK {
    private fun findMaxSumSubArray(k: Int, arr: IntArray): Int {
        var windowSum = 0
        var maxSum = 0
        var windowStart = 0
        for (windowEnd in arr.indices) {
            windowSum += arr[windowEnd] // add the next element
            // slide the window, we don't need to slide if we've not hit the required window size of 'k'
            if (windowEnd >= k - 1) {
                maxSum = Math.max(maxSum, windowSum)
                windowSum -= arr[windowStart] // subtract the element going out
                windowStart++ // slide the window ahead
            }
        }
        return maxSum
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(
            "Maximum sum of a subarray of size K: "
                    + findMaxSumSubArray(3, intArrayOf(2, 1, 5, 1, 3, 2))
        )
        println(
            ("Maximum sum of a subarray of size K: "
                    + findMaxSumSubArray(2, intArrayOf(2, 3, 4, 1, 5)))
        )
    }
}