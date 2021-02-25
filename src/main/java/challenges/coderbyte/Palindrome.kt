package challenges.coderbyte

object Palindrome {

    private fun palindrome(str: String): String {
        val string = str.replace("\\s".toRegex(), "")
        val isPalindrome = string == StringBuilder(string).reverse().toString()
        return isPalindrome.toString()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(palindrome("never odd or even")) // true
        println(palindrome("eye")) // true
        println(palindrome("Good")) // false
    }
}