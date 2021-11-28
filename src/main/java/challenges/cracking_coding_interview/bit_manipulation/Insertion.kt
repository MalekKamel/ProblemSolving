package challenges.cracking_coding_interview.bit_manipulation

import challenges.util.AssortedMethods.toFullBinaryString


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