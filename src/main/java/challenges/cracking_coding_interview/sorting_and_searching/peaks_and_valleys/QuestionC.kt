package challenges.cracking_coding_interview.sorting_and_searching.peaks_and_valleys

import challenges.util.AssortedMethods.arrayToString

object QuestionC {
    private fun swap(array: IntArray, left: Int, right: Int) {
        val temp = array[left]
        array[left] = array[right]
        array[right] = temp
    }

    fun sortValleyPeak(array: IntArray) {
        var i = 1
        while (i < array.size) {
            if (array[i - 1] < array[i]) {
                swap(array, i - 1, i)
            }
            if (i + 1 < array.size && array[i + 1] < array[i]) {
                swap(array, i + 1, i)
            }
            i += 2
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(5, 3, 1, 2, 3)
        println(arrayToString(array))
        sortValleyPeak(array)
        println(arrayToString(array))
        println(Tester.confirmValleyPeak(array))
    }
}