package challenges.educative_grokking_coding_interview.twe_pointers._1

import challenges.util.PrintHyphens


object ValidPalindrome {
    private fun isPalindrome(s: String): Boolean {
        var left = 0
        var right = s.length - 1
        println("\tThe element being pointed to by the left pointer is '" + s[left] + "'")
        println("\tThe element being pointed to by the right pointer is '" + s[right] + "'")
        while (left < right) {
            println("\tWe check if the two elements are indeed the same, in this case...")
            // If the elements at index left and index right are not equal,
            if (s[left] != s[right]) {
                println("\tThe elements aren't the same, hence we return False")
                return false // then the symmetry is broken, the string is not a palindrome
            }
            println("\tThey are the same, thus we move the two pointers toward the middle to continue the \n\tverification process.\n")
            left += 1 // Heading towards the right
            right -= 1 // Heading towards the left
            println("\tThe new element at the left pointer is " + s[left])
            println("\tThe new element at the right pointer is " + s[right])
        }
        // We reached the middle of the string without finding a mismatch, so it is a palindrome.
        return true
    }

    //Driver code
    @JvmStatic
    fun main(arg: Array<String>) {
        val testCase = arrayOf(
            "RACEACAR",
            "A",
            "ABCDEFGFEDCBA",
            "ABC",
            "ABCBA",
            "ABBA",
            "RACEACAR"
        )
        for (k in testCase.indices) {
            println("Test Case #" + (k + 1))
            println(PrintHyphens.repeat("-", 100))
            println("\tThe input string is " + testCase[k] + "' and the length of the string is " + testCase[k].length + ".")
            println(
                """
                    
                  Is it a palindrome?..... ${isPalindrome(testCase[k])}
                    """.trimIndent()
            )
            println(PrintHyphens.repeat("-", 100))
        }
    }
}