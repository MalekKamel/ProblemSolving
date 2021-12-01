package challenges.cracking_coding_interview.bit_manipulation.next_number

/**
 * Given a positive integer, print the next smallest and the next largest number that have
 * the same number of 1 bits in their binary representation.
 */
object QuestionC {

    fun getNextArith(n: Int): Int {
        var c = n
        var c0 = 0
        var c1 = 0
        while (c and 1 == 0 && c != 0) {
            c0++
            c = c shr 1
        }
        while (c and 1 == 1) {
            c1++
            c = c shr 1
        }

        /* If c is 0, then n is a sequence of 1s followed by a sequence of 0s. This is already the biggest
		 * number with c1 ones. Return error.
		 */return if (c0 + c1 == 31 || c0 + c1 == 0) {
            -1
        } else n + (1 shl c0) + (1 shl c1 - 1) - 1

        /* Arithmetically:
		 * 2^c0 = 1 << c0
		 * 2^(c1-1) = 1 << (c0 - 1)
		 * next = n + 2^c0 + 2^(c1-1) - 1;
		 */
    }

    fun getPrevArith(n: Int): Int {
        var temp = n
        var c0 = 0
        var c1 = 0
        while (temp and 1 == 1 && temp != 0) {
            c1++
            temp = temp shr 1
        }

        /* If temp is 0, then the number is a sequence of 0s followed by a sequence of 1s. This is already
		 * the smallest number with c1 ones. Return -1 for an error.
		 */if (temp == 0) {
            return -1
        }
        while (temp and 1 == 0 && temp != 0) {
            c0++
            temp = temp shr 1
        }

        /* Arithmetic:
		 * 2^c1 = 1 << c1
		 * 2^(c0 - 1) = 1 << (c0 - 1)
		 */return n - (1 shl c1) - (1 shl c0 - 1) + 1
    }

    fun binPrint(i: Int) {
        println(i.toString() + ": " + Integer.toBinaryString(i))
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val i = 13948
        val p1 = getPrevArith(i)
        val n1 = getNextArith(i)
        Tester.binPrint(p1)
        Tester.binPrint(n1)
    }

}