package challenges.cracking_coding_interview.stacks_queues.three_in_one

import challenges.util.AssortedMethods.arrayToString
import java.util.*


class MultiStack(numberOfStacks: Int, defaultSize: Int) {
    /* StackInfo is a simple class that holds a set of data about
	 * each stack. It does not hold the actual items in the stack.
	 * We could have done this with just a bunch of individual
	 * variables, but that’s messy and doesn’t gain us much. */
    private inner class StackInfo(var start: Int, var capacity: Int) {
        var size = 0

        /* Check if an index on the full array is within the stack
         * boundaries. The stack can wrap around to the start of
         * the array. */
        fun isWithinStackCapacity(index: Int): Boolean {
            /* If outside of bounds of array, return false. */
            if (index < 0 || index >= values.size) {
                return false
            }

            /* If index wraps around, adjust it. */
            val contiguousIndex = if (index < start) index + values.size else index
            val end = start + capacity
            return contiguousIndex in start until end
        }

        fun lastCapacityIndex(): Int {
            return adjustIndex(start + capacity - 1)
        }

        fun lastElementIndex(): Int {
            return adjustIndex(start + size - 1)
        }

        val isFull: Boolean
            get() = size == capacity
        val isEmpty: Boolean
            get() = size == 0

    }

    private val info: Array<StackInfo?>
    val values: IntArray

    /* Returns the number of items actually present in stack. */
    private fun numberOfElements(): Int {
        var size = 0
        for (sd in info) {
            size += sd!!.size
        }
        return size
    }

    /* Returns true is all the stacks are full. */
    private fun allStacksAreFull(): Boolean {
        return numberOfElements() == values.size
    }

    /* Adjust index to be within the range of 0 -> length - 1. */
    private fun adjustIndex(index: Int): Int {
        /* Java's mod operator can return neg values. For example,
		 * (-11 % 5) will return -1, not 4. We actually want the
		 * value to be 4 (since we're wrapping around the index).
		 */
        val max = values.size
        return (index % max + max) % max
    }

    /* Get index after this index, adjusted for wrap around. */
    private fun nextIndex(index: Int): Int {
        return adjustIndex(index + 1)
    }

    /* Get index before this index, adjusted for wrap around. */
    private fun previousIndex(index: Int): Int {
        return adjustIndex(index - 1)
    }

    /* Shift items in stack over by one element. If we have
	 * available capacity, then we'll end up shrinking the stack
	 * by one element. If we don't have available capacity, then
	 * we'll need to shift the next stack over too. */
    private fun shift(stackNum: Int) {
        println("/// Shifting $stackNum")
        val stack = info[stackNum]

        /* If this stack is at its full capacity, then you need
		 * to move the next stack over by one element. This stack
		 * can now claim the freed index. */if (stack!!.size >= stack.capacity) {
            val nextStack = (stackNum + 1) % info.size
            shift(nextStack)
            stack.capacity++ // claim index that next stack lost
        }

        /* Shift all elements in stack over by one. */
        var index = stack.lastCapacityIndex()
        while (stack.isWithinStackCapacity(index)) {
            values[index] = values[previousIndex(index)]
            index = previousIndex(index)
        }

        /* Adjust stack data. */values[stack.start] = 0 // Clear item
        stack.start = nextIndex(stack.start) // move start
        stack.capacity-- // Shrink capacity
    }

    /* Expand stack by shifting over other stacks */
    private fun expand(stackNum: Int) {
        println("/// Expanding stack $stackNum")
        shift((stackNum + 1) % info.size)
        info[stackNum]!!.capacity++
    }

    /* Push value onto stack num, shifting/expanding stacks as
	 * necessary. Throws exception if all stacks are full. */
    fun push(stackNum: Int, value: Int) {
        println("/// Pushing stack $stackNum: $value")
        if (allStacksAreFull()) {
            throw FullStackException()
        }

        /* If this stack is full, expand it. */
        val stack = info[stackNum]
        if (stack!!.isFull) {
            expand(stackNum)
        }

        /* Find the index of the top element in the array + 1,
		 * and increment the stack pointer */stack.size++
        values[stack.lastElementIndex()] = value
    }

    /* Remove value from stack. */
    fun pop(stackNum: Int): Int {
        println("/// Popping stack $stackNum")
        val stack = info[stackNum]
        if (stack!!.isEmpty) {
            throw EmptyStackException()
        }

        /* Remove last element. */
        val value = values[stack.lastElementIndex()]
        values[stack.lastElementIndex()] = 0 // Clear item
        stack.size-- // Shrink size
        return value
    }

    /* Get top element of stack.*/
    fun peek(stackNum: Int): Int {
        val stack = info[stackNum]
        return values[stack!!.lastElementIndex()]
    }

    fun getStackValues(stackNum: Int): IntArray {
        val stack = info[stackNum]
        val items = IntArray(stack!!.size)
        for (i in items.indices) {
            items[i] = values[adjustIndex(stack.start + i)]
        }
        return items
    }

    fun stackToString(stackNum: Int): String {
        val items = getStackValues(stackNum)
        return stackNum.toString() + ": " + arrayToString(items)
    }

    init {
        /* Create metadata for all the stacks. */
        info = arrayOfNulls(numberOfStacks)
        for (i in 0 until numberOfStacks) {
            info[i] = StackInfo(defaultSize * i, defaultSize)
        }
        values = IntArray(numberOfStacks * defaultSize)
    }
}