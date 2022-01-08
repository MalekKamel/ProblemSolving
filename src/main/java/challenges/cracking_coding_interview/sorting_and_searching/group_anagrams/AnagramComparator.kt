package challenges.cracking_coding_interview.sorting_and_searching.group_anagrams

import java.util.Arrays
import java.util.Comparator

class AnagramComparator : Comparator<String> {

    override fun compare(s1: String, s2: String): Int {
        return sortChars(s1).compareTo(sortChars(s2))
    }

    private fun sortChars(s: String): String {
        val content = s.toCharArray()
        Arrays.sort(content)
        return String(content)
    }

}