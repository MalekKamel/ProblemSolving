package challenges.cracking_coding_interview.moderate.q8_english_int

import challenges.util.AssortedMethods.randomIntInRange
import java.util.*

object Question {
    var smalls = arrayOf(
        "Zero",
        "One",
        "Two",
        "Three",
        "Four",
        "Five",
        "Six",
        "Seven",
        "Eight",
        "Nine",
        "Ten",
        "Eleven",
        "Twelve",
        "Thirteen",
        "Fourteen",
        "Fifteen",
        "Sixteen",
        "Seventeen",
        "Eighteen",
        "Nineteen"
    )
    var tens = arrayOf("", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")
    var bigs = arrayOf("", "Thousand", "Million", "Billion")
    var hundred = "Hundred"
    var negative = "Negative"
    private fun convert(num: Int): String {
        var num = num
        if (num == 0) {
            return smalls[0]
        } else if (num < 0) {
            return negative + " " + convert(-1 * num)
        }
        val parts = LinkedList<String>()
        var chunkCount = 0
        while (num > 0) {
            if (num % 1000 != 0) {
                val chunk = convertChunk(num % 1000) + " " + bigs[chunkCount]
                parts.addFirst(chunk)
            }
            num /= 1000 // shift chunk
            chunkCount++
        }
        return listToString(parts)
    }

    /* Convert a linked list of strings to a string, dividing it up with spaces. */
    private fun listToString(parts: LinkedList<String>): String {
        val sb = StringBuilder()
        while (parts.size > 1) {
            sb.append(parts.pop())
            sb.append(" ")
        }
        sb.append(parts.pop())
        return sb.toString()
    }

    private fun convertChunk(_number: Int): String {
        var number = _number
        val parts = LinkedList<String>()

        /* Convert hundreds place */
        if (number >= 100) {
            parts.addLast(smalls[number / 100])
            parts.addLast(hundred)
            number %= 100
        }

        /* Convert tens place */
        if (number in 10..19) {
            parts.addLast(smalls[number])
        } else if (number >= 20) {
            parts.addLast(tens[number / 10])
            number %= 10
        }

        /* Convert ones place */
        if (number in 1..9) {
            parts.addLast(smalls[number])
        }
        return listToString(parts)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        /* numbers between 100000 and 1000000 */
        for (i in 0..7) {
            val value = (-1 * Math.pow(10.0, i.toDouble())).toInt()
            val s = convert(value)
            println("$value: $s")
        }

        /* numbers between 0 and 100 */
        for (i in 0..9) {
            val value = randomIntInRange(0, 100)
            val s = convert(value)
            println("$value: $s")
        }

        /* numbers between 100 and 1000 */
        for (i in 0..9) {
            val value = randomIntInRange(100, 1000)
            val s = convert(value)
            println("$value: $s")
        }

        /* numbers between 1000 and 100000 */
        for (i in 0..9) {
            val value = randomIntInRange(1000, 100000)
            val s = convert(value)
            println("$value: $s")
        }


        /* numbers between 100000 and 100000000 */
        for (i in 0..9) {
            val value = randomIntInRange(100000, 100000000)
            val s = convert(value)
            println("$value: $s")
        }

        /* numbers between 100000000 and 1000000000 */
        for (i in 0..9) {
            val value = randomIntInRange(100000000, 1000000000)
            val s = convert(value)
            println("$value: $s")
        }

        /* numbers between 1000000000 and Integer.MAX_VALUE */
        for (i in 0..9) {
            val value = randomIntInRange(1000000000, Int.MAX_VALUE)
            val s = convert(value)
            println("$value: $s")
        }
    }
}