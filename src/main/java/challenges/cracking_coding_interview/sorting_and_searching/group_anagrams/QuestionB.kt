package challenges.cracking_coding_interview.sorting_and_searching.group_anagrams

import challenges.util.AssortedMethods.stringArrayToString
import java.util.HashMap
import java.util.Arrays
import kotlin.jvm.JvmStatic
import challenges.util.AssortedMethods
import challenges.cracking_coding_interview.sorting_and_searching.group_anagrams.AnagramComparator
import challenges.data_structure.HashMapList
import java.util.ArrayList

object QuestionB {
    private fun sort(array: Array<String>) {
        val mapList: HashMapList<String, String> = HashMapList()

        /* Group words by anagram */for (s in array) {
            val key = sortChars(s)
            mapList.put(key, s)
        }

        /* Convert hash table to array */
        var index = 0
        for (key in mapList.keySet()) {
            val list: ArrayList<String> = mapList.get(key)
            for (t in list) {
                array[index] = t
                index++
            }
        }
    }

    private fun sortChars(s: String): String {
        val content = s.toCharArray()
        Arrays.sort(content)
        return String(content)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = arrayOf("apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee")
        sort(array)
        println(stringArrayToString(array))
    }
}