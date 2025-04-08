package challenges.mostafa._8_hashtable

import kotlin.math.abs

/**
You are given an array of points in the X-Y plane points where points[i] = [xi, yi].

Return the minimum area of a rectangle formed from these points, with sides parallel to
the X and Y axes. If there is not any such rectangle, return 0.

Example 1:

Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4

Example 2:

Input: points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2

Constraints:

1 <= points.length <= 500
points[i].length == 2
0 <= xi, yi <= 4 * 10^4
All the given points are unique.

https://leetcode.com/problems/minimum-area-rectangle/submissions/1294592502/
 */

internal object P1MinimumAreaRectangle {

    /**
     * 1. Problem Explanation
     * The problem asks us to find the smallest possible area of a rectangle that can be formed using
     * a subset of the given points in a 2D plane. The crucial constraint is that the sides of the rectangle
     * must be parallel to the X and Y axes. If no such rectangle can be formed from the given points, we should return 0.
     *
     * 2. Pattern Identification and Rationale
     * The core idea to solve this problem lies in recognizing that a rectangle with sides parallel
     * to the axes is uniquely defined by two diagonally opposite corner points. If we have two points,
     * say (x1,y1) and (x2,y2), that can be the diagonally opposite corners of a rectangle with axis-parallel sides,
     * then the other two corners must be (x1 ,y2) and (x2,y1). For such a rectangle to exist within
     * the given set of points, both of these other two points must also be present in the input array.
     *
     * Therefore, we can iterate through all possible pairs of points from the input. For each pair,
     * we can check if they can form the diagonally opposite corners of a rectangle. If they
     * can (i.e., their x-coordinates are different and their y-coordinates are different), we then
     * need to check if the other two implied corner points exist in the original set of points.
     *
     * This approach suggests an iterative strategy where we examine combinations of points. We need
     * an efficient way to check if a given point exists in the input set. Using a HashSet to store
     * the input points will allow for fast lookups (average time complexity of O(1)).
     *
     * 3. Solution Breakdown
     * Here's a step-by-step breakdown of the solution:
     *
     * Store points in a Set: Convert the input array of points into a HashSet. This will allow for
     * efficient checking of whether a specific point exists in the input. Each point can be represented
     * as a Pair<Int, Int> in Kotlin.
     *
     * Initialize minimum area: Initialize a variable minArea to a very large value (or Int.MAX_VALUE)
     * to keep track of the minimum area found so far. If no rectangle is found, this value will remain
     * unchanged, and we will return 0 at the end.
     *
     * Iterate through pairs of points: Use nested loops to iterate through all possible pairs of distinct
     * points in the input array. Let the outer loop iterate from index i from 0 to n-2, and the inner
     * loop iterate from index j from i+1 to n-1, where n is the number of points.
     *
     * Check for potential diagonal corners: For each pair of points points[i] and points[j], let them
     * be (x1,y1) and (x2,y2) respectively. If x1 != x2 and y1 != y2, then these two points can potentially
     * be the diagonally opposite corners of a rectangle with axis-parallel sides.
     *
     * Check for the other two corners: If the condition in step 4 is met, we need to check if the other
     * two implied corner points, (x1,y2) and (x2,y1), are present in the HashSet created in step 1.
     *
     * Calculate area: If both (x1,y2) and (x2,y1) exist in the set, then we have found a rectangle.
     * Calculate its area as abs(x1 - x2) * abs(y1 - y2).
     *
     * Update minimum area: Compare the calculated area with the current minArea. If the calculated
     * area is smaller, update minArea to this new value.
     *
     * Return result: After iterating through all pairs of points, if minArea is still equal to its
     * initial large value, it means no rectangle was found, so return 0. Otherwise, return the final minArea.
     *
     * 4. Time Complexity
     * Storing points in a HashSet: This takes O(n) time, where n is the number of points.
     * Iterating through pairs of points: The nested loops iterate through all pairs of points, which
     * takes O(n^2) time.
     * Checking for the other two corners in the HashSet: Each lookup in the HashSet takes O(1) on average.
     * Calculating and updating the minimum area: These are constant time operations.
     * Therefore, the overall time complexity of this solution is dominated by the nested loops, resulting in O(n^2).
     */
    private fun minAreaRect(points: Array<IntArray>): Int {
        val pointSet = points.map { it[0] to it[1] }.toSet()
        var minArea = Int.MAX_VALUE

        for (i in points.indices) {
            for (j in i + 1 until points.size) {
                val (x1, y1) = points[i]
                val (x2, y2) = points[j]

                if (x1 != x2 && y1 != y2) {
                    val p1 = x1 to y2
                    val p2 = x2 to y1

                    if (p1 in pointSet && p2 in pointSet) {
                        val area = abs(x2 - x1) * abs(y2 - y1)
                        minArea = minOf(minArea, area)
                    }
                }
            }
        }

        return if (minArea != Int.MAX_VALUE) minArea else 0
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val points1 = arrayOf(
            intArrayOf(1, 1),
            intArrayOf(1, 3),
            intArrayOf(3, 1),
            intArrayOf(3, 3),
            intArrayOf(2, 2))
        println(minAreaRect(points1))  // Output: 4

        val points2 = arrayOf(
            intArrayOf(1, 1),
            intArrayOf(1, 3),
            intArrayOf(3, 1),
            intArrayOf(3, 3),
            intArrayOf(4, 1),
            intArrayOf(4, 3))
       // println(minAreaRect(points2))  // Output: 2
    }

}