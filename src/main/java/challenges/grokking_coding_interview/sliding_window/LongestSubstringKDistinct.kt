package challenges.grokking_coding_interview.sliding_window

/**
 * Given a string, find the length of the longest substring in it with no more than K distinct characters.
 *
 * https://www.educative.io/courses/grokking-the-coding-interview/YQQwQMWLx80
 */
internal object LongestSubstringKDistinct {
    private fun findLength(str: String?, k: Int): Int {
        require(!str.isNullOrEmpty())
        var windowStart = 0
        var maxLength = 0
        val charFrequencyMap: MutableMap<Char, Int> = HashMap()
        // in the following loop we'll try to extend the range [windowStart, windowEnd]
        for (windowEnd in str.indices) {
            val rightChar = str[windowEnd]
            charFrequencyMap[rightChar] = charFrequencyMap.getOrDefault(rightChar, 0) + 1
            // shrink the sliding window, until we are left with 'k' distinct characters in the frequency map
            while (charFrequencyMap.size > k) {
                val leftChar = str[windowStart]
                charFrequencyMap[leftChar] = charFrequencyMap[leftChar]!! - 1
                if (charFrequencyMap[leftChar] == 0) {
                    charFrequencyMap.remove(leftChar)
                }
                windowStart++ // shrink the window
            }
            maxLength = maxLength.coerceAtLeast(windowEnd - windowStart + 1) // remember the maximum length so far
            // Equlas
//            maxLength = Math.max(maxLength, windowEnd - windowStart + 1) // remember the maximum length so far
        }
        return maxLength
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Length of the longest substring: " + findLength("araaci", 2))
        println("Length of the longest substring: " + findLength("araaci", 1))
        println("Length of the longest substring: " + findLength("cbbebi", 3))
    }
}