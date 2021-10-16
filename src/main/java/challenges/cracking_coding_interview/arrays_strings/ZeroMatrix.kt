package challenges.cracking_coding_interview.arrays_strings

import challenges.util.AssortedMethods


object ZeroMatrix {
    private fun setZeros(matrix: Array<IntArray>) {
        var rowHasZero = false
        var colHasZero = false

        // Check if first row has a zero
        for (j in 0 until matrix[0].size) {
            if (matrix[0][j] == 0) {
                rowHasZero = true
                break
            }
        }

        // Check if first column has a zero
        for (i in matrix.indices) {
            if (matrix[i][0] == 0) {
                colHasZero = true
                break
            }
        }

        // Check for zeros in the rest of the array
        for (i in 1 until matrix.size) {
            for (j in 1 until matrix[0].size) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0
                    matrix[0][j] = 0
                }
            }
        }

        // Nullify rows based on values in first column
        for (i in 1 until matrix.size) {
            if (matrix[i][0] == 0) {
                nullifyRow(matrix, i)
            }
        }

        // Nullify columns based on values in first row
        for (j in 1 until matrix[0].size) {
            if (matrix[0][j] == 0) {
                nullifyColumn(matrix, j)
            }
        }

        // Nullify first row
        if (rowHasZero) {
            nullifyRow(matrix, 0)
        }

        // Nullify first column
        if (colHasZero) {
            nullifyColumn(matrix, 0)
        }
    }

    private fun nullifyRow(matrix: Array<IntArray>, row: Int) {
        for (j in 0 until matrix[0].size) {
            matrix[row][j] = 0
        }
    }

    private fun nullifyColumn(matrix: Array<IntArray>, col: Int) {
        for (i in matrix.indices) {
            matrix[i][col] = 0
        }
    }

    fun matricesAreEqual(m1: Array<IntArray>, m2: Array<IntArray>): Boolean {
        if (m1.size != m2.size || m1[0].size != m2[0].size) {
            return false
        }
        for (k in m1.indices) {
            for (j in 0 until m1[0].size) {
                if (m1[k][j] != m2[k][j]) {
                    return false
                }
            }
        }
        return true
    }

    fun cloneMatrix(matrix: Array<IntArray>): Array<IntArray> {
        val c = Array(matrix.size) { IntArray(matrix[0].size) }
        for (i in matrix.indices) {
            for (j in 0 until matrix[0].size) {
                c[i][j] = matrix[i][j]
            }
        }
        return c
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val nrows = 10
        val ncols = 15
        val matrix = AssortedMethods.randomMatrix(nrows, ncols, -10, 10)
        AssortedMethods.printMatrix(matrix)
        setZeros(matrix)
        println()
        AssortedMethods.printMatrix(matrix)
    }
}