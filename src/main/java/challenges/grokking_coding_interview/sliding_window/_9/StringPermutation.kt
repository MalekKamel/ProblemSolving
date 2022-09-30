package challenges.grokking_coding_interview.sliding_window._9

/**
 * Given a string and a pattern, find out if the string contains any permutation of the pattern.
 * Permutation is defined as the re-arranging of the characters of the string. For example, “abc” has the following six permutations:
 * abc
 * acb
 * bac
 * bca
 * cab
 * cba
 * If a string has ‘n’ distinct characters, it will have n! permutations.
 *
 * https://www.educative.io/courses/grokking-the-coding-interview/N0o9QnPLKNv
 */
object StringPermutation {
    private fun findPermutation(str: String, pattern: String): Boolean {
        var windowStart = 0
        var matched = 0
        val charFrequencyMap: MutableMap<Char, Int> = HashMap()
        for (chr in pattern.toCharArray()) charFrequencyMap[chr] = charFrequencyMap.getOrDefault(chr, 0) + 1

        // our goal is to match all the characters from the 'charFrequencyMap' with the current window
        // try to extend the range [windowStart, windowEnd]
        for (windowEnd in str.indices) {
            val rightChar = str[windowEnd]
            if (charFrequencyMap.containsKey(rightChar)) {
                // decrement the frequency of the matched character
                charFrequencyMap[rightChar] = charFrequencyMap[rightChar]!! - 1
                if (charFrequencyMap[rightChar] == 0) // character is completely matched
                        matched++
            }
            if (matched == charFrequencyMap.size) return true
            if (windowEnd >= pattern.length - 1) { // shrink the window by one character
                val leftChar = str[windowStart++]
                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap[leftChar] == 0) matched-- // before putting the character back, decrement the matched count
                    // put the character back for matching
                    charFrequencyMap[leftChar] = charFrequencyMap[leftChar]!! + 1
                }
            }
        }
        return false
    }

    /**
     *  This is a brute force solution by me.
     * The problem in this solution is that we have to loop over the pattern chars
     * every time in the sliding window.
     */
    private fun findPermutation2(str: String, pattern: String): Boolean {
        val patternLength = pattern.length
        var windowStart = 0
        val charFrequencyMap: MutableMap<Char, Int> = HashMap()

        for (windowEnd in str.toCharArray().indices) {
            val rightChar = str[windowEnd]
            charFrequencyMap[rightChar] = charFrequencyMap.getOrDefault(rightChar, 0) + 1
            if (windowEnd - windowStart + 1 == patternLength) {
                var isPermutation = true
                for (char in pattern) {
                    if (!charFrequencyMap.containsKey(char)) {
                        isPermutation = false
                        break
                    }
                }
                if (isPermutation) return true
                val leftChar = str[windowStart]
                charFrequencyMap[leftChar] = charFrequencyMap[leftChar]!! - 1
                if (charFrequencyMap[leftChar]!! == 0) charFrequencyMap.remove(leftChar)
                windowStart++
            }
        }
        return false
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(findPermutation("oidbcaf", "abc"))
        println(findPermutation("odicf", "dc"))
        println(findPermutation("bcdxabcdy", "bcdxabcdy"))
        println(findPermutation("aaacb", "abc"))
        println("=============")
        println(findPermutation2("oidbcaf", "abc"))
        println(findPermutation2("odicf", "dc"))
        println(findPermutation2("bcdxabcdy", "bcdxabcdy"))
        println(findPermutation2("aaacb", "abc"))
    }
}
