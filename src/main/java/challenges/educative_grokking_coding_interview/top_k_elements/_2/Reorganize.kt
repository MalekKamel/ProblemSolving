package challenges.educative_grokking_coding_interview.top_k_elements._2

import java.util.*
import java.util.AbstractMap.SimpleEntry

/**
Given a string, str, rearrange it so that any two adjacent characters are not the same.
If such a reorganization of the characters is possible, output any possible valid arrangement. Otherwise, return an empty string.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/N7wXYOkmVPL
 */
internal object Reorganize {
    private fun reorganizeString(str: String): String {
        val charCounter: MutableMap<Char, Int> = HashMap()
        for (c in str.toCharArray()) {
            val freq = charCounter.getOrDefault(c, 0) + 1
            charCounter[c] = freq
        }
        val maxFreqChar =
            PriorityQueue { (_, value): Map.Entry<Char, Int>, (_, value1): Map.Entry<Char, Int> -> value1 - value }
        maxFreqChar.addAll(charCounter.entries)
        var previous: Map.Entry<Char, Int>? = null
        val result = StringBuilder(str.length)
        while (!maxFreqChar.isEmpty() || previous != null) {
            if (previous != null && maxFreqChar.isEmpty()) return ""
            val (key, value) = maxFreqChar.poll()
            val count = value - 1
            result.append(key)
            if (previous != null) {
                maxFreqChar.add(previous)
                previous = null
            }
            if (count != 0) {
                previous = SimpleEntry(key, count)
            }
        }
        return result.toString()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val inputs = arrayOf(
            "programming",
            "hello",
            "fofjjb",
            "abbacdde",
            "aba",
            "awesome",
            "aaab",
            "aab"
        )
        for (i in inputs.indices) {
            print(i + 1)
            println(".\tInput string: \"" + inputs[i] + "\"")
            var output = reorganizeString(inputs[i])
            output = if (output.isEmpty()) "''" else output
            println("\tReorganized string: \"$output\"")
            println(String(CharArray(100)).replace('\u0000', '-'))
        }
    }
}