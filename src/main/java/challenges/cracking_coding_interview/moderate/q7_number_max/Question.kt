package challenges.cracking_coding_interview.moderate.q7_number_max

object Question {
    /* Flips a 1 to a 0 and a 0 to a 1 */
    fun flip(bit: Int): Int {
        return 1 xor bit
    }

    /* Returns 1 if a is positive, and 0 if a is negative */
    private fun sign(a: Int): Int {
        return flip(a shr 31 and 0x1)
    }

    private fun getMaxNaive(a: Int, b: Int): Int {
        val k = sign(a - b)
        val q = flip(k)
        return a * k + b * q
    }

    private fun getMax(a: Int, b: Int): Int {
        val c = a - b
        val sa = sign(a) // if a >= 0, then 1 else 0
        val sb = sign(b) // if b >= 0, then 1 else 0
        val sc = sign(c) // depends on whether or not a - b overflows

        /* We want to define a value k which is 1 if a > b and 0 if a < b. 
		 * (if a = b, it doesn't matter what value k is) */
        val use_sign_of_a = sa xor sb // If a and b have different signs, then k = sign(a)
        val use_sign_of_c = flip(sa xor sb) // If a and b have the same sign, then k = sign(a - b)

        /* We can't use a comparison operator, but we can multiply values by 1 or 0 */
        val k = use_sign_of_a * sa + use_sign_of_c * sc
        val q = flip(k) // opposite of k
        return a * k + b * q
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var a = 26
        var b = -15
        println("max_naive(" + a + ", " + b + ") = " + getMaxNaive(a, b))
        println("max(" + a + ", " + b + ") = " + getMax(a, b))
        a = -15
        b = 2147483647
        println("max_naive(" + a + ", " + b + ") = " + getMaxNaive(a, b))
        println("max(" + a + ", " + b + ") = " + getMax(a, b))
    }
}