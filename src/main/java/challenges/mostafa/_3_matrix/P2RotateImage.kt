package challenges.mostafa._3_matrix

/**
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
DO NOT allocate another 2D matrix and do the rotation.



Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]

Example 2:
Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]


Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000

https://leetcode.com/problems/rotate-image/description/
 */

internal object P2RotateImage {

    private fun rotate(matrix: Array<IntArray>) {
        transpose(matrix)
        reflect(matrix)
    }

    private fun transpose(matrix: Array<IntArray>) {
        val n = matrix.size
        for (r in 0 until n) {
            for (c in r until n) {
                val temp = matrix[r][c]
                matrix[r][c] = matrix[c][r]
                matrix[c][r] = temp
            }
        }
    }

    private fun reflect(matrix: Array<IntArray>) {
        val n = matrix.size
        for (r in 0 until n) {
            for (c in 0 until n / 2) {
                val temp = matrix[r][c]
                matrix[r][c] = matrix[r][n - 1 - c]
                matrix[r][n - 1 - c] = temp
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val matrix = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        )

        println("Original matrix:")
        printMatrix(matrix)

        rotate(matrix)

        println("Rotated matrix:")
        printMatrix(matrix)
    }
}