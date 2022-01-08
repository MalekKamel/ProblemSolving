package challenges.cracking_coding_interview.sorting_and_searching.group_anagrams

import challenges.util.AssortedMethods.stringArrayToString
import java.util.HashMap
import java.util.Arrays
import kotlin.jvm.JvmStatic
import challenges.util.AssortedMethods
import challenges.cracking_coding_interview.sorting_and_searching.group_anagrams.AnagramComparator

object Question {
    @JvmStatic
    fun main(args: Array<String>) {
        val array = arrayOf("apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee")
        println(stringArrayToString(array))
        Arrays.sort(array, AnagramComparator())
        println(stringArrayToString(array))
    }
}