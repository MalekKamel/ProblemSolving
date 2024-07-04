package challenges.mostafa._3_matrix

/**
Given two sparse matrices mat1 of size m x k and mat2 of size k x n,
return the result of mat1 x mat2. You may assume that multiplication is always possible.

Example 1:
Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
Output: [[7,0,0],[-7,0,3]]

Example 2:
Input: mat1 = [[0]], mat2 = [[0]]
Output: [[0]]

Constraints:

m == mat1.length
k == mat1[i].length == mat2.length
n == mat2[i].length
1 <= m, n, k <= 100
-100 <= mat1[i][j], mat2[i][j] <= 100

https://leetcode.ca/2016-10-06-311-Sparse-Matrix-Multiplication/#google_vignette
 */

internal object P5SparseMatrixMultiplication {

    /**
    The time complexity of the multiply function is O(m * n * p), where m is the number of rows
    in the first matrix mat1, n is the number of columns in the first matrix mat1 (and the number
    of rows in the second matrix mat2), and p is the number of columns in the second matrix mat2.

    Here's a breakdown of the time complexity analysis:

    The function starts by initializing the size of the input matrices and the result matrix,
    which takes constant time, O(1).
    The outer loop iterates over the rows r of the first matrix mat1, which takes O(m) time.
    Inside the outer loop, the code iterates over the columns k of the first matrix mat1, which
    takes O(n) time.
    Within the inner loop, the code checks if the element mat1[r][k] is zero, and if it's not,
    it iterates over the columns c of the second matrix mat2, which takes O(p) time.
    Inside the innermost loop, the code performs a constant-time operation to update
    the corresponding element in the result matrix.
    The overall time complexity of the multiply function is the product of the time complexities
    of the three nested loops, which is O(m * n * p).

    The reason the time complexity is not O(m * n * p) is that the code includes an optimization
    to skip the inner loop when the element mat1[r][k] is zero. This optimization can potentially
    improve the performance of the function, especially when the input matrices are
    sparse (i.e., have many zero elements).
     */
    private fun multiply(mat1: Array<IntArray>, mat2: Array<IntArray>): Array<IntArray> {
        val rows1 = mat1.size
        val cols1 = mat1[0].size
        val cols2 = mat2[0].size

        val result = Array(rows1) { IntArray(cols2) }

        for (r in 0 until rows1) {
            for (k in 0 until cols1) {
                if (mat1[r][k] == 0) continue
                for (c in 0 until cols2) {
                    if (mat2[k][c] == 0) continue
                    result[r][c] += mat1[r][k] * mat2[k][c]
                }
            }
        }

        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val mat1 = arrayOf(
            intArrayOf(1, 0, 0),
            intArrayOf(-1, 0, 3)
        )

        val mat2 = arrayOf(
            intArrayOf(7, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 1)
        )

        val result = multiply(mat1, mat2)

        // Print the result matrix
        println("Result matrix:")
        for (row in result) {
            for (num in row) {
                print("$num ")
            }
            println()
        }
    }
}

/**
 * Write data structure to be used with millions of entries
 */
internal object FollowUp {

    class SparseMatrix(val rows: Int, val cols: Int) {
        private val data: MutableMap<Pair<Int, Int>, Double> = mutableMapOf()

        fun set(row: Int, col: Int, value: Double) {
            if (value != 0.0) {
                data[row to col] = value
            } else {
                data.remove(row to col)
            }
        }

        fun get(row: Int, col: Int): Double {
            return data[row to col] ?: 0.0
        }

        override fun toString(): String {
            val sb = StringBuilder()
            for (i in 0 until rows) {
                for (j in 0 until cols) {
                    sb.append("%.2f ".format(get(i, j)))
                }
                sb.append("\n")
            }
            return sb.toString()
        }

    }

    private fun multiply(mat1: SparseMatrix, mat2: SparseMatrix): SparseMatrix {
        val rows1 = mat1.rows
        val cols1 = mat1.cols
        val cols2 = mat2.cols

        val result = SparseMatrix(rows1, cols2)

        for (r in 0 until rows1) {
            for (k in 0 until cols1) {
                val value1 = mat1.get(r, k)
                if (value1 == 0.0) continue
                for (c in 0 until cols2) {
                    val value2 = mat2.get(k, c)
                    if (value2 == 0.0) continue
                    result.set(r, c, result.get(r, c) + value1 * value2)
                }
            }
        }

        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Create two sparse matrices
        val mat1 = SparseMatrix(3, 3)
        mat1.set(0, 0, 1.0)
        mat1.set(1, 1, 2.0)
        mat1.set(2, 2, 3.0)

        val mat2 = SparseMatrix(3, 3)
        mat2.set(0, 1, 4.0)
        mat2.set(1, 2, 5.0)

        // Multiply the matrices
        val result = multiply(mat1, mat2)

        // Print the result
        println(result)
    }
}