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

https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 */

internal object P6LargestRectangleInHistogram {

    /**
     * 1. Problem Explanation
     * The problem asks us to find the largest rectangular area that can fit within a histogram
     * represented by an array of bar heights. Each bar has a width of 1. The rectangle's
     * width can span across one or more adjacent bars, and its height is limited by the
     * shortest bar within that width.
     *
     * 2. Pattern Identification and Rationale
     * This problem can be efficiently solved using a stack-based approach. This pattern is suitable
     * because we need to keep track of potential rectangles as we iterate through the histogram.
     * The stack helps us maintain the indices of bars in increasing order of height.
     * When we encounter a bar shorter than the top of the stack, it signifies the end of a potential
     * rectangle whose height was determined by the popped bar. The stack allows us to efficiently
     * calculate the width of this rectangle.
     *
     * Advantages of the stack-based approach:
     * - Efficiently determines the left and right boundaries for each bar as the minimum height.
     * - Avoids redundant calculations by processing each bar as a potential minimum height only once.
     * - Achieves a time complexity of O(n), where n is the number of bars.
     *
     * 3. Solution Breakdown
     * a. Initialize an empty stack to store indices of the heights array.
     * b. Initialize a variable `maxArea` to 0 to keep track of the largest rectangle area found so far.
     * c. Iterate through the `heights` array, and for each bar at index `i`:
     *    i. While the stack is not empty and the height of the current bar is less than or equal to
     *       the height of the bar at the index at the top of the stack:
     *       - Pop the index of the top bar from the stack. Let this index be `poppedIndex`.
     *       - The height of the rectangle formed with `heights[poppedIndex]` as the minimum height.
     *       - The width of the rectangle is determined by the indices around `poppedIndex`:
     *         - If the stack is now empty, the width extends from the beginning of the array (index 0)
     *           to the current index `i`.
     *         - If the stack is not empty, the width extends from the index immediately after the new
     *           top of the stack to the current index `i`.
     *       - Calculate the area: `heights[poppedIndex] * width`.
     *       - Update `maxArea` if the calculated area is greater.
     *    ii. Push the current index `i` onto the stack.
     * d. After the loop finishes, there might still be indices left in the stack. These represent
     *    bars for which we haven't found a smaller bar to their right. We need to process them similarly
     *    to step 3.c.i. To handle this cleanly, we can conceptually append a 0 to the end of the
     *    `heights` array. This will ensure that all remaining bars in the stack are processed.
     * e. Return `maxArea`.
     */
     fun largestRectangleArea(heights: IntArray): Int {
        // Initialize an empty ArrayDeque to serve as our stack. This stack will store
        // indices of the `heights` array. We use a deque because it allows efficient
        // addition and removal from both ends.
        val stack = ArrayDeque<Int>()

        // Initialize a variable to keep track of the maximum rectangular area found so far.
        var maxArea = 0

        // Get the number of bars in the histogram.
        val n = heights.size

        // Iterate through the `heights` array, from the first bar to one position beyond the last.
        // The extra iteration (up to `n`) is a clever way to handle any remaining bars in the stack
        // after the initial loop finishes. It ensures that all potential rectangles are considered.
        // Why is this extra iteration necessary?
        //
        //The core idea of the stack-based approach is to calculate the area of a rectangle when we
        // encounter a bar that is shorter than the bar at the top of the stack. This shorter bar
        // acts as a right boundary for the rectangle formed by the taller bar.
        //
        //However, consider a scenario where the histogram's heights are non-decreasing (e.g., [1, 2, 3, 4, 5]).
        // In this case, when the loop reaches the end of the actual bars (i.e., i goes from 0 to n-1),
        // the condition heights[i] <= heights[stack.peekLast()] inside the while loop might never
        // become true for the bars remaining in the stack. These bars in the stack still represent
        // potential rectangles that extend to the end of the histogram.
        //
        //The extra iteration (i == n) serves as a trigger to process any remaining bars in the stack.
        for (i in 0..n) {
            // While the stack is not empty AND (we've reached the end of the array OR the height of the
            // current bar is less than or equal to the height of the bar at the index at the top of the stack):
            // This condition signifies that the current bar is shorter than the bar at the top of the stack,
            // meaning the rectangle formed by the bar at the top of the stack can no longer extend to the right.
            while (stack.isNotEmpty() && (i == n || heights[i] <= heights[stack.last()])) {
                // Pop the index of the top bar from the stack. This bar's height will be the height of the
                // rectangle we are now calculating.
                val height = heights[stack.removeLast()]

                // Calculate the width of the rectangle.
                // If the stack is now empty, it means the popped bar was the shortest so far, and the
                // width extends from the beginning of the histogram (index 0) to the current index `i`.
                val width = if (stack.isEmpty()) {
                    i
                } else {
                    // If the stack is not empty, the width is the distance between the current index `i` and
                    // the index of the bar just below the popped one in the stack (which is now the top).
                    // We subtract 1 because the indices are inclusive.
                    i - stack.last() - 1
                }

                // Calculate the area of the rectangle formed with the popped height and calculated width.
                maxArea = maxOf(maxArea, height * width)
            }
            // Push the current index `i` onto the stack. This means we are considering this bar as a potential
            // boundary for future rectangles. We store the index because we need to calculate widths later.
            stack.addLast(i)
        }

        // After iterating through all the bars (and the extra conceptual bar at the end), `maxArea`
        // will hold the area of the largest rectangle found.
        return maxArea
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val heights = intArrayOf(2, 1, 5, 6, 2, 3)
        println(largestRectangleArea(heights))  // Output: 10
    }
}