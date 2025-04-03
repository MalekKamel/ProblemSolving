package challenges.codility._7_stacks_and_queues

import java.util.Stack

/**
 * A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:
 *
 * S is empty;
 * S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
 * S has the form "VW" where V and W are properly nested strings.
 * For example, the string "{[()()]}" is properly nested but "([)()]" is not.
 *
 * Write a function:
 *
 * fun solution(S: String): Int
 *
 * that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.
 *
 * For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [0..200,000];
 * string S is made only of the following characters: '(', '{', '[', ']', '}' and/or ')'.
 *
 * https://app.codility.com/programmers/lessons/7-stacks_and_queues/brackets/
 */

class Brackets {
    /**
     * 1. Problem Explanation
     * The problem asks us to determine if a given string S, composed of parentheses '()',
     * curly braces '{}', and square brackets '[]', is properly nested. A string is properly nested
     * if:
     * - It is empty.
     * - It is enclosed by a matching pair of parentheses, braces, or brackets, and the content
     * inside is also properly nested.
     * - It is a concatenation of two properly nested strings.
     * We need to return 1 if the string is properly nested and 0 otherwise.
     *
     * 2. Pattern Identification and Rationale
     * The problem exhibits a clear pattern of matching opening and closing brackets.
     * This suggests using a stack data structure. When we encounter an opening bracket, we push it
     * onto the stack. When we encounter a closing bracket, we check if the stack is not empty and
     * if the top element of the stack is the corresponding opening bracket. If it is, we pop
     * the opening bracket from the stack. If at any point we encounter a closing bracket and
     * the stack is empty or the top element is not the matching opening bracket, the string
     * is not properly nested. Finally, after processing the entire string, if the stack is empty,
     * it means all opening brackets have been correctly closed, and the string is properly nested.
     *
     * This approach is suitable because it allows us to keep track of the open brackets and
     * efficiently check if they are closed in the correct order. The Last-In-First-Out (LIFO)
     * nature of the stack perfectly matches the nesting requirement of the brackets.
     *
     * 3. Solution Breakdown
     * - Initialize an empty stack to store opening brackets.
     * - Iterate through each character in the input string S.
     * - For each character:
     * - If the character is an opening bracket ('(', '{', '['), push it onto the stack.
     * - If the character is a closing bracket (')', '}', ']'):
     * - Check if the stack is empty. If it is, return 0 (no matching opening bracket).
     * - Pop the top element from the stack.
     * - Check if the popped element is the corresponding opening bracket for the current closing
     * bracket. If not, return 0 (mismatched brackets).
     * - After iterating through all characters, check if the stack is empty. If it is,
     * return 1 (all brackets are properly nested). Otherwise, return 0
     * (some opening brackets are not closed).
     *
     * 4. Efficient Implementation
     *
     */
    fun solution(S: String): Int {
        if (S.isEmpty()) {
            return 1
        }

        val stack = Stack<Char>()

        for (char in S) {
            when (char) {
                '(', '{', '[' -> stack.push(char)
                ')' -> if (stack.isEmpty() || stack.pop() != '(') return 0
                '}' -> if (stack.isEmpty() || stack.pop() != '{') return 0
                ']' -> if (stack.isEmpty() || stack.pop() != '[') return 0
            }
        }

        return if (stack.isEmpty()) 1 else 0
    }
}

fun main() {
    val solution = Brackets()

    // Test case 1: Properly nested
    val s1 = "{[()()]}"
    val result1 = solution.solution(s1)
    println("Input: $s1, Output: $result1") // Expected output: 1

    // Test case 2: Not properly nested
    val s2 = "([)()]"
    val result2 = solution.solution(s2)
    println("Input: $s2, Output: $result2") // Expected output: 0

    // Test case 3: Empty string (properly nested)
    val s3 = ""
    val result3 = solution.solution(s3)
    println("Input: $s3, Output: $result3") // Expected output: 1

    // Test case 4: Only opening brackets (not properly nested)
    val s4 = "{{{"
    val result4 = solution.solution(s4)
    println("Input: $s4, Output: $result4") // Expected output: 0

    // Test case 5: Only closing brackets (not properly nested)
    val s5 = "}}}"
    val result5 = solution.solution(s5)
    println("Input: $s5, Output: $result5") // Expected output: 0

    // Test case 6: Mixed and properly nested
    val s6 = "([]{})"
    val result6 = solution.solution(s6)
    println("Input: $s6, Output: $result6") // Expected output: 1

    // Test case 7: Mixed and not properly nested
    val s7 = "([{}])("
    val result7 = solution.solution(s7)
    println("Input: $s7, Output: $result7") // Expected output: 0
}