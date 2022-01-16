package challenges.cracking_coding_interview.sorting_and_searching.peaks_and_valleys

import challenges.util.AssortedMethods

object Tester {
    fun confirmValleyPeak(array: IntArray): Boolean {
        for (i in 1 until array.size - 1) {
            val prev = array[i - 1]
            val curr = array[i]
            val next = array[i + 1]
            return if (prev <= curr && curr >= next) {
                continue
            } else if (prev >= curr && curr <= next) {
                continue
            } else {
                false
            }
        }
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 0..99) {
            val originalArray: IntArray = AssortedMethods.randomArray(10, 0, 100)
            val arrayA = originalArray.clone()
            val arrayB = originalArray.clone()
            val arrayC = originalArray.clone()
            // println(AssortedMethods.arrayToString(array));
            QuestionA.sortValleyPeak(arrayA)
            QuestionB.sortValleyPeak(arrayB)
            QuestionC.sortValleyPeak(arrayC)
            if (!confirmValleyPeak(arrayA) || !confirmValleyPeak(arrayB) || !confirmValleyPeak(arrayC)) {
                println(AssortedMethods.arrayToString(originalArray))
                println(AssortedMethods.arrayToString(arrayA))
                println(AssortedMethods.arrayToString(arrayB))
                println(AssortedMethods.arrayToString(arrayC))
                println(confirmValleyPeak(arrayA))
                println(confirmValleyPeak(arrayB))
                println(confirmValleyPeak(arrayC))
                break
            }
        }
    }
}