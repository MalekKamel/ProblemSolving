package challenges.hackerrank

import java.util.*

/*
Given an expression string s, write a program to examine whether the pairs
and the orders of “{“, “}”, “(“, “)”, “[“, “]” are correct in s.
 */
object BalancedParentheses {

    fun isValid(s: String): Boolean {
        val chars = s.toCharArray()
        val stack = Stack<Char>()

        for (ch in chars) {
            if (!stack.isEmpty() && ch == '}' && stack.peek() == '{') {
                stack.pop()
                continue
            }
            if (!stack.isEmpty() && ch == ']' && stack.peek() == '[') {
                stack.pop()
                continue
            }
            if (!stack.isEmpty() && ch == ')' && stack.peek() == '(') {
                stack.pop()
                continue
            }
            stack.push(ch)
        }

        return stack.isEmpty()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(isValid("{}")) // true
        println(isValid("{}{}")) // true
        println(isValid("{{}}")) // true
        println(isValid("}{}")) // false
        println(isValid("{}}")) // false
        println(isValid("}{")) // false
        println(isValid("{}}{")) // false

        println(isValid("()")) // true
        println(isValid("(())")) // true
        println(isValid("()(")) // false
        println(isValid(")()(")) // false

        println(isValid("[]")) // true
        println(isValid("[[]]")) // true
        println(isValid("][]")) // false
        println(isValid("][][")) // false
    }
}