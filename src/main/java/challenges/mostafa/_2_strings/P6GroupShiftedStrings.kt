package challenges.mostafa._2_strings

/**
Given a string, we can "shift" each of its letter to its successive letter,
for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
"abc" -> "bcd" -> ... -> "xyz"

Given a list of strings which contains only lowercase alphabets, group all strings that belong to
the same shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]
Output:
[
["abc","bcd","xyz"],
["az","ba"],
["acef"],
["a","z"]
]

https://leetcode.ca/2016-08-05-249-Group-Shifted-Strings/
 */

internal object P6GroupShiftedStrings {

    /**
    The approach of using character differences to group strings is based on the observation
    that strings that are "similar" in terms of the relative positions of their characters in
    the alphabet will have the same sequence of differences between their adjacent characters.

    Here's an example to illustrate this:

    Let's say we have the following strings:
    "abc"
    "bcd"
    "xyz"
    "yza"
    If we calculate the character differences for each string, we get:

    "abc": [1, 1]
    (the difference between 'a' and 'b' is 1, and the difference between 'b' and 'c' is also 1)
    "bcd": [1, 1]
    (the difference between 'b' and 'c' is 1, and the difference between 'c' and 'd' is also 1)
    "xyz": [23, 2]
    (the difference between 'x' and 'y' is 23, and the difference between 'y' and 'z' is 2)
    "yza": [23, 2]
    (the difference between 'y' and 'z' is 23, and the difference between 'z' and 'a' is 2)
    As you can see, the strings "abc" and "bcd" have the same sequence of character
    differences, [1, 1],
    and the strings "xyz" and "yza" also have the same sequence of character differences, [23, 2].
    This means that these strings can be considered "similar" in terms of the relative positions
    of their characters in the alphabet.

    The groupStrings function in the code takes advantage of this observation by
    using the character differences as a key to group the input strings. This allows it
    to efficiently identify and group together strings that have the same sequence of
    character differences, which can be useful in various applications, such as:

    Text analysis: Grouping similar strings can be helpful for tasks like finding synonyms,
    detecting typos, or identifying common patterns in text data.
    Data compression: By identifying and compressing groups of similar strings, you can
    achieve better compression ratios for text data.
    Cryptanalysis: The character difference approach can be useful in cryptanalysis, as it can
    help identify patterns and relationships in encrypted text.

    Overall, the character difference approach is a simple but effective way to identify
    and group similar strings, and it can be a valuable tool in a variety of text-processing
    and data-analysis scenarios.

    ---------------------

    Time Complexity:

    The time complexity of the provided groupStrings function can be analyzed as follows:

    Initialization of groups map: This is a constant-time operation, as creating an empty
    mutableMapOf takes O(1) time.
    Iterating through the input list of strings: The function iterates through the input list
    of strings using a for loop. This takes O(n) time, where n is the number of strings in the
    input list.
    Calculating the character differences: The calculateDifferences function is called for each
    string in the input list. This function has a time complexity of O(m), where m is the length
    of the string, as it iterates through the characters in the string once.
    Checking and updating the groups map: For each string, the function checks if the list of
    character differences is already a key in the groups map. If it is, the string is added
    to the corresponding mutable list. If it's not, a new entry is added to the map. These
    operations (lookup, add, and append) on a mutableMapOf have an average time complexity of O(1).
    Putting it all together, the overall time complexity of the groupStrings function is:
    O(n * m)

    where:

    n is the number of strings in the input list
    m is the maximum length of the strings in the input list

    This is because the function iterates through the input list of n strings, and for each string,
    it calculates the character differences, which takes O(m) time.

    The space complexity of the function is O(n + k), where k is the number of unique groups
    of strings (i.e., the number of distinct lists of character differences). This is because
    the function stores the grouped strings in a mutableMapOf, where the keys are the lists
    of character differences and the values are the mutable lists of strings.
     */
    private fun groupStrings(strings: List<String>): List<List<String>> {
        // 1. Initialize a mutable map to store groups of strings.
        // The keys are lists of integer differences, and the values are lists of strings.
        val groups = mutableMapOf<List<Int>, MutableList<String>>()

        // 2. Iterate through each string in the input list.
        for (string in strings) {
            // 3. Calculate the differences between consecutive characters in the current string.
            val differences = calculateDifferences(string)
            groups.computeIfAbsent(differences) { mutableListOf() }.add(string)
        }

        // 7. Return the values of the map as a list of lists, which represents the grouped strings.
        return groups.values.toList()
    }

    private fun calculateDifferences(string: String): List<Int> {
        // 1. Initialize a mutable list to store the differences between consecutive characters.
        val differences = mutableListOf<Int>()

        // 2. Iterate through the string, starting from the second character (index 1).
        for (i in 1 until string.length) {
            // 3. Calculate the difference between the current character and the previous character.
            // Add 26 to handle negative differences (wrapping around the alphabet).
            // Take the modulo 26 to ensure the difference is within the range [0, 25].
            val sum = string[i] - string[i - 1] + 26
            val diff = sum % 26
            // 4. Add the calculated difference to the list.
            differences.add(diff)
        }

        // 5. Return the list of differences.
        return differences
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val strings = listOf("abc", "bcd", "acef", "xyz", "az", "ba", "a", "z")

        val groupedStrings = groupStrings(strings)
        for (group in groupedStrings) {
            println(group.joinToString(", "))
        }
    }
}