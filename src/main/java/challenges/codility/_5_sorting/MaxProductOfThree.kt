package challenges.codility._5_sorting

/**
A non-empty array A consisting of N integers is given. The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).

For example, array A such that:

    A[0] = -3
    A[1] = 1
    A[2] = 2
    A[3] = -2
    A[4] = 5
    A[5] = 6
contains the following example triplets:

(0, 1, 2), product is −3 * 1 * 2 = −6
(1, 2, 4), product is 1 * 2 * 5 = 10
(2, 4, 5), product is 2 * 5 * 6 = 60
Your goal is to find the maximal product of any triplet.

Write a function:

fun solution(A: IntArray): Int

that, given a non-empty array A, returns the value of the maximal product of any triplet.

For example, given array A such that:

    A[0] = -3
    A[1] = 1
    A[2] = 2
    A[3] = -2
    A[4] = 5
    A[5] = 6
the function should return 60, as the product of triplet (2, 4, 5) is maximal.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [3..100,000];
each element of array A is an integer within the range [−1,000..1,000].

https://app.codility.com/programmers/lessons/6-sorting/max_product_of_three/
*/

class MaxProductOfThree {
    /**
     * 1. Problem Explanation
     * The problem asks us to find the maximal product of any triplet
     * (three numbers with increasing indices) from a given array of integers.
     *
     * 2. Pattern Identification and Rationale
     * The maximal product can be formed by either the three largest positive numbers or
     * by the two smallest negative numbers multiplied by the largest positive number. Sorting
     * the array allows us to easily identify these numbers. Sorting the array takes O(N log N)
     * time, which is efficient for the given constraints. After sorting, we can directly access
     * the potential candidates for the maximal product.
     *
     * 3. Solution Breakdown
     * - Step 1: Sort the input array `A` in non-decreasing order.
     * - Step 2: Calculate the product of the three largest elements in the sorted array. These will
     * be the last three elements.
     * - Step 3: Calculate the product of the two smallest elements and the largest element in
     * the sorted array. These will be the first two and the last element, respectively. This case
     * is important when the array contains negative numbers, as the product of two negative numbers
     * is positive and can potentially lead to a larger overall product when multiplied by a large
     * positive number.
     * - Step 4: Return the maximum of the two products calculated in steps 2 and 3.
     *
     * 4. Efficient Implementation
     */
    fun solution(A: IntArray): Int {
        val n = A.size
        A.sort()

        // Product of the three largest elements
        val product1 = A[n - 1] * A[n - 2] * A[n - 3]

        // Product of the two smallest elements and the largest element
        val product2 = A[0] * A[1] * A[n - 1]

        return maxOf(product1, product2)
    }
}

fun main() {
    val solution = MaxProductOfThree()

    // Test case 1
    val A1 = intArrayOf(-3, 1, 2, -2, 5, 6)
    val result1 = solution.solution(A1)
    println("Maximal product for A1: $result1") // Expected output: 60

    // Test case 2
    val A2 = intArrayOf(-5, -4, -3, -2, -1)
    val result2 = solution.solution(A2)
    println("Maximal product for A2: $result2") // Expected output: -6

    // Test case 3
    val A3 = intArrayOf(1, 2, 3)
    val result3 = solution.solution(A3)
    println("Maximal product for A3: $result3") // Expected output: 6

    // Test case 4
    val A4 = intArrayOf(-1, 0, 1)
    val result4 = solution.solution(A4)
    println("Maximal product for A4: $result4") // Expected output: 0

    // Test case 5
    val A5 = intArrayOf(-10, -10, 1, 2, 3)
    val result5 = solution.solution(A5)
    println("Maximal product for A5: $result5") // Expected output: 300

    // Test case 6
    val A6 = intArrayOf(10, 10, 10)
    val result6 = solution.solution(A6)
    println("Maximal product for A6: $result6") // Expected output: 1000
}