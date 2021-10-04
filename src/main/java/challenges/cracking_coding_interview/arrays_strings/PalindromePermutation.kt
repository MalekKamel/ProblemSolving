package challenges.cracking_coding_interview.arrays_strings

import java.util.*

/*
Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome.
A palindrome is a word or phrase that is the same forwards and backwards.
A permutation is a rearrangement of letters.
The palindrome does not need to be limited to just dictionary words.
EXAMPLE
Input: Tact Coa
Output: True (permutations: "taco cat", "atco eta", etc.)
 */
object Palindrome {

    private fun solve(s: String): Boolean {
        val string = s.replace("\\s".toRegex(), "")
        val chars = string.toCharArray()

        val stack = Stack<Char>()
        for (char in chars) {
            stack.push(char)
        }

        for (char in chars) {
            val char2 = stack.pop()
            if (char != char2) return false
        }
        return true
    }

    private fun solve2(s: String): String {
        val string = s.replace("\\s".toRegex(), "")
        val isPalindrome = string == StringBuilder(string).reverse().toString()
        return isPalindrome.toString()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(solve("taco cat")) // true
        println(solve2("taco cat")) // true
        println(solve("atco cta")) // true
        println(solve("eye")) // true
        println(solve("taco cate")) // false
    }
}