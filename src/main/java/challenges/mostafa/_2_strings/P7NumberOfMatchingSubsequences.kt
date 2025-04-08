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

    /**
     *  1. Problem Explanation
     *  We need to determine how many words in the given array 'words' are subsequences of the string 's'.
     *  A subsequence is a sequence that can be derived from another sequence by deleting some
     *  or no elements without changing the order of the remaining elements.
     *
     *  2. Pattern Identification and Rationale
     *  The pattern here is a straightforward string comparison with subsequence checking.
     *  We can iterate through each word in the 'words' array and check if it's a subsequence of 's'.
     *  We can efficiently perform subsequence checking by using two pointers, one for 's' and one
     *  for the current word.
     *  This approach avoids unnecessary string manipulations and provides a relatively efficient
     *  solution.
     *
     *  3. Solution Breakdown
     *  - Initialize a counter to track the number of matching subsequences.
     *  - Iterate through each word in the 'words' array.
     *  - For each word, use two pointers to check if it's a subsequence of 's'.
     *  - If the word is a subsequence, increment the counter.
     *  - Return the final counter value.
     */
    private fun numMatchingSubseq(s: String, words: Array<String>): Int {
        var count = 0
        for (word in words) {
            if (isSubsequence(word, s)) {
                count++
            }
        }
        return count
    }

    private fun isSubsequence(s: String, t: String): Boolean {
        var sIndex = 0
        for (c in t) {
            if (sIndex >= s.length) return true
            if (s[sIndex] == c) sIndex++
        }
        return sIndex == s.length
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