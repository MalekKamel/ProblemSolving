package challenges.educative_grokking_coding_interview.top_k_elements._4

import java.util.*

/**
Given an array of integers, arr, and an integer, k, return the k most frequent elements.
Note: You can return the answer in any order.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/m2ND3J3GY2n
 */
internal object FrequentElements {
    private fun topKFrequent(arr: IntArray, k: Int): List<Int> {
        // Find the frequency of each number
        val numFrequencyMap: MutableMap<Int, Int> = HashMap()
        for (n in arr) numFrequencyMap[n] = numFrequencyMap.getOrDefault(n, 0) + 1
        val topKElements =
            PriorityQueue { (_, value): Map.Entry<Int, Int>, (_, value1): Map.Entry<Int, Int> -> value - value1 }

        // Go through all numbers of the numFrequencyMap and push them into topKElements, which will have
        // the top k frequent numbers. If the heap size is more than k, we remove the smallest (top) number.
        for (entry in numFrequencyMap.entries) {
            topKElements.add(entry)
            if (topKElements.size > k) {
                topKElements.poll()
            }
        }

        // Create a list of top k numbers
        val topNumbers: MutableList<Int> = ArrayList(k)
        while (!topKElements.isEmpty()) {
            topNumbers.add(topKElements.poll().key)
        }
        topNumbers.sort()
        return topNumbers
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Driver code
        val inputs = arrayOf(
            intArrayOf(1, 3, 5, 12, 11, 12, 11, 12, 5),
            intArrayOf(1, 3, 5, 14, 18, 14, 5),
            intArrayOf(2, 3, 4, 5, 6, 7, 7),
            intArrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1),
            intArrayOf(2, 4, 3, 2, 3, 4, 5, 4, 4, 4),
            intArrayOf(1, 1, 1, 1, 1, 1),
            intArrayOf(2, 3)
        )
        val inputK = intArrayOf(3, 2, 1, 1, 3, 4, 2)
        for (i in inputK.indices) {
            val result = topKFrequent(inputs[i], inputK[i])
            print(i + 1)
            println(".\tInput: (" + Arrays.toString(inputs[i]) + ", " + inputK[i] + ")")
            println(
                """
	Top ${inputK[i]} frequent elements: $result"""
            )
            println(String(CharArray(100)).replace('\u0000', '-'))
        }
    }
}