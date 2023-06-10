package challenges.educative_grokking_coding_interview.fast_slow_pointers._4

import java.util.*

/**
An input array, nums containing non-zero integers, is given, where the value at each index represents the number of places to skip forward (if the value is positive) or backward (if the value is negative). When skipping forward or backward, wrap around if you reach either end of the array. For this reason, we are calling it a circular array. Determine if this circular array has a cycle. A cycle is a sequence of indices in the circular array characterized by the following:

The same set of indices is repeated when the sequence is traversed in accordance with the aforementioned rules.
The length of the sequence is at least two.
The loop must be in a single direction, forward or backward.
It should be noted that a cycle in the array does not have to originate at the beginning. A cycle can begin from any point in the array.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/g2m3z3wzLDD
 */


object CircularArrayLoop {
    fun circularArrayLoop(nums: IntArray): Boolean {
        val size = nums.size
        // Iterate through each index of the array 'nums'.
        for (i in 0 until size) {
            // Set slow and fast pointer at current index value.
            var slow = i
            var fast = i
            // Set true in 'forward' if element is positive, set false otherwise.
            val forward = nums[i] > 0
            while (true) {
                // Move slow pointer to one step.
                slow = nextStep(slow, nums[slow], size)
                // If cycle is not possible, break the loop and start from next element.
                if (isNotCycle(nums, forward, slow)) break

                // First move of fast pointer.
                fast = nextStep(fast, nums[fast], size)
                // If cycle is not possible, break the loop and start from next element.
                if (isNotCycle(nums, forward, fast)) break

                // Second move of fast pointer.
                fast = nextStep(fast, nums[fast], size)
                // If cycle is not possible, break the loop and start from next element.
                if (isNotCycle(nums, forward, fast)) break

                // At any point, if fast and slow pointers meet each other,
                // it indicates that loop has been found, return true.
                if (slow == fast) return true
            }
        }
        return false
    }

    // A function to calculate the next step
    fun nextStep(pointer: Int, value: Int, size: Int): Int {
        var result = (pointer + value) % size
        if (result < 0) result += size
        return result
    }

    // A function to detect a cycle doesn't exist
    fun isNotCycle(nums: IntArray, prevDirection: Boolean, pointer: Int): Boolean {
        // Set current direction to true if current element is positive, set false otherwise.
        val currDirection = nums[pointer] >= 0

        // If current direction and previous direction are different or moving a pointer takes back to the same value,
        // then the cycle is not possible, we return true, otherwise return false.
        return if (prevDirection != currDirection || Math.abs(nums[pointer] % nums.size) == 0) {
            true
        } else false
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val input = arrayOf(
            intArrayOf(-2, -3, -9),
            intArrayOf(-5, -4, -3, -2, -1),
            intArrayOf(-1, -2, -3, -4, -5),
            intArrayOf(2, 1, -1, -2),
            intArrayOf(-1, -2, -3, -4, -5, 6),
            intArrayOf(1, 2, -3, 3, 4, 7, 1),
            intArrayOf(2, 2, 2, 7, 2, -1, 2, -1, -1)
        )
        for (i in input.indices) {
            println(
                """
                    ${i + 1}.	Circular array = ${Arrays.toString(input[i])}
                    
                    """.trimIndent()
            )
            val result = circularArrayLoop(input[i])
            println("\tFound Loop = $result")
            println(String(CharArray(100)).replace('\u0000', '-'))
        }
    }
}