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
        println(minAreaRect(points2))  // Output: 2
    }

}