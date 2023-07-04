package challenges.educative_grokking_coding_interview.top_k_elements._5

import challenges.util.PrintHyphens
import java.util.*

/**
Find the kth largest element in an unsorted array.
Note: We need to find the kth largest element in the sorted order, not the kth distinct element.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/g79o2467JpG
 */
internal object KthLargestElement {

    private fun findKthLargest(array: IntArray, k: Int): Int {
        val kNumbersMinHeap = PriorityQueue { n1: Int, n2: Int -> n1 - n2 }
        // Put first k elements in the min heap
        for (i in 0 until k) kNumbersMinHeap.add(array[i])

        // Go through the remaining elements of the array, if the element from the array is greater than the
        // top (smallest) element of the heap, remove the top element from heap and add the element from array
        for (i in k until array.size) {
            if (array[i] > kNumbersMinHeap.peek()) {
                kNumbersMinHeap.poll()
                kNumbersMinHeap.add(array[i])
            }
        }

        // The root of the heap has the Kth largest element
        return kNumbersMinHeap.peek()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Driver code
        val inputs = arrayOf(
            intArrayOf(1, 5, 12, 2, 11, 9, 7, 30, 20),
            intArrayOf(23, 13, 17, 19, 10),
            intArrayOf(3, 2, 5, 6, 7),
            intArrayOf(1, 4, 6, 0, 2),
            intArrayOf(1, 2, 3, 4, 5, 6, 7)
        )
        val K = intArrayOf(3, 4, 5, 1, 7)
        for (i in K.indices) {
            print(i + 1)
            print(".\tInput array: " + Arrays.toString(inputs[i]))
            print(", K:  " + K[i])
            println(
                """
	kth largest element: ${findKthLargest(inputs[i], K[i])}"""
            )
            System.out.println(PrintHyphens.repeat("-", 100))
        }
    }
}