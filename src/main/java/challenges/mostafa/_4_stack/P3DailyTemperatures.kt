package challenges.mostafa._4_stack

import java.util.Stack

/**
Given an array of integers temperatures represents the daily temperatures, return an array
answer such that answer[i] is the number of days you have to wait after the ith day to get
a warmer temperature. If there is no future day for which this is possible,
keep answer[i] == 0 instead.


Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]

Constraints:

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100

https://leetcode.com/problems/daily-temperatures/description/
 */

internal object P3DailyTemperatures {

    private fun dailyTemperatures(temperatures: IntArray): IntArray {
        val n = temperatures.size
        val answer = IntArray(n)
        val stack = Stack<Int>()

        for (i in 0 until n) {
            while (stack.isNotEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                val prevIndex = stack.pop()
                answer[prevIndex] = i - prevIndex
            }
            stack.push(i)
        }

        return answer
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val temperatures = intArrayOf(73, 74, 75, 71, 69, 72, 76, 73)
        val answer = dailyTemperatures(temperatures)
        println(answer.contentToString()) // Output: [1, 1, 4, 2, 1, 1, 0, 0]

        val temperatures2 = intArrayOf(5, 4, 3, 2, 1, 6)
        val answer2 = dailyTemperatures(temperatures2)
        println(answer2.contentToString()) // Output: [1, 1, 4, 2, 1, 1, 0, 0]
    }
}