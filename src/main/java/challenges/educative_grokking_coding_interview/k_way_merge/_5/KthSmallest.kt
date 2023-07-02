package challenges.educative_grokking_coding_interview.k_way_merge._5

import java.util.*

/**
Find the kth smallest element in an (n×n)
matrix, where each row and column of the matrix is sorted in ascending order.

Although there can be repeating values in the matrix, each element is considered unique and, therefore,
contributes to calculating the kth smallest element.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/mEoD8KqPyr3
 */
internal object KthSmallest {
    private fun kthSmallestElement(matrix: Array<IntArray>, k: Int): Int {
        // storing the number of rows in the matrix to use it in later
        val rowCount = matrix.size
        // declaring a min-heap to keep track of smallest elements
        val minHeap = PriorityQueue { a: IntArray, b: IntArray ->
            a[0] - b[0]
        }
        for (i in 0 until rowCount) {
            // pushing the first element of each row in the min-heap
            // The offer() method pushes an element into an existing heap
            // in such a way that the heap property is maintained.
            minHeap.offer(intArrayOf(matrix[i][0], i, 0))
        }
        var numbersChecked = 0
        var smallestElement = 0
        // iterating over the elements pushed in our minHeap
        while (!minHeap.isEmpty()) {
            // get the smallest number from top of heap and its corresponding row and column
            val curr = minHeap.poll()
            smallestElement = curr[0]
            val rowIndex = curr[1]
            val colIndex = curr[2]
            numbersChecked++
            // when numbersChecked equals k, we'll return smallestElement
            if (numbersChecked == k) {
                break
            }
            // if the current element has a next element in its row,
            // add the next element of that row to the minHeap
            if (colIndex + 1 < matrix[rowIndex].size) {
                minHeap.offer(intArrayOf(matrix[rowIndex][colIndex + 1], rowIndex, colIndex + 1))
            }
        }
        // return the Kth smallest number found in the matrix
        return smallestElement
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val matrix = arrayOf(
            arrayOf(intArrayOf(2, 6, 8), intArrayOf(3, 7, 10), intArrayOf(5, 8, 11)),
            arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)),
            arrayOf(intArrayOf(5)),
            arrayOf(
                intArrayOf(2, 5, 7, 9, 10),
                intArrayOf(4, 6, 8, 12, 14),
                intArrayOf(11, 13, 16, 18, 20),
                intArrayOf(15, 17, 21, 24, 26),
                intArrayOf(19, 22, 23, 25, 28)
            ),
            arrayOf(
                intArrayOf(3, 5, 7, 9, 11, 13),
                intArrayOf(6, 8, 10, 12, 14, 16),
                intArrayOf(15, 17, 19, 21, 23, 25),
                intArrayOf(18, 20, 22, 24, 26, 28),
                intArrayOf(27, 29, 31, 33, 35, 37),
                intArrayOf(30, 32, 34, 36, 38, 40)
            )
        )
        val k = intArrayOf(3, 4, 1, 10, 15)
        for (i in k.indices) {
            print(i + 1)
            println(".\tInput matrix: " + Arrays.deepToString(matrix[i]))
            println("\tK =  " + k[i])
            println("\tKth smallest number in the matrix is: " + kthSmallestElement(matrix[i], k[i]))
            println(String(CharArray(100)).replace('\u0000', '-'))
        }
    }
}