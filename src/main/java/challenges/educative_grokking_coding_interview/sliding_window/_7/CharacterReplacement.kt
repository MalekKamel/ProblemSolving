package challenges.educative_grokking_coding_interview.sliding_window._7

/**
 * Given a string with lowercase letters only, if you are allowed to replace no more than k letters with
 * any letter, find the length of the longest substring having the same letters after replacement.
 *
 * https://www.educative.io/courses/grokking-the-coding-interview/R8DVgjq78yR
 */
object CharacterReplacement {
    private fun findLength(str: String, k: Int): Int {
        var windowStart = 0
        var maxLength = 0
        var maxRepeatLetterCount = 0
        val letterFrequencyMap: MutableMap<Char, Int> = HashMap()
        // try to extend the range [windowStart, windowEnd]
        for (windowEnd in str.indices) {
            val rightChar = str[windowEnd]
            letterFrequencyMap[rightChar] = letterFrequencyMap.getOrDefault(rightChar, 0) + 1

            // we don't need to place the maxRepeatLetterCount under the below 'if', see the
            // explanation in the 'Solution' section above.
            maxRepeatLetterCount = Math.max(maxRepeatLetterCount, letterFrequencyMap[rightChar]!!)

            // current window size is from windowStart to windowEnd, overall we have a letter which is
            // repeating 'maxRepeatLetterCount' times, this means we can have a window which has one letter
            // repeating 'maxRepeatLetterCount' times and the remaining letters we should replace.
            // if the remaining letters are more than 'k', it is the time to shrink the window as we
            // are not allowed to replace more than 'k' letters
            val length = windowEnd - windowStart + 1
            val remaining = length - maxRepeatLetterCount
            if (remaining > k) {
                val leftChar = str[windowStart]
                letterFrequencyMap[leftChar] = letterFrequencyMap[leftChar]!! - 1
                windowStart++
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1)
        }
        return maxLength
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(findLength("aabccbb", 2))
        println(findLength("abbcb", 1))
        println(findLength("abccde", 1))
    }
}