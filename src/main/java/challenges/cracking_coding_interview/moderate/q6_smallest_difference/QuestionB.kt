package challenges.cracking_coding_interview.moderate.q6_smallest_difference

import java.util.*

object QuestionB {
    fun findSmallestDifference(arrayA: IntArray, arrayB: IntArray): Int {
        if (arrayA.isEmpty() || arrayB.isEmpty()) return -1
        Arrays.sort(arrayA)
        Arrays.sort(arrayB)
        var indexA = 0
        var indexB = 0
        var smallestDifference = Int.MAX_VALUE
        while (indexA < arrayA.size && indexB < arrayB.size) {
            val difference = Math.abs(arrayA[indexA] - arrayB[indexB])
            smallestDifference = Math.min(smallestDifference, difference)
            if (arrayA[indexA] < arrayB[indexB]) {
                indexA++
            } else {
                indexB++
            }
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