package challenges.cracking_coding_interview.stacks_queues.three_in_one

import challenges.util.AssortedMethods
import java.util.*

class FixedMultiStack(private val stackCapacity: Int) {
    private val numberOfStacks = 3
    val values: IntArray = IntArray(stackCapacity * numberOfStacks)
    private val sizes: IntArray = IntArray(numberOfStacks)

    /* Push value onto stack. */
    fun push(stackNum: Int, value: Int) {
        /* Check that we have space for the next element */
        if (isFull(stackNum)) {
            throw FullStackException()
        }

        /* Increment stack pointer and then update top value. */sizes[stackNum]++
        values[indexOfTop(stackNum)] = value
    }

    /* Pop item from top stack. */
    fun pop(stackNum: Int): Int {
        if (isEmpty(stackNum)) {
            throw EmptyStackException()
        }
        val topIndex = indexOfTop(stackNum)
        val value = values[topIndex] // Get top
        values[topIndex] = 0 // Clear
        sizes[stackNum]-- // Shrink
        return value
    }

    /* Return top element. */
    fun peek(stackNum: Int): Int {
        if (isEmpty(stackNum)) {
            throw EmptyStackException()
        }
        return values[indexOfTop(stackNum)]
    }

    /* Return if stack is empty. */
    fun isEmpty(stackNum: Int): Boolean {
        return sizes[stackNum] == 0
    }

    /* Return if stack is full. */
    fun isFull(stackNum: Int): Boolean {
        return sizes[stackNum] == stackCapacity
    }

    /* Returns index of the top of the stack. */
    private fun indexOfTop(stackNum: Int): Int {
        val offset = stackNum * stackCapacity
        val size = sizes[stackNum]
        return offset + size - 1
    }

    private fun getStackValues(stackNum: Int): IntArray {
        val items = IntArray(sizes[stackNum])
        for (i in items.indices) {
            items[i] = values[stackNum * stackCapacity + i]
        }
        return items
    }

    fun stackToString(stackNum: Int): String {
        val items = getStackValues(stackNum)
        return stackNum.toString() + ": " + AssortedMethods.arrayToString(items)
    }

}