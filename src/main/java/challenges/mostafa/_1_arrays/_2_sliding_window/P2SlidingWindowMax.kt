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

    /**
    The time complexity of the provided maxSlidingWindow function is O(n log k),
    where n is the length of the input array nums and k is the size of the sliding window.

    Here's a breakdown of the time complexity:

    Initialization: The time complexity for initializing the result array and the PriorityQueue is O(1).
    Loop through the input array:
    The loop iterates through the nums array, which has a time complexity of O(n).
    Inside the loop:
    Removing elements from the queue: The time complexity for removing elements from
    the queue is O(log k), as the queue is implemented using a priority queue.
    Adding the current element to the queue: The time complexity for adding an element to
    the queue is O(log k).
    Updating the result array: The time complexity for updating the result array is O(1).
    Combining these factors, the overall time complexity of the maxSlidingWindow function is
    O(n * log k), which can be simplified to O(n log k).

    The space complexity of the function is O(k), as the maximum size of the PriorityQueue is k.
     */
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