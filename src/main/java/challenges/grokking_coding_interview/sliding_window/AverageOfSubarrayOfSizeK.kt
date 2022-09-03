package challenges.grokking_coding_interview.sliding_window


object AverageOfSubarrayOfSizeK {
    private fun findAverages(K: Int, arr: IntArray): DoubleArray {
        val result = DoubleArray(arr.size - K + 1)
        var windowSum = 0.0
        var windowStart = 0
        for (windowEnd in arr.indices) {
            windowSum += arr[windowEnd] // add the next element
            // slide the window, we don't need to slide if we've not hit the required window size of 'k'
            if (windowEnd >= K - 1) {
                result[windowStart] = windowSum / K // calculate the average
                windowSum -= arr[windowStart] // subtract the element going out
                windowStart++ // slide the window ahead
            }
        }
        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val result = findAverages(5, intArrayOf(1, 3, 2, 6, -1, 4, 1, 8, 2))
        println("Averages of subarrays of size K: " + result.contentToString())
    }
}