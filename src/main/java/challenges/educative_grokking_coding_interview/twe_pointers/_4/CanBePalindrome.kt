package challenges.educative_grokking_coding_interview.twe_pointers._4

/**
Write a function that takes a string as input and checks whether it can be
a valid palindrome by removing at most one character from it.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/qAWVrz2GkjG
 */
object CanBePalindrome {
    private fun canBePalindrome(input: String): Boolean {
        var left = 0
        var right = input.length - 1

        while (left < right) {
            if (input[left] != input[right]) {
                // If the characters at the left and right indices don't match,
                // try removing either the left or the right character and check
                // if the resulting string is a palindrome.
                return isPalindrome(input.substring(left, right)) || isPalindrome(
                    input.substring(
                        left + 1,
                        right + 1
                    )
                )
            }
            left++
            right--
        }
        return true
    }

    private fun isPalindrome(str: String): Boolean {
        var left = 0
        var right = str.length - 1

        while (left < right) {
            if (str[left] != str[right]) {
                return false
            }
            left++
            right--
        }
        return true
    }

    @JvmStatic
    fun main(arg: Array<String>) {
        val str1 = "racecar"
        val str2 = "radar"
        val str3 = "level"
        val str4 = "hello"

        println(canBePalindrome(str1)) // true
        println(canBePalindrome(str2)) // true
        println(canBePalindrome(str3)) // true
        println(canBePalindrome(str4)) // false
    }
}
