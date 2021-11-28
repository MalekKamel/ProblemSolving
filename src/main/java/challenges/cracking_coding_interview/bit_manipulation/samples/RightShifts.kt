package challenges.cracking_coding_interview.bit_manipulation.samples

import challenges.util.AssortedMethods.toFullBinaryString


object RightShifts {

    private fun repeatedArithmeticShift(x: Int, count: Int): Int {
        var x = x
        for (i in 0 until count) {
            x = x shr 1 // Arithmetic shift by 1
        }
        return x
    }

    private fun repeatedLogicalShift(x: Int, count: Int): Int {
        var x = x
        for (i in 0 until count) {
            x = x ushr 1 // Logical shift by 1
        }
        return x
    }

    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 8 downTo -8) {
            println(toFullBinaryString(i) + ": " + i)
        }
        val x = -93242
        val resultArithmetic = repeatedArithmeticShift(x, 40)
        val resultLogical = repeatedLogicalShift(x, 40)
        println(toFullBinaryString(resultArithmetic) + ": " + resultArithmetic)
        println(toFullBinaryString(resultLogical) + ": " + resultLogical)
    }
}