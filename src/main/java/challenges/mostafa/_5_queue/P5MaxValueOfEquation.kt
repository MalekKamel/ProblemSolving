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

    /**
     *  1. Problem Explanation
     *  The problem asks us to find the maximum value of the equation yi + yj + |xi - xj|
     *  for a pair of points (xi, yi) and (xj, yj) in a given array of 2D points.
     *  The array of points is sorted by their x-values (xi < xj for i < j).
     *  We are also given an integer k, and the pair of points must satisfy the condition |xi - xj| <= k.

     *  2. Pattern Identification and Rationale
     *  Since we need to find the maximum value among pairs of points that satisfy a certain condition,
     *  a brute-force approach would involve checking all possible pairs (i, j) where i < j and |xi - xj| <= k,
     *  and then calculating the equation's value. However, with up to 10^5 points, this O(n^2) approach
     *  would likely be too slow.

     *  We can observe that for a fixed j, we are looking for an i < j such that xj - xi <= k (since xi < xj),
     *  which means xi >= xj - k. We want to maximize yi + yj + (xj - xi) because xj > xi.
     *  Rearranging this, we want to maximize (yi - xi) + yj + xj. For a fixed j, yj + xj is constant.
     *  Therefore, we need to find an i < j within the x-distance constraint that maximizes yi - xi.

     *  This suggests that we can iterate through the points with index j, and for each j, we need to efficiently
     *  find the maximum value of yi - xi among all i < j such that xj - xi <= k.
     *  A priority queue (max heap) can be used to maintain the maximum values of yi - xi encountered so far
     *  within the relevant window.

     *  3. Solution Breakdown
     *  a. Initialize a variable `maxValue` to negative infinity to store the maximum value found so far.
     *  b. Initialize a priority queue `pq` to store pairs of (yi - xi, xi). The priority queue will be ordered
     *     based on the first element (yi - xi) in descending order.
     *  c. Iterate through the `points` array with index `j` from 0 to `points.size - 1`.
     *  d. For each point `points[j] = [xj, yj]`:
     *     i. Remove points from the priority queue whose x-coordinate `xi` is less than `xj - k`. This ensures
     *        that we only consider points that satisfy the condition |xi - xj| <= k (since xi < xj, this simplifies to xj - xi <= k).
     *     ii. If the priority queue is not empty, it means there is at least one point `points[i]` (where i < j)
     *         that satisfies the x-distance constraint. Get the maximum value of `yi - xi` from the top of the priority queue.
     *     iii. Calculate the current value of the equation: `(yi - xi) + yj + xj`.
     *     iv. Update `maxValue` with the maximum of its current value and the calculated value.
     *     v. Add the current point's `(yj - xj, xj)` to the priority queue. This makes the current point available
     *        for future iterations where it might serve as the 'i' in the pair.
     *  e. After iterating through all the points, return `maxValue`.

     *  4. Time Complexity
     *  - Iterating through the points takes O(n) time.
     *  - For each point, we perform at most one addition to and one removal from the priority queue.
     *  - Priority queue operations (insertion and deletion of the maximum element) take O(log n) time in the worst case.
     *  - Therefore, the overall time complexity of this solution is O(n log n).
     */
    private  fun findMaxValueOfEquation(points: Array<IntArray>, k: Int): Int {
        val pq = java.util.PriorityQueue<Pair<Int, Int>> { a, b -> b.first - a.first }
        var maxValue = Int.MIN_VALUE

        for (j in points.indices) {
            val xj = points[j][0]
            val yj = points[j][1]

            // Remove points from the priority queue that are outside the k-distance window
            while (pq.isNotEmpty() && xj - pq.peek().second > k) {
                pq.poll()
            }

            // If the priority queue is not empty, calculate the current equation value
            if (pq.isNotEmpty()) {
                val yiMinusXi = pq.peek().first
                maxValue = maxOf(maxValue, yiMinusXi + yj + xj)
            }

            // Add the current point's (yj - xj, xj) to the priority queue
            pq.offer(yj - xj to xj)
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