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

    /**
    The purpose of this function is to transpose the input matrix. Transposing a matrix means
    interchanging the rows and columns, so that the new rows are the original columns, and
    the new columns are the original rows.

    Here's how the function does this:

    It first gets the size of the square matrix, which is stored in the variable n.
    Then, it uses a nested loop to iterate through the matrix.
    The outer loop iterates over the rows, from 0 to n-1.
    The inner loop iterates over the columns, but only from the current row index r to n-1.
    This is because the matrix is symmetric, so we only need to process the upper
    triangle (the part above the main diagonal).
    For each element at position (r, c), the function swaps it with the element at position (c, r).
    This has the effect of transposing the matrix.

    Time Complexity
    The time complexity of the transpose() function is O(n^2), where n is the size of
    the square matrix.

    The function has two nested loops, where the outer loop iterates over the rows (r) from
    0 to n-1, and the inner loop iterates over the columns (c) from r to n-1. This means that
    the function performs n * (n-r) operations, where r ranges from 0 to n-1. The sum of
    this series is:
    Σ(r=0 to n-1) n * (n-r) = n^2
    Therefore, the overall time complexity of the transpose() function is O(n^2).

    The time complexity of the reflect() function is also O(n^2), where n is the size of
    the square matrix.

    The function has two nested loops, where the outer loop iterates over the rows (r) from
    0 to n-1, and the inner loop iterates over the columns (c) from 0 to n/2-1. This means that
    the function performs n * (n/2) operations, where r ranges from 0 to n-1. The total number
    of operations is:
    n * (n/2) = n^2/2

    Since the time complexity is determined by the dominant term, which is n^2, the overall time
    complexity of the reflect() function is also O(n^2).
     */
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