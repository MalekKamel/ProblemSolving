package challenges.cracking_coding_interview.bit_manipulation.conversion

import challenges.util.AssortedMethods.toFullBinaryString


/**
 * Write a function to determine the number of bits you would need to flip to convert integer A to integer B.
 * EXAMPLE
 * Input: 29 (or: 11101), 15 (or: 01111) Output: 2
 */
object QuestionA {

    fun bitSwapRequired(a: Int, b: Int): Int {
        var count = 0
        var c = a xor b
        while (c != 0) {
            count += c and 1 // Increment count if c ends with a 1
            c = c ushr 1 // Shift right by 1
        }
        return count
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val a = -23432
        val b = 512132
        println(a.toString() + ": " + toFullBinaryString(a))
        println(b.toString() + ": " + toFullBinaryString(b))
        println("Required number of bits: " + bitSwapRequired(a, b))
    }
}