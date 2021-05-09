package challenges.misc

/*
Given an integer array arr, count how many elements x there are, such that x + 1 is also an arr.
If there are duplicates in arr, count them separately.
 */
object ElementsCount {

    private fun solve(arr: IntArray): Int {
        var result = 0
        val elements = hashMapOf<Int, Int>()
        arr.forEachIndexed { i, item -> elements[item] = i }
        for (item in arr) {
            if (elements[item + 1] == null) continue
            result++
        }
        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(solve(intArrayOf(1, 2, 3)))                  // 2
        println(solve(intArrayOf(1, 1, 3, 3, 5, 5, 7, 7)))   // 0
        println(solve(intArrayOf(1, 3, 2, 3, 5, 0)))         // 3
        println(solve(intArrayOf(1, 1, 2, 2)))               // 2
        println(solve(intArrayOf(1, 1, 2)))                  // 2
    }
}