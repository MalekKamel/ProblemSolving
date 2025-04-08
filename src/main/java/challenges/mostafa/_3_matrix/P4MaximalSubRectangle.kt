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


    That line, if (currSum < 0) currSum = 0;, is a crucial part of Kadane's algorithm, which is being
    applied here to find the maximum subarray sum within each vertical strip defined by c1 and c2.
    Let's break down why it's there:

    The Problem:

    Within the inner loop (iterating through rows r), currSum is tracking the sum of the current contiguous
    subarray of the colSum array. The goal is to find the maximum possible sum of any contiguous subarray
    within that colSum.

    Why Reset currSum to 0?

    Imagine you're iterating through the elements of colSum and calculating currSum. If at any point
    currSum becomes negative, it means that including this negative sum in any future contiguous subarray
    will always decrease the overall sum.

    Consider this scenario within colSum: [1, 2, -5, 4, 5]

    currSum starts at 0.
    currSum becomes 1.
    currSum becomes 1 + 2 = 3.
    currSum becomes 3 + (-5) = -2.

    At this point, currSum is -2. If we were to continue adding the next element (4) to this -2,
    the sum would be 2. However, we could have started a new contiguous subarray at 4, which would
    have a larger sum initially.

    The Logic of Kadane's Algorithm:

    The core idea is that if the current contiguous subarray sum is negative, it's better to discard
    it and start a new contiguous subarray from the next element. A negative currSum will never contribute
    to a larger maximum sum in the future. By resetting currSum to 0, we are effectively saying,
    "This current subarray is dragging the sum down; let's start fresh from the next element."

    In the Context of the Maximal Rectangle Sum:

    The outer loops (c1 and c2) define vertical strips within the matrix. For each strip, colSum
    stores the sum of the elements in each row within that strip. The inner loop then applies
    Kadane's algorithm to this colSum array to find the maximum possible sum of a contiguous
    vertical segment within that strip. By iterating through all possible vertical strips and
    finding the maximum contiguous sum within each, the algorithm ultimately finds the maximal
    rectangle sum.

    Therefore, the line if (currSum < 0) currSum = 0; is essential for Kadane's algorithm to
    correctly identify the maximum contiguous subarray sum within each colSum, which is a key step
    in finding the overall maximal rectangle sum.
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