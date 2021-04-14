package challenges.cracking_coding_interview

/*
Implement an algorithm to determine if a string has all unique characters. What if you cannot
use additional data structures?
 */
object UniqueChars {

    /*
    Brute force solution
    Complexity: O(N^2)
     */
    private fun solveBruteForce(s: String): Boolean {
        val chars = s.toCharArray()
        for ((i, ch) in chars.withIndex()) {
            for ((j, char) in chars.withIndex()) {
                if (j == i) continue
                if (ch == char) return false
            }
        }
        return true
    }

    /*
   Optimized solution
   Complexity: O(N)
    */
    private fun solve(s: String): Boolean {
        if (s.length > 128) return false
        val charSet = BooleanArray(128)
        for (char in s.toCharArray()) {
            val value = char.toInt()
            if (charSet[value]) return false
            charSet[value] = true
        }
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(solve("abcd")) // true
        println(solve("abcdd")) // false
    }
}