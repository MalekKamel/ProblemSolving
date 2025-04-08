package challenges.mostafa._5_queue

import java.util.PriorityQueue

/**
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane
and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance
(i.e., √(x1 - x2)^2 + (y1 - y2)^2).

You may return the answer in any order. The answer is guaranteed to be
unique (except for the order that it is in).


Example 1:

Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].

Example 2:
Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.


Constraints:

1 <= k <= points.length <= 10^4
-10^4 <= xi, yi <= 10^4

https://leetcode.com/problems/k-closest-points-to-origin/
 */

internal object P2KClosestPointsToOrigin {

    /**
     *  1. Problem Explanation
     *      The problem asks us to find the k points in a given array that are closest to the
     *      origin (0, 0) in a 2D plane.
     *      The distance is calculated using the Euclidean distance formula. We need to return
     *      these k points as an array.
     *
     *  2. Pattern Identification and Rationale
     *      The problem requires us to find the "k smallest" elements based on their distance to the origin.
     *      This suggests using a min-heap or a max-heap.
     *
     *      Using a Max-Heap:
     *      We can maintain a max-heap of size k. For each point, we calculate its squared Euclidean
     *      distance to the origin.
     *      If the heap has fewer than k elements, we add the current point to the heap.
     *      If the heap is full (has k elements), we compare the distance of the current point with
     *      the distance of the farthest point in the heap (the root of the max-heap).
     *      If the current point is closer (has a smaller squared distance), we remove the farthest
     *      point from the heap and add the current point.
     *      After iterating through all the points, the k points remaining in the max-heap will be
     *      the k closest points.
     *
     *      Why a Max-Heap is Suitable:
     *      A max-heap allows us to efficiently keep track of the k largest distances encountered so
     *      far. By comparing the current point's distance with the largest distance in the heap, we
     *      can decide whether to include the current point in our top k closest points. This avoids
     *      sorting all the distances, which would be less efficient.
     *
     *  3. Solution Breakdown
     *      Step 1: Create a max-heap to store the k closest points encountered so far. The heap will
     *      be ordered based on the squared Euclidean distance from the origin (we use squared distance
     *      to avoid the computationally more expensive square root operation).
     *      Step 2: Iterate through each point in the input array.
     *      Step 3: For each point [x, y], calculate its squared Euclidean distance to the origin:
     *      distance_squared = x^2 + y^2.
     *      Step 4: If the size of the max-heap is less than k, add the current point to the heap.
     *      Step 5: If the size of the max-heap is equal to k, compare the squared distance of the
     *      current point with the squared distance of the point at the root of the max-heap (which
     *      has the largest distance among the k points currently in the heap).
     *      Step 6: If the current point's squared distance is smaller than the squared distance at
     *      the root of the heap, remove the root and add the current point to the heap.
     *      Step 7: After iterating through all the points, the max-heap will contain the k closest
     *      points. Extract these points from the heap and return them as an array.
     *
     *  4. Time Complexity
     *      - Iterating through all n points takes O(n) time.
     *      - For each point, we might perform heap operations (insertion and removal), which take O(log k)
     *      time since the heap size is at most k.
     *      - Therefore, the overall time complexity is O(n log k).
     */

    /**
    The formula `distance = x^2 + y^2` is used to calculate the Euclidean distance between
    a point `(x, y)` and the origin `(0, 0)`.

    The Euclidean distance between two points `(x1, y1)` and `(x2, y2)` is calculated as:

    ```
    √((x2 - x1)^2 + (y2 - y1)^2)
    ```

    In this case, since we are calculating the distance from the point `(x, y)` to the origin
    `(0, 0)`, the formula simplifies to:

    ```
    √(x^2 + y^2)
    ```

    However, since we are only interested in finding the k closest points, we don't need
    to calculate the square root. Instead, we can use the squared Euclidean distance, which
    is `x^2 + y^2`. This is because the squared Euclidean distance preserves the relative order of
    the distances, so the points with the smallest squared distances will also be the points with
    the smallest actual Euclidean distances.

    By using the squared Euclidean distance, we can avoid the computationally more expensive square
    root operation, which makes the overall algorithm more efficient.
     */
    private fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        val maxHeap = PriorityQueue<IntArray> { p1, p2 ->
            val distance1 = p1[0] * p1[0] + p1[1] * p1[1]
            val distance2 = p2[0] * p2[0] + p2[1] * p2[1]
            distance2 - distance1
        }

        for (point in points) {
            maxHeap.offer(point)
            if (maxHeap.size > k) {
                maxHeap.poll()
            }
        }

        return maxHeap.toTypedArray()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        var points = arrayOf(intArrayOf(1, 3), intArrayOf(-2, 2))
        var k = 1
        var result = kClosest(points, k)
        println(result.contentDeepToString()) // [[-2, 2]]

        // Example 2
        points = arrayOf(intArrayOf(3, 3), intArrayOf(5, -1), intArrayOf(-2, 4))
        k = 2
        result = kClosest(points, k)
        println(result.contentDeepToString()) // [[3, 3], [-2, 4]]
    }
}