package challenges.cracking_coding_interview.stacks_queues.stack_of_plates

import java.util.*


class Stack(private val capacity: Int) {
    var top: Node? = null
    var bottom: Node? = null
    var size = 0
    val isFull: Boolean
        get() = capacity == size

    private fun join(above: Node?, below: Node?) {
        if (below != null) below.above = above
        if (above != null) above.below = below
    }

    fun push(v: Int): Boolean {
        if (size >= capacity) return false
        size++
        val n = Node(v)
        if (size == 1) bottom = n
        join(n, top)
        top = n
        return true
    }

    fun pop(): Int {
        val top = top ?: throw EmptyStackException()
        val t: Node = top
        this.top = top.below
        size--
        return t.value
    }

    val isEmpty: Boolean
        get() = size == 0

    fun removeBottom(): Int {
        val b: Node? = bottom
        bottom = bottom?.above
        if (bottom != null) bottom?.below = null
        size--
        return b?.value ?: -1
    }
}