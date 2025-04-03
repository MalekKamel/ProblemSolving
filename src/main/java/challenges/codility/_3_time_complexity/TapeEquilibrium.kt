package challenges.codility._3_time_complexity

import kotlin.math.abs
import kotlin.test.assertEquals
/**
 * A non-empty array A consisting of N integers is given. Array A represents numbers on a tape.
 *
 * Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].
 *
 * The difference between the two parts is the value of: |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|
 *
 * In other words, it is the absolute difference between the sum of the first part and the sum of the second part.
 *
 * For example, consider array A such that:
 *
 * A[0] = 3
 * A[1] = 1
 * A[2] = 2
 * A[3] = 4
 * A[4] = 3
 * We can split this tape in four places:
 *
 * P = 1, difference = |3 − 10| = 7
 * P = 2, difference = |4 − 9| = 5
 * P = 3, difference = |6 − 7| = 1
 * P = 4, difference = |10 − 3| = 7
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given a non-empty array A of N integers, returns the minimal difference that can be achieved.
 *
 * For example, given:
 *
 * A[0] = 3
 * A[1] = 1
 * A[2] = 2
 * A[3] = 4
 * A[4] = 3
 * the function should return 1, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [2..100,000];
 * each element of array A is an integer within the range [−1,000..1,000].
 *
 * https://app.codility.com/programmers/lessons/3-time_complexity/tape_equilibrium/
 */
class Solution {
    fun solution(A: IntArray): Int {
        val n = A.size // Get the size of the input array
        var totalSum = A.sum() // Calculate the total sum of the array
        var leftSum = 0 // Initialize the left sum to 0
        var minDifference = Int.MAX_VALUE // Initialize the minimum difference to the maximum possible integer value

        for (i in 0 until n - 1) { // Iterate through possible split points (0 to n-2)
            leftSum += A[i] // Add the current element to the left sum
            val rightSum = totalSum - leftSum // Calculate the right sum
            val difference = abs(leftSum - rightSum) // Calculate the absolute difference
            minDifference = minOf(minDifference, difference) // Update the minimum difference if necessary
        }

        return minDifference // Return the minimum difference
    }
}

fun main() {
    val solution = Solution()

    // Test cases
    assertEquals(1, solution.solution(intArrayOf(3, 1, 2, 4, 3)))
    assertEquals(20, solution.solution(intArrayOf(-10, -20, -30, -40, 100)))
}