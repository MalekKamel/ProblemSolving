package challenges.cracking_coding_interview.bit_manipulation.flip_bit_to_win

import kotlin.math.max

/**
 *  You have an integer, and you can flip exactly one bit from a 0 to a 1.
 *  Write code to find the length of the longest sequence of ls you could create.
 * EXAMPLE
 * Input: 1775 (or: 11011101111) Output: 8
 */
object QuestionB {
    fun longestSequence(n: Int): Int {
        if (n == -1) return Integer.BYTES * 8
        val sequences = getAlternatingSequences(n)
        return findLongestSequence(sequences)
    }

    /* Return a list of the sizes of the sequences. The sequence starts
	 * off with the number of 0s (which might be 0) and then alternates
	 * with the counts of each value.*/
    private fun getAlternatingSequences(n: Int): ArrayList<Int> {
        var n = n
        val sequences = ArrayList<Int>()
        var searchingFor = 0
        var counter = 0
        for (i in 0 until Integer.BYTES * 8) {
            if (n and 1 != searchingFor) {
                sequences.add(counter)
                searchingFor = n and 1 // Flip 1 to 0 or 0 to 1
                counter = 0
            }
            counter++
            n = n ushr 1
        }
        sequences.add(counter)
        return sequences
    }

    private fun findLongestSequence(seq: ArrayList<Int>): Int {
        var maxSeq = 1
        var i = 0
        while (i < seq.size) {
            val zerosSeq = seq[i]
            val onesSeqPrev = if (i - 1 >= 0) seq[i - 1] else 0
            val onesSeqNext = if (i + 1 < seq.size) seq[i + 1] else 0
            var thisSeq = 0
            if (zerosSeq == 1) { // Can merge
                thisSeq = onesSeqNext + 1 + onesSeqPrev
            } else if (zerosSeq > 1) { // Just add a one to either side
                thisSeq = 1 + max(onesSeqPrev, onesSeqNext)
            } else if (zerosSeq == 0) { // No zero, but take either side
                thisSeq = max(onesSeqPrev, onesSeqNext)
            }
            maxSeq = max(thisSeq, maxSeq)
            i += 2
        }
        return maxSeq
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val originalNumber = 1775
        val newNumber = longestSequence(originalNumber)
        println(Integer.toBinaryString(originalNumber))
        println(newNumber)
    }
}