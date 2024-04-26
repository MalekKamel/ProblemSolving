package challenges.mostafa._1_arrays._2_sliding_window

import java.util.PriorityQueue

/**
You are given an array of integers nums, there is a sliding window of size k which is moving
from the very left of the array to the very right. You can only see the k numbers in the window.
Each time the sliding window moves right by one position.

Return the max sliding window.

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]

Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6 7       3
1 [3  -1  -3] 5  3  6  7       3
1  3 [-1  -3  5] 3  6  7       5
1  3  -1 [-3  5  3] 6  7       5
1  3  -1  -3 [5  3  6] 7       6
1  3  -1  -3  5 [3  6  7]      7
Example 2:

Input: nums = [1], k = 1
Output: [1]

https://leetcode.com/problems/sliding-window-maximum/
 */

internal object P2SlidingWindowMax {

    private fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val result = IntArray(nums.size - k + 1)
        val queue = PriorityQueue<Pair<Int, Int>>(compareBy { -it.first })

        for (i in nums.indices) {
            // Remove elements that are outside the window
            while (queue.isNotEmpty() && queue.peek().second <= i - k) {
                queue.poll()
            }

            // Add the current element to the queue
            queue.offer(nums[i] to i)

            // If the window is fully formed, add the maximum element to the result
            if (i >= k - 1) {
                result[i - k + 1] = queue.peek().first
            }
        }

        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 3, -1, -3, 5, 3, 6, 7)
        val k = 3

        println(maxSlidingWindow(array, k).contentToString())
    }
}