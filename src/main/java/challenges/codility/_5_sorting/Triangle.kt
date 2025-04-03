package challenges.codility._5_sorting

import java.util.Arrays

/**
An array A consisting of N integers is given. A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:

A[P] + A[Q] > A[R],
A[Q] + A[R] > A[P],
A[R] + A[P] > A[Q].
For example, consider array A such that:

A[0] = 10    A[1] = 2    A[2] = 5
A[3] = 1      A[4] = 8    A[5] = 20
Triplet (0, 2, 4) is triangular.

Write a function:

fun solution(A: IntArray): Int

that, given an array A consisting of N integers, returns 1 if there exists a triangular
triplet for this array and returns 0 otherwise.

For example, given array A such that:

A[0] = 10    A[1] = 2    A[2] = 5
A[3] = 1      A[4] = 8    A[5] = 20
the function should return 1, as explained above. Given array A such that:

A[0] = 10    A[1] = 50    A[2] = 5
A[3] = 1
the function should return 0.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].

https://app.codility.com/programmers/lessons/6-sorting/triangle/
 */

class Triangle {
    /**
     * 1. Problem Explanation
     * The problem asks us to find if there exists any triplet of indices (P, Q, R)
     * in a given array A such that 0 ≤ P < Q < R < N and the following three conditions hold:
     * A[P] + A[Q] > A[R]
     * A[Q] + A[R] > A[P]
     * A[R] + A[P] > A[Q]
     * If such a triplet exists, the function should return 1, otherwise 0.
     *
     * 2. Pattern Identification and Rationale
     * The core of the problem lies in checking a specific condition for triplets of numbers
     * in an array. A naive approach would be to iterate through all possible combinations of three
     * indices, which would have a time complexity of O(N^3). Given the constraint
     * on N (up to 100,000), this approach is not efficient enough.
     *
     * Let's consider what happens if we sort the array A. Suppose we have a sorted array such
     * that A[P] <= A[Q] <= A[R] and P < Q < R.
     * Then, the conditions A[Q] + A[R] > A[P] and A[R] + A[P] > A[Q] will always be true because
     * A[R] is the largest (or equal to) and A[P] is the smallest (or equal to). Since all numbers
     * are integers, their sums will naturally be greater than or equal to the other element. For
     * instance, A[Q] + A[R] >= A[Q] + A[Q] >= A[R] >= A[P] (if elements are non-negative, but even
     * with negative numbers, if sorted, this generally holds).
     *
     * The crucial condition becomes A[P] + A[Q] > A[R]. If we have a sorted array, we can consider
     * adjacent triplets. Let's say we have indices i, i+1, and i+2. If A[i] + A[i+1] > A[i+2]
     * in a sorted array, then the triplet (i, i+1, i+2) satisfies the triangular condition because:
     * A[i+1] + A[i+2] > A[i+1] >= A[i]
     * A[i+2] + A[i] > A[i+2] >= A[i+1]
     * A[i] + A[i+1] > A[i+2] (this is the condition we are checking).
     *
     * Therefore, the pattern we can use is to first sort the array and then iterate through
     * adjacent triplets to check the condition A[i] + A[i+1] > A[i+2]. This reduces the time
     * complexity significantly.
     *
     * 3. Solution Breakdown
     * Step 1: Sort the input array A in non-decreasing order.
     * Step 2: Iterate through the sorted array from the first element up to the third-to-last
     * element (index N-3). This is because we need at least three elements to form a triplet.
     * Step 3: For each index `i` in the loop, consider the triplet formed by the elements at
     * indices `i`, `i+1`, and `i+2`. Since the array is sorted, we have A[i] <= A[i+1] <= A[i+2],
     * and the indices satisfy the condition P < Q < R.
     * Step 4: Check if the condition A[i] + A[i+1] > A[i+2] holds.
     * Step 5: If this condition is true for any `i`, it means we have found a triangular triplet,
     * so we can immediately return 1.
     * Step 6: If the loop finishes without finding any such triplet, it means no triangular
     * triplet exists in the array, so we return 0.
     *
     * 4. Efficient Implementation
     * The implementation involves sorting the array, which typically takes O(N log N) time. Then,
     * we iterate through the array once, which takes O(N) time. The overall time complexity will
     * be dominated by the sorting step, making it O(N log N). The space complexity depends on
     * the sorting algorithm used. In Kotlin, `Arrays.sort()` for primitive types uses a dual-pivot
     * quicksort which has an average time complexity of O(N log N) and a space complexity of O(log N)
     * due to recursion depth.
     */
    fun solution(A: IntArray): Int {
        val n = A.size
        if (n < 3) {
            return 0
        }
        Arrays.sort(A)
        for (i in 0 until n - 2) {
            // Need to use Long to avoid potential integer overflow when adding A[i] and A[i+1]
            if (A[i].toLong() + A[i + 1].toLong() > A[i + 2]) {
                return 1
            }
        }
        return 0
    }
}

fun main() {
    val solution = Triangle()

    // Test case 1
    val A1 = intArrayOf(10, 2, 5, 1, 8, 20)
    println(solution.solution(A1)) // Expected output: 1

    // Test case 2
    val A2 = intArrayOf(10, 50, 5, 1)
    println(solution.solution(A2)) // Expected output: 0

    // Test case 3: Empty array
    val A3 = intArrayOf()
    println(solution.solution(A3)) // Expected output: 0

    // Test case 4: Array with two elements
    val A4 = intArrayOf(1, 2)
    println(solution.solution(A4)) // Expected output: 0

    // Test case 5: Array with three elements that form a triangle
    val A5 = intArrayOf(3, 4, 5)
    println(solution.solution(A5)) // Expected output: 1

    // Test case 6: Array with three elements that do not form a triangle
    val A6 = intArrayOf(1, 2, 5)
    println(solution.solution(A6)) // Expected output: 0

    // Test case 7: Array with negative numbers (should still work if sorted)
    val A7 = intArrayOf(-3, -2, -1)
    println(solution.solution(A7)) // Expected output: 0

    val A8 = intArrayOf(1, 1, 1)
    println(solution.solution(A8)) // Expected output: 1

    val A9 = intArrayOf(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE)
    println(solution.solution(A9)) // Expected output: 1

    val A10 = intArrayOf(-2147483648, -2147483648, -2147483648)
    println(solution.solution(A10)) // Expected output: 0
}