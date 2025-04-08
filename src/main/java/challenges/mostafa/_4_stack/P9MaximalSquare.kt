package challenges.mostafa._4_stack

/**
Given an m x n binary matrix filled with 0's and 1's, find the largest square containing
only 1's and return its area.

Example 1:
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4

Example 2:
Input: matrix = [["0","1"],["1","0"]]
Output: 1
Example 3:

Input: matrix = [["0"]]
Output: 0


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is '0' or '1'.

https://leetcode.com/problems/maximal-square/description/
 */

internal object P9MaximalSquare {

    private fun maximalSquare(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return 0

        val rows = matrix.size
        val cols = matrix[0].size
        var maxArea = 0

        // Create a histogram-like array to store the height of each column
        val heights = IntArray(cols) { 0 }

        for (r in 0 until rows) {
            for (c in 0 until cols) {
                heights[c] = if (matrix[r][c] == '1') heights[c] + 1 else 0
            }

            maxArea = maxOf(maxArea, largestSquareInHistogram(heights))
        }

        return maxArea
    }

    private fun largestSquareInHistogram(heights: IntArray): Int {
        // Initialize an empty ArrayDeque to serve as our stack. This stack will store
        // indices of the `heights` array. We use a deque because it allows efficient
        // addition and removal from both ends.
        val stack = ArrayDeque<Int>()

        // Initialize a variable to keep track of the maximum rectangular area found so far.
        var maxArea = 0

        // Get the number of bars in the histogram.
        val n = heights.size

        // Iterate through the `heights` array, from the first bar to one position beyond the last.
        // The extra iteration (up to `n`) is a clever way to handle any remaining bars in the stack
        // after the initial loop finishes. It ensures that all potential rectangles are considered.
        // Why is this extra iteration necessary?
        //
        //The core idea of the stack-based approach is to calculate the area of a rectangle when we
        // encounter a bar that is shorter than the bar at the top of the stack. This shorter bar
        // acts as a right boundary for the rectangle formed by the taller bar.
        //
        //However, consider a scenario where the histogram's heights are non-decreasing (e.g., [1, 2, 3, 4, 5]).
        // In this case, when the loop reaches the end of the actual bars (i.e., i goes from 0 to n-1),
        // the condition heights[i] <= heights[stack.peekLast()] inside the while loop might never
        // become true for the bars remaining in the stack. These bars in the stack still represent
        // potential rectangles that extend to the end of the histogram.
        //
        //The extra iteration (i == n) serves as a trigger to process any remaining bars in the stack.
        for (i in 0..n) {
            // While the stack is not empty AND (we've reached the end of the array OR the height of the
            // current bar is less than or equal to the height of the bar at the index at the top of the stack):
            // This condition signifies that the current bar is shorter than the bar at the top of the stack,
            // meaning the rectangle formed by the bar at the top of the stack can no longer extend to the right.
            while (stack.isNotEmpty() && (i == n || heights[i] <= heights[stack.last()])) {
                // Pop the index of the top bar from the stack. This bar's height will be the height of the
                // rectangle we are now calculating.
                val height = heights[stack.removeLast()]

                // Calculate the width of the rectangle.
                // If the stack is now empty, it means the popped bar was the shortest so far, and the
                // width extends from the beginning of the histogram (index 0) to the current index `i`.
                val width = if (stack.isEmpty()) {
                    i
                } else {
                    // If the stack is not empty, the width is the distance between the current index `i` and
                    // the index of the bar just below the popped one in the stack (which is now the top).
                    // We subtract 1 because the indices are inclusive.
                    i - stack.last() - 1
                }

                val side = minOf(height, width)
                val squareArea = side * side
                // Calculate the area of the rectangle formed with the popped height and calculated width.
                maxArea = maxOf(maxArea, squareArea)
            }
            // Push the current index `i` onto the stack. This means we are considering this bar as a potential
            // boundary for future rectangles. We store the index because we need to calculate widths later.
            stack.addLast(i)
        }

        // After iterating through all the bars (and the extra conceptual bar at the end), `maxArea`
        // will hold the area of the largest rectangle found.
        return maxArea
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val matrix1 = arrayOf(
            charArrayOf('1', '0', '1', '0', '0'),
            charArrayOf('1', '0', '1', '1', '1'),
            charArrayOf('1', '1', '1', '1', '1'),
            charArrayOf('1', '0', '0', '1', '0')
        )
        println(maximalSquare(matrix1)) // Output: 4

        val matrix2 = arrayOf(
            charArrayOf('0', '1'),
            charArrayOf('1', '0')
        )
        println(maximalSquare(matrix2)) // Output: 1

        val matrix3 = arrayOf(
            charArrayOf('0')
        )
        println(maximalSquare(matrix3)) // Output: 0
    }
}