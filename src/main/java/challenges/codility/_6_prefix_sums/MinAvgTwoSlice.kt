package challenges.codility._6_prefix_sums

/**
 * A non-empty array A consisting of N integers is given. A pair of integers (P, Q),
 * such that 0 ≤ P < Q < N, is called a slice of array A (notice that the slice contains
 * at least two elements). The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q]
 * divided by the length of the slice. To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q])
 * / (Q − P + 1).
 *
 * For example, array A such that:
 *
 *   A[0] = 4
 *   A[1] = 2
 *   A[2] = 2
 *   A[3] = 5
 *   A[4] = 1
 *   A[5] = 5
 *   A[6] = 8
 *
 * contains the following example slices:
 *
 * slice (1, 2), whose average is (2 + 2) / 2 = 2;
 * slice (3, 4), whose average is (5 + 1) / 2 = 3;
 * slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
 *
 * The goal is to find the starting position of a slice whose average is minimal.
 *
 * Write a function:
 *
 * fun solution(A: IntArray): Int
 *
 * that, given a non-empty array A consisting of N integers, returns the starting position
 * of the slice with the minimal average. If there is more than one slice with a minimal
 * average, you should return the smallest starting position of such a slice.
 * For example, given array A such that:
 *
 *   A[0] = 4
 *   A[1] = 2
 *   A[2] = 2
 *   A[3] = 5
 *   A[4] = 1
 *   A[5] = 5
 *   A[6] = 8
 *
 * the function should return 1, as explained above.
 * Write an efficient algorithm for the following assumptions:
 * N is an integer within the range [2..100,000];
 * each element of array A is an integer within the range [−10,000..10,000]
 *
 * https://app.codility.com/programmers/lessons/5-prefix_sums/min_avg_two_slice/
 */

class MinAvgTwoSlice {
    /**
     * 1. Problem Explanation
     *
     * The problem asks us to find the starting index of a slice (subarray of at least two elements)
     * within a given array of integers that has the minimum average value. If multiple slices
     * have the same minimum average, we should return the smallest starting index among them.
     *
     * 2. Pattern Identification and Rationale
     *
     * The core idea to solve this problem efficiently relies on the observation that if a slice
     * of length greater than 2 has the minimum average, then there must exist a slice of
     * length 2 or 3 with the same minimum average. This property allows us to only consider
     * slices of length 2 and 3 to find the overall minimum average.
     *
     * The pattern used here is a simple iteration and comparison. We iterate through the array
     * and calculate the averages of all possible slices of length 2 and 3. We keep track of
     * the minimum average found so far and the starting index of the slice that produced it.
     *
     * This approach is suitable because it significantly reduces the number of calculations needed.
     * Instead of checking all possible slice lengths (from 2 to N), we only need to check
     * lengths 2 and 3. This leads to a linear time complexity, which is efficient given
     * the constraints of the problem.
     */
    fun solution(A: IntArray): Int {
        val n = A.size
        var minAverage = Double.MAX_VALUE
        var minStartIndex = -1

        for (i in 0 until n - 1) {
            // Check slice of length 2
            val avg2 = (A[i].toDouble() + A[i + 1]) / 2.0
            if (avg2 < minAverage) {
                minAverage = avg2
                minStartIndex = i
            }

            // Check slice of length 3 if possible
            if (i < n - 2) {
                val avg3 = (A[i].toDouble() + A[i + 1] + A[i + 2]) / 3.0
                if (avg3 < minAverage) {
                    minAverage = avg3
                    minStartIndex = i
                }
            }
        }

        return minStartIndex
    }
}

fun main() {
    // Problem Statement:
    // A non-empty array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P < Q < N, is called a slice of array A (notice that the slice contains at least two elements). The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice. To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).
    // The goal is to find the starting position of a slice whose average is minimal. If there is more than one slice with a minimal average, you should return the smallest starting position of such a slice.

    val sol = MinAvgTwoSlice()
    val A1 = intArrayOf(4, 2, 2, 5, 1, 5, 8)
    println(sol.solution(A1)) // Expected output: 1

    val A2 = intArrayOf(10, 10, 10)
    println(sol.solution(A2)) // Expected output: 0

    val A3 = intArrayOf(5, 5, 5, 5)
    println(sol.solution(A3)) // Expected output: 0

    val A4 = intArrayOf(-3, -5, -8, -4, -10)
    println(sol.solution(A4)) // Expected output: 2

    val A5 = intArrayOf(1, 4, 1, 4, 1, 4)
    println(sol.solution(A5)) // Expected output: 0
}