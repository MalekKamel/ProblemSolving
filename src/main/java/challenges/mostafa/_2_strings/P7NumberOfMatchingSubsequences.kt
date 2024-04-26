package challenges.mostafa._2_strings

/**
Given a string s and an array of strings words, return the number of words[i]
that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some
characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".

Example 1:

Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".

Example 2:

Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2

Constraints:

1 <= s.length <= 5 * 104
1 <= words.length <= 5000
1 <= words[i].length <= 50
s and words[i] consist of only lowercase English letters.

https://leetcode.com/problems/number-of-matching-subsequences/description/
 */

internal object P7NumberOfMatchingSubsequences {

    private fun numMatchingSubseq(s: String, words: Array<String>): Int {
        val charIndexMap = mutableMapOf<Char, MutableList<Int>>()

        // Store the indices of each character in the string s
        for ((i, char) in s.withIndex()) {
            charIndexMap.computeIfAbsent(char) { mutableListOf() }.add(i)
        }

        return words.count { isSubsequence(it, charIndexMap) }
    }

    // Helper function to check if word is a subsequence of s
    private fun isSubsequence(word: String, charIndexMap: Map<Char, MutableList<Int>>): Boolean {
        var prevIndex = -1

        // Check each character in the word
        for (char in word) {
            val indices = charIndexMap[char] ?: return false
            // Use binary search to find the smallest index greater than prevIndex
            val index = indices.binarySearch(prevIndex + 1)

            prevIndex = if (index < 0) {
                val insertionPoint = -(index + 1)
                if (insertionPoint >= indices.size) return false
                indices[insertionPoint]
            } else indices[index]
        }

        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var s = "abcde"
        var words = arrayOf("a", "bb", "acd", "ace")
        println(numMatchingSubseq(s, words)) // Output: 3

        s = "dsahjpjauf"
        words = arrayOf("ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax")
        println(numMatchingSubseq(s, words)) // Output: 2
    }
}