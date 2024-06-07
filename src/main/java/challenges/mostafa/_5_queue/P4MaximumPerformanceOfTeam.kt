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


 */

internal object P4MaximumPerformanceOfTeam {

    data class Engineer(val speed: Int, val efficiency: Int)

    private fun maxPerformance(
        n: Int,
        speed: IntArray,
        efficiency: IntArray,
        k: Int
    ): Int {
        val engineers = (0 until n).map { Engineer(speed[it], efficiency[it]) }
        val sortedEngineers = engineers.sortedByDescending { it.efficiency }

        var totalSpeed = 0L
        var maxPerformance = 0L
        val speedHeap = PriorityQueue<Int>()

        for (engineer in sortedEngineers) {
            if (speedHeap.size < k) {
                speedHeap.offer(engineer.speed)
                totalSpeed += engineer.speed

            } else if (speedHeap.isNotEmpty() && speedHeap.peek() < engineer.speed) {
                totalSpeed -= speedHeap.poll()
                speedHeap.offer(engineer.speed)
                totalSpeed += engineer.speed
            }

            maxPerformance = maxOf(maxPerformance, totalSpeed * engineer.efficiency)
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