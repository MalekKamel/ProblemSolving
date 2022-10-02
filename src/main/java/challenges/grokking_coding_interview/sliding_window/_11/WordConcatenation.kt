package challenges.grokking_coding_interview.sliding_window._11


object WordConcatenation {
    private fun findWordConcatenation(str: String, words: Array<String>): List<Int> {
        val wordFrequencyMap: MutableMap<String, Int> = HashMap()
        for (word in words) wordFrequencyMap[word] = wordFrequencyMap.getOrDefault(word, 0) + 1
        val resultIndices: MutableList<Int> = ArrayList()
        val wordsCount = words.size
        val wordLength = words[0].length
        for (i in 0..str.length - wordsCount * wordLength) {
            val wordsSeen: MutableMap<String, Int> = HashMap()
            for (j in 0 until wordsCount) {
                val nextWordIndex = i + j * wordLength
                // get the next word from the string
                val word = str.substring(nextWordIndex, nextWordIndex + wordLength)
                if (!wordFrequencyMap.containsKey(word)) // break if we don't need this word
                    break
                wordsSeen[word] = wordsSeen.getOrDefault(word, 0) + 1 // add the word to the 'wordsSeen' map

                // no need to process further if the word has higher frequency than required
                if (wordsSeen[word]!! > wordFrequencyMap.getOrDefault(word, 0)) break
                if (j + 1 == wordsCount) // store index if we have found all the words
                    resultIndices.add(i)
            }
        }
        return resultIndices
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var result = findWordConcatenation("catfoxcat", arrayOf("cat", "fox"))
        println(result)
        result = findWordConcatenation("catcatfoxfox", arrayOf("cat", "fox"))
        println(result)
    }
}