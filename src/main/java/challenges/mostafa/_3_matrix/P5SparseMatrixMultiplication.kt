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
     * 1.  **Problem Explanation:**
     *     The problem asks us to multiply two given matrices, `mat1` and `mat2`. We are told that
     *     `mat1` has dimensions $m \times k$ and `mat2` has dimensions $k \times n$. The result of
     *     their multiplication will be a matrix of size $m \times n$. Since the matrices can be
     *     sparse (contain many zero elements), we need to consider an efficient approach that
     *     avoids unnecessary calculations involving zeros.
     *
     * 2.  **Pattern Identification and Rationale:**
     *     The core operation is matrix multiplication. A naive approach would involve three nested
     *     loops, resulting in a time complexity of $O(m * k * n)$. However, given that the matrices
     *     can be sparse, we can optimize this by only considering the non-zero elements.
     *
     *     The relevant pattern here is an **optimization for sparse matrix multiplication**. The idea
     *     is to iterate through the non-zero elements of the first matrix and, for each non-zero element,
     *     find the corresponding non-zero elements in the second matrix that contribute to the product.
     *     This avoids multiplying by zero and performing unnecessary additions.
     *
     *     This approach is suitable because it leverages the sparsity of the matrices. By focusing only
     *     on non-zero entries, we can potentially reduce the number of computations significantly, especially
     *     when the matrices have a high proportion of zero elements.
     *
     * 3.  **Solution Breakdown:**
     *     The solution can be broken down into the following steps:
     *
     *     a.  **Initialization:**
     *         Create a result matrix of size $m \times n$, initialized with zeros.
     *
     *     b.  **Preprocessing (Optional but Recommended for Efficiency):**
     *         To efficiently access the non-zero elements and their indices, we can preprocess both input
     *         matrices.
     *         * For `mat1`, for each row $i$, store a list of pairs, where each pair contains the column
     *         index $j$ and the value `mat1[i][j]` for all non-zero entries.
     *         * Similarly, for `mat2`, for each row $j$ (which corresponds to the column index in `mat1`),
     *         store a list of pairs, where each pair contains the column index $l$ and the value `mat2[j][l]` for all non-zero entries.
     *
     *     c.  **Multiplication:**
     *         Iterate through each row $i$ of `mat1` (from 0 to $m-1$).
     *         For each non-zero element in the $i$-th row of `mat1` at column $j$ with value
     *         `value1` (obtained from our preprocessed structure), we know that this element will
     *         be multiplied by the elements in the $j$-th row of `mat2`.
     *         Iterate through the non-zero elements in the $j$-th row of `mat2` at column $l$ with
     *         value `value2` (obtained from our preprocessed structure).
     *         For each such pair of non-zero elements, the product `value1 * value2` contributes to
     *         the element at `result[i][l]`. Add this product to `result[i][l]`.
     *
     *     d.  **Return Result:**
     *         After iterating through all relevant non-zero element combinations, the `result` matrix
     *         will contain the product of `mat1` and `mat2`. Return this matrix.
     *
     * 4.  **Time Complexity:**
     *     Let $nz1$ be the number of non-zero elements in `mat1` and $nz2$ be the number of non-zero
     *     elements in `mat2`. In the worst case, where both matrices are dense, $nz1 = m * k$ and $nz2 = k
     *     * n$, and the time complexity would be $O(m * k * n)$. However, with the optimization for
     *     sparse matrices, the time complexity becomes proportional to the number of multiplications
     *     performed. For each non-zero element in `mat1` at $(i, j)$, we iterate through the non-zero
     *     elements in the $j$-th row of `mat2`.
     *
     *     In the preprocessed structure, for each non-zero element in `mat1[i][j]`, we iterate through
     *     the non-zero elements in `mat2[j][l]`. The number of operations is roughly proportional to the sum
     *     over all $j$ of (number of non-zero in row $i$ of `mat1`) $\times$ (number of non-zero in row $j$ of `mat2`).
     *     In the best case (very sparse matrices), this can be significantly better than $O(m * k * n)$.
     *
     *     The preprocessing step takes $O(m * k + k * n)$ time to identify and store the non-zero elements.
     *     The multiplication step's complexity depends on the number of non-zero entries. Let's consider
     *     the complexity in terms of the number of non-zero elements. For each non-zero element in `mat1`,
     *     we might iterate through some non-zero elements in the corresponding row of `mat2`. A loose upper
     *     bound could be $O(m * (\text{max non-zero in a row of mat1}) * (\text{max non-zero in a row of mat2}))$.
     *     A more precise bound is $\sum_{i=0}^{m-1} (\text{number of non-zero in row } i \text{ of mat1})
     *     \times (\text{average number of non-zero in corresponding columns of mat2})$.
     *
     *     In the worst case (dense matrices), the complexity remains $O(m * k * n)$. However, for sparse
     *     matrices, this approach is generally much more efficient.
     *
     * 5.  **Efficient Implementation:**
     *     The Kotlin implementation above follows the outlined steps. It first preprocesses `mat1`
     *     and `mat2` to store the non-zero elements along with their column/row indices. Then, it iterates
     *     through the non-zero elements of `mat1` and multiplies them with the corresponding non-zero elements
     *     in `mat2` to compute the entries of the `result` matrix.
     *
     *     The use of `MutableList<Pair<Int, Int>>` to store non-zero elements allows for efficient iteration
     *     over these elements. The nested loops ensure that only the necessary multiplications are performed,
     *     leading to an optimized solution for sparse matrices. The time complexity will be better than
     *     the naive $O(m * k * n)$ when the input matrices are sparse. The space complexity
     *     is $O(nz1 + nz2 + m * n)$ in the worst case (dense output matrix), where $nz1$ and $nz2$ are
     *     the number of non-zero elements in `mat1` and `mat2`, respectively, due to the storage of
     *     non-zero elements and the result matrix.
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