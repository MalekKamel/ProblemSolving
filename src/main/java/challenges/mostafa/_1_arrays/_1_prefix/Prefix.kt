package challenges.mostafa._1_arrays._1_prefix

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