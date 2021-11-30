package challenges.cracking_coding_interview.bit_manipulation.flip_bit_to_win

object QuestionD {

    fun flipBit(a: Int): Int {
        /* If all 1s, this is already the longest sequence. */
        var a = a
        if (a.inv() == 0) return Integer.BYTES * 8
        var currentLength = 0
        var previousLength = 0
        var maxLength = 1 // We can always have a sequence of at least one 1
        while (a != 0) {
            if (a and 1 == 1) {
                currentLength++
            } else if (a and 1 == 0) {
                /* Update to 0 (if next bit is 0) or currentLength (if next bit is 1). */
                previousLength = if (a and 2 == 0) 0 else currentLength
                currentLength = 0
            }
            maxLength = Math.max(previousLength + currentLength + 1, maxLength)
            a = a ushr 1
        }
        return maxLength
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val cases = arrayOf(
            intArrayOf(-1, 32),
            intArrayOf(Int.MAX_VALUE, 32),
            intArrayOf(-10, 31),
            intArrayOf(0, 1),
            intArrayOf(1, 2),
            intArrayOf(15, 5),
            intArrayOf(1775, 8)
        )
        for (c in cases) {
            val x = flipBit(c[0])
            val r = c[1] == x
            println(c[0].toString() + ": " + x + ", " + c[1] + " " + r)
        }
    }

}