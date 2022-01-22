package challenges.cracking_coding_interview.moderate.q6_smallest_difference

import java.util.*

object QuestionC {
    private fun getClosestValue(array: IntArray, target: Int): Int {
        var low = 0
        var high = array.size - 1
        var mid: Int
        while (low <= high) {
            mid = low + (high - low) / 2
            if (array[mid] < target) {
                low = mid + 1
            } else if (array[mid] > target) {
                high = mid - 1
            } else {
                return array[mid]
            }
        }
        val valueA = if (low < 0 || low >= array.size) Int.MAX_VALUE else array[low]
        val valueB = if (high < 0 || high >= array.size) Int.MAX_VALUE else array[high]
        return if (Math.abs(valueA - target) < Math.abs(valueB - target)) valueA else valueB // return closest value
    }

    fun findSmallestDifference(shorter: IntArray, longer: IntArray): Int {
        if (shorter.isEmpty() || longer.isEmpty()) return -1
        if (shorter.size > longer.size) return findSmallestDifference(longer, shorter)
        Arrays.sort(shorter)
        var smallestDifference = Int.MAX_VALUE
        for (target in longer) {
            val closest = getClosestValue(shorter, target)
            smallestDifference = Math.min(smallestDifference, Math.abs(closest - target))
        }
        return smallestDifference
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array1 = intArrayOf(1, 3, 15, 11, 2)
        val array2 = intArrayOf(23, 127, 234, 19, 8)
        val difference = findSmallestDifference(array1, array2)
        println(difference)
    }
}