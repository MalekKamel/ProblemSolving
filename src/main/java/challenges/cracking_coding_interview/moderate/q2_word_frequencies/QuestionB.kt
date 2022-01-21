package challenges.cracking_coding_interview.moderate.q2_word_frequencies

import challenges.util.AssortedMethods.getLongTextBlobAsStringList

object QuestionB {
    fun setupDictionary(book: Array<String?>?): HashMap<String, Int> {
        val table = HashMap<String, Int>()
        for (_word in book!!) {
            val word = _word!!.toLowerCase()
            if (word.trim { it <= ' ' } !== "") {
                if (!table.containsKey(word)) {
                    table[word] = 0
                }
                table[word] = table[word]!! + 1
            }
        }
        return table
    }

    fun getFrequency(table: HashMap<String, Int>?, word: String?): Int {
        var word = word
        if (table == null || word == null) {
            return -1
        }
        word = word.toLowerCase()
        return if (table.containsKey(word)) {
            table[word]!!
        } else 0
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val wordlist = getLongTextBlobAsStringList()
        val dictionary = setupDictionary(wordlist)
        val words = arrayOf("the", "Lara", "and", "outcropping", "career", "it")
        for (word in words) {
            println(word + ": " + getFrequency(dictionary, word))
        }
    }
}