package challenges.mostafa._4_stack

import java.util.Stack

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
        val stack = Stack<Int>()
        var maxArea = 0

        for ((i, height) in heights.withIndex()) {
            while (stack.isNotEmpty() && heights[stack.peek()] > height) {
                val lastHeight = heights[stack.pop()]
                val width = if (stack.isEmpty()) i else i - stack.peek() - 1
                val side = minOf(lastHeight, width)
                val squareArea = side * side
                maxArea = maxOf(maxArea, squareArea)
            }
            stack.push(i)
        }

        while (stack.isNotEmpty()) {
            val lastHeight = heights[stack.pop()]
            val width = if (stack.isEmpty()) heights.size else heights.size - stack.peek() - 1
            val side = minOf(lastHeight, width)
            val squareArea = side * side
            maxArea = maxOf(maxArea, squareArea)
        }

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