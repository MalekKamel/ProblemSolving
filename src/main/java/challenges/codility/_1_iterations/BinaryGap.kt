package challenges.codility._1_iterations

/**
A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.

For example, number 9 has binary representation 1001 and contains a binary gap of length 2. The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. The number 20 has binary representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps. The number 32 has binary representation 100000 and has no binary gaps.

Write a function:

class Solution { public int solution(int N); }

that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.

For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5. Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..2,147,483,647].

https://app.codility.com/programmers/lessons/1-iterations/binary_gap/
 */
fun solution(N: Int): Int {
    val binary = Integer.toBinaryString(N)
    var maxGap = 0
    var currentGap = 0
    var foundOne = false

    for (char in binary) {
        if (char == '1') {
            if (foundOne) {
                maxGap = maxOf(maxGap, currentGap)
            }
            currentGap = 0
            foundOne = true
        } else if (foundOne) {
            currentGap++
        }
    }

    return maxGap
}

fun main() {
    // Test cases
    val testCases = mapOf(
        1041 to 5,
        32 to 0,
        9 to 2,
        529 to 4,
        20 to 1,
        15 to 0,
        1 to 0,
        6 to 0,
        561892 to 3,
        805306373 to 25
    )

    var allTestsPassed = true;

    testCases.forEach { (input, expected) ->
        val result = solution(input)
        if (result == expected) {
            println("Test case: $input -> Passed (Result: $result)")
        } else {
            println("Test case: $input -> Failed (Expected: $expected, Actual: $result)")
            allTestsPassed = false;
        }
    }
    if (allTestsPassed) {
        println("All tests passed!")
    } else {
        println("Some tests failed.")
    }
}