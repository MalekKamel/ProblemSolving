package challenges.mostafa._2_strings

/**
Given a string s, return true if the s can be palindrome after deleting at most one
character from it.

Example 1:

Input: s = "aba"
Output: true

Example 2:

Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.

Example 3:

Input: s = "abc"
Output: false

Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.

https://leetcode.com/problems/valid-palindrome-ii/description/
 */

internal object Solution {

    private fun validPalindrome(s: String): Boolean {
        return validPalindromeHelper(s, 0, s.lastIndex, false)
    }

    private fun validPalindromeHelper(
        s: String,
        left: Int,
        right: Int,
        deleted: Boolean
    ): Boolean {
        if (left >= right) return true

        if (s[left] == s[right])
            return validPalindromeHelper(s, left + 1, right - 1, deleted)

        if (deleted) return false

        return validPalindromeHelper(s, left + 1, right, true)
                || validPalindromeHelper(s, left, right - 1, true)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(validPalindrome("aba"))
        println(validPalindrome("abca"))
        println(validPalindrome("abc"))
    }
}

internal object Solution2 {

    /**
     * 1. Problem Explanation
     * We need to determine if a given string can become a palindrome by removing at most one character.
     * A palindrome is a string that reads the same forwards and backward.
     *
     * 2. Pattern Identification and Rationale
     * We can use a two-pointer approach to check for palindromes efficiently.
     * If we encounter a mismatch, we have two options: either remove the left character or
     * the right character.
     * We then check if either of the resulting substrings is a palindrome.
     * This approach avoids generating all possible substrings, leading to a more efficient solution.
     *
     * 3. Solution Breakdown
     * - Initialize two pointers, left and right, pointing to the start and end of the string, respectively.
     * - Iterate while left < right:
     *   - If s[left] == s[right], move both pointers inward.
     *   - If s[left] != s[right], check if removing s[left] or s[right] results in a palindrome.
     *   - Return true if either of the resulting substrings is a palindrome, or if the original
     *   string is a palindrome.
     */
    private fun validPalindrome(s: String): Boolean {
        var left = 0
        var right = s.lastIndex

        while (left < right) {
            if (s[left] != s[right])
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1)
            left++
            right--
        }

        return true
    }


    private fun isPalindrome(s: String, left: Int, right: Int): Boolean {
        var i = left
        var j = right

        while (i < j) {
            if (s[i] != s[j]) return false
            i++
            j--
        }

        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(validPalindrome("aba"))
        println(validPalindrome("abca"))
        println(validPalindrome("abc"))
    }
}