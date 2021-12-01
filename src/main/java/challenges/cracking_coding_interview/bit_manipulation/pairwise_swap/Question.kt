package challenges.cracking_coding_interview.bit_manipulation.pairwise_swap

import challenges.util.AssortedMethods.toFullBinaryString


/**
 * Write a program to swap odd and even bits in an integer with as few instructions
 * as possible (e.g., bit O and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).
 */
object Question {

    private fun swapOddEvenBits(x: Int): Int {
        return x and -0x55555556 ushr 1 or (x and 0x55555555 shl 1)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val a = 234321
        println(a.toString() + ": " + toFullBinaryString(a))
        val b = swapOddEvenBits(a)
        println(b.toString() + ": " + toFullBinaryString(b))
    }
}