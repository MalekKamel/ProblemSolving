package challenges.mostafa._1_arrays.ad_hoc

/**
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.

Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array
[0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9

Constraints:

n == height.length1 <= n <= 2 * 1040 <= height[i] <= 10^5

https://leetcode.com/problems/trapping-rain-water/description/

Related: https://leetcode.com/problems/container-with-most-water/description/
 */

internal object P2TrappingRainWater {

    /**
     * The approach used in the provided code is dynamic programming.
     *
     * Here's a breakdown of the approach:
     *
     * Pre-computation of Maximum Heights:
     *
     * It calculates two arrays: leftMax and rightMax.
     * leftMax[i] stores the maximum height of the bar to the left of or at index i. This is computed
     * by iterating from left to right.
     * rightMax[i] stores the maximum height of the bar to the right of or at index i. This is computed
     * by iterating from right to left.
     * Determining Water Level:
     *
     * For each bar at index i, the maximum water level that can be held above it is determined by
     * the shorter of the tallest bar to its left (leftMax[i]) and the tallest bar to its right (rightMax[i]).
     * Calculating Trapped Water at Each Bar:
     *
     * The amount of water trapped above a bar at index i is the difference between this maximum
     * water level (minOf(leftMax[i], rightMax[i])) and the height of the current bar (height[i]).
     * If this difference is negative, it means no water is trapped at that position.
     * Accumulating Total Trapped Water:
     *
     * The code iterates through all the bars and sums up the amount of water trapped above each
     * bar to get the total trapped rainwater.
     * In essence, the dynamic programming approach optimizes the solution by pre-calculating
     * the maximum heights to the left and right of each bar, avoiding redundant calculations
     * within the main loop for calculating trapped water. This leads to an efficient solution
     * with a time complexity of O(n) and a space complexity of O(n).
     *
     * Dynamic programming is a problem-solving technique that breaks down a complex problem into
     * smaller overlapping subproblems, solves each subproblem only once, and stores the solutions
     * to avoid redundant computations. It's particularly useful for problems that exhibit two key properties:
     *
     * 1. Optimal Substructure:
     *
     * This means that the optimal solution to the overall problem can be constructed from the optimal
     * solutions to its subproblems.
     * In the context of the "Trapping Rain Water" problem, the total trapped water is the sum of
     * the water trapped above each individual bar. The amount of water trapped above each bar
     * depends on the maximum heights to its left and right, which can be seen as subproblems.
     * 2. Overlapping Subproblems:
     *
     * This means that the same subproblems are encountered multiple times when solving the larger problem.
     * In the "Trapping Rain Water" example, when calculating the trapped water for each bar, we
     * are essentially looking for the maximum height to the left and right. These calculations
     * might involve looking at the same indices multiple times if we didn't store the results.
     * Dynamic programming addresses this by storing the results of these subproblems.
     * How Dynamic Programming Works (General Steps):
     *
     * Identify the Subproblems: Break down the main problem into smaller, self-similar subproblems.
     *
     * Define the State: Determine the parameters that uniquely define each subproblem. The state
     * should capture all the necessary information to solve the subproblem. In the "Trapping Rain Water"
     * example, the state can be thought of as the index i of the bar for which we want to determine
     * the trapped water (although the direct DP state here is more about the maximums to the left and right).
     *
     * Formulate the Recurrence Relation: Define a relationship between the solution to a larger
     * subproblem and the solutions to its smaller subproblems. This is the core of the dynamic
     * programming approach. In our example, the amount of water at index i depends on the
     * leftMax[i] and rightMax[i], which were derived from previous subproblems.
     *
     * Memoization (Top-Down) or Tabulation (Bottom-Up):
     *
     * Memoization: Solve the problem recursively, but store the results of already solved subproblems
     * in a data structure (like a dictionary or hash map). Before solving a subproblem, check if
     * its solution is already stored. If so, return the stored value; otherwise, solve it and
     * store the result.
     * Tabulation: Build a table (usually an array or a multi-dimensional array) to store the solutions
     * to the subproblems in a bottom-up manner. Start by solving the smallest subproblems and then
     * use their solutions to solve larger subproblems until the solution to the original problem
     * is obtained. The provided code uses tabulation.
     * Solve the Original Problem: Once the subproblems are solved and their results are stored,
     * the solution to the original problem can be easily obtained from the stored results.
     *
     * Benefits of Dynamic Programming:
     *
     * Efficiency: By solving each subproblem only once and storing the results, dynamic programming
     * avoids redundant computations, which can significantly reduce the time complexity compared to
     * naive recursive approaches.
     * Optimality: When applied correctly to problems with optimal substructure, dynamic programming
     * guarantees finding the optimal solution.
     * In the context of the provided "Trapping Rain Water" code:
     *
     * Subproblems: Finding the maximum height to the left and right of each bar.
     * State: The index i of the bar.
     * Recurrence Relation (Implicit):
     * leftMax[i] = max(height[i], leftMax[i-1])
     * rightMax[i] = max(height[i], rightMax[i+1])
     * water[i] = min(leftMax[i], rightMax[i]) - height[i]
     * Tabulation: The code uses tabulation to build the leftMax and rightMax arrays and then uses
     * them to calculate the total water.
     * Therefore, the code leverages the principles of dynamic programming by breaking down the problem
     * into finding the maximum boundaries and then using these pre-computed values to efficiently
     * calculate the trapped water at each position.
     *
     * The term "dynamic programming" was coined by Richard Bellman in the 1950s. The "dynamic"
     * part refers to the fact that the solution evolves over time as subproblems are solved and
     * their results are used to solve larger problems. Think of it as a multi-stage decision
     * process where each decision builds upon the previous ones.
     *
     * The "programming" part in the name is a bit of a historical artifact. At the time,
     * "programming" often referred to the process of making decisions or planning, rather than
     * specifically writing computer code. Bellman was working on optimizing decision processes,
     * and "programming" in this context meant finding an optimal policy or schedule.
     *
     * There are a couple of reasons often cited for Bellman choosing this particular name:
     *
     * Hiding the Mathematical Nature: Bellman himself mentioned that he wanted to choose a name
     * that sounded less mathematical and more appealing to the military funding sources he was
     * working with. The term "programming" had a connotation related to planning and scheduling,
     * which might have been more readily understood and supported.
     *
     * Time-Varying Aspects: The "dynamic" aspect highlights the sequential or multi-stage nature
     * of the problems that dynamic programming typically solves. The solutions are built up step
     * by step, often based on decisions made at earlier stages.
     *
     * So, while the name might seem a bit misleading from a modern software development perspective
     * where "programming" primarily refers to coding, it reflects the historical context and the nature
     * of the problem-solving technique as a process of making sequential decisions to achieve an optimal
     * outcome.
     *
     * Essentially, "dynamic programming" refers to an approach where you make a sequence of interrelated
     * decisions, and each decision depends on the state resulting from the previous decisions. The "dynamic"
     * aspect emphasizes this sequential, evolving nature of the solution.
     *
     *
     */
    private fun trap(height: IntArray): Int {
        val n = height.size
        if (n == 0) return 0

        val leftMax = IntArray(n)
        val rightMax = IntArray(n)
        var water = 0

        leftMax[0] = height[0]
        for (i in 1 until n)
            leftMax[i] = maxOf(height[i], leftMax[i - 1])

        rightMax[n - 1] = height[n - 1]
        for (i in n - 2 downTo 0)
            rightMax[i] = maxOf(height[i], rightMax[i + 1])

        for (i in 0 until n)
            water += minOf(leftMax[i], rightMax[i]) - height[i]

        return water
    }


    @JvmStatic
    fun main(args: Array<String>) {
        var array = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)

        println("1st")
        println(trap(array))

        println("2nd")
        array = intArrayOf(4, 2, 0, 3, 2, 5)
        println(trap(array))
    }
}