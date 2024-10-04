package challenges.mostafa._1_arrays._1_prefix

/**
Given an array of integers, find the maximum difference between two
elements such that the larger number appears after the smaller number.
○ It is guaranteed that there is at least 2 numbers satisfying the condition
● Input ⇒ Output
○ {1000, 2, 3, 10, 6, 4, 8, 1} ⇒ (2, 10) ⇒ 8
○ {7, 9, 5, 6, 3, 2} ⇒ (7, 9) ⇒ 2
○ {2, 5, 15, 6, 4} ⇒ (2, 15) ⇒ 13

https://leetcode.com/problems/maximum-difference-between-increasing-elements/
 */

internal object P1MaxDiff {

    private fun findMaxDifference(array: IntArray): Int {
        var maxDiff = Int.MIN_VALUE
        var maxNum = array[array.size - 1]

        for (i in array.size - 2 downTo 0) {
            val currentElement = array[i]
            if (currentElement < maxNum)
                maxDiff = maxOf(maxDiff, maxNum - currentElement)
            maxNum = maxOf(maxNum, currentElement)
        }

        return if (maxDiff == Int.MIN_VALUE) return -1 else maxDiff
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1000, 2, 3, 10, 6, 4, 8, 1)
        // expected: 8
        println(findMaxDifference(array))
    }

}