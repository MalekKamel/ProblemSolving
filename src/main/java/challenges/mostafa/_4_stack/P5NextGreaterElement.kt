package challenges.mostafa._4_stack

import java.util.Stack

/**
Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order
next in the array, which means you could search circularly to find its next greater number.
If it doesn't exist, return -1 for this number.


Example 1:

Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number.
The second 1's next greater number needs to search circularly, which is also 2.

Example 2:

Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]

Constraints:

1 <= nums.length <= 10^4
-10^9 <= nums[i] <= 10^9

https://leetcode.com/problems/next-greater-element-ii/description/
 */

internal object P5NextGreaterElement {

    private fun nextGreaterElements(nums: IntArray): IntArray {
        val n = nums.size
        val stack = Stack<Int>()
        val result = IntArray(n) { -1 }

        for (i in 0 until 2 * n) {
            val index = i % n
            while (stack.isNotEmpty() && nums[stack.peek()] < nums[index]) {
                result[stack.pop()] = nums[index]
            }
            stack.push(index)
        }

        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val nums = intArrayOf(1, 2, 3, 4, 3)
        val result = nextGreaterElements(nums)
        println(result.contentToString()) // Output: [2, 3, 4, -1, 4]
    }
}

/**
 Modulo Operator Usage

 The modulo operator (%) in programming is used to find the remainder of a division operation.
 Here are some common scenarios where the modulo operator can be useful:

 1. **Checking for even/odd numbers**: You can use the modulo operator to determine whether
 a number is even or odd. If the remainder of a number divided by 2 is 0, the number is
 even; otherwise, it's odd.

 ```
 if (number % 2 == 0) {
 // number is even
 } else {
 // number is odd
 }
 ```

 2. **Implementing circular data structures**: The modulo operator can be used to wrap around
 the end of a data structure, such as an array or a list, and start again from the beginning.
 This is useful for implementing circular buffers, rotating displays, and other cyclic data structures.

 ```
 let index = (currentIndex + 1) % arrayLength;
 ```

 3. **Formatting time and dates**: The modulo operator can be used to format time and date
 values, such as converting 24-hour time to 12-hour time or calculating the day of the week.

 ```
 let hour = (totalHours % 12) || 12; // convert 24-hour time to 12-hour time
 let dayOfWeek = (totalDays % 7); // calculate the day of the week (0 = Sunday, 1 = Monday, etc.)
 ```

 4. **Implementing hash functions**: Hash functions often use the modulo operator to map
 a large input domain to a smaller output range, ensuring that the output values are evenly distributed.

 ```
 let hashIndex = (hashCode(key) % bucketCount);
 ```

 5. **Performing division with integer values**: The modulo operator can be used to perform
 integer division, where the result is the quotient, and the remainder is the modulo.

 ```
 let quotient = Math.floor(dividend / divisor);
 let remainder = dividend % divisor;
 ```

 6. **Implementing game logic**: In game programming, the modulo operator is often used to
 implement game mechanics, such as wrapping around the screen, handling player turns, or cycling
 through a list of power-ups or items.

 The modulo operator is a versatile tool in programming and can be used in a variety of scenarios
 where you need to work with the remainder of a division operation.
 */