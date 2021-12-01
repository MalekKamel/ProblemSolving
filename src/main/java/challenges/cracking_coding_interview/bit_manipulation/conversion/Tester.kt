package challenges.cracking_coding_interview.bit_manipulation.conversion

import challenges.util.AssortedMethods.toFullBinaryString


object Tester {

    @JvmStatic
    fun main(args: Array<String>) {
        val a = -23432
        val b = 512132
        println(a.toString() + ": " + toFullBinaryString(a))
        println(b.toString() + ": " + toFullBinaryString(b))
        val nbits = QuestionA.bitSwapRequired(a, b)
        val nbits2 = QuestionB.bitSwapRequired(a, b)
        println("Required number of bits: $nbits $nbits2")
    }
}