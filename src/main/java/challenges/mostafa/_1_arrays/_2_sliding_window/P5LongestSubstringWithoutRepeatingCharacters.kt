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
        var s = "abcabcbb"

        println("First")
        println(lengthOfLongestSubstring(s))

        println("Second")
        s = "bbbbb"
        println(lengthOfLongestSubstring(s))

        println("Third")
        s = "pwwkew"
        println(lengthOfLongestSubstring(s))
    }
}