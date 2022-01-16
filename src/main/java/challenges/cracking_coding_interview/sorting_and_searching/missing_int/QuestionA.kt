package challenges.cracking_coding_interview.sorting_and_searching.missing_int

import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.util.*
import kotlin.experimental.and
import kotlin.experimental.or

object QuestionA {
    var numberOfInts = Int.MAX_VALUE.toLong() + 1
    var bitfield = ByteArray((numberOfInts / 8).toInt())

    @Throws(FileNotFoundException::class)
    fun findOpenNumber() {
        val root = System.getProperty("user.dir")
        val path = "$root/src/main/java/challenges/cracking_coding_interview/sorting_and_searching/missing_int/input.txt"
        val `in` = Scanner(FileReader(path))

        while (`in`.hasNextInt()) {
            val n = `in`.nextInt()
            /* Finds the corresponding number in the bitfield by using
			 * the OR operator to set the nth bit of a byte 
			 * (e.g., 10 would correspond to bit 2 of index 1 in
			 * the byte array). */bitfield[n / 8] = bitfield[n / 8] or ((1 shl n % 8).toByte())
        }
        for (i in bitfield.indices) {
            for (j in 0..7) {
                /* Retrieves the individual bits of each byte. When 0 bit
				 * is found, finds the corresponding value. */
                if (bitfield[i] and (1 shl j).toByte() == 0.toByte()) {
                    println(i * 8 + j)
                    return
                }
            }
        }
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        findOpenNumber()
    }
}