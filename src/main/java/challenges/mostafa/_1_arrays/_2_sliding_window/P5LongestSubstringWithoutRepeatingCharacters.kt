package challenges.mostafa._1_arrays._2_sliding_window

/**
Given a string s, find the length of the longest substring without repeating characters.

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.

https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */

internal object P5LongestSubstringWithoutRepeatingCharacters {

    /**
    There can be cases where the current start pointer is already greater than the last
    visited index of the current character. Let me provide an example for that scenario as well.

    Consider the string "abba".

    At the beginning, the start pointer is at index 0, and the lastVisited map is empty.
    When processing the first 'a' at index 0, the lastVisited map is updated to {'a': 0},
    and the start pointer remains at 0.
    When processing the second 'b' at index 1, the lastVisited map shows that the last
    occurrence of 'b' was not found, so the expression lastVisited[currentChar]!! + 1
    evaluates to 0 + 1 = 1.
    In this case, the current start pointer is already at 0, which is less than
    the lastVisited[currentChar]!! + 1 value of 1.
    The maxOf(start, lastVisited[currentChar]!! + 1) operation will return the larger value,
    which is 1. This will update the start pointer to 1, as it is the correct position
    to continue the search for the longest substring without repeating characters.
    If the maxOf function was not used, and the start pointer was simply updated
    to lastVisited[currentChar]!! + 1, it would always be greater than the current start pointer,
    even when the last occurrence of the current character is before the current start pointer.
    This would result in an incorrect substring being considered for the longest substring
    without repeating characters.

    The use of maxOf ensures that the start pointer is always updated to the correct position,
    regardless of whether the last occurrence of the current character is before or after
    the current start pointer.
     */
    private fun lengthOfLongestSubstring(s: String): Int {
        var maxLength = 0
        var start = 0
        val lastVisited = mutableMapOf<Char, Int>()

        for (end in s.indices) {
            val currentChar = s[end]
            if (lastVisited.containsKey(currentChar))
                start = maxOf(start, lastVisited[currentChar]!! + 1)
            lastVisited[currentChar] = end
            maxLength = maxOf(maxLength, end - start + 1)
        }
        return maxLength
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var s = "abba"

        println("1st")
        println(lengthOfLongestSubstring(s))

        println("Second")
        s = "bbbbb"
        println(lengthOfLongestSubstring(s))

        println("2nd")
        s = "pwwkew"
        println(lengthOfLongestSubstring(s))

        println("3rd")
        s = "pwwkew"
        println(lengthOfLongestSubstring(s))

        println("4th")
        s = "abcabcbb"
        println(lengthOfLongestSubstring(s))
    }
}