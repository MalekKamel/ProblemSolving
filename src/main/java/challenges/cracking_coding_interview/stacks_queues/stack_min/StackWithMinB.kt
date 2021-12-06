package challenges.cracking_coding_interview.stacks_queues.stack_min

import java.util.*


class StackWithMinB : Stack<Int>() {
    var s2: Stack<Int> = Stack()

    override fun push(value: Int): Int {
        if (value <= min()) {
            s2.push(value)
        }
        return super.push(value)
    }

    override fun pop(): Int {
        val value = super.pop()
        if (value == min()) {
            s2.pop()
        }
        return value
    }

    fun min(): Int {
        return if (s2.isEmpty()) {
            Int.MAX_VALUE
        } else {
            s2.peek()
        }
    }

}
