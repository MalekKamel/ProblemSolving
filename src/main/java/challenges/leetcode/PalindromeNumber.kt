package challenges.leetcode

import java.util.*

/**
Given an integer x, return true if x is palindrome integer.

An integer is a palindrome when it reads the same backward as forward.

For example, 121 is a palindrome while 123 is not.


Example 1:

Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.
Example 2:

Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Constraints:

-231 <= x <= 231 - 1

Follow up: Could you solve it without converting the integer to a string?
 */
object PalindromeNumber {

    private fun isPalindrome(x: Int): Boolean {
        if (x < 0) return false
        return isPalindrome(x.toString())
    }

    private fun isPalindrome(s: String): Boolean {
        val length = s.length
        val chars = s.toCharArray()

        for (i in s.indices) {
            if (chars[i] != chars[length - i - 1]) return false
        }
        return true
    }

    private fun isPalindrome2(s: String): Boolean {
        val length = s.length
        val chars = s.toCharArray()
        var fast = 0
        var slow = 0

        val stack = Stack<Char>()

        while (fast < length - 1) {
            stack.push(chars[slow])
            fast += 2
            slow++
        }

        // Skip the middle
        slow++

        while (stack.isNotEmpty()) {
            val item = stack.pop()
            if (chars[slow] != item) return false
            slow++
        }
        return true
    }

    fun isPalindrome3(_num: Int): Boolean {
        var num = _num
        if (num < 0) return false
        var reversed = 0
        var remainder: Int
        val original = num
        while (num != 0) {
            remainder = num % 10 // reversed integer is stored in variable
            reversed =
                reversed * 10 + remainder //multiply reversed by 10 then add the remainder, so it gets stored at next decimal place.
            num /= 10 //the last digit is removed from num after division by 10.
        }
        // palindrome if original and reversed are equal
        return original == reversed
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var x = 13231
        println("$x: ${isPalindrome(x)}")
        println("$x: ${isPalindrome2(x.toString())}")
        println("$x: ${isPalindrome3(x)}")

        x = 132311
        println("$x: ${isPalindrome(x)}")
        println("$x: ${isPalindrome2(x.toString())}")
        println("$x: ${isPalindrome3(x)}")
    }
}