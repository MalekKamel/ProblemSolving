package challenges.leetcode

import java.util.*

/**
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

Constraints:

1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lower-case English letters.
 */
object LongestCommonPrefix {

    private fun longestCommonPrefix(strs: Array<String>): String {
        val size = strs.size
        var result = ""

        for ((i, str) in strs.withIndex()) {
            val prefix = StringBuilder()

            val current = str.toCharArray()
            if (i + 1 >= size) break

            val next = strs[i + 1].toCharArray()

            if (current.first() != next.first()) continue

            for (j in current.indices) {
                if (j >= next.size) break
                if (current[j] != next[j]) continue
                prefix.append(current[j])
            }

            if (result.isEmpty()) {
                result = prefix.toString()
                continue
            }

            if (prefix.isNotEmpty() && prefix.length < result.length) {
                result = prefix.toString()
            }
        }
        return result
    }

    private fun longestCommonPrefix2(strs: Array<String>): String {
        if (strs.isEmpty()) return ""
        var prefix = strs[0]
        for (i in 1 until strs.size) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length - 1)
                if (prefix.isEmpty()) return ""
            }
        }
        return prefix
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var strs = arrayOf("flower", "flow", "flight")
        println("${strs.joinToString { it }}: ${longestCommonPrefix(strs)}")
        println("${strs.joinToString { it }}: ${longestCommonPrefix2(strs)}")

        strs = arrayOf("dog", "racecar", "car")
        println("${strs.joinToString { it }}: ${longestCommonPrefix(strs)}")
        println("${strs.joinToString { it }}: ${longestCommonPrefix2(strs)}")
    }
}