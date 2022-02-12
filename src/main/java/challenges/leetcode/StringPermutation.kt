package challenges.leetcode

/*
Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

Example 1:

Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input: s1 = "ab", s2 = "eidboaoo"
Output: false

Constraints:

1 <= s1.length, s2.length <= 104
s1 and s2 consist of lowercase English letters.

https://leetcode.com/problems/permutation-in-string/
 */
object StringPermutation {

    private fun checkInclusion(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false
        val s1map = IntArray(26)
        val s2map = IntArray(26)
        for (i in s1.indices) {
            s1map[s1[i] - 'a']++
            s2map[s2[i] - 'a']++
        }
        var count = 0
        for (i in 0..25) if (s1map[i] == s2map[i]) count++
        for (i in 0 until s2.length - s1.length) {
            val r = s2[i + s1.length] - 'a'
            val l = s2[i] - 'a'
            if (count == 26) return true
            s2map[r]++
            if (s2map[r] == s1map[r]) count++
            else if (s2map[r] == s1map[r] + 1) count--
            s2map[l]--
            if (s2map[l] == s1map[l]) count++
            else if (s2map[l] == s1map[l] - 1) count--
        }
        return count == 26
    }

    private fun checkInclusion2(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false
        val s1map = IntArray(26)
        val s2map = IntArray(26)
        for (i in s1.indices) {
            s1map[s1[i] - 'a']++
            s2map[s2[i] - 'a']++
        }
        for (i in 0 until s2.length - s1.length) {
            if (matches(s1map, s2map)) return true
            s2map[s2[i + s1.length] - 'a']++
            s2map[s2[i] - 'a']--
        }
        return matches(s1map, s2map)
    }

    private fun matches(s1map: IntArray, s2map: IntArray): Boolean {
        for (i in 0..25) {
            if (s1map[i] != s2map[i]) return false
        }
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var s1 = "ab"
        var s2 = "eidbaooo"

        println("s1 = $s1, s2 = $s2")
        println(checkInclusion(s1, s2))
        println(checkInclusion2(s1, s2))

        s1 = "ab"
        s2 = "eidboaoo"
        println("s1 = $s1, s2 = $s2")
        println(checkInclusion(s1, s2))
        println(checkInclusion2(s1, s2))
    }
}