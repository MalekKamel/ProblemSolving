package challenges.cracking_coding_interview.bit_manipulation.draw_line

import kotlin.experimental.and
import kotlin.experimental.or


/**
 *  monochrome screen is stored as a single array of bytes, allowing eight consecutive pixels
 *  to be stored in one byte. The screen has width w, where w is divisible by 8 (that is, no byte will be split across rows).
 *  The height of the screen, of course, can be derived from the length of the array and the width.
 *  Implement a function that draws a horizontal line from (xl, y) to ( x2, y).
 * The method signature should look something like:
 * drawLine(byte[] screen, int width, int xl, int x2, int y)
 */
object Question {

    private fun computeByteNum(width: Int, x: Int, y: Int): Int {
        return (width * y + x) / 8
    }

    private fun drawLine(screen: ByteArray, width: Int, x1: Int, x2: Int, y: Int) {
        val startOffset = x1 % 8
        var firstFullByte = x1 / 8
        if (startOffset != 0) {
            firstFullByte++
        }
        val endOffset = x2 % 8
        var lastFullByte = x2 / 8
        if (endOffset != 7) {
            lastFullByte--
        }

        // Set full bytes
        for (b in firstFullByte..lastFullByte) {
            screen[width / 8 * y + b] = 0xFF.toByte()
        }
        val start_mask = (0xFF shr startOffset).toByte()
        val end_mask = (0xFF shr endOffset + 1).inv().toByte()

        // Set start and end of line
        if (x1 / 8 == x2 / 8) { // If x1 and x2 are in the same byte
            val mask = (start_mask and end_mask) as Byte
            screen[width / 8 * y + x1 / 8] = screen[width / 8 * y + x1 / 8] or mask
        } else {
            if (startOffset != 0) {
                val byteNumber = width / 8 * y + firstFullByte - 1
                screen[byteNumber] = screen[byteNumber] or start_mask
            }
            if (endOffset != 7) {
                val byteNumber = width / 8 * y + lastFullByte + 1
                screen[byteNumber] = screen[byteNumber] or end_mask
            }
        }
    }

    private fun printByte(b: Byte) {
        for (i in 7 downTo 0) {
            val c = if (b shr i and 1 == 1) '1' else '_'
            print(c)
        }
    }

    private fun printScreen(screen: ByteArray, width: Int) {
        val height = screen.size * 8 / width
        for (r in 0 until height) {
            var c = 0
            while (c < width) {
                val b = screen[computeByteNum(width, c, r)]
                printByte(b)
                c += 8
            }
            println("")
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val width = 8 * 1
        val height = 1
        for (r in 0 until height) {
            for (c1 in 0 until width) {
                for (c2 in c1 until width) {
                    val screen = ByteArray(width * height / 8)
                    println("row: $r: $c1 -> $c2")
                    drawLine(screen, width, c1, c2, r)
                    printScreen(screen, width)
                    println("\n\n")
                }
            }
        }
    }
}

private infix fun Byte.shr(other: Int): Int {
    return (this.toInt() shr other)
}
