package challenges.codility._3_time_complexity

/**
 * A small frog wants to get to the other side of the road. The frog is currently located at position X and wants to get to a position greater than or equal to Y. The small frog always jumps a fixed distance, D.
 *
 * Count the minimal number of jumps that the small frog must perform to reach its target.
 *
 * Write a function:
 *
 * fun solution(X: Int, Y: Int, D: Int): Int
 *
 * that, given three integers X, Y and D, returns the minimal number of jumps from position X to a position equal to or greater than Y.
 *
 * For example, given:
 *
 * X = 10
 * Y = 85
 * D = 30
 * the function should return 3, because the frog will be positioned as follows:
 *
 * after the first jump, at position 10 + 30 = 40
 * after the second jump, at position 10 + 30 + 30 = 70
 * after the third jump, at position 10 + 30 + 30 + 30 = 100
 * Write an efficient algorithm for the following assumptions:
 *
 * X, Y and D are integers within the range [1..1,000,000,000];
 * X ≤ Y.
 *
 * https://app.codility.com/programmers/lessons/3-time_complexity/frog_jmp/
 */
fun solution(X: Int, Y: Int, D: Int): Int {
    val distanceToCover = Y - X
    if (distanceToCover <= 0) {
        return 0
    }
    val jumps = distanceToCover / D
    return if (distanceToCover % D == 0) {
        jumps
    } else {
        jumps + 1
    }
}

fun main() {
    // Test cases
    println(solution(10, 85, 30)) // Expected output: 3
    println(solution(1, 5, 2))    // Expected output: 2
    println(solution(1, 1, 1))    // Expected output: 0
    println(solution(1, 2, 1))    // Expected output: 1
    println(solution(10, 100, 1))  // Expected output: 90
    println(solution(10, 100, 10)) //Expected output 9
    println(solution(10, 103, 10)) //Expected output 10
    println(solution(10, 11, 1)) //Expected output 1
    println(solution(10, 10, 1)) //Expected output 0
}