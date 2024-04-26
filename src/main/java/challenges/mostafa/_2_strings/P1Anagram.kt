package challenges.mostafa._2_strings

/**
Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
typically using all the original letters exactly once.


Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false


Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.


Follow up: What if the inputs contain Unicode characters? How would you adapt your
solution to such a case?
 */

internal object P1Anagram {

    private fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val charCount = mutableMapOf<Char, Int>()

        // Count the occurrences of each character in string s
        for (char in s) {
            charCount[char] = charCount.getOrDefault(char, 0) + 1
        }

        // Decrement the count for each character in string t
        // If any count becomes zero or the character is not present, it means t is not an anagram of s
        for (char in t) {
            if (!charCount.containsKey(char) || charCount[char] == 0) return false
            charCount[char] = charCount[char]!! - 1
        }

        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(isAnagram("anagram", "nagaram"))
        println(isAnagram("rat", "car"))
    }
}