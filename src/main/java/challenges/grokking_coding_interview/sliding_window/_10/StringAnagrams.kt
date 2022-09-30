package challenges.grokking_coding_interview.sliding_window._10

object StringAnagrams {

    private fun findStringAnagrams(str: String, pattern: String): List<Int> {
        val resultIndices: MutableList<Int> = ArrayList()
        var windowStart = 0

        val charFrequencyMap: MutableMap<Char, Int> = HashMap()
        val patternLength = pattern.length
        var matched = 0

        for (chr in pattern.toCharArray())
            charFrequencyMap[chr] = charFrequencyMap.getOrDefault(chr, 0) + 1

        for (windowEnd in str.indices) {
            val rightChar = str[windowEnd]

            if (charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap[rightChar] = charFrequencyMap[rightChar]!! - 1
                if (charFrequencyMap[rightChar] == 0) {
                    matched++
                }
            }
            if (matched == charFrequencyMap.size) // have we found an anagram?
                resultIndices.add(windowStart)

            if (windowEnd >= patternLength - 1) {
                val leftChar = str[windowStart]
                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap[leftChar] == 0) {
                        matched--
                    }
                    charFrequencyMap[leftChar] = charFrequencyMap[leftChar]!! + 1
                }
                windowStart++
            }

        }

        return resultIndices
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(findStringAnagrams("ppqp", "pq"))
        println(findStringAnagrams("abbcabc", "abc"))
    }
}