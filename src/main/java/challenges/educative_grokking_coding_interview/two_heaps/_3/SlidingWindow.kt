package challenges.educative_grokking_coding_interview.two_heaps._3

import challenges.util.PrintHyphens
import java.util.*

/**
Given an integer array, nums, and an integer, k, there is a sliding window of size k, which is moving
from the very left to the very right of the array. We can only see the k numbers in the window.
Each time the sliding window moves right by one position.
Given this scenario, return the median of the each window. Answers within (10 power -5) of the actual
value will be accepted.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/g2REMP4N4K9
 */
object SlidingWindow {
    private fun medianSlidingWindow(nums: IntArray, k: Int): DoubleArray {
        // To store the medians
        val medians: MutableList<Double> = ArrayList()

        // To keep track of the numbers that need to be removed from the heaps
        val outgoingNum = HashMap<Int, Int>()

        // Max heap
        val smallList = PriorityQueue(Collections.reverseOrder<Int>())

        // Min heap
        val largeList = PriorityQueue<Int>()

        // Initialize the max heap
        for (i in 0 until k) smallList.offer(nums[i])

        // Transfer the top 50% of the numbers from max heap to min heap
        for (i in 0 until k / 2) largeList.offer(smallList.poll())
        var i = k
        while (true) {
            // If the window size is odd
            if (k and 1 == 1) medians.add(smallList.peek().toDouble()) else medians.add(
                (smallList.peek().toLong() + largeList.peek()
                    .toLong()).toDouble() * 0.5
            )

            // Break the loop if all elements have been processed
            if (i >= nums.size) break

            // Outgoing number
            val outNum = nums[i - k]

            // Incoming number
            val inNum = nums[i]
            i++

            // Variable to keep the heaps balanced
            var balance = 0

            // If the outgoing number is from max heap
            if (outNum <= smallList.peek()) balance -= 1 else balance += 1

            // Add/Update the outgoing number in the hash map
            if (outgoingNum.containsKey(outNum)) outgoingNum[outNum] =
                outgoingNum[outNum]!! + 1 else outgoingNum[outNum] =
                1

            // If the incoming number is less than the top of the max heap, add it in that heap
            // Otherwise, add it in the min heap
            if (smallList.size > 0 && inNum <= smallList.peek()) {
                balance += 1
                smallList.offer(inNum)
            } else {
                balance -= 1
                largeList.offer(inNum)
            }

            // Re-balance the heaps
            if (balance < 0) smallList.offer(largeList.poll()) else if (balance > 0) largeList.offer(smallList.poll())

            // Remove invalid numbers present in the hash map from top of max heap
            while (smallList.size > 0 && outgoingNum.containsKey(smallList.peek()) && outgoingNum[smallList.peek()]!! > 0) outgoingNum[smallList.peek()] =
                outgoingNum[smallList.poll()]!! - 1

            // Remove invalid numbers present in the hash map from top of min heap
            while (largeList.size > 0 && outgoingNum.containsKey(largeList.peek()) && outgoingNum[largeList.peek()]!! > 0) outgoingNum[largeList.peek()] =
                outgoingNum[largeList.poll()]!! - 1
        }
        return medians.stream().mapToDouble { obj: Double -> obj }.toArray()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val arr = arrayOf(
            intArrayOf(3, 1, 2, -1, 0, 5, 8),
            intArrayOf(1, 2),
            intArrayOf(4, 7, 2, 21),
            intArrayOf(22, 23, 24, 56, 76, 43, 121, 1, 2, 0, 0, 2, 3, 5),
            intArrayOf(1, 1, 1, 1, 1)
        )
        val k = intArrayOf(4, 1, 2, 5, 2)
        for (i in k.indices) {
            print(i + 1)
            println(".\tInput array =" + arr[i].contentToString() + ", k = " + k[i])
            val output = medianSlidingWindow(arr[i], k[i])
            println("\tMedians =" + output.contentToString())
            println(PrintHyphens.repeat("-", 100))
        }
    }
}