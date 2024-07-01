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