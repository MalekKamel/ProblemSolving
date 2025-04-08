package challenges.mostafa._4_stack

/**
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.


Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2


Constraints:

-231 <= val <= 231 - 1
Methods pop, top and getMin operations will always be called on non-empty stacks.
At most 3 * 104 calls will be made to push, pop, top, and getMin.

https://leetcode.com/problems/min-stack/description/
 */

internal object P2MinStack {

    class MinStack {
        private val stack: MutableList<Pair<Int, Int>> = mutableListOf()

        fun push(value: Int) {
            // If the stack is empty, push the value and the value as the minimum
            if (stack.isEmpty()) {
                stack.add(Pair(value, value))
            } else {
                // Otherwise, push the value and the new minimum value
                val currentMin = stack.last().second
                stack.add(Pair(value, minOf(currentMin, value)))
            }
        }

        fun pop() {
            stack.removeLast()
        }

        fun top(): Int {
            return stack.last().first
        }

        fun getMin(): Int {
            return stack.last().second
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val minStack = MinStack()

        minStack.push(5)
        println("Pushed 5 to the stack. Current minimum: ${minStack.getMin()}")
        // Output: Pushed 5 to the stack. Current minimum: 5

        minStack.push(2)
        println("Pushed 2 to the stack. Current minimum: ${minStack.getMin()}")
        // Output: Pushed 2 to the stack. Current minimum: 2

        minStack.push(3)
        println("Pushed 3 to the stack. Current minimum: ${minStack.getMin()}")
        // Output: Pushed 3 to the stack. Current minimum: 2

        println("Top element: ${minStack.top()}")
        // Output: Top element: 3

        minStack.pop()
        println("Popped the top element. Current minimum: ${minStack.getMin()}")
        // Output: Popped the top element. Current minimum: 2

        println("Top element: ${minStack.top()}")
        // Output: Top element: 2
    }
}