package challenges.mostafa._4_stack

/**
You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the
columns of the matrix in any order.

Return the area of the largest submatrix within matrix where every element of the submatrix
is 1 after reordering the columns optimally.

Example 1:
Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
Output: 4
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 4.

Example 2:
Input: matrix = [[1,0,1,0,1]]
Output: 3
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 3.
Example 3:

Input: matrix = [[1,1,0],[1,0,1]]
Output: 2
Explanation: Notice that you must rearrange entire columns, and there is no way to make
a submatrix of 1s larger than an area of 2.


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m * n <= 10^5
matrix[i][j] is either 0 or 1.

https://leetcode.com/problems/largest-submatrix-with-rearrangements/description/
 */

internal object P8LargestSubmatrixWithRearrangements {

    private fun largestSubmatrix(matrix: Array<IntArray>): Int {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return 0

        val rows = matrix.size
        val cols = matrix[0].size
        var maxArea = 0

        // Create an array to store the histogram heights for each column
        val heights = IntArray(cols) { 0 }

        for (r in 0 until rows) {
            // Reset the histogram heights
            for (c in 0 until cols) {
                heights[c] = if (matrix[r][c] == 1) heights[c] + 1 else 0
            }

            // Find the largest rectangle in the current histogram
            maxArea = maxOf(maxArea, largestRectangleInHistogram(heights))
        }

        return maxArea
    }

    private fun largestRectangleInHistogram(heights: IntArray): Int {
        val sortedHeights = heights.sortedDescending().toIntArray()
        var maxArea = 0

        // Calculate the maximum area for the current histogram
        for (i in sortedHeights.indices) {
            val height = sortedHeights[i]
            val width = i + 1
            val area = height * width
            maxArea = maxOf(maxArea, area)
        }

        return maxArea
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val matrix = arrayOf(
            intArrayOf(0, 0, 1),
            intArrayOf(1, 1, 1),
            intArrayOf(1, 0, 1)
        )

        val maxArea = largestSubmatrix(matrix)
        println("Largest rectangle area: $maxArea")
    }
}
