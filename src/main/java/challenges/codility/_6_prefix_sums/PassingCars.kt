package challenges.codility._6_prefix_sums

/*
A non-empty array A consisting of N integers is given. The consecutive elements of array
 A represent consecutive cars on a road.

Array A contains only 0s and/or 1s:

0 represents a car traveling east,
1 represents a car traveling west.
The goal is to count passing cars. We say that a pair of cars (P, Q), where 0 ≤ P < Q < N, is passing
when P is traveling to the east and Q is traveling to the west.

For example, consider array A such that:

    A[0] = 0
    A[1] = 1
    A[2] = 0
    A[3] = 1
    A[4] = 1
We have five pairs of passing cars: (0, 1), (0, 3), (0, 4), (2, 3), (2, 4).

Write a function:

fun solution(A: IntArray): Int

that, given a non-empty array A of N integers, returns the number of pairs of passing cars.

The function should return −1 if the number of pairs of passing cars exceeds 1,000,000,000.

For example, given:

    A[0] = 0
    A[1] = 1
    A[2] = 0
    A[3] = 1
    A[4] = 1
the function should return 5, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer that can have one of the following values: 0, 1.

https://app.codility.com/programmers/lessons/5-prefix_sums/passing_cars/
*/

class PassingCars {
    fun solution(A: IntArray): Int {
        // 1. Problem Explanation
        // The problem asks us to count the number of pairs of cars (P, Q) in a given array A,
        // where P comes before Q in the array (P < Q), and the car at index P is traveling east (A[P] == 0)
        // while the car at index Q is traveling west (A[Q] == 1). We need to return the total count,
        // or -1 if the count exceeds 1,000,000,000.

        // 2. Pattern Identification and Rationale
        // The problem requires us to count pairs based on a specific condition involving the relative order and values of elements.
        // A naive approach of checking all pairs would be O(N^2), which is not efficient enough for the given constraints.
        // A more efficient approach is to iterate through the array once. We can maintain a count of eastward-traveling cars encountered so far.
        // When we encounter a westward-traveling car, it will form a passing pair with all the eastward-traveling cars encountered before it.
        // This approach allows us to solve the problem in O(N) time complexity.

        // 3. Solution Breakdown
        // Step 1: Initialize a variable to keep track of the number of eastward-traveling cars encountered.
        // Step 2: Initialize a variable to store the total count of passing car pairs.
        // Step 3: Iterate through the input array.
        // Step 4: If the current element is 0 (eastward), increment the eastward car count.
        // Step 5: If the current element is 1 (westward), add the current eastward car count to the total passing car count.
        // Step 6: After the loop, check if the passing car count exceeds the limit. If so, return -1. Otherwise, return the count.

        // 4. Efficient Implementation
        var eastCount = 0
        var passingCars = 0L // Use Long to avoid potential overflow before checking the limit

        for (car in A) {
            if (car == 0) {
                eastCount++
            } else if (car == 1) {
                passingCars += eastCount
            }

            if (passingCars > 1000_000_000) {
                return -1
            }
        }

        return passingCars.toInt()
    }
}

fun main() {
    val solution = PassingCars()

    // Test case 1
    val A1 = intArrayOf(0, 1, 0, 1, 1)
    val result1 = solution.solution(A1)
    println("Test Case 1: Input = [${A1.joinToString()}], Output = $result1") // Expected output: 5

    // Test case 2
    val A2 = intArrayOf(1, 1, 0, 0)
    val result2 = solution.solution(A2)
    println("Test Case 2: Input = [${A2.joinToString()}], Output = $result2") // Expected output: 0

    // Test case 3
    val A3 = intArrayOf(0, 0, 0, 0)
    val result3 = solution.solution(A3)
    println("Test Case 3: Input = [${A3.joinToString()}], Output = $result3") // Expected output: 0

    // Test case 4
    val A4 = intArrayOf(1, 1, 1, 1)
    val result4 = solution.solution(A4)
    println("Test Case 4: Input = [${A4.joinToString()}], Output = $result4") // Expected output: 0

    // Test case 5
    val A5 = IntArray(100000) { 0 }
    val result5 = solution.solution(A5)
    println("Test Case 5: Input with 100000 zeros, Output = $result5") // Expected output: 0

    // Test case 6
    val A6 = IntArray(100000) { 1 }
    val result6 = solution.solution(A6)
    println("Test Case 6: Input with 100000 ones, Output = $result6") // Expected output: 0

    // Test case 7
    val A7 = IntArray(50000) { 0 } + IntArray(50000) { 1 }
    val result7 = solution.solution(A7)
    println("Test Case 7: Input with 50000 zeros followed by 50000 ones, Output = $result7") // Expected output: 2500000000 (should return -1)

    // Test case 8
    val A8 = intArrayOf(0, 1, 0, 1, 0, 1)
    val result8 = solution.solution(A8)
    println("Test Case 8: Input = [${A8.joinToString()}], Output = $result8") // Expected output: 3 + 2 + 1 = 6
}