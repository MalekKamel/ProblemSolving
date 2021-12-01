package challenges.cracking_coding_interview.bit_manipulation

import challenges.util.AssortedMethods.toFullBinaryString


/**
 * You are given two 32-bit numbers, N and M, and two bit positions, i and j.
 * Write a method to insert M into N such that M starts at bit j and ends at bit i.
 * You can assume that the bits j through i have enough space to fit all of M.
 * That is, if M = 10011, you can assume that there are at least 5 bits between j and i.
 * You would not, for example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
 * EXAMPLE
 * Input: N 10000000000, M 10011, i 2, j 6 Output:N = 10001001100
 */
object Insertion {

    private fun updateBits(n: Int, m: Int, i: Int, j: Int): Int {
        // Validation
        if (i > j || i < 0 || j >= 32) {
            return 0
        }

        /* Create a mask to clear bits i through j in n
		/ * EXAMPLE: i = 2, j = 4. Result should be 11100011.
		 * (Using 8 bits for this example.  This is obviously not actually 8 bits.)
		 */
        val allOnes = 0.inv() // allOnes = 11111111
        val left = if (j < 31) allOnes shl j + 1 else 0 // 1s until position j, then 0s. left = 11100000
        val right = (1 shl i) - 1 // 1s after position i.  right = 00000011
        val mask = left or right // All 1s, except for 0s between i and j. mask = 11100011

        /* Clear i through j, then put m in there */
        val nCleared = n and mask // Clear bits j through i.
        val mShifted = m shl i // Move m into correct position.

        /* OR them, and we're done! */return nCleared or mShifted
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val a = 23423.inv()
        println(toFullBinaryString(a))
        val b = 5
        println(toFullBinaryString(b))
        val c = updateBits(a, b, 29, 31)
        println(toFullBinaryString(c))
    }
}