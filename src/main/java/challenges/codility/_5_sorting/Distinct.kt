package challenges.codility._5_sorting

/**
 * Given an array A consisting of N integers, returns the number of distinct values in array A.
 *
 * For example, given array A consisting of six elements such that:
 *
 * A[0] = 2    A[1] = 1    A[2] = 1
 * A[3] = 2    A[4] = 3    A[5] = 1
 * the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [−1,000,000..1,000,000].
 *
 * https://app.codility.com/programmers/lessons/6-sorting/distinct/
 */
class Distinct {
    fun solution(A: IntArray): Int {
        val distinctValues = HashSet<Int>()
        for (element in A) {
            distinctValues.add(element)
        }
        return distinctValues.size
    }
}


fun main() {
    val solution = Distinct()
    val A1 = intArrayOf(2, 1, 1, 2, 3, 1)
    println(solution.solution(A1)) // Expected output: 3

    val A2 = intArrayOf()
    println(solution.solution(A2)) // Expected output: 0

    val A3 = intArrayOf(5, 5, 5)
    println(solution.solution(A3)) // Expected output: 1

    val A4 = intArrayOf(-1000000, 1000000, 0)
    println(solution.solution(A4)) // Expected output: 3
}