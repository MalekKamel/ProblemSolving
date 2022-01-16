package challenges.cracking_coding_interview.sorting_and_searching.sorted_matrix_search

class Coordinate(var row: Int, var column: Int) : Cloneable {
    fun inbounds(matrix: Array<IntArray>): Boolean {
        return row >= 0 && column >= 0 && row < matrix.size && column < matrix[0].size
    }

    fun isBefore(p: Coordinate): Boolean {
        return row <= p.row && column <= p.column
    }

    public override fun clone(): Any {
        return Coordinate(row, column)
    }

    fun moveDownRight() {
        row++
        column++
    }

    fun setToAverage(min: Coordinate, max: Coordinate) {
        row = (min.row + max.row) / 2
        column = (min.column + max.column) / 2
    }
}