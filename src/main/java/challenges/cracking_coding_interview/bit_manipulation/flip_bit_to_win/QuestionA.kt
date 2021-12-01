package challenges.cracking_coding_interview.bit_manipulation.flip_bit_to_win

import kotlin.math.max

/**
 *  You have an integer, and you can flip exactly one bit from a 0 to a 1.
 *  Write code to find the length of the longest sequence of ls you could create.
 * EXAMPLE
 * Input: 1775 (or: 11011101111) Output: 8
 */
object QuestionA {

    var SEQUENCE_LENGTH = 32

    private fun getBit(num: Int, i: Int): Boolean {
        return num and (1 shl i) != 0
    }

    fun longestSequence(n: Int): Int {
        var maxSeq = 0
        for (i in 0 until SEQUENCE_LENGTH) {
            maxSeq = max(maxSeq, longestSequenceOf1s(n, i))
        }
        return maxSeq
    }

    fun longestSequenceOf1s(n: Int, indexToIgnore: Int): Int {
        var max = 0
        var counter = 0
        for (i in 0 until SEQUENCE_LENGTH) {
            if (i == indexToIgnore || getBit(n, i)) {
                counter++
                max = max(counter, max)
            } else {
                counter = 0
            }
        }
        return max
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val originalNumber = Int.MAX_VALUE
        val newNumber = longestSequence(originalNumber)
        println(Integer.toBinaryString(originalNumber))
        println(newNumber)
    }
}