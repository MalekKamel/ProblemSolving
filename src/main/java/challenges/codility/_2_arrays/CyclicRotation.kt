package challenges.codility._2_arrays

/**
 * An array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one index,
 * and the last element of the array is moved to the first place. For example, the rotation of array A = [3, 8, 9, 7, 6] is
 * [6, 3, 8, 9, 7] (elements are shifted right by one index and 6 is moved to the first place).
 *
 * The goal is to rotate array A K times; that is, each element of A will be shifted to the right K times.
 *
 * Write a function:
 *
 * fun solution(A: IntArray, K: Int): IntArray
 *
 * that, given an array A consisting of N integers and an integer K, returns the array A rotated K times.
 *
 * For example, given
 *
 * A = [3, 8, 9, 7, 6]
 * K = 3
 * the function should return [9, 7, 6, 3, 8]. Three rotations were made:
 *
 * [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
 * [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
 * [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
 * For another example, given
 *
 * A = [0, 0, 0]
 * K = 1
 * the function should return [0, 0, 0]
 *
 * Given
 *
 * A = [1, 2, 3, 4]
 * K = 4
 * the function should return [1, 2, 3, 4]
 *
 * Assume that:
 *
 * N and K are integers within the range [0..100];
 * each element of array A is an integer within the range [−1,000..1,000].
 * In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
 *
 * https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/
 */
fun solution(A: IntArray, K: Int): IntArray {
    val n = A.size
    if (n == 0) {
        return A
    }
    val rotations = K % n
    if (rotations == 0) {
        return A
    }
    val rotatedArray = IntArray(n)
    for (i in 0 until n) {
        rotatedArray[(i + rotations) % n] = A[i]
    }
    return rotatedArray
}

fun main() {
    // Test cases
    val test1 = intArrayOf(3, 8, 9, 7, 6)
    val result1 = solution(test1, 3)
    println(result1.contentToString()) // Output: [9, 7, 6, 3, 8]

    val test2 = intArrayOf(0, 0, 0)
    val result2 = solution(test2, 1)
    println(result2.contentToString()) // Output: [0, 0, 0]

    val test3 = intArrayOf(1, 2, 3, 4)
    val result3 = solution(test3, 4)
    println(result3.contentToString()) // Output: [1, 2, 3, 4]

    val test4 = intArrayOf(1, 2, 3, 4, 5)
    val result4 = solution(test4, 1)
    println(result4.contentToString()) // Output: [5, 1, 2, 3, 4]

    val test5 = intArrayOf(1, 2, 3, 4, 5)
    val result5 = solution(test5, 5)
    println(result5.contentToString()) // Output: [1, 2, 3, 4, 5]

    val test6 = intArrayOf(1,2,3,4,5)
    val result6 = solution(test6, 6)
    println(result6.contentToString()) // Output : [5, 1, 2, 3, 4]

    val test7 = intArrayOf()
    val result7 = solution(test7, 3);
    println(result7.contentToString()) // Output: []

}