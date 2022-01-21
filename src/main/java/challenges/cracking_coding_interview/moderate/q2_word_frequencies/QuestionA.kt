package challenges.cracking_coding_interview.moderate.q2_word_frequencies

import challenges.util.AssortedMethods.getLongTextBlobAsStringList

object QuestionA {
    fun getFrequency(book: Array<String?>?, word: String): Int {
        var word = word
        word = word.trim { it <= ' ' }.toLowerCase()
        var count = 0
        for (w in book!!) {
            if (w!!.trim { it <= ' ' }.toLowerCase() == word) {
                count++
            }
        }
        return count
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val wordlist = getLongTextBlobAsStringList()
        val words = arrayOf("the", "Lara", "and", "outcropping", "career", "it")
        for (word in words) {
            println(word + ": " + getFrequency(wordlist, word))
        }
    }
}