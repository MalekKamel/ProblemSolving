package challenges.cracking_coding_interview.sorting_and_searching.sorted_matrix_search

import challenges.util.AssortedMethods.printMatrix

object QuestionB {
    private fun partitionAndSearch(
        matrix: Array<IntArray>,
        origin: Coordinate,
        dest: Coordinate,
        pivot: Coordinate,
        x: Int
    ): Coordinate? {
        val lowerLeftOrigin = Coordinate(pivot.row, origin.column)
        val lowerLeftDest = Coordinate(dest.row, pivot.column - 1)
        val upperRightOrigin = Coordinate(origin.row, pivot.column)
        val upperRightDest = Coordinate(pivot.row - 1, dest.column)
        return findElement(
            matrix,
            lowerLeftOrigin,
            lowerLeftDest,
            x
        )
            ?: return findElement(
                matrix,
                upperRightOrigin,
                upperRightDest,
                x
            )
    }

    private fun findElement(matrix: Array<IntArray>, origin: Coordinate, dest: Coordinate, x: Int): Coordinate? {
        if (!origin.inbounds(matrix) || !dest.inbounds(matrix)) {
            return null
        }
        if (matrix[origin.row][origin.column] == x) {
            return origin
        } else if (!origin.isBefore(dest)) {
            return null
        }

        /* Set start to start of diagonal and end to the end of the diagonal. Since
		 * the grid may not be square, the end of the diagonal may not equal dest.
		 */
        val start = origin.clone() as Coordinate
        val diagDist = Math.min(dest.row - origin.row, dest.column - origin.column)
        val end = Coordinate(start.row + diagDist, start.column + diagDist)
        val p = Coordinate(0, 0)

        /* Do binary search on the diagonal, looking for the first element greater than x */while (start.isBefore(end)) {
            p.setToAverage(start, end)
            if (x > matrix[p.row][p.column]) {
                start.row = p.row + 1
                start.column = p.column + 1
            } else {
                end.row = p.row - 1
                end.column = p.column - 1
            }
        }

        /* Split the grid into quadrants. Search the bottom left and the top right. */return partitionAndSearch(
            matrix,
            origin,
            dest,
            start,
            x
        )
    }

    fun findElement(matrix: Array<IntArray>, x: Int): Coordinate? {
        val origin = Coordinate(0, 0)
        val dest = Coordinate(matrix.size - 1, matrix[0].size - 1)
        return findElement(matrix, origin, dest, x)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val matrix = arrayOf(
            intArrayOf(15, 30, 50, 70, 73),
            intArrayOf(35, 40, 100, 102, 120),
            intArrayOf(36, 42, 105, 110, 125),
            intArrayOf(46, 51, 106, 111, 130),
            intArrayOf(48, 55, 109, 140, 150)
        )
        printMatrix(matrix)
        val m = matrix.size
        val n = matrix[0].size
        var count = 0
        val littleOverTheMax = matrix[m - 1][n - 1] + 10
        for (i in 0 until littleOverTheMax) {
            val c = findElement(matrix, i)
            if (c != null) {
                println(i.toString() + ": (" + c.row + ", " + c.column + ")")
                count++
            }
        }
        println("Found $count unique elements.")
    }
}