package challenges.cracking_coding_interview.bit_manipulation

/**
 * Given a real number between O and 1 (e.g., 0.72) that is passed in as a double, print the binary representation.
 * If the number cannot be represented accurately in binary with at most 32 characters, print "ERROR:'
 */
object BinaryToString {

    private fun printBinary(_num: Double): String {
        var num = _num
        if (num >= 1 || num <= 0) {
            return "ERROR"
        }
        val binary = StringBuilder()
        binary.append(".")
        while (num > 0) {
            // Setting a limit on length: 32 characters
            if (binary.length > 32) return "ERROR"

            val r = num * 2
            num = if (r >= 1) {
                binary.append(1)
                r - 1
            } else {
                binary.append(0)
                r
            }
        }
        return binary.toString()
    }

    private fun printBinary2(_num: Double): String {
        var num = _num
        if (num >= 1 || num <= 0) {
            return "ERROR"
        }
        val binary = StringBuilder()
        var frac = 0.5
        binary.append(".")
        while (num > 0) {
            // Setting a limit on length: 32 characters
            if (binary.length >= 32) return "ERROR"

            if (num >= frac) {
                binary.append(1)
                num -= frac
            } else {
                binary.append(0)
            }
            frac /= 2.0
        }
        return binary.toString()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val bs = printBinary(.125)
        println(bs)
        for (i in 0..999) {
            val num = i / 1000.0
            val binary = printBinary(num)
            val binary2 = printBinary2(num)
            if (binary != "ERROR" || binary2 != "ERROR") {
                println("$num : $binary $binary2")
            }
        }
    }
}