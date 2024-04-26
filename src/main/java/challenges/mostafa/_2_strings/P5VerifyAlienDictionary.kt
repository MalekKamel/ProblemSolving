package challenges.mostafa._2_strings

/**
In an alien language, surprisingly, they also use English lowercase letters, but possibly
in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet,
return true if and only if the given words are sorted lexicographically in this alien language.

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence
the sequence is unsorted.

Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.)
According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined
as the blank character which is less than any other character

https://leetcode.com/problems/verifying-an-alien-dictionary/description/
 */

internal object P5VerifyAlienDictionary {

    private fun isAlienSorted(words: Array<String>, order: String): Boolean {
        val indices = mutableMapOf<Char, Int>()
        for (i in order.indices) {
            indices[order[i]] = i
        }

        for (i in 1 until words.size) {
            if (words[i - 1] != words[i] && !isSmaller(words[i - 1], words[i], indices))
                return false
        }
        return true
    }

    private fun isSmaller(a: String, b: String, indices: Map<Char, Int>): Boolean {
        val len = minOf(a.length, b.length)

        for (i in 0 until len) {
            if (a[i] != b[i]) return indices[a[i]]!! < indices[b[i]]!!
        }

        return a.length < b.length
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(isAlienSorted(arrayOf("hello", "leetcode"), "hlabcdefgijkmnopqrstuvwxyz"))
        println(isAlienSorted(arrayOf("word", "world", "row"), "worldabcefghijkmnpqstuvxyz"))
        println(isAlienSorted(arrayOf("apple", "app"), "abcdefghijklmnopqrstuvwxyz"))
    }
}