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

    /**
     *  What is Lexicographical order?
     *
     * Lexicographical order, also known as lexical order or dictionary order, is essentially
     * a way of ordering sequences of elements (like words, numbers, or other symbols) based on
     * the order of their component elements. Here's a breakdown:
     *
     * Core Concept:
     *
     * It's a generalization of the alphabetical order used in dictionaries.
     * It compares elements in sequences position by position.
     * The order is determined by the first position where the elements in the sequences differ.
     *
     * How it Works:
     *
     * Comparison from Left to Right: Sequences are compared element by element, starting from the first
     * element.
     * Finding the First Difference: If the elements at a certain position are the same, the comparison
     * moves to the next position. This continues until a difference is found.
     * Determining Order: The order of the sequences is then determined by the order of the differing
     * elements.
     * Handling Different Lengths: If one sequence is shorter than the other, and all the elements up to
     * the shorter sequence's length are the same, the shorter sequence is considered to come first.
     * In many computational context, this is thought of as the shorter word being padded with blank
     * or null values.
     *
     * Examples:
     *
     * In a standard dictionary, "apple" comes before "application" because, even though they share
     * the prefix "appl," "e" comes before "i."
     * In numerical lexicographical order, "10" comes before "2" because "1" comes before "2."
     *
     * In essence, lexicographical order provides a consistent method for sorting sequences, making
     * it fundamental in various applications, including:
     *
     * Dictionaries and encyclopedias
     * Computer science (string sorting, data structure organization)
     * Mathematics (ordering of tuples and sets)
     *
     * 1. Problem Explanation
     *  The problem asks to determine if a given array of words is sorted lexicographically
     *   according to a custom alphabet order.
     *
     * 2. Pattern Identification and Rationale
     *
     *   The problem can be solved using a straightforward iterative comparison approach. We iterate
     *   through the array of words, comparing each word with the next one. To perform the comparison,
     *   we use a hash map (dictionary) to store the indices of each character in the alien alphabet.
     *
     *   Iterative Comparison: This is suitable because we need to check the relative order of adjacent words.
     *   Hash Map (Dictionary): This is efficient for looking up the index of a character in the alien
     *   alphabet, allowing for constant-time lookups (O(1)).
     *
     * 3. Solution Breakdown
     *
     *   Create a character index map:
     *   Create a mutableMapOf<Char, Int> to store the alien alphabet's character indices.
     *   Iterate through the order string and populate the map with character-index pairs.
     *   Iterate and compare adjacent words:
     *   Iterate through the words array, comparing each word with the previous one.
     *   Call a helper function isSmaller to compare two words based on the alien order.
     *   If isSmaller returns false, it means the words are not in the correct order, and we return false.
     *
     *   Helper function isSmaller:
     *   Find the minimum length of the two words being compared.
     *   Iterate through the characters of the words up to the minimum length.
     *   Get the indices of the characters from the character index map.
     *   If the characters are different, compare their indices. If the first character's index
     *   is smaller, return true; otherwise, return false.
     *   If all characters up to the minimum length are the same, the shorter word is considered smaller.
     *   Return true: If all word pairs are sorted, return true.
     *
     * 4. Efficient Implementation
     *
     *   The provided Kotlin implementation is efficient and readable.
     *
     *   Time Complexity: O(N * M), where N is the number of words and M is the maximum length of a word.
     *   Space Complexity: O(1), because the size of the indices map is limited to the number of
     *   lowercase English letters (26).
     *   The code includes test cases within the main function to demonstrate the solution's correctness.
     *   The helper function isSmaller enhances readability and modularity.
     *   The use of minOf and until makes the code concise and idiomatic.
     *
     */
    private fun isAlienSorted(words: Array<String>, order: String): Boolean {
        // 1. Create a map to store the alien alphabet's character indices.
        val indices = mutableMapOf<Char, Int>()
        for (i in order.indices) {
            indices[order[i]] = i
        }

        // 2. Iterate through the words, comparing adjacent pairs.
        for (i in 1 until words.size) {
            // 3. Check if the previous word is not the same as the current word and if the previous word is greater than the current word in alien order.
            if (words[i - 1] != words[i] && !isSmaller(words[i - 1], words[i], indices)) {
                // 4. If the words are not in sorted order, return false.
                return false
            }
        }
        // 5. If all word pairs are sorted, return true.
        return true
    }

    // Helper function to compare two words in alien order.
    private fun isSmaller(word1: String, word2: String, indices: Map<Char, Int>): Boolean {
        // 1. Find the minimum length of the two words.
        val minLength = minOf(word1.length, word2.length)

        // 2. Iterate through the characters of the words up to the minimum length.
        for (i in 0 until minLength) {
            // 3. Get the indices of the characters in the alien alphabet.
            val index1 = indices[word1[i]]!!
            val index2 = indices[word2[i]]!!

            // 4. If the characters are different, compare their indices.
            if (index1 != index2) {
                // 5. If the index of the character in word1 is less than the index of the character in word2, word1 is smaller.
                return index1 < index2
            }
        }

        // 6. If all characters up to the minimum length are the same, the shorter word is smaller.
        return word1.length <= word2.length
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(isAlienSorted(arrayOf("hello", "leetcode"), "hlabcdefgijkmnopqrstuvwxyz"))
        println(isAlienSorted(arrayOf("word", "world", "row"), "worldabcefghijkmnpqstuvxyz"))
        println(isAlienSorted(arrayOf("apple", "app"), "abcdefghijklmnopqrstuvwxyz"))
    }
}