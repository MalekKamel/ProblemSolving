package challenges.educative_grokking_coding_interview.twe_pointers._3

/**
Given a sentence, reverse the order of its words without affecting the order of letters within a given word.
Time Complexity: O(n)
 */

object ReverseWords {
    private fun reverseWords(s: String): String {
        // trim spaces and convert string to string builder
        val s1 = s.toCharArray()
        val s2 = cleanSpaces(s1, s1.size)
        val builder = StringBuilder(s2)

        // reverse the whole string using the strRev() function
        strRev(builder, 0, builder.length - 1)

        // reverse every word
        val n = builder.length
        var start = 0
        var end = 0
        // Find the start index of each word by detecting spaces.
        while (start < n) {
            // Find the end index of the word.
            while (end < n && builder[end] != ' ') ++end
            // Let's call our helper function to reverse the word in-place.
            strRev(builder, start, end - 1)
            // moving to the next word
            start = end + 1
            ++end
        }
        return builder.toString()
    }

    // Function to reverse the whole string
    private fun strRev(sb: StringBuilder, startRev: Int, endRev: Int) {
        // Starting from the two ends of the list, and moving
        // in towards the centre of the string, swap the characters
        var start = startRev
        var end = endRev
        while (start < end) {
            val temp = sb[start] // temp store for swapping
            sb.setCharAt(start++, sb[end])
            sb.setCharAt(end--, temp)
        }
    }

    // trim leading, trailing and multiple spaces
    private fun cleanSpaces(a: CharArray?, n: Int): String {
        // Convert character array to string
        var str = String(a!!, 0, n)

        // Trim extra spaces at the beginning and end of the string
        // And replace multiple spaces with a single space
        str = str.replace("\\s+".toRegex(), " ").trim { it <= ' ' }
        return str
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val inputs = arrayOf(
            "Hello World", "We love Python",
            "The quick brown fox jumped over the lazy dog.",
            "Hey", "To be or not to be", "AAAAA", " Hello     World "
        )
        for (i in inputs.indices) {
            print(i + 1)
            println(".\tActual string:\t\t" + inputs[i])
            println("\tReversed String:\t" + reverseWords(inputs[i]))
            println(String(CharArray(100)).replace('\u0000', '-'))
        }
    }
}