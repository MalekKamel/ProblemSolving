package challenges.codility._3_time_complexity

// Problem:
// An array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.
// Your goal is to find that missing element.
// Write a function:
// fun solution(A: IntArray): Int
// that, given an array A, returns the value of the missing element.
// For example, given array A such that:
//   A[0] = 2
//   A[1] = 3
//   A[2] = 1
//   A[3] = 5
// the function should return 4, as it is the missing element.
// Write an efficient algorithm for the following assumptions:
// N is an integer within the range [0..100,000];
// the elements of A are all distinct;
// each element of array A is an integer within the range [1..(N + 1)].
//
// https://app.codility.com/programmers/lessons/3-time_complexity/perm_missing_elem/

fun solution(A: IntArray): Int {
    val n = A.size
    // Pattern: The sum of numbers from 1 to (n + 1) can be calculated using the formula: (n + 1) * (n + 2) / 2.
    // The sum of the given array can be calculated.
    // The missing element is the difference between these two sums.

    // The formula (n + 1) * (n + 2) / 2 calculates the sum of the arithmetic progression from 1 to (n + 1). Here's a breakdown of why it works:
    //
    // Understanding Arithmetic Progressions
    //
    // An arithmetic progression is a sequence of numbers in which the difference between any two consecutive terms is constant.  
    // In our case, the sequence is 1, 2, 3, ..., (n + 1), where the common difference is 1.
    // Derivation of the Formula
    //
    // Sum of an Arithmetic Progression:
    //
    // The general formula for the sum (S) of an arithmetic progression is:
    // S = (n / 2) * (first term + last term)
    // Where:
    // n is the number of terms.
    // The "first term" is the initial value in the sequence.
    // The "last term" is the final value in the sequence.
    // Applying the Formula to Our Problem:
    //
    // In our sequence (1, 2, 3, ..., (n + 1)):
    // The number of terms is (n + 1).
    // The first term is 1.
    // The last term is (n + 1).
    // Substituting these values into the general formula:
    // S = ((n + 1) / 2) * (1 + (n + 1))
    // S = ((n + 1) / 2) * (n + 2)
    // S = (n + 1) * (n + 2) / 2
    // Intuitive Explanation
    //
    // Imagine pairing the first and last terms, the second and second-to-last terms, and so on.  
    // Each pair sums to (n + 2):
    // 1 + (n + 1) = (n + 2)
    // 2 + n = (n + 2)
    // 3 + (n - 1) = (n + 2)
    // There are (n + 1) / 2 such pairs.
    // Therefore, the total sum is ((n + 1) / 2) * (n + 2), which simplifies to (n + 1) * (n + 2) / 2.
    // In the context of the problem:
    //
    // The N in the problem, refers to the number of elements in the array.
    // The sequence that we are summing is from 1 to N+1, as that is the range of the numbers that the problem specifies.
    // That is why we use (N+1) in the formula, to calculate the sum of the sequence from 1 to N+1.

    val expectedSum = (n + 1).toLong() * (n + 2) / 2
    val actualSum = A.sum().toLong()
    return (expectedSum - actualSum).toInt()
}

fun main() {
    // Test cases
    val test1 = intArrayOf(2, 3, 1, 5)
    println(solution(test1)) // Output: 4

    val test2 = intArrayOf(1)
    println(solution(test2)) // Output: 2

    val test3 = intArrayOf(2)
    println(solution(test3)) // Output: 1

    val test4 = intArrayOf(4, 1, 3)
    println(solution(test4)) // output: 2

    val test5 = intArrayOf()
    println(solution(test5)) //output: 1
}