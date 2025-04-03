package challenges.codility._4_counting_elements

// Problem:
// A non-empty array A consisting of N integers is given.
//
// A permutation is a sequence containing each element from 1 to N once, and only once.
//
// For example, array A such that:
//
//     A[0] = 4
//     A[1] = 1
//     A[2] = 3
//     A[3] = 2
// is a permutation, but array A such that:
//
//     A[0] = 4
//     A[1] = 1
//     A[2] = 3
// is not a permutation, because value 2 is missing.
//
// The goal is to check whether array A is a permutation.
//
// Write a function:
//
// fun solution(A: IntArray): Int
//
// that, given an array A, returns 1 if array A is a permutation and 0 if it is not.
//
// For example, given array A such that:
//
//     A[0] = 4
//     A[1] = 1
//     A[2] = 3
//     A[3] = 2
// the function should return 1.
//
// Given array A such that:
//
//     A[0] = 4
//     A[1] = 1
//     A[2] = 3
// the function should return 0.
//
// Write an efficient algorithm for the following assumptions:
//
// N is an integer within the range [1..100,000];
// each element of array A is an integer within the range [1..1,000,000,000].
//
// https://app.codility.com/programmers/lessons/4-counting_elements/perm_check/

fun solution(A: IntArray): Int {
    val n = A.size
    val seen = BooleanArray(n + 1)

    for (element in A) {
        if (element < 1 || element > n || seen[element]) {
            return 0
        }
        seen[element] = true
    }

    return 1
}

fun main() {
    // Test cases
    val test1 = intArrayOf(4, 1, 3, 2)
    val test2 = intArrayOf(4, 1, 3)
    val test3 = intArrayOf(1)
    val test4 = intArrayOf(2)
    val test5 = intArrayOf(1,2,3,4,5)
    val test6 = intArrayOf(1,3,6,4,1,2)
    val test7 = intArrayOf(1,4,1)

    println("Test 1: ${solution(test1)}") // Output: 1
    println("Test 2: ${solution(test2)}") // Output: 0
    println("Test 3: ${solution(test3)}") // Output: 1
    println("Test 4: ${solution(test4)}") // Output: 0
    println("Test 5: ${solution(test5)}") // Output: 1
    println("Test 6: ${solution(test6)}") // Output: 0
    println("Test 7: ${solution(test7)}") // Output: 0
}