package challenges.codility._4_counting_elements

/**
 * You are given N counters, initially set to 0, and you have two possible operations on them:
 *
 * increase(X) − counter X is increased by 1,
 * max counter − all counters are set to the maximum value of any counter.
 * A non-empty array A of M integers is given. This array represents consecutive operations:
 *
 * if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
 * if A[K] = N + 1 then operation K is max counter.
 * For example, given integer N = 5 and array A such that:
 *
 * A[0] = 3
 * A[1] = 4
 * A[2] = 4
 * A[3] = 6
 * A[4] = 1
 * A[5] = 4
 * A[6] = 4
 *
 * the values of the counters after each consecutive operation will be:
 *
 * (0, 0, 1, 0, 0)
 * (0, 0, 1, 1, 0)
 * (0, 0, 1, 2, 0)
 * (2, 2, 2, 2, 2)
 * (3, 2, 2, 2, 2)
 * (3, 2, 2, 3, 2)
 * (3, 2, 2, 4, 2)
 *
 * The goal is to calculate the value of every counter after all operations.
 *
 * Write a function:
 *
 * fun solution(N: Int, A: IntArray): IntArray
 *
 * that, given an integer N and a non-empty array A consisting of M integers, returns a sequence of integers representing
 * the values of the counters.
 *
 * Result array should be returned as an array of integers.
 *
 * For example, given:
 *
 * A[0] = 3
 * A[1] = 4
 * A[2] = 4
 * A[3] = 6
 * A[4] = 1
 * A[5] = 4
 * A[6] = 4
 *
 * the function should return [3, 2, 2, 4, 2], as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N and M are integers within the range [1..100,000];
 * each element of array A is an integer within the range [1..N + 1].
 */
class Solution {

    fun solution(N: Int, A: IntArray): IntArray {
        val counters = IntArray(N)
        var maxCounter = 0
        var lastMaxCounter = 0

        for (operation in A) {
            if (operation in 1..N) {
                counters[operation - 1] = maxOf(lastMaxCounter, counters[operation - 1]) + 1
                maxCounter = maxOf(maxCounter, counters[operation - 1])
            } else if (operation == N + 1) {
                lastMaxCounter = maxCounter
            }
        }

        for (i in counters.indices) {
            counters[i] = maxOf(counters[i], lastMaxCounter)
        }

        return counters
    }
}

fun main() {
    val N = 5
    val A = intArrayOf(3, 4, 4, 6, 1, 4, 4)
    val result = Solution().solution(N, A)
    println(result.contentToString()) // Output: [3, 2, 2, 4, 2]

    val N2 = 1
    val A2 = intArrayOf(1, 2, 1, 2, 1);
    val result2 = Solution().solution(N2, A2);
    println(result2.contentToString()) // Output: [3]

    val N3 = 1;
    val A3 = intArrayOf(1);
    val result3 = Solution().solution(N3, A3);
    println(result3.contentToString()) //Output: [1]

    val N4 = 5;
    val A4 = intArrayOf(6, 6, 6, 6);
    val result4 = Solution().solution(N4, A4);
    println(result4.contentToString()) //Output: [0, 0, 0, 0, 0]
}