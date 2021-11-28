package challenges.cracking_coding_interview.bit_manipulation.samples

import challenges.util.AssortedMethods.toFullBinaryString


object SampleCode {

    private fun getBit(num: Int, i: Int): Boolean {
        return num and (1 shl i) != 0
    }

    private fun setBit(num: Int, i: Int): Int {
        return num or (1 shl i)
    }

    private fun clearBit(num: Int, i: Int): Int {
        val mask = (1 shl i).inv()
        return num and mask
    }

    private fun updateBit(num: Int, i: Int, bitIs1: Boolean): Int {
        val value = if (bitIs1) 1 else 0
        val mask = (1 shl i).inv()
        return num and mask or (value shl i)
    }

    private fun clearBitsMSBThroughI(num: Int, i: Int): Int {
        val mask = (1 shl i) - 1
        return num and mask
    }

    private fun clearBitsIThrough0(num: Int, i: Int): Int {
        val mask = -1 shl i + 1
        return num and mask
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var number = 59
        println("Testing with number: $number")

        // Get Bit
        println("Get Bit")
        println(toFullBinaryString(number))
        for (i in 31 downTo 0) {
            val res = if (getBit(number, i)) 1 else 0
            print(res)
        }

        // Update Bit
        println("\n\nUpdate Bit")
        var num1 = 1578 // arbitrary number
        for (i in 31 downTo 0) {
            num1 = updateBit(num1, i, getBit(number, i))
        }
        println(num1)

        // Set and Clear Bit
        println("\nSet and Clear Bit")
        var num2 = 1578 // arbitrary number
        for (i in 31 downTo 0) {
            num2 = if (getBit(number, i)) {
                setBit(num2, i)
            } else {
                clearBit(num2, i)
            }
        }
        println(num2)

        // Clear Bits MSB through i
        number = 13242352
        val clearMSBThrough = 4
        println("\nClear bits MSB through $clearMSBThrough")
        println(toFullBinaryString(number))
        val num3 = clearBitsMSBThroughI(number, clearMSBThrough)
        println(toFullBinaryString(num3))

        // Clear Bits i through 0
        val clearToLSB = 2
        println("\nClear bits $clearToLSB through 0")
        number = -1
        println(toFullBinaryString(number))
        val num4 = clearBitsIThrough0(number, clearToLSB)
        println(toFullBinaryString(num4))
    }

}