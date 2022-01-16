package challenges.cracking_coding_interview.sorting_and_searching.sorted_matrix_search

import challenges.util.AssortedMethods

object QuestionA {
    private fun findElement(matrix: Array<IntArray>, elem: Int): Boolean {
        var row = 0
        var col = matrix[0].size - 1
        while (row < matrix.size && col >= 0) {
            if (matrix[row][col] == elem) {
                return true
            } else if (matrix[row][col] > elem) {
                col--
            } else {
                row++
            }
        }
        return false
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val M = 10
        val N = 5
        val matrix = Array(M) { IntArray(N) }
        for (i in 0 until M) {
            for (j in 0 until N) {
                matrix[i][j] = 10 * i + j
            }
        }
        AssortedMethods.printMatrix(matrix)
        for (i in 0 until M) {
            for (j in 0 until M) {
                val v = 10 * i + j
                println(v.toString() + ": " + findElement(matrix, v))
            }
        }
    }
}