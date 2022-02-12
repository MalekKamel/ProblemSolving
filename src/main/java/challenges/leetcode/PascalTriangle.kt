package challenges.leetcode

/**
Given an integer numRows, return the first numRows of Pascal's triangle.
In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example 1:

Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
Example 2:

Input: numRows = 1
Output: [[1]]

Constraints:

1 <= numRows <= 30

https://leetcode.com/problems/pascals-triangle/
 */
object PascalTriangle {

    private fun generate(numRows: Int): List<List<Int>> {
        val ans: MutableList<List<Int>> = ArrayList(numRows)
        for (i in 0 until numRows) {
            val row: MutableList<Int> = ArrayList(i + 1)
            while (row.size <= i) row.add(1)
            val mid = i shr 1
            for (j in 1..mid) {
                val value = ans[i - 1][j - 1] + ans[i - 1][j]
                row[j] = value
                row[row.size - j - 1] = value
            }
            ans.add(row)
        }
        return ans
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val rows = generate(5)
        println(rows)
    }
}