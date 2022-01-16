package challenges.cracking_coding_interview.sorting_and_searching.missing_int

import java.io.FileNotFoundException
import java.io.FileReader
import java.io.PrintWriter
import java.util.*
import kotlin.experimental.and
import kotlin.experimental.or

object QuestionB {
    @Throws(FileNotFoundException::class)
    fun findOpenNumber(filename: String?): Int {
        val rangeSize = 1 shl 20 // 2^20 bits (2^17 bytes)

        /* Get count of number of values within each block. */
        val blocks = getCountPerBlock(filename, rangeSize)

        /* Find a block with a missing value. */
        val blockIndex = findBlockWithMissing(blocks, rangeSize)
        if (blockIndex < 0) return -1

        /* Create bit vector for items within this range. */
        val bitVector = getBitVectorForRange(filename, blockIndex, rangeSize)

        /* Find a zero in the bit vector */
        val offset = findZero(bitVector)
        return if (offset < 0) -1 else blockIndex * rangeSize + offset

        /* Compute missing value. */
    }

    /* Get count of items within each range. */
    @Throws(FileNotFoundException::class)
    fun getCountPerBlock(filename: String?, rangeSize: Int): IntArray {
        val arraySize = Int.MAX_VALUE / rangeSize + 1
        val blocks = IntArray(arraySize)
        val `in` = Scanner(FileReader(filename))
        while (`in`.hasNextInt()) {
            val value = `in`.nextInt()
            blocks[value / rangeSize]++
        }
        `in`.close()
        return blocks
    }

    /* Find a block whose count is low. */
    private fun findBlockWithMissing(blocks: IntArray, rangeSize: Int): Int {
        for (i in blocks.indices) {
            if (blocks[i] < rangeSize) {
                return i
            }
        }
        return -1
    }

    /* Create a bit vector for the values within a specific range. */
    @Throws(FileNotFoundException::class)
    fun getBitVectorForRange(filename: String?, blockIndex: Int, rangeSize: Int): ByteArray {
        val startRange = blockIndex * rangeSize
        val endRange = startRange + rangeSize
        val bitVector = ByteArray(rangeSize / java.lang.Byte.SIZE)
        val `in` = Scanner(FileReader(filename))
        while (`in`.hasNextInt()) {
            val value = `in`.nextInt()
            /* If the number is inside the block that's missing 
			 * numbers, we record it */if (startRange <= value && value < endRange) {
                val offset = value - startRange
                val mask = 1 shl offset % java.lang.Byte.SIZE
                bitVector[offset / java.lang.Byte.SIZE] = bitVector[offset / java.lang.Byte.SIZE] or mask.toByte()
            }
        }
        `in`.close()
        return bitVector
    }

    /* Find bit index that is 0 within byte. */
    private fun findZero(b: Byte): Int {
        for (i in 0 until java.lang.Byte.SIZE) {
            val mask = 1 shl i
            if (b and mask.toByte() == 0.toByte()) {
                return i
            }
        }
        return -1
    }

    /* Find a zero within the bit vector and return the index. */
    private fun findZero(bitVector: ByteArray): Int {
        for (i in bitVector.indices) {
            if (bitVector[i].toInt() != 0.inv()) { // If not all 1s
                val bitIndex = findZero(
                    bitVector[i]
                )
                return i * java.lang.Byte.SIZE + bitIndex
            }
        }
        return -1
    }

    @Throws(FileNotFoundException::class)
    fun generateFile(filename: String?, max: Int, missing: Int) {
        val writer = PrintWriter(filename)
        var i = 0
        while (i < max && i >= 0) {
            if (i != missing) {
                writer.println(i)
            }
            if (i % 10000 == 0) {
                println("Now at location: $i")
            }
            i++
        }
        writer.flush()
        writer.close()
    }

    @Throws(FileNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val filename = "Ch 10. Scalability and Memory Limits/Q10_04_Missing_Int/input.txt"
        val max = 10000000
        val missing = 1234325
        println("Generating file...")
        generateFile(filename, max, missing)
        println("Generated file from 0 to $max with $missing missing.")
        println("Searching for missing number...")
        println("Missing value: " + findOpenNumber(filename))
    }
}