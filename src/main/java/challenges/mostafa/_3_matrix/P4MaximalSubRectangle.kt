package challenges.mostafa._3_matrix

/**
Given a 2-dimensional array of positive and negative integers, find the sub-rectangle with the largest
sum. The sum of a rectangle is the sum of all the elements in that rectangle. In this problem
the subrectangle with the largest sum is referred to as the maximal sub-rectangle.
A sub-rectangle is any contiguous sub-array of size 1 × 1 or greater located within the whole array.

As an example, the maximal sub-rectangle of the array:
0 −2 −7 0
9 2 −6 2
−4 1 −4 1
−1 8 0 −2

is in the lower-left-hand corner:

9 2
−4 1
−1 8
and has the sum of 15.

Input

The input consists of an N × N array of integers.
The input begins with a single positive integer N on a line by itself indicating the size of the square
two dimensional array. This is followed by N2
integers separated by white-space (newlines and spaces).
These N2
integers make up the array in row-major order (i.e., all numbers on the first row, left-to-right,
then all numbers on the second row, left-to-right, etc.). N may be as large as 100. The numbers in the
array will be in the range [−127, 127].

Output
The output is the sum of the maximal sub-rectangle.

Sample Input
4
0 -2 -7 0 9 2 -6 2
-4 1 -4 1 -1
8 0 -2

Sample Output
15

https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=44#google_vignette
 */

internal object P4MaximalSubRectangle {

    /**
    Time Complexity

    The time complexity of the given code, maximalRectangleSum, is O(m * n^2), where m is the number
    of rows and n is the number of columns in the input matrix.

    Here's a breakdown of the time complexity analysis:

    The outer loop iterates over the columns c1 from 0 to n-1, which takes O(n) time.
    Inside the outer loop, the code initializes a colSum array of size m, which takes O(m) time.
    The inner loop iterates over the columns c2 from c1 to n-1, which takes O(n-c1) time, which
    can be simplified to O(n) time.
    Inside the inner loop, the code iterates over the rows r from 0 to m-1, which takes O(m) time.
    Within the row iteration, the code performs constant-time operations, such as updating colSum,
    calculating currSum, and updating maxSum.
    Therefore, the overall time complexity of the maximalRectangleSum function is the product of
    the time complexities of the outer and inner loops, which is O(n * (n * m)) = O(m * n^2).
     */
    private fun maximalRectangleSum(matrix: Array<IntArray>): Int {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return 0

        val rows = matrix.size
        val cols = matrix[0].size
        var maxSum = Int.MIN_VALUE

        for (c1 in 0 until cols) {
            val colSum = IntArray(rows) { 0 }
            for (c2 in c1 until cols) {
                var currSum = 0
                for (r in 0 until rows) {
                    colSum[r] += matrix[r][c2]
                    currSum += colSum[r]
                    maxSum = maxOf(maxSum, currSum)
                    if (currSum < 0) currSum = 0
                }
            }
        }

        return maxSum
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val matrix = arrayOf(
            intArrayOf(0, -2, -7, 0),
            intArrayOf(9, 2, -6, 2),
            intArrayOf(-4, 1, -4, 1),
            intArrayOf(-1, 8, 0, -2),
        )

        val matrix2 = arrayOf(
            intArrayOf(5, -4, -4, 4, 4),
            intArrayOf(-4, -4, 4, 4, 5),
            intArrayOf(4, 4, 4, 4, -5),
            intArrayOf(4, 4, 4, 4, -5)
        )
        val maxSum = maximalRectangleSum(matrix)
        println("The maximal sub-rectangle sum is: $maxSum")

        val maxSum2 = maximalRectangleSum(matrix2)
        println("The maximal sub-rectangle sum is: $maxSum2")

        val matrix3 = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4),
            intArrayOf(5, 6),
        )

        val maxSum3 = maximalRectangleSum(matrix3)
        println("The maximal sub-rectangle sum is: $maxSum3")
    }
}