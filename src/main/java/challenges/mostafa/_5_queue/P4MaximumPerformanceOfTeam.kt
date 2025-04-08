package challenges.mostafa._5_queue

import java.util.PriorityQueue

/**
You are given two integers n and k and two integer arrays speed and efficiency both of length n.
There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed
and efficiency of the ith engineer respectively.

Choose at most k different engineers out of the n engineers to form a team with the
maximum performance.

The performance of a team is the sum of its engineers' speeds multiplied by the minimum efficiency
among its engineers.

Return the maximum performance of this team. Since the answer can be a huge number, return
it modulo 10^9 + 7.


Example 1:

Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
Output: 60
Explanation:
We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4)
and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.

Example 2:

Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
Output: 68
Explanation:
This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and
engineer 5 to get the maximum performance of the team.
That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.

Example 3:

Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
Output: 72


Constraints:

1 <= k <= n <= 10^5
speed.length == n
efficiency.length == n
1 <= speed[i] <= 10^5
1 <= efficiency[i] <= 10^8

https://leetcode.com/problems/maximum-performance-of-a-team/description/
 */

internal object P4MaximumPerformanceOfTeam {

    /**
     * Problem Explanation:
     * The problem asks us to select at most k engineers from a group of n engineers, each having
     * a specific speed and efficiency. The goal is to form a team such that the team's performance
     * is maximized. The performance of a team is calculated by multiplying the sum of the speeds
     * of all engineers in the team by the minimum efficiency among all the engineers in that team.
     * We need to return this maximum performance modulo 10^9+7.
     *
     * Pattern Identification and Rationale:
     * The core idea to solve this problem efficiently involves considering each engineer's efficiency
     * as the minimum efficiency of a potential team. If we fix an engineer as having the minimum efficiency
     * in a team, then to maximize the performance, we should try to include other engineers with efficiency
     * greater than or equal to this minimum efficiency and with the highest possible speeds.
     *
     * This suggests an approach where we iterate through the engineers, considering each one as
     * the potential engineer with the minimum efficiency in a team. To efficiently manage the speeds
     * of the other engineers we might include in the team (those with efficiency at least as high),
     * we can use a min-heap data structure.
     *
     * The algorithm will follow a greedy strategy combined with the use of a min-heap. We will sort
     * the engineers based on their efficiency in descending order. Then, as we iterate through
     * the sorted engineers, the current engineer's efficiency will be the minimum efficiency for any
     * team we form including this engineer and potentially some of the engineers we have already
     * processed (those with higher or equal efficiency). For each such minimum efficiency, we want to
     * pick at most k−1 other engineers with the highest speeds among those with efficiency greater
     * than or equal to the current one. The min-heap will help us keep track of the speeds of
     * the engineers in our current team and efficiently remove the one with the smallest speed if
     * the team size exceeds k.
     *
     * Solution Breakdown:
     * a.  Represent Engineers: Create an array of pairs, where each pair stores the efficiency and
     * speed of an engineer.
     * b.  Sort by Efficiency: Sort the engineers in descending order based on their efficiency.
     * This allows us to process engineers from the most efficient to the least efficient. When we
     * consider an engineer, their efficiency will be the minimum efficiency for any team formed with
     * them and any previously considered engineers.
     * c.  Maintain Speed Sum and Heap: Initialize a variable currentSpeedSum to keep track of the sum
     * of speeds of the engineers in the current potential team and a min-priority queue (speedHeap)
     * to store the speeds of these engineers. The min-heap will help us efficiently remove the engineer
     * with the smallest speed if we have more than k engineers in our consideration.
     * d.  Iterate Through Sorted Engineers: Iterate through the sorted array of engineers. For each engineer:
     * i.  Add the current engineer's speed to currentSpeedSum and add their speed to the speedHeap.
     * ii. If the size of the speedHeap exceeds k, it means we have more than k engineers in our current
     * consideration. To maintain at most k engineers, we remove the engineer with the minimum speed from
     * the speedHeap and subtract their speed from currentSpeedSum.
     * iii. Calculate the performance of the current team. The minimum efficiency is the efficiency of
     * the current engineer (since we are iterating in descending order of efficiency, all engineers
     * currently in our speedHeap have an efficiency greater than or equal to the current one). The sum
     * of speeds is currentSpeedSum. The performance is currentSpeedSum * currentEfficiency.
     * iv. Update the maxPerformance found so far if the current team's performance is greater.
     * e.  Return Result: After iterating through all the engineers, maxPerformance will hold the maximum
     * possible performance. Return this value modulo 10^9+7.
     *
     * Time Complexity:
     * a.  Sorting the engineers takes O(n log n) time.
     * b.  Iterating through the n engineers takes O(n) time.
     * c.  In each iteration, we perform operations on the min-heap. Adding and removing elements from
     * a min-heap of size at most k takes O(log k) time. Since we do this at most n times, the total
     * time for heap operations is O(n log k).
     * d.  All other operations inside the loop take O(1) time.
     * Therefore, the overall time complexity of the algorithm is dominated by the sorting step and
     * the heap operations, resulting in O(n log n + n log k). Since k≤n, this can be simplified to O(n log n).
     */
    private fun maxPerformance(
        n: Int,
        speed: IntArray,
        efficiency: IntArray,
        k: Int
    ): Int {
        val engineers = Array(n) { i -> Pair(efficiency[i], speed[i]) }
        // Sort engineers in descending order of their efficiency
        engineers.sortByDescending { it.first }

        var maxPerformance = 0L
        var currentSpeedSum = 0L
        // Min-heap to store the speeds of the engineers in the current team
        val speedHeap = PriorityQueue<Int>()

        for ((currentEfficiency, currentSpeed) in engineers) {
            // Add the current engineer to the team
            currentSpeedSum += currentSpeed
            speedHeap.offer(currentSpeed)

            // If the team size exceeds k, remove the engineer with the minimum speed
            if (speedHeap.size > k) {
                currentSpeedSum -= speedHeap.poll()
            }

            // Calculate the performance of the current team
            val currentPerformance = currentSpeedSum * currentEfficiency
            maxPerformance = maxOf(maxPerformance, currentPerformance)
        }

        return (maxPerformance % (1e9 + 7)).toInt()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        val n1 = 6
        val speed1 = intArrayOf(2, 10, 3, 1, 5, 8)
        val efficiency1 = intArrayOf(5, 4, 3, 9, 7, 2)
        val k1 = 2
        val result1 = maxPerformance(n1, speed1, efficiency1, k1)
        println("Example 1 Output: $result1") // Expected: 60

        // Example 2
        val n2 = 6
        val speed2 = intArrayOf(2, 10, 3, 1, 5, 8)
        val efficiency2 = intArrayOf(5, 4, 3, 9, 7, 2)
        val k2 = 3
        val result2 = maxPerformance(n2, speed2, efficiency2, k2)
        println("Example 2 Output: $result2") // Expected: 68

        // Example 3
        val n3 = 6
        val speed3 = intArrayOf(2, 10, 3, 1, 5, 8)
        val efficiency3 = intArrayOf(5, 4, 3, 9, 7, 2)
        val k3 = 4
        val result3 = maxPerformance(n3, speed3, efficiency3, k3)
        println("Example 3 Output: $result3") // Expected: 72
    }
}