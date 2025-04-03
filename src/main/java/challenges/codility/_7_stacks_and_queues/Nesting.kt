package challenges.codility._7_stacks_and_queues

/**
 * A string S consisting of N characters is called properly nested if:
 *
 * S is empty;
 * S has the form "(U)" where U is a properly nested string;
 * S has the form "VW" where V and W are properly nested strings.
 * For example, string "(()(())())" is properly nested but string "())" isn't.
 *
 * Write a function:
 *
 * fun solution(S: String): Int
 *
 * that, given a string S consisting of N characters, returns 1 if string S is properly nested and 0 otherwise.
 *
 * For example, given S = "(()(())())", the function should return 1 and given S = "())", the function should return 0, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [0..1,000,000];
 * string S is made only of the characters '(' and/or ')'.
 *
 * https://app.codility.com/programmers/lessons/7-stacks_and_queues/nesting/
 */
import java.util.Deque
import java.util.LinkedList

class Solution {
    fun solution(S: String): Int {
        // 1. Problem Explanation
        // The problem asks us to determine if a given string of parentheses is "properly nested".
        // A string is properly nested if it follows specific rules, essentially meaning that
        // every opening parenthesis has a corresponding closing parenthesis in the correct order.

        // 2. Pattern Identification and Rationale
        // The most suitable pattern for solving this problem is using a Stack data structure.
        // The core idea is to iterate through the string. When we encounter an opening parenthesis '(',
        // we push it onto the stack. When we encounter a closing parenthesis ')', we try to pop
        // an opening parenthesis from the stack. If the stack is empty when we encounter a closing
        // parenthesis, or if after processing the entire string, the stack is not empty, then the
        // string is not properly nested.

        // Advantages of using a Stack:
        // - It naturally handles the LIFO (Last-In, First-Out) nature of nested parentheses.
        // - It allows us to efficiently check if each closing parenthesis has a corresponding
        //   most recently opened parenthesis.

        // 3. Solution Breakdown
        // Step 1: Initialize an empty stack.
        val stack: Deque<Char> = LinkedList()

        // Step 2: Iterate through each character in the input string S.
        for (char in S) {
            // Step 3: If the character is an opening parenthesis '(', push it onto the stack.
            if (char == '(') {
                stack.push(char)
                continue
            }
            // Step 4: If the character is a closing parenthesis ')'.
            if (char == ')') {
                // Step 4a: Check if the stack is empty. If it is, it means there is no matching
                //         opening parenthesis for the current closing parenthesis, so the string
                //         is not properly nested. Return 0.
                if (stack.isEmpty()) {
                    return 0
                }
                // Step 4b: If the stack is not empty, pop the top element. This represents
                //         matching the current closing parenthesis with the most recently opened one.
                stack.pop()
            }
        }

        // Step 5: After iterating through the entire string, check if the stack is empty.
        //         If it is empty, it means all opening parentheses have been correctly closed.
        //         Return 1.
        //         If the stack is not empty, it means there are unclosed opening parentheses.
        //         Return 0.
        return if (stack.isEmpty()) 1 else 0
    }
}

fun main() {
    val solution = Solution()

    // Test case 1: Properly nested string
    val s1 = "(()(())())"
    val result1 = solution.solution(s1)
    println("Input: $s1, Output: $result1") // Expected output: 1

    // Test case 2: Not properly nested string
    val s2 = "())"
    val result2 = solution.solution(s2)
    println("Input: $s2, Output: $result2") // Expected output: 0

    // Test case 3: Empty string
    val s3 = ""
    val result3 = solution.solution(s3)
    println("Input: $s3, Output: $result3") // Expected output: 1

    // Test case 4: Only opening parentheses
    val s4 = "(("
    val result4 = solution.solution(s4)
    println("Input: $s4, Output: $result4") // Expected output: 0

    // Test case 5: Only closing parentheses
    val s5 = "))"
    val result5 = solution.solution(s5)
    println("Input: $s5, Output: $result5") // Expected output: 0

    // Test case 6: Another properly nested string
    val s6 = "()"
    val result6 = solution.solution(s6)
    println("Input: $s6, Output: $result6") // Expected output: 1

    // Test case 7: Another not properly nested string
    val s7 = ")("
    val result7 = solution.solution(s7)
    println("Input: $s7, Output: $result7") // Expected output: 0
}