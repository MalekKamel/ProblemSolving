package challenges.educative_grokking_coding_interview.fast_slow_pointers

import challenges.util.PrintHyphens
import kotlin.math.pow

/**
Write an algorithm to determine if a number nis happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 11 (where it will stay), or it loops endlessly in a cycle which does not include 11.
Those numbers for which this process ends in 1 1
are happy.
Return TRUE if n is a happy number, and FALSE if not.

Time complexity: O(nlog(n))
Space complexity: O(1)

https://www.educative.io/courses/grokking-coding-interview-patterns-java/RLDKqX6OJwY
 */
object HappyNumber {
    private fun sumOfSquaredDigits(number: Int): Int {
        var number = number
        var totalSum = 0
        while (number != 0) {
            val digit = number % 10
            number /= 10
            totalSum += digit.toDouble().pow(2.0).toInt()
        }
        return totalSum
    }

    private fun isHappyNumber(n: Int): Boolean {
        var slowPointer = n
        var fastPointer = sumOfSquaredDigits(n)
        while (fastPointer != 1 && slowPointer != fastPointer) {
            slowPointer = sumOfSquaredDigits(slowPointer)
            fastPointer = sumOfSquaredDigits(sumOfSquaredDigits(fastPointer))
        }
        return fastPointer == 1
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val a = intArrayOf(1, 5, 19, 25, 7)
        for (i in a.indices) {
            println((i + 1).toString() + ".\tInput Number: " + a[i])
            val output = if (isHappyNumber(a[i])) "True" else "False"
            println("\n\tIs it a happy number? $output")
            println(PrintHyphens.repeat("-", 100))
        }
    }
}