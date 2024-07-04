package challenges.mostafa._3_matrix

/**

 */

internal object MultiplyMatrices {

    private fun multiplyMatrices(
        matrixA: Array<DoubleArray>,
        matrixB: Array<DoubleArray>
    ): Array<DoubleArray> {
        // Get the dimensions of the matrices
        val rowsA = matrixA.size
        val rowsB = matrixB.size
        val colsA = matrixA[0].size
        val colsB = matrixB[0].size

        // Check if the matrices can be multiplied
        if (colsA != rowsB) throw IllegalArgumentException("Matrices cannot be multiplied")

        // Create the result matrix
        val result = Array(rowsA) { DoubleArray(colsB) }

        // Perform matrix multiplication
        for (r in 0 until rowsA) {
            for (c in 0 until colsB) {
                for (k in 0 until colsA) {
                    result[r][c] += matrixA[r][k] * matrixB[k][c]
                }
            }
        }

        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val matrixA = arrayOf(
            doubleArrayOf(1.0, 2.0),
            doubleArrayOf(3.0, 4.0),
            doubleArrayOf(5.0, 6.0)
        )

        val matrixB = arrayOf(
            doubleArrayOf(7.0, 8.0),
            doubleArrayOf(9.0, 10.0)
        )

        val result = multiplyMatrices(matrixA, matrixB)

        for (row in result) {
            println(row.contentToString())
        }
    }
}