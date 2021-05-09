package challenges.cracking_coding_interview.arrays_strings

/*
Given two strings, write a method to decide if one is a permutation of the other.

 */
object CheckPermutations {

    private fun solveWithSorting(s1: String, s2: String): Boolean {
        if (s1.length != s2.length) return false
        return s1.toSortedSet() == s2.toSortedSet()
    }

    private fun solveWithIdenticalChars(s1: String, s2: String): Boolean {
        if (s1.length != s2.length) return false

        val letters = IntArray(128)

        for (char in s1.toCharArray()) {
            letters[char.toInt()]++
        }

        for (char in s2.toCharArray()) {
            val index = char.toInt()
            letters[index]--
            if (letters[index] < 0) return false
        }

        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(solveWithSorting("god", "dog")) // true
        println(solveWithSorting("god", "odg")) // true
        println(solveWithSorting("get", "dog")) // false

        println(solveWithIdenticalChars("god", "dog")) // true
        println(solveWithIdenticalChars("god", "odg")) // true
        println(solveWithIdenticalChars("get", "dog")) // false
    }
}