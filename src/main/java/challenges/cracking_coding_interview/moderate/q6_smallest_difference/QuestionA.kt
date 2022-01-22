package challenges.cracking_coding_interview.moderate.q6_smallest_difference

object QuestionA {
    fun findSmallestDifference(arrayA: IntArray, arrayB: IntArray): Int {
        if (arrayA.isEmpty() || arrayB.isEmpty()) return -1
        var smallestDifference = Int.MAX_VALUE
        for (a in arrayA) {
            for (b in arrayB) {
                smallestDifference = Math.min(smallestDifference, Math.abs(a - b))
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