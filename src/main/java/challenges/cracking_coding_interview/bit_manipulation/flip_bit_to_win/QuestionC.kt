package challenges.cracking_coding_interview.bit_manipulation.flip_bit_to_win

import kotlin.math.max

/**
 *  You have an integer, and you can flip exactly one bit from a 0 to a 1.
 *  Write code to find the length of the longest sequence of ls you could create.
 * EXAMPLE
 * Input: 1775 (or: 11011101111) Output: 8
 */
object QuestionC {

    var SEQUENCE_LENGTH = 32

    /* Given set of three sequences ordered as {0s, then 1s, then 0s},
	 * find max sequence that can be formed. */
    private fun getMaxSequence(sequences: IntArray): Int { /* 1s, then 0s, then [old] ones */
        return if (sequences[1] == 1) { // a single 0 -> merge sequences
            sequences[0] + sequences[2] + 1
        } else if (sequences[1] == 0) { // no 0s -> take one side
            max(sequences[0], sequences[2])
        } else {  // many 0s -> take side, add 1 (flip a bit)
            max(sequences[0], sequences[2]) + 1
        }
    }

    fun shift(sequences: IntArray) {
        sequences[2] = sequences[1]
        sequences[1] = sequences[0]
        sequences[0] = 0
    }

    fun longestSequence(n: Int): Int {
        var n = n
        var searchingFor = 0
        val sequences = intArrayOf(0, 0, 0) // Counts of last 3 sequences
        var maxSequence = 1
        for (i in 0 until SEQUENCE_LENGTH) {
            if (n and 1 != searchingFor) {
                if (searchingFor == 1) { // End of 1s + 0s + 1s sequence
                    maxSequence = max(maxSequence, getMaxSequence(sequences))
                }
                searchingFor = n and 1 // Flip 1 to 0 or 0 to 1
                shift(sequences) // Shift sequences
            }
            sequences[0]++
            n = n ushr 1
        }

        /* Check final set of sequences */if (searchingFor == 0) {
            shift(sequences)
        }
        val finalSequence = getMaxSequence(sequences)
        maxSequence = max(finalSequence, maxSequence)
        return maxSequence
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val originalNumber = Int.MAX_VALUE
        val newNumber = longestSequence(originalNumber)
        println(Integer.toBinaryString(originalNumber))
        println(newNumber)
    }
}