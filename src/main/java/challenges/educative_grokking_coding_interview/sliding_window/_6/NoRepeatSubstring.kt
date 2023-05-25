package challenges.educative_grokking_coding_interview.sliding_window._6

/**
 * Given a string, find the length of the longest substring, which has all distinct characters.
 *
 * https://www.educative.io/courses/grokking-the-coding-interview/YMzBx1gE5EO
 */
object NoRepeatSubstring {

    private fun findLength(str: String): Int {
        var windowStart = 0
        var maxLength = 0
        val charIndexMap: MutableMap<Char, Int> = HashMap()
        // try to extend the range [windowStart, windowEnd]
        for (windowEnd in str.indices) {
            val rightChar = str[windowEnd]
            // if the map already contains the 'rightChar', shrink the window from the beginning so that
            // we have only one occurrence of 'rightChar'
            if (charIndexMap.containsKey(rightChar)) {
                // this is tricky; in the current window, we will not have any 'rightChar' after its previous index
                // and if 'windowStart' is already ahead of the last index of 'rightChar', we'll keep 'windowStart'
                windowStart = Math.max(windowStart, charIndexMap[rightChar]!! + 1)
            }
            charIndexMap[rightChar] = windowEnd // insert the 'rightChar' into the map
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1) // remember the maximum length so far
        }
        return maxLength
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Length of the longest substring: " + findLength("aabccbb"))
        println("Length of the longest substring: " + findLength("abbbb"))
        println("Length of the longest substring: " + findLength("abccde"))
    }

}