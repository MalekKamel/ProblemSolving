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

 */

internal object P6GroupShiftedStrings {

    private fun groupStrings(strings: List<String>): List<List<String>> {
        val groups = mutableMapOf<List<Int>, MutableList<String>>()

        for (string in strings) {
            val differences = calculateDifferences(string)

            if (differences in groups) {
                groups[differences]?.add(string)
            } else {
                groups[differences] = mutableListOf(string)
            }
        }

        return groups.values.toList()
    }

    private fun calculateDifferences(string: String): List<Int> {
        val differences = mutableListOf<Int>()

        for (i in 1 until string.length) {
            val sum = string[i] - string[i - 1] + 26
            val diff = sum % 26
            differences.add(diff)
        }

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