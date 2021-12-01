package challenges.cracking_coding_interview.bit_manipulation.conversion

import challenges.util.AssortedMethods.toFullBinaryString


/**
 * Write a function to determine the number of bits you would need to flip to convert integer A to integer B.
 * EXAMPLE
 * Input: 29 (or: 11101), 15 (or: 01111) Output: 2
 */
object QuestionB {

    fun bitSwapRequired(a: Int, b: Int): Int {
        var count = 0
        var c = a xor b

        println("****")
        println(c.toString() + ": " + toFullBinaryString(c))

        while (c != 0) {
            println("c - 1: " + c + ": " + toFullBinaryString(c - 1))

            c = c and c - 1

            println("c: " + c + ": " + toFullBinaryString(c))

            count++

            println("****")
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