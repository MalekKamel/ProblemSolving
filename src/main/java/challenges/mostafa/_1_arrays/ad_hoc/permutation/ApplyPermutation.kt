package challenges.mostafa._1_arrays.ad_hoc.permutation

/**
Given an array of N elements and a permutation p of distinct values from [0, N-1].
Applying a permutation means to reorder the array based on p: in the ith-location
assign the value from the p[i] location.
 */

internal object ApplyPermutation {

    private fun applyPermutation(array: IntArray, permutation: IntArray): IntArray {
        val n = array.size
        val result = IntArray(n)

        for (i in 0 until n) {
            result[permutation[i]] = array[i]
        }

        return result
    }

    private fun applyPermutation2(array: IntArray, permutation: IntArray) {
        val n = array.size
        val visited = BooleanArray(n)

        for (i in 0 until n) {
            if (visited[i]) continue

            var cycleStart = i
            while (!visited[cycleStart]) {
                visited[i] = true
                cycleStart = permutation[cycleStart]
            }

            var idx = permutation[cycleStart]
            var value = array[cycleStart]

            while (idx != cycleStart) {
                visited[idx] = true

                val swap = array[idx]
                array[idx] = value
                value = swap
                idx = permutation[idx]
            }

            array[cycleStart] = value
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(10, 20, 30, 40)
        val permutation = intArrayOf(2, 0, 3, 1)
        println(applyPermutation(array, permutation).contentToString())
        applyPermutation2(array, permutation)
        println(array.contentToString())
    }
}