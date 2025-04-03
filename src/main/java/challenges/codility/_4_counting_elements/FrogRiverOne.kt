package challenges.codility._4_counting_elements

// Problem:
// A small frog wants to get to the other side of a river. The frog is initially located on one bank of the river (position 0) and wants to get to the opposite bank (position X+1). Leaves fall from a tree onto the surface of the river.
//
// You are given an array A consisting of N integers representing the falling leaves. A[K] represents the position where one leaf falls at time K, measured in seconds.
//
// The goal is to find the earliest time when the frog can jump to the other side of the river. The frog can cross only when leaves appear at every position across the river from 1 to X (that is, we want to find the earliest moment when all the positions from 1 to X are covered by leaves). You may assume that the speed of the current in the river is negligibly small, i.e. the leaves do not change their positions once they fall in the river.
//
// For example, you are given integer X = 5 and array A such that:
//
//  A[0] = 1
//  A[1] = 3
//  A[2] = 1
//  A[3] = 4
//  A[4] = 2
//  A[5] = 3
//  A[6] = 5
//  A[7] = 4
// In second 6, a leaf falls into position 5. This is the earliest time when leaves appear in every position across the river.
//
// Write a function:
//
// fun solution(X: Int, A: IntArray): Int
//
// that, given a non-empty array A consisting of N integers and integer X, returns the earliest time when the frog can jump to the other side of the river.
//
// If the frog is never able to jump to the other side of the river, the function should return −1.
//
// For example, given X = 5 and array A such that:
//
//  A[0] = 1
//  A[1] = 3
//  A[2] = 1
//  A[3] = 4
//  A[4] = 2
//  A[5] = 3
//  A[6] = 5
//  A[7] = 4
// the function should return 6, as explained above.
//
// Write an efficient algorithm for the following assumptions:
//
// N and X are integers within the range [1..100,000];
// each element of array A is an integer within the range [1..X].
//
// https://app.codility.com/programmers/lessons/4-counting_elements/frog_river_one/

fun solution(X: Int, A: IntArray): Int {
    val positions = BooleanArray(X + 1)
    var count = 0

    for (i in A.indices) {
        val position = A[i]
        if (position in 1..X && !positions[position]) {
            positions[position] = true
            count++
            if (count == X) {
                return i
            }
        }
    }

    return -1
}

fun main() {
    // Test cases
    val X1 = 5
    val A1 = intArrayOf(1, 3, 1, 4, 2, 3, 5, 4)
    println(solution(X1, A1)) // Output: 6

    val X2 = 2
    val A2 = intArrayOf(1, 1, 1, 1)
    println(solution(X2, A2)) // Output: -1

    val X3 = 1
    val A3 = intArrayOf(1)
    println(solution(X3, A3)) // Output: 0

    val X4 = 2
    val A4 = intArrayOf(1, 2)
    println(solution(X4, A4)) // Output: 1

    val X5 = 2
    val A5 = intArrayOf(2, 1)
    println(solution(X5, A5)) //Output: 1

    val X6 = 3
    val A6 = intArrayOf(1, 3, 2)
    println(solution(X6, A6)) //Output: 2

    val X7 = 3
    val A7 = intArrayOf(3, 2, 1)
    println(solution(X7, A7)) //Output: 2

    val X8 = 4
    val A8 = intArrayOf(1, 2, 3, 4)
    println(solution(X8, A8)) //Output: 3

    val X9 = 4
    val A9 = intArrayOf(4, 3, 2, 1)
    println(solution(X9, A9)) //Output: 3

    val X10 = 5
    val A10 = intArrayOf(3, 1, 2, 5, 4)
    println(solution(X10, A10)) //Output: 4
}