package challenges.cracking_coding_interview.stacks_queues.queue_via_stacks

import java.util.*


class MyQueue<T> {
    var stackNewest: Stack<T> = Stack<T>()
    var stackOldest: Stack<T> = Stack<T>()

    fun size(): Int {
        return stackNewest.size + stackOldest.size
    }

    fun add(value: T) {
        // Push onto stack1
        stackNewest.push(value)
    }

    /* Move elements from stackNewest into stackOldest. This is usually done so that we can
	 * do operations on stackOldest.
	 */
    private fun shiftStacks() {
        if (stackOldest.isEmpty()) {
            while (!stackNewest.isEmpty()) {
                stackOldest.push(stackNewest.pop())
            }
        }
    }

    fun peek(): T {
        shiftStacks()
        return stackOldest.peek() // retrieve the oldest item.
    }

    fun remove(): T {
        shiftStacks()
        return stackOldest.pop() // pop the oldest item.
    }

}