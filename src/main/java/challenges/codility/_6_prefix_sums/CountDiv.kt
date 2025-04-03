package challenges.codility._6_prefix_sums

// Write a function:
//
// fun solution(A: Int, B: Int, K: Int): Int
//
// that, given three integers A, B and K, returns the number of integers within the range [A..B]
// that are divisible by K, i.e.:
//
// { i : A ≤ i ≤ B, i mod K = 0 }
//
// For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are
// three numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.
//
// Write an efficient algorithm for the following assumptions:
//
// A and B are integers within the range [0..2,000,000,000];
// K is an integer within the range [1..2,000,000,000];
// A ≤ B.
//
// https://app.codility.com/programmers/lessons/5-prefix_sums/count_div/

class CountDiv {
    /**
     * 1. Problem Explanation
     * The problem asks us to count the number of integers within a given range [A, B] (inclusive)
     * that are divisible by a given integer K.
     *
     * 2. Pattern Identification and Rationale
     * The problem can be solved using a mathematical approach rather than iterating through
     * the entire range. We can find the count of numbers divisible by K up to B and subtract
     * the count of numbers divisible by K up to A-1.
     * This approach avoids iterating through potentially very large ranges, making it efficient.
     *
     * 3. Solution Breakdown
     * Step 1: Find the number of multiples of K less than or equal to B. This can be calculated
     * by integer division B / K.
     * Step 2: Find the number of multiples of K less than or equal to A - 1. This can be calculated
     * by integer division (A - 1) / K.
     * Step 3: The number of multiples of K within the range [A, B] is the difference between
     * the results of Step 1 and Step 2.
     *
     * 4. Efficient Implementation
     * The implementation uses integer division and subtraction, which are constant time operations.
     * Therefore, the overall time complexity is O(1). The space complexity is also O(1) as we are only using a few variables.
     */
    fun solution(A: Int, B: Int, K: Int): Int {
        if (A == 0) {
            return (B / K) + 1  // Include 0
        }

        val countB = B / K
        val countA = (A - 1) / K
        return countB - countA
    }
}

fun main() {
    val solution = CountDiv()

    // Test case 1: A = 6, B = 11, K = 2
    val result1 = solution.solution(6, 11, 2)
    println("For A = 6, B = 11, K = 2, the count is: $result1") // Expected output: 3

    // Test case 2: A = 0, B = 0, K = 1
    val result2 = solution.solution(0, 0, 1)
    println("For A = 0, B = 0, K = 1, the count is: $result2") // Expected output: 1

    // Test case 3: A = 1, B = 10, K = 3
    val result3 = solution.solution(1, 10, 3)
    println("For A = 1, B = 10, K = 3, the count is: $result3") // Expected output: 3 (3, 6, 9)

    // Test case 4: A = 0, B = 20, K = 5
    val result4 = solution.solution(0, 20, 5)
    println("For A = 0, B = 20, K = 5, the count is: $result4") // Expected output: 5 (0, 5, 10, 15, 20)

    // Test case 5: A = 5, B = 25, K = 5
    val result5 = solution.solution(5, 25, 5)
    println("For A = 5, B = 25, K = 5, the count is: $result5") // Expected output: 5 (5, 10, 15, 20, 25)

    // Test case 6: Large numbers
    val result6 = solution.solution(0, 2000000000, 10000000)
    println("For A = 0, B = 2000000000, K = 10000000, the count is: $result6") // Expected output: 201

    val result7 = solution.solution(10000000, 2000000000, 10000000)
    println("For A = 10000000, B = 2000000000, K = 10000000, the count is: $result7") // Expected output: 200
}