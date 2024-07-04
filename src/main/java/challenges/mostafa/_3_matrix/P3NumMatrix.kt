package challenges.mostafa._3_matrix

/**
Given a 2D matrix matrix, handle multiple queries of the following type:

Calculate the sum of the elements of matrix inside the rectangle defined by its upper left
corner (row1, col1) and lower right corner (row2, col2).

Implement the NumMatrix class:

NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix
inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
You must design an algorithm where sumRegion works on O(1) time complexity.

Example 1:

Input
["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
Output
[null, 8, 11, 12]

Explanation
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
-10^4 <= matrix[i][j] <= 10^4
0 <= row1 <= row2 < m
0 <= col1 <= col2 < n
At most 104 calls will be made to sumRegion.

https://leetcode.com/problems/range-sum-query-2d-immutable/description/
 */

class NumMatrix(matrix: Array<IntArray>) {
    private val prefixSum: Array<IntArray>

    /**
    Here's a breakdown of the formula:

    1. matrix[r - 1][c - 1] represents the value at the current cell (r-1, c-1) in the original matrix.
    2. prefixSum[r - 1][c] represents the sum of all elements in the rectangle from (0, 0) to (r-2, c-1).
    3. prefixSum[r][c - 1] represents the sum of all elements in the rectangle from (0, 0) to (r-1, c-2).
    4. prefixSum[r - 1][c - 1] represents the sum of all elements in the rectangle from (0, 0) to (r-2, c-2).

    By subtracting prefixSum[r - 1][c - 1], we are removing the overlap between the regions
    represented by prefixSum[r - 1][c] and prefixSum[r][c - 1], ensuring that the final
    prefixSum[r][c] value represents the sum of all elements in the rectangle from (0, 0) to (r-1, c-1).

    This subtraction step is crucial for the efficient calculation of the prefix sum, as it
    allows the sum of any rectangular region to be computed in constant time using
    the precomputed prefixSum array.

     Time Complexity
    The time complexity of the provided code is as follows:

    Initialization (Constructor):
    The time complexity of the initialization (constructor) is O(m*n), where m is the number of
    rows and n is the number of columns in the input matrix. This is because the nested loop
    iterates through the entire matrix to compute the prefix sum.
    sumRegion(row1, col1, row2, col2) method:
    The time complexity of the sumRegion method is O(1). This is because the method performs
    a constant number of operations to calculate the sum of the region, regardless of the size
    of the input matrix. The prefix sum array allows for this constant-time operation.
    In summary:

    The time complexity of the initialization (constructor) is O(m*n), where m is the number
    of rows and n is the number of columns in the input matrix.
    The time complexity of the sumRegion method is O(1), as it performs a constant
    number of operations.
    This implementation using the prefix sum technique allows for efficient querying of
    the sum of a rectangular region in the matrix, with a trade-off of requiring additional
    memory to store the prefix sum array.
     */
    init {
        val rows = matrix.size
        val cols = matrix[0].size
        prefixSum = Array(rows + 1) { IntArray(cols + 1) }

        for (r in 1..rows) {
            for (c in 1..cols) {
                prefixSum[r][c] = matrix[r - 1][c - 1] +
                        prefixSum[r - 1][c] +
                        prefixSum[r][c - 1] -
                        prefixSum[r - 1][c - 1]
            }
        }
    }

    fun sumRegion(row1: Int,
                  col1: Int,
                  row2: Int,
                  col2: Int): Int {
        return prefixSum[row2 + 1][col2 + 1] -
                prefixSum[row1][col2 + 1] -
                prefixSum[row2 + 1][col1] +
                prefixSum[row1][col1]
    }
}

fun main() {
    val matrix = arrayOf(
        intArrayOf(3, 0, 1, 4, 2),
        intArrayOf(5, 6, 3, 2, 1),
        intArrayOf(1, 2, 0, 1, 5),
        intArrayOf(4, 1, 0, 1, 7),
        intArrayOf(1, 0, 3, 0, 5)
    )

    val numMatrix = NumMatrix(matrix)

    val sum1 = numMatrix.sumRegion(2, 1, 4, 3)
    println("Sum of region (2, 1) to (4, 3): $sum1")

    val sum2 = numMatrix.sumRegion(1, 1, 2, 2)
    println("Sum of region (1, 1) to (2, 2): $sum2")

    val sum3 = numMatrix.sumRegion(1, 2, 2, 4)
    println("Sum of region (1, 2) to (2, 4): $sum3")
}