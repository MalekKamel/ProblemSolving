package challenges.cracking_coding_interview.sorting_and_searching

import challenges.util.AssortedMethods.printIntArray
import challenges.util.AssortedMethods.randomArray


object MergeSort {
    private fun mergesort(array: IntArray) {
        val helper = IntArray(array.size)
        mergesort(array, helper, 0, array.size - 1)
    }

    private fun mergesort(
        array: IntArray,
        helper: IntArray,
        low: Int, high: Int
    ) {
        if (low >= high) return
        val middle = low + (high - low) / 2
        mergesort(array, helper, low, middle) // Sort left half
        mergesort(array, helper, middle + 1, high) // Sort right half
        merge(array, helper, low, middle, high) // Merge them
    }

    private fun merge(
        array: IntArray,
        helper: IntArray,
        low: Int, middle: Int,
        high: Int
    ) {
        /* Copy both halves into a helper array */
        for (i in low..high) {
            helper[i] = array[i]
        }

        var helperLeft = low
        var helperRight = middle + 1
        var current = low

        /* Iterate through helper array. Compare the left and right
             * half, copying back the smaller element from the two halves
             * into the original array.
             * */
        while (helperLeft <= middle && helperRight <= high) {
            if (helper[helperLeft] <= helper[helperRight]) {
                array[current] = helper[helperLeft]
                helperLeft++
            } else { // If right element is smaller than left element
                array[current] = helper[helperRight]
                helperRight++
            }
            current++
        }

        /* Copy the rest of the left side of the array into the
         * target array
         *  */
        val remaining = middle - helperLeft
        for (i in 0..remaining) {
            array[current + i] = helper[helperLeft + i]
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val size = 8
        val array = randomArray(size, 0, size - 1)
        val validate = IntArray(size)
        printIntArray(array)
        for (i in 0 until size) {
            validate[array[i]]++
        }
        mergesort(array)
        for (i in 0 until size) {
            validate[array[i]]--
        }
        printIntArray(array)
        for (i in 0 until size) {
            if (validate[i] != 0 || i < size - 1 && array[i] > array[i + 1]) {
                println("ERROR")
            }
        }
    }
}