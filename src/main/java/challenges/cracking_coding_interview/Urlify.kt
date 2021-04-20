package challenges.cracking_coding_interview

import java.lang.StringBuilder

/*
URLify: Write a method to replace all spaces in a string with '%20'.
You may assume that the string has sufficient space at the end to hold the additional characters,
and that you are given the "true" length of the string.
(Note: If implementing in Java, please use a character array so that you can perform this operation in place.)
EXAMPLE
Input: "Mr John Smith ", 13 Output: "Mr%20John%20Smith"
 */
object Urlify {

    private fun solveBruteForce(s: String): String {
        val string = s.trim()
        val chars = string.toCharArray()
        val result = StringBuilder()
        for (ch in chars) {
            if (ch == ' ') {
                result.append("%20")
                continue
            }
            result.append(ch)
        }
        return result.toString()
    }

    private fun solve(str: CharArray, trueLength: Int) {
        val spaceCount = spaceCount(str, trueLength)
        var index = trueLength + spaceCount * 2

        for (i in trueLength - 1 downTo 0) {
            if (str[i] != ' ') {
                str[index - 1] = str[i]
                index--
                continue
            }
            str[index - 1] = '0'
            str[index - 2] = '2'
            str[index - 3] = '%'
            index -= 3
        }
    }

    private fun spaceCount(str: CharArray, trueLength: Int): Int {
        var count = 0
        for (i in 0 until trueLength) {
            if (str[i] == ' ') count++
        }
        return count
    }

    private fun trueLength(arr: CharArray): Int {
        return lastCharIndex(arr) + 1
    }

    private fun lastCharIndex(arr: CharArray): Int {
        for (i in arr.indices.reversed()) {
            if (arr[i] != ' ') return i
        }
        return -1
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val str = "Mr John Smith    "
        val arr = str.toCharArray()
        solve(arr, trueLength(arr))
        println("\"" + arr.joinToString("") + "\"")
    }
}