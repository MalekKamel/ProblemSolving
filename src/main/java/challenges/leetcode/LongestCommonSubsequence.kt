package challenges.leetcode

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some
 * characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * Example 1:
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 * Constraints:
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 *
 * https://leetcode.com/problems/longest-common-subsequence/
 *
 * Explanation Video: https://www.youtube.com/watch?v=sSno9rV8Rhg
 */
class LCS {
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        // 1. Problem Explanation
        // The problem asks us to find the length of the longest subsequence that is common to both input strings.
        // A subsequence doesn't have to be contiguous, but the relative order of characters must be preserved.

        // 2. Pattern Identification and Rationale
        // Dynamic Programming:
        // This problem can be efficiently solved using dynamic programming.
        // We can build a 2D table where dp[i][j] represents the length of the longest common subsequence of text1[0...i-1] and text2[0...j-1].
        // This approach avoids redundant calculations by storing and reusing intermediate results.
        // It is suitable because the problem has overlapping subproblems and optimal substructure.

        // 3. Solution Breakdown
        val m = text1.length
        val n = text2.length

        // Create a 2D array to store the lengths of common subsequences.
        val dp = Array(m + 1) { IntArray(n + 1) }

        // Iterate through the strings, building the dp table.
        for (i in 1..m) {
            for (j in 1..n) {
                if (text1[i - 1] == text2[j - 1]) {
                    // If the characters match, increment the length of the LCS.
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    // If the characters don't match, take the maximum LCS length from the previous subproblems.
                    dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }

        // The result is stored in the bottom-right cell of the dp table.
        return dp[m][n]
    }


}

class LCSString {
    fun longestCommonSubsequence(text1: String, text2: String): String {
        val n = text1.length
        val m = text2.length
        var maxLength = 0
        var endIndex = 0

        // Create a 2D array to store lengths of common suffixes of substrings.
        // dp[i][j] stores the length of the longest common suffix of str1[0...i-1] and str2[0...j-1].
        val dp = Array(n + 1) { IntArray(m + 1) }

        for (i in 1..n) {
            for (j in 1..m) {
                if (text1[i - 1] == text2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j]
                        endIndex = i
                    }
                } else {
                    dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }

        return if (maxLength > 0) {
            text1.substring(endIndex - maxLength, endIndex)
        } else {
            ""
        }
    }


}

fun main() {
    val solution = LCS()
    // Test cases
    println(solution.longestCommonSubsequence("abc", "abd"))            // Output: 2
    println(solution.longestCommonSubsequence("abc", "abc"))            // Output: 3
    println(solution.longestCommonSubsequence("abc", "def"))            // Output: 0
    println(solution.longestCommonSubsequence("bl", "yby"))             // Output: 1
    println(solution.longestCommonSubsequence("bsbininm", "jmjkbkjkv")) // Output: 1

    val solution2 = LCSString()
    // Test cases
    println(solution2.longestCommonSubsequence("abc", "abd"))            // Output: ab
    println(solution2.longestCommonSubsequence("abc", "abc"))            // Output: abc
    println(solution2.longestCommonSubsequence("abc", "def"))            // Output: (EMPTY)
    println(solution2.longestCommonSubsequence("bl", "yby"))             // Output: b
    println(solution2.longestCommonSubsequence("bsbininm", "jmjkbkjkv")) // Output: b
}