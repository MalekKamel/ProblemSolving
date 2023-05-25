package challenges.educative_grokking_coding_interview.sliding_window._8

object ReplacingOnes {
    private fun findLength(arr: IntArray, k: Int): Int {
        var windowStart = 0
        var maxLength = 0
        var maxOnesCount = 0
        // try to extend the range [windowStart, windowEnd]
        for (windowEnd in arr.indices) {
            if (arr[windowEnd] == 1) maxOnesCount++

            // current window size is from windowStart to windowEnd, overall we have a maximum of 1s
            // repeating a maximum of 'maxOnesCount' times, this means that we can have a window with
            // 'maxOnesCount' 1s and the remaining are 0s which should replace with 1s.
            // now, if the remaining 0s are more than 'k', it is the time to shrink the window as we
            // are not allowed to replace more than 'k' Os
            if (windowEnd - windowStart + 1 - maxOnesCount > k) {
                if (arr[windowStart] == 1) maxOnesCount--
                windowStart++
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1)
        }
        return maxLength
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(findLength(intArrayOf(0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1), 2))
        println(findLength(intArrayOf(0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1), 3))
    }
}