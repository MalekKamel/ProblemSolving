package challenges.cracking_coding_interview.sorting_and_searching.peaks_and_valleys

import challenges.util.AssortedMethods


object QuestionA {
    fun sortValleyPeak(array: IntArray) {
        array.sort()
        var i = 1
        while (i < array.size) {
            swap(array, i - 1, i)
            i += 2
        }
    }

    private fun swap(array: IntArray, left: Int, right: Int) {
        val temp = array[left]
        array[left] = array[right]
        array[right] = temp
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