package challenges.educative_grokking_coding_interview.sliding_window._10

/**
 * Given a string and a pattern, find all anagrams of the pattern in the given string.
 * Every anagram is a permutation of a string. As we know, when we are not allowed to repeat characters
 * while finding permutations of a string, we get N! permutations (or anagrams) of a string having N
 * characters. For example, here are the six anagrams of the string “abc”:
 * abc
 * acb
 * bac
 * bca
 * cab
 * cba
 *
 * Write a function to return a list of starting indices of the anagrams of the pattern in the given string.
 *
 * https://www.educative.io/courses/grokking-the-coding-interview/xl2g3vxrMq3
 */
object StringAnagrams {

    private fun findStringAnagrams(str: String, pattern: String): List<Int> {
        val resultIndices: MutableList<Int> = ArrayList()
        var windowStart = 0

        val charFrequencyMap: MutableMap<Char, Int> = HashMap()
        val patternLength = pattern.length
        var matched = 0

        for (chr in pattern.toCharArray())
            charFrequencyMap[chr] = charFrequencyMap.getOrDefault(chr, 0) + 1

        for (windowEnd in str.indices) {
            val rightChar = str[windowEnd]

            if (charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap[rightChar] = charFrequencyMap[rightChar]!! - 1
                if (charFrequencyMap[rightChar] == 0) {
                    matched++
                }
            }
            if (matched == charFrequencyMap.size) // have we found an anagram?
                resultIndices.add(windowStart)

            if (windowEnd >= patternLength - 1) {
                val leftChar = str[windowStart]
                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap[leftChar] == 0) {
                        matched--
                    }
                    charFrequencyMap[leftChar] = charFrequencyMap[leftChar]!! + 1
                }
                windowStart++
            }

        }

        return resultIndices
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(findStringAnagrams("ppqp", "pq"))
        println(findStringAnagrams("abbcabc", "abc"))
    }
}