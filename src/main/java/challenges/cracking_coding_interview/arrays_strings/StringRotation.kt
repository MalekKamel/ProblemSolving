package challenges.cracking_coding_interview.arrays_strings

/*
Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes,
 write a method to rotate the image by 90 degrees. Can you do this in place?
 */
object StringRotation {

    private fun isRotation(s1: String, s2: String): Boolean {
        if (s1.isEmpty()) return false
        if (s1.length != s2.length) return false

        // concatenate s1 and s1 within new buffer
        val s1s1 = s1 + s1
        return isSubstring(s1s1, s2)
    }

    private fun isSubstring(big: String, small: String): Boolean {
        return big.indexOf(small) >= 0
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val pairs =
            arrayOf(
                arrayOf("apple", "pleap"),
                arrayOf("waterbottle", "erbottlewat"),
                arrayOf("camera", "macera")
            )
        for (pair in pairs) {
            val word1 = pair[0]
            val word2 = pair[1]
            val isRotation = isRotation(word1, word2)
            println("$word1, $word2: $isRotation")
        }
    }
}