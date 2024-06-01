package challenges.mostafa._4_stack

/**
Given an array of integers heights representing the histogram's bar height where the width of
each bar is 1, return the area of the largest rectangle in the histogram.

Example 1:
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.

Example 2:
Input: heights = [2,4]
Output: 4


Constraints:

1 <= heights.length <= 10^5
0 <= heights[i] <= 10^4
 */

internal object P6LargestRectangleInHistogram {

    private fun largestRectangleArea(heights: IntArray): Int {
        val stack = ArrayDeque<Int>()
        var maxArea = 0

        heights.forEachIndexed { index, height ->
            while (stack.isNotEmpty() && heights[stack.last()] > height) {
                val lastHeight = heights[stack.removeLast()]
                val width = if (stack.isEmpty()) index else index - stack.last() - 1
                maxArea = maxOf(maxArea, lastHeight * width)
            }
            stack.addLast(index)
        }

        while (stack.isNotEmpty()) {
            val lastHeight = heights[stack.removeLast()]
            val width = if (stack.isEmpty()) heights.size else heights.size - stack.last() - 1
            maxArea = maxOf(maxArea, lastHeight * width)
        }

        return maxArea
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val heights = intArrayOf(2, 1, 5, 6, 2, 3)
        println(largestRectangleArea(heights))  // Output: 10
    }
}