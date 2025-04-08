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

1 <= s.length <= 5 * 10^4
t.length == s.length
s and t consist of any valid ascii character.

https://leetcode.com/problems/isomorphic-strings/solutions/1985459/205-isomorphic-strings/
 */

internal object P3IsomorphicString {

    /**
     * 1. Problem Explanation
     * The problem asks us to determine if two given strings, 's' and 't', are isomorphic.
     * Isomorphic means that the characters in 's' can be replaced to get 't', following
     * these rules: each character in 's' must map to exactly one character in 't',
     * and no two characters in 's' can map to the same character in 't', but a character
     * can map to itself.
     *
     * 2. Pattern Identification and Rationale
     * The pattern is to use two maps (or dictionaries) to track the mappings between
     * characters in 's' and 't'.
     * This approach is suitable because it allows us to efficiently check if a character
     * in 's' has already been mapped to a character in 't', and vice-versa.
     * Using two maps ensures that the one-to-one mapping rule is enforced in both directions.
     *
     * 3. Solution Breakdown
     * - Create two maps: 'sMap' to store mappings from characters in 's' to characters in 't',
     *   and 'tMap' to store mappings from characters in 't' to characters in 's'.
     * - Iterate through the strings 's' and 't' simultaneously.
     * - For each pair of characters (sChar, tChar) at the same index:
     *   - If 'sChar' is already in 'sMap', check if its mapping matches 'tChar'.
     *   - If 'tChar' is already in 'tMap', check if its mapping matches 'sChar'.
     *   - If either check fails, return false.
     *   - If 'sChar' is not in 'sMap' and 'tChar' is not in 'tMap', create the mappings in both maps.
     * - If the loop completes without returning false, return true.
     *
     * Why Two Maps?
     * While it might seem like a single map could suffice, using only one map would lead to incorrect
     * results in certain scenarios. Here's why two maps are necessary:
     *
     * The Problem with a Single Map
     *
     * Let's say you only use sMap (mapping characters from s to t). Consider these steps:
     *
     * Mapping s to t: You correctly map characters from s to t.
     * Checking Consistency: You can check if the current character in s maps to the corresponding
     * character in t.
     * However, you cannot reliably check if the current character in t is already mapped to by another
     * character in s. This is where the problem lies.
     *
     * Example Illustrating the Issue
     *
     * Consider these strings:
     *
     * s = "ab"
     * t = "aa"
     * If you use only sMap, the process would look like this:
     *
     * sMap['a'] = 'a'
     * sMap['b'] = 'a'
     * With only the sMap, this would seem valid. The function would return true.
     *
     * However, t has 'a' mapped to by both 'a' and 'b' from string s, which violates the isomorphic
     * rule.
     *
     * Why Two Maps Solve the Problem
     *
     * By using tMap (mapping characters from t to s), you ensure that:
     *
     * s to t Mapping: You check if a character in s maps to the correct character in t.
     * t to s Mapping: You also check if a character in t is already mapped to by a different
     * character in s.
     * In the example above, tMap['a'] would be assigned to 'a' during the first iteration. When
     * the second iteration comes along and it attempts to assign tMap['a'] to 'b', the code will
     * detect this violation and return false.
     *
     * In essence:
     *
     * sMap ensures that each character in s maps to only one character in t.
     * tMap ensures that each character in t is mapped to by only one character in s.
     * Therefore, you need both maps to fully enforce the bidirectional one-to-one mapping rule
     * required for isomorphic strings.
     */
    private fun isIsomorphic(s: String, t: String): Boolean {
        // 1. Length Check:
        if (s.length != t.length) return false
        // If the lengths of the two strings are different, they cannot be isomorphic.
        // This is a crucial early optimization.

        // 2. Maps for Character Mappings:
        val sMap = mutableMapOf<Char, Char>()
        val tMap = mutableMapOf<Char, Char>()
        // 'sMap' will store mappings from characters in 's' to characters in 't'.
        // 'tMap' will store mappings from characters in 't' to characters in 's'.
        // Using two maps ensures the one-to-one mapping rule is maintained in both directions.

        // 3. Iteration and Mapping Check:
        for (i in s.indices) {
            val sChar = s[i]
            val tChar = t[i]

            // 4. Existing Mapping Check:
            if (sMap.containsKey(sChar) && sMap[sChar] != tChar) return false
            // If 'sChar' is already in 'sMap', check if its mapped value matches 'tChar'.
            // If it doesn't, it means the mapping is inconsistent, and the strings are not isomorphic.
            if (tMap.containsKey(tChar) && tMap[tChar] != sChar) return false
            // Similarly, check if 'tChar' is already in 'tMap' and if its mapped value matches 'sChar'.

            // 5. Creating Mappings:
            sMap[sChar] = tChar
            tMap[tChar] = sChar
            // If the checks in step 4 pass, create the mappings in both maps.
            // This ensures that the mapping is consistent for the rest of the strings.
        }

        // 6. Isomorphic Confirmation:
        return true
        // If the loop completes without returning false, it means all mappings are consistent,
        // and the strings are isomorphic.
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(isIsomorphic("egg", "add"))
        println(isIsomorphic("foo", "bar"))
        println(isIsomorphic("paper", "title"))
        println(isIsomorphic("badc", "baba"))
    }
}