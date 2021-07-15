package challenges.coderbyte

/**
 * Have the function PalindromeCreator(str) take the str parameter being passed and determine if it is possible to create a palindromic string of minimum length 3 characters by removing 1 or 2 characters. For example: if str is "abjchba" then you can remove the characters jc to produce "abhba" which is a palindrome. For this example your program should return the two characters that were removed with no delimiter and in the order they appear in the string, so jc. If 1 or 2 characters cannot be removed to produce a palindrome, then return the string not possible. If the input string is already a palindrome, your program should return the string palindrome.
 *
 *
 * The input will only contain lowercase alphabetic characters. Your program should always attempt to create the longest palindromic substring by removing 1 or 2 characters (see second sample test case as an example). The 2 characters you remove do not have to be adjacent in the string.
 * Examples
 * Input: "mmop"
 * Output: not possible
 * Input: "kjjjhjjj"
 * Output: k
 */
object Palindrome {
    private val notPossible = Result.NOT_POSSIBLE.value

    private enum class Result(val value: String) {
        PALINDROME("palindrome"),
        NOT_POSSIBLE("not possible")
    }

    private fun palindromeCreator(str: String): String {
        //If the input string is already a palindrome
        if (isPalindrome(str)) return Result.PALINDROME.value

        val fromFirst = createPalindrome(str, false)
        val fromLast = createPalindrome(str, true)

        if (fromFirst != notPossible && fromLast != notPossible) {
            if (fromFirst.length > 2 || fromLast.length > 2) return notPossible
            return if (fromFirst.length > fromLast.length) fromLast else fromFirst
        }

        if (fromFirst != fromLast && fromFirst == notPossible) {
            return if (fromLast.length > 2) notPossible else fromLast
        }

        return if (fromLast != fromFirst && fromLast == notPossible) {
            if (fromFirst.length > 2) notPossible else fromFirst
        } else notPossible
    }

    private fun isPalindrome(string: String): Boolean {
        return string == reverseString(string)
    }

    private fun reverseString(string: String): String {
        return StringBuilder(string).reverse().toString()
    }

    private fun createPalindrome(_str: String, reverseOrder: Boolean): String {
        var str = _str
        if (reverseOrder) str = reverseString(str)
        val result = StringBuilder()
        val temp = StringBuilder(str)
        for (i in str.indices) {
            if (str[i] != str[str.length - 1 - i]) {
                if (removeCharAndCheck(result, temp)) return result.toString()
            }
        }
        return Result.NOT_POSSIBLE.value
    }

    private fun removeCharAndCheck(result: StringBuilder, temp: StringBuilder): Boolean {
        result.append(temp[0])
        temp.deleteCharAt(0)
        return isPalindrome(temp.toString()) && temp.length > 2
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(palindromeCreator("mnop"))
        println(palindromeCreator("kjjjhjjj"))
        println(palindromeCreator("kkkkkjjjhjjj"))
    }
}