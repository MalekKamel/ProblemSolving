package challenges.cracking_coding_interview.arrays_strings

import java.util.*

/*
Check if a string is a palindrome
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