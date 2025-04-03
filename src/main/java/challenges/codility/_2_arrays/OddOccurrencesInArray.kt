package challenges.codility._2_arrays

/**
 * A non-empty array A consisting of N integers is given. The array contains an odd number of elements,
 * and each element of the array can be paired with another element that has the same value,
 * except for one element that is left unpaired.
 *
 * For example, in array A such that:
 *
 * A[0] = 9  A[1] = 3  A[2] = 9
 * A[3] = 3  A[4] = 9  A[5] = 7
 * A[6] = 9
 * the elements at indexes 0 and 2 have value 9,
 * the elements at indexes 1 and 3 have value 3,
 * the elements at indexes 4 and 6 have value 9,
 * the element at index 5 has value 7 and is unpaired.
 * Write a function:
 *
 * fun solution(A: IntArray): Int
 *
 * that, given an array A consisting of N integers fulfilling the above conditions, returns the value of the unpaired element.
 *
 * For example, given array A such that:
 *
 * A[0] = 9  A[1] = 3  A[2] = 9
 * A[3] = 3  A[4] = 9  A[5] = 7
 * A[6] = 9
 * the function should return 7, as explained in the example above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an odd integer within the range [1..1,000,000];
 * each element of array A is an integer within the range [1..1,000,000,000];
 * all but one of the values in A occur an even number of times.
 *
 * https://app.codility.com/programmers/lessons/2-arrays/odd_occurrences_in_array/
 */
fun solution(A: IntArray): Int {
    // Pattern: XOR operation.
    // Explanation:
    // The XOR (exclusive OR) operation has the property that a number XORed with itself is 0.
    // Also, XOR is commutative and associative, meaning the order of operations doesn't matter.
    // If we XOR all the elements in the array, the paired elements will cancel each other out (result in 0),
    // and the unpaired element will remain.

    var result = 0
    for (element in A) {
        result = result xor element
    }
    return result
}

fun main() {
    // Test cases
    val test1 = intArrayOf(9, 3, 9, 3, 9, 7, 9)
    println(solution(test1)) // Expected: 7

    val test2 = intArrayOf(1, 2, 1, 2, 3)
    println(solution(test2)) // Expected: 3

    val test3 = intArrayOf(5)
    println(solution(test3)) //Expected: 5

    val test4 = intArrayOf(1,1,2)
    println(solution(test4)) //Expected 2

    val test5 = intArrayOf(1,1,3,3,4)
    println(solution(test5)) //Expected 4

}