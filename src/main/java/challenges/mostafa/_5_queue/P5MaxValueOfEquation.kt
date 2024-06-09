package challenges.mostafa._5_queue

/**
You are given an array points containing the coordinates of points on a 2D plane, sorted by
the x-values, where points[i] = [xi, yi] such that xi < xj for all 1 <= i < j <= points.length.
You are also given an integer k.

Return the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k
and 1 <= i < j <= points.length.

It is guaranteed that there exists at least one pair of points that satisfy
the constraint |xi - xj| <= k.

Example 1:

Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
Output: 4
Explanation: The first two points satisfy the condition |xi - xj| <= 1 and if we calculate
the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points also satisfy the condition
and give a value of 10 + -10 + |5 - 6| = 1.
No other pairs satisfy the condition, so we return the max of 4 and 1.
Example 2:

Input: points = [[0,0],[3,0],[9,2]], k = 3
Output: 3
Explanation: Only the first two points have an absolute difference of 3 or less in the x-values,
and give the value of 0 + 0 + |0 - 3| = 3.

Constraints:

2 <= points.length <= 10^5
points[i].length == 2
-108 <= xi, yi <= 10^8
0 <= k <= 2 * 10^8
xi < xj for all 1 <= i < j <= points.length
xi form a strictly increasing sequence.

https://leetcode.com/problems/max-value-of-equation/description/
 */

internal object P5MaxValueOfEquation {

    private fun findMaxValueOfEquation(points: Array<IntArray>, k: Int): Int {
        val deque = ArrayDeque<Pair<Int, Int>>()
        var maxValue = Int.MIN_VALUE

        for ((x, y) in points) {
            // Remove the points from the front of the deque whose x-value difference with
            // the current point is greater than k. This ensures that we only consider
            // points within the constraint |xi - xj| <= k.
            while (deque.isNotEmpty() && x - deque.first().first > k) {
                deque.removeFirst()
            }

            // Update the maximum value if the deque is not empty
            if (deque.isNotEmpty())
                maxValue = maxOf(maxValue, y - deque.first().first + x + deque.first().second)

            // Remove points from the deque whose y-value minus x-value is less than the current point's y-value minus x-value
            while (deque.isNotEmpty() && deque.last().second - deque.last().first < y - x) {
                deque.removeLast()
            }

            deque.addLast(x to y)
        }

        return maxValue
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val points = arrayOf(
            intArrayOf(1, 3),
            intArrayOf(2, 0),
            intArrayOf(5, 10),
            intArrayOf(6, -10)
        )
        val k = 1
        val maxValue = findMaxValueOfEquation(points, k)
        println("Maximum value of the equation: $maxValue")

        val points2 = arrayOf(
            intArrayOf(0, 0),
            intArrayOf(3, 0),
            intArrayOf(9, 2)
        )
        val k2 = 3
        val maxValue2 = findMaxValueOfEquation(points2, k2)
        println("Maximum value of the equation: $maxValue2")
    }
}