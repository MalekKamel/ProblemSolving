package challenges.educative_grokking_coding_interview.k_way_merge._2

import java.util.*

/**
You are given an m number of sorted lists in ascending order and an integer, k, find the kth
smallest number among all the given lists.
Although there can be repeating values in the lists, each element is considered unique and, therefore,
contributes to calculating the kth
smallest element.

If k is greater than the total number of elements in the input lists, return the greatest element from all the
lists and if there are no elements in the input lists, return 0.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/qAjl2YpWQwD
 */
internal object KSmallestNumber {
    private fun kSmallestNumber(lists: List<List<Int?>>, k: Int): Int {
        // storing the length of lists to use it in a loop later
        val listLength = lists.size
        // declaring a min-heap to keep track of smallest elements
        val kthSmallest = PriorityQueue { a: IntArray, b: IntArray ->
            a[0] - b[0]
        }
        for (index in 0 until listLength) {
            // if there are no elements in the input lists, continue
            if (lists[index].isEmpty()) {
                continue
            } else {
                // placing the first element of each list in the min-heap
                kthSmallest.offer(intArrayOf(lists[index][0]!!, index, 0))
            }
        }

        // set a counter to match if our kth element
        // equals to that counter, return that number
        var numbersChecked = 0
        var smallestNumber = 0
        while (!kthSmallest.isEmpty()) {  // iterating over the elements pushed in our min-heap
            // get the smallest number from top of heap and its corresponding list and index
            val smallest = kthSmallest.poll()
            smallestNumber = smallest[0]
            val listIndex = smallest[1]
            val numIndex = smallest[2]
            numbersChecked++
            if (numbersChecked == k) {
                break
            }

            // if there are more elements in list of the top element,
            // add the next element of that list to the min-heap
            if (numIndex + 1 < lists[smallest[1]].size) {
                kthSmallest.offer(intArrayOf(lists[listIndex][numIndex + 1]!!, listIndex, numIndex + 1))
            }
        }

        // return the Kth number found in input lists
        return smallestNumber
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val lists = listOf(
            listOf(
                listOf(2, 6, 8),
                listOf(3, 6, 10),
                listOf(5, 8, 11)
            ),
            listOf(
                listOf(1, 2, 3),
                listOf(4, 5),
                listOf(6, 7, 8, 15),
                listOf(10, 11, 12, 13),
                listOf(5, 10)
            ),
            listOf(
                listOf(),
                listOf(),
                listOf()
            ),
            listOf(
                listOf(1, 1, 3, 8),
                listOf(5, 5, 7, 9),
                listOf(3, 5, 8, 12)
            ),
            listOf(
                listOf(5, 8, 9, 17),
                listOf(),
                listOf(8, 17, 23, 24)
            )
        )
        val k = intArrayOf(5, 50, 7, 4, 8)

        // loop to execute till the length of list k
        for (i in k.indices) {
            println(
                "${i + 1}.\t Input lists: " + lists[i] +
                        "\n\t K = " + k[i] +
                        "\n\t " + k[i] + "th smallest number from the given lists is: " +
                        kSmallestNumber(lists[i], k[i])
            )
            println(String(CharArray(100)).replace('\u0000', '-'))
        }
    }
}