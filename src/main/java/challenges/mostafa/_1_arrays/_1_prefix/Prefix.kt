package challenges.mostafa._1_arrays._1_prefix

/**
A "prefix sum" refers to a cumulative sum of a sequence of numbers. Essentially, for a given array,
the prefix sum array will contain the running total of the elements up to each index.
 */
internal object Prefix {

    /**
    The s - 1 term is used to exclude the sum of elements before the starting index s of the
    desired subarray, allowing you to efficiently calculate the subarray sum using the prefix sum array.
     */
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