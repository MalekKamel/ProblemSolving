package challenges.mostafa._3_matrix

/**

 */

class NumMatrix(matrix: Array<IntArray>) {
    private val prefixSum: Array<IntArray>

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

    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
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