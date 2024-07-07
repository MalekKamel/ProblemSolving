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
                    // If the stack is now empty, it means the current closing parenthesis doesn't have
                    // a matching opening parenthesis. In this case, we push the current index
                    // onto the stack to ensure that the next opening parenthesis will be correctly matched.
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