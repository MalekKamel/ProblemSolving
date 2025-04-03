package challenges.codility._4_counting_elements

/**
The Problem:

Write a function:

fun solution(A: IntArray): Int

that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].

https://app.codility.com/programmers/lessons/4-counting_elements/missing_integer/
*/

class MissingInteger {
    fun solution(A: IntArray): Int {
        // 1. Problem Explanation
        // The problem asks us to find the smallest positive integer (greater than 0) that is missing from a given array of integers.

        // 2. Pattern Identification and Rationale
        // The most suitable approach for this problem is to use a set data structure.
        // A set allows for efficient (average O(1) time complexity) checking of whether an element exists in the collection.
        // We can iterate through the input array and store all the positive integers in a set.
        // Then, we can start checking for positive integers starting from 1. The first positive integer that is not found in the set will be our answer.
        // This approach avoids redundant checks and provides a relatively efficient solution.

        // 3. Solution Breakdown
        // Step 1: Create a HashSet to store all the positive integers present in the input array.
        val positiveNumbers = HashSet<Int>()

        // Step 2: Iterate through the input array and add all positive numbers to the HashSet.
        for (num in A) {
            if (num > 0) {
                positiveNumbers.add(num)
            }
        }

        // Step 3: Start checking for the smallest missing positive integer, starting from 1.
        var missingPositive = 1
        while (true) {
            // Step 4: If the current 'missingPositive' is not found in the HashSet, then it is the smallest missing positive integer.
            if (!positiveNumbers.contains(missingPositive)) {
                return missingPositive
            }
            // Step 5: If 'missingPositive' is found, increment it and continue checking.
            missingPositive++
        }
    }
}

fun main() {
    val solution = MissingInteger()

    // Test case 1
    val A1 = intArrayOf(1, 3, 6, 4, 1, 2)
    val result1 = solution.solution(A1)
    println("Input: ${A1.contentToString()}, Output: $result1") // Expected output: 5

    // Test case 2
    val A2 = intArrayOf(1, 2, 3)
    val result2 = solution.solution(A2)
    println("Input: ${A2.contentToString()}, Output: $result2") // Expected output: 4

    // Test case 3
    val A3 = intArrayOf(-1, -3)
    val result3 = solution.solution(A3)
    println("Input: ${A3.contentToString()}, Output: $result3") // Expected output: 1

    // Test case 4
    val A4 = intArrayOf(1)
    val result4 = solution.solution(A4)
    println("Input: ${A4.contentToString()}, Output: $result4") // Expected output: 2

    // Test case 5
    val A5 = intArrayOf(2)
    val result5 = solution.solution(A5)
    println("Input: ${A5.contentToString()}, Output: $result5") // Expected output: 1

    // Test case 6
    val A6 = intArrayOf(Int.MAX_VALUE)
    val result6 = solution.solution(A6)
    println("Input: ${A6.contentToString()}, Output: $result6") // Expected output: 1

    // Test case 7
    val A7 = intArrayOf(1, 1, 1)
    val result7 = solution.solution(A7)
    println("Input: ${A7.contentToString()}, Output: $result7") // Expected output: 2
}