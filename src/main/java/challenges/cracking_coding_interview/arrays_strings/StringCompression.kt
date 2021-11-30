package challenges.cracking_coding_interview.arrays_strings

/*
Implement a method to perform basic string compression using
the counts of repeated characters. For example, the string aabcccccaaa would become a2blc5a3.
If the "compressed" string would not become smaller than the original string, your method should return
the original string. You can assume the string has only uppercase and lowercase letters (a - z).
 */

object StringCompression {
    private fun compress(str: String): String {
        val compressed = StringBuilder()
        var countConsecutive = 0
        for (i in str.indices) {
            countConsecutive++

            /* If next character is different than current, append this char to result.*/
            if (i + 1 >= str.length || str[i] != str[i + 1]) {
                compressed.append(str[i])
                compressed.append(countConsecutive)
                countConsecutive = 0
            }
        }
        return if (compressed.length < str.length) compressed.toString() else str
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val str = "aabcccccaaa"
        println(str) // a2b1c5a3
        println(compress(str))
    }
}

/**
 * Instead,we can check in advance.This will be more optimal in cases where we don't have a large number of
 * repeating characters.It will avoid us having to create a string that we never use.
 * The downside of this is that it causes a second loop through the characters and also adds nearly duplicated code.
 */
object StringCompression2 {
    private fun compress(str: String): String {
        val finalLength = countCompression(str)

        val compressed = StringBuffer(finalLength) // initialize capacity
        var countConsecutive = 0
        for (i in str.indices) {
            countConsecutive++

            /* If next character is different than current, append this char to result.*/
            if (i + 1 >= str.length || str[i] != str[i + 1]) {
                compressed.append(str[i])
                compressed.append(countConsecutive)
                countConsecutive = 0
            }
        }
        return compressed.toString()
    }

    private fun countCompression(str: String): Int {
        var compressedLength = 0
        var countConsecutive = 0
        for (i in str.indices) {
            countConsecutive++

            /* If next character is different than current, append this char to result.*/
            if (i + 1 >= str.length || str[i] != str[i + 1]) {
                compressedLength += 1 + countConsecutive.toString().length
                countConsecutive = 0
            }
        }
        return compressedLength
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var str = "aabcccccaaa"
        println(str) // a2b1c5a3
        println(compress(str))
        str = "abbaaaac"
        println(str) // a1b2a4c1
        println(compress(str))
    }
}
