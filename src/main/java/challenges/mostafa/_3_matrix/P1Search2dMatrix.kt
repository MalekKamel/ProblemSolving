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

    /**
    The time complexity of the given code is O(m + n), where m is the number of rows and n is
    the number of columns in the matrix.

    Here's how we can arrive at this time complexity:

    The code starts by initializing the row and column indices to the top-right corner of the matrix.
    It then enters a while loop that continues as long as the row index is less than the number of
    rows and the column index is greater than or equal to 0.
    Inside the loop, the code checks if the value at the current position matches the target value.
    If so, it returns true.
    If the target value is greater than the current value, the row index is incremented.
    If the target value is less than the current value, the column index is decremented.
    This process continues until either the target value is found (returning true) or the loop
    terminates (returning false).
    The maximum number of steps the loop can take is the sum of the number of rows and the number
    of columns, as in the worst case, the algorithm will have to traverse the entire matrix
    diagonally before determining that the target value is not present. This results in a time
    complexity of O(m + n).

    The space complexity of this solution is O(1), as it uses a constant amount of additional
    space to store the row and column indices.
     */
    private fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val rows = matrix.size
        val cols = matrix[0].size
        var r = 0
        var c = cols - 1

        while (r < rows && c >= 0) {
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