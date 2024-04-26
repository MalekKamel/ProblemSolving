package challenges.mostafa._2_strings

/**
Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving
the order of characters. No two characters may map to the same character, but a character
may map to itself.


Example 1:

Input: s = "egg", t = "add"
Output: true

Example 2:

Input: s = "foo", t = "bar"
Output: false

Example 3:

Input: s = "paper", t = "title"
Output: true

Constraints:

1 <= s.length <= 5 * 104
t.length == s.length
s and t consist of any valid ascii character.

https://leetcode.com/problems/isomorphic-strings/solutions/1985459/205-isomorphic-strings/
 */

internal object P3IsomorphicString {


    private fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val sToTMap = mutableMapOf<Char, Char>()
        val tToSMap = mutableMapOf<Char, Char>()

        for (i in s.indices) {
            val sChar = s[i]
            val tChar = t[i]

            if (sToTMap.containsKey(sChar) && sToTMap[sChar] != tChar) return false
            if (tToSMap.containsKey(tChar) && tToSMap[tChar] != sChar) return false

            sToTMap[sChar] = tChar
            tToSMap[tChar] = sChar
        }

        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(isIsomorphic("egg", "add"))
        println(isIsomorphic("foo", "bar"))
        println(isIsomorphic("paper", "title"))
        println(isIsomorphic("badc", "baba"))
    }
}