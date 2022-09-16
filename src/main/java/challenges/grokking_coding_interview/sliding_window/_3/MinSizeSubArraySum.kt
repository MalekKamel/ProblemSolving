package challenges.grokking_coding_interview.sliding_window._3

/**
 * Given an array of positive integers and a number ‘S,’ find the length of the smallest contiguous
 * subarray whose sum is greater than or equal to ‘S’.
 * Return 0 if no such subarray exists.
 *
 * https://www.educative.io/courses/grokking-the-coding-interview/7XMlMEQPnnQ
 */
internal object MinSizeSubArraySum {
    private fun findMinSubArray(S: Int, a: IntArray): Int {
        val n = a.size
        var lengthOfSmallestSubarray = Int.MAX_VALUE
        var windowSum = 0
        var windowStart = 0
        for (windowEnd in 0 until n) {
            windowSum += a[windowEnd] // Add the next element to the window
            while (windowSum >= S) { // Shrink the window as small as possible until the 'windowSum' is smaller than 'K'
                lengthOfSmallestSubarray = Math.min(lengthOfSmallestSubarray, windowEnd - windowStart + 1)
                windowSum -= a[windowStart] // Discard the element at 'windowStart' since it is going out of the window
                windowStart++ // Shrink the window
            }
        }
        return if (lengthOfSmallestSubarray == Int.MAX_VALUE) 0 else lengthOfSmallestSubarray
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var result = findMinSubArray(7, intArrayOf(2, 1, 5, 2, 3, 2))
        println("Smallest subarray length: $result")
        result = findMinSubArray(8, intArrayOf(3, 4, 1, 1, 6))
        println("Smallest subarray length: $result")
        result = findMinSubArray(8, intArrayOf(2, 1, 5, 2, 3, 2))
        println("Smallest subarray length: $result")
    }
}