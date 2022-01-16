package challenges.cracking_coding_interview.sorting_and_searching.peaks_and_valleys

import challenges.util.AssortedMethods


object QuestionB {
    private fun swap(array: IntArray, left: Int, right: Int) {
        val temp = array[left]
        array[left] = array[right]
        array[right] = temp
    }

    fun sortValleyPeak(array: IntArray) {
        var i = 1
        while (i < array.size) {
            val biggestIndex = maxIndex(array, i - 1, i, i + 1)
            if (i != biggestIndex) {
                swap(array, i, biggestIndex)
            }
            i += 2
        }
    }

    fun maxIndex(array: IntArray, a: Int, b: Int, c: Int): Int {
        val len = array.size
        val aValue = if (a >= 0 && a < len) array[a] else Int.MIN_VALUE
        val bValue = if (b >= 0 && b < len) array[b] else Int.MIN_VALUE
        val cValue = if (c >= 0 && c < len) array[c] else Int.MIN_VALUE
        val max = Math.max(aValue, Math.max(bValue, cValue))
        return if (aValue == max) {
            a
        } else if (bValue == max) {
            b
        } else {
            c
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(48, 40, 31, 62, 28, 21, 64, 40, 23, 17)
        println(AssortedMethods.arrayToString(array))
        sortValleyPeak(array)
        println(AssortedMethods.arrayToString(array))
        println(Tester.confirmValleyPeak(array))
    }
}