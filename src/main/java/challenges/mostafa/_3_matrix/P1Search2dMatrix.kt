package challenges.mostafa._3_matrix

/**
Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.

Example 1:

Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]],
target = 5
Output: true

Example 2:

Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]],
target = 20
Output: false

Constraints:

m == matrix.length
n == matrix[i].length
1 <= n, m <= 300
-109 <= matrix[i][j] <= 109
All the integers in each row are sorted in ascending order.
All the integers in each column are sorted in ascending order.
-109 <= target <= 109

https://leetcode.com/problems/search-a-2d-matrix-ii/description/
 */

internal object P1Search2dMatrix {

    private fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val rows = matrix.size
        val cols = matrix[0].size
        var r = 0
        var c = cols - 1

        while (c >= 0 && r < rows) {
            val value = matrix[r][c]

            if (target == value) return true

            if (target > value) r++
            else c--
        }

        return false
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val matrix = arrayOf(
            intArrayOf(1, 4, 7, 11, 15),
            intArrayOf(2, 5, 8, 12, 19),
            intArrayOf(3, 6, 9, 16, 22),
            intArrayOf(10, 13, 14, 17, 24),
            intArrayOf(18, 21, 23, 26, 30)
        )

        println(searchMatrix(matrix, 5))  // true
        println(searchMatrix(matrix, 20)) // false
        println(searchMatrix(matrix, 6))  // true
        println(searchMatrix(matrix, 0))  // false
        println(searchMatrix(matrix, 30)) // true
    }
}