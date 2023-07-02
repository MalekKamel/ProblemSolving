package challenges.educative_grokking_coding_interview.k_way_merge._3

import challenges.util.PrintHyphens

import java.util.Arrays

import java.util.PriorityQueue

import java.util.ArrayList

/**
Given two lists and an integer k, find k
pairs of numbers with the smallest sum so that in each pair, each list contributes one number to the pair.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/3jrDWvArpgn
 */
internal class Pair(var sum: Int, var i: Int, var j: Int)
internal object FindKPairs {
    private fun kSmallestPairs(list1: IntArray, list2: IntArray, k: Int): List<List<Int>> {
        val pairs: MutableList<List<Int>> = ArrayList()
        // storing the length of lists to use it in a loop later
        val listLength = list1.size
        // declaring a min-heap to keep track of the smallest sums
        val minHeap = PriorityQueue { a: Pair, b: Pair -> a.sum - b.sum }
        // iterate over the length of list 1
        for (i in 0 until Math.min(k, listLength)) {
            // computing sum of pairs all elements of list1 with first index
            // of list2 and placing it in the min-heap
            minHeap.add(Pair(list1[i] + list2[0], i, 0))
        }
        var counter = 1
        // iterate over elements of min-heap and only go upto k
        while (!minHeap.isEmpty() && counter <= k) {
            // placing sum of the top element of min-heap
            // and its corresponding pairs in i and j
            val pair = minHeap.poll()
            val i = pair.i
            val j = pair.j
            // add pairs with the smallest sum in the new list
            pairs.add(Arrays.asList(list1[i], list2[j]))
            // increment the index for 2nd list, as we've
            // compared all possible pairs with the 1st index of list2
            val nextElement = j + 1
            // if next element is available for list2 then add it to the heap
            if (list2.size > nextElement) {
                minHeap.add(Pair(list1[i] + list2[nextElement], i, nextElement))
            }
            counter++
        }
        // return the pairs with the smallest sums
        return pairs
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val list1 = arrayOf(
            intArrayOf(2, 8, 9),
            intArrayOf(1, 2, 300),
            intArrayOf(1, 1, 2),
            intArrayOf(4, 6),
            intArrayOf(4, 7, 9),
            intArrayOf(1, 1, 2)
        )
        val list2 = arrayOf(
            intArrayOf(1, 3, 6),
            intArrayOf(1, 11, 20, 35, 300),
            intArrayOf(1, 2, 3),
            intArrayOf(2, 3),
            intArrayOf(4, 7, 9),
            intArrayOf(1)
        )
        val k = intArrayOf(9, 30, 1, 2, 5, 4)
        for (i in k.indices) {
            val result = kSmallestPairs(list1[i], list2[i], k[i])
            print(i + 1)
            println(".\tInput lists: " + Arrays.toString(list1[i]) + ", " + Arrays.toString(list2[i]))
            println("\tK = " + k[i])
            print("\tPairs with smallest sum are: $result")
            println("\n")
            System.out.println(PrintHyphens.repeat("-", 100))
        }
    }
}