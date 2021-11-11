package challenges.cracking_coding_interview.stacks_queues.stack_of_plates

import java.util.*


class SetOfStacks(var capacity: Int) {
    var stacks = ArrayList<Stack>()
    private val lastStack: Stack?
        get() = if (stacks.size == 0) {
            null
        } else stacks[stacks.size - 1]

    fun push(v: Int) {
        val last = lastStack
        if (last != null && !last.isFull) { // add to last
            last.push(v)
        } else { // must create new stack
            val stack = Stack(
                capacity
            )
            stack.push(v)
            stacks.add(stack)
        }
    }

    fun pop(): Int {
        val last = lastStack ?: throw EmptyStackException()
        val v = last.pop()
        if (last.size == 0) {
            stacks.removeAt(stacks.size - 1)
        }
        return v
    }

    fun popAt(index: Int): Int {
        return leftShift(index, true)
    }

    private fun leftShift(index: Int, removeTop: Boolean): Int {
        val stack = stacks[index]
        val removedItem: Int = if (removeTop) stack.pop() else stack.removeBottom()
        if (stack.isEmpty) {
            stacks.removeAt(index)
        } else if (stacks.size > index + 1) {
            val v = leftShift(index + 1, false)
            stack.push(v)
        }
        return removedItem
    }

    val isEmpty: Boolean
        get() {
            val last = lastStack
            return last == null || last.isEmpty
        }

}