package challenges.cracking_coding_interview.bit_manipulation.next_number

/**
 * Given a positive integer, print the next smallest and the next largest number that have
 * the same number of 1 bits in their binary representation.
 */
object QuestionA {

    private fun countOnes(i: Int): Int {
        var i = i
        var count = 0
        while (i > 0) {
            if (i and 1 == 1) {
                count++
            }
            i = i shr 1
        }
        return count
    }

    fun countZeros(i: Int): Int {
        return 32 - countOnes(i)
    }

    private fun hasValidNext(i: Int): Boolean {
        var i = i
        if (i == 0) {
            return false
        }
        var count = 0
        while (i and 1 == 0) {
            i = i shr 1
            count++
        }
        while (i and 1 == 1) {
            i = i shr 1
            count++
        }
        return count != 31
    }

    private fun hasValidPrev(i: Int): Boolean {
        var i = i
        while (i and 1 == 1) {
            i = i shr 1
        }
        return i != 0
    }

    fun getNextSlow(i: Int): Int {
        var i = i
        if (!hasValidNext(i)) {
            return -1
        }
        val numOnes = countOnes(i)
        i++
        while (countOnes(i) != numOnes) {
            i++
        }
        return i
    }

    fun getPrevSlow(i: Int): Int {
        var i = i
        if (!hasValidPrev(i)) {
            return -1
        }
        val numOnes = countOnes(i)
        i--
        while (countOnes(i) != numOnes) {
            i--
        }
        return i
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val i = 13948
        val p1 = getPrevSlow(i)
        val n1 = getNextSlow(i)
        Tester.binPrint(p1)
        Tester.binPrint(n1)
    }
}