package challenges.mostafa._4_stack

import java.util.Stack

/**
Given a string containing just the characters '(' and ')', return the length of the longest
valid (well-formed) parentheses
substring.

Example 1:

Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".

Example 2:
Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".

Example 3:
Input: s = ""
Output: 0

Constraints:

0 <= s.length <= 3 * 10^4
s[i] is '(', or ')'.

https://leetcode.com/problems/longest-valid-parentheses/description/
 */

internal object P11LongestValidParentheses {

    /**
    The key aspects of this solution are:

    1. Using a stack to keep track of the indices of the opening parentheses.
    2. Updating the maxLength variable whenever a valid parentheses substring is found.
    3. Pushing the current index onto the stack when a closing parenthesis is encountered
        but there is no matching opening parenthesis on the stack.
     */

    /**
     * The -1 we initially push onto the stack in the stack-based solution for the Longest Valid Parentheses
     * problem serves as a base index for calculating the length of a valid substring that starts from
     * the beginning of the string.
     *
     * Let's break down why it's necessary and how it works:
     *
     * Scenario 1: A valid substring starts at the beginning of the input string.
     *
     * Consider the input s = "()()".
     *
     * We push -1 onto the stack: [-1]
     * We encounter ( at index 0: [-1, 0]
     * We encounter ) at index 1:
     * We pop from the stack: [-1]
     * The stack is not empty.
     * maxLength = maxOf(0, 1 - (-1)) = maxOf(0, 2) = 2. The valid substring "()" has length 2.
     * We encounter ( at index 2: [-1, 2]
     * We encounter ) at index 3:
     * We pop from the stack: [-1]
     * The stack is not empty.
     * maxLength = maxOf(2, 3 - (-1)) = maxOf(2, 4) = 4. The valid substring "()()" has length 4.
     * Without the initial -1, when we encounter the first closing parenthesis of a valid substring
     * starting at index 0, the stack would become empty after the first pop. If we then tried to
     * calculate the length using the current index and the top of the stack, there would be no top element.
     *
     * Scenario 2: A valid substring starts after some invalid parentheses.
     *
     * Consider the input s = ")()"
     *
     * We push -1 onto the stack: [-1]
     * We encounter ) at index 0:
     * We pop from the stack: []
     * The stack is now empty, so we push the current index: [0]
     * We encounter ( at index 1: [0, 1]
     * We encounter ) at index 2:
     * We pop from the stack: [0]
     * The stack is not empty.
     * maxLength = maxOf(0, 2 - 0) = maxOf(0, 2) = 2. The valid substring "()" has length 2.
     * Here, the -1 wasn't directly used in the final calculation of the valid substring "()", but
     * it was crucial for the initial state of the stack. When the first ) was encountered without
     * a matching (, the stack became empty, and we pushed the index of that ) to mark the "boundary"
     * before a potential new valid substring. When the subsequent valid "()" was found, the top
     * of the stack (0) correctly indicated the index before this valid substring started.
     *
     * In essence, the -1 acts as a placeholder at the beginning, ensuring that when a valid substring
     * starts from index 0, we can correctly calculate its length by subtracting -1 from the index of
     * the closing parenthesis. It provides a consistent way to calculate the length of valid substrings
     * regardless of their starting position within the input string.
     */
    private fun longestValidParentheses(s: String): Int {
        if (s.isEmpty()) return 0

        val stack = Stack<Int>()
        stack.push(-1)
        var maxLength = 0

        for (i in s.indices) {
            when (s[i]) {
                '(' -> {
                    stack.push(i)
                }

                ')' -> {
                    stack.pop()
                    // If the stack becomes empty after popping, it means that the current closing
                    // parenthesis does not have a corresponding opening parenthesis within the current
                    // potential valid substring. To correctly calculate the length of subsequent
                    // valid substrings, we push the current index i onto the stack. This essentially
                    // marks the position before a new potential valid substring could start.
                    if (stack.isEmpty()) {
                        stack.push(i)
                        continue
                    }
                    maxLength = maxOf(maxLength, i - stack.last())
                }
            }
        }

        return maxLength
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val examples = listOf(
            "(()" to 2,
            ")()())" to 4,
            "" to 0
        )

        for ((input, expected) in examples) {
            val result = longestValidParentheses(input)
            println("Input: $input, Output: $result, Expected: $expected")
            check(result == expected) { "Test case failed for input: $input" }
        }
    }
}