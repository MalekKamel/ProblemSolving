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
 */

internal object P2TrappingRainWater {

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