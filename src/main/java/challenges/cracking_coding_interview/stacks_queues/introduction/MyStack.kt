package challenges.cracking_coding_interview.stacks_queues.introduction

import java.util.*


class MyStack<T> {
    private inner class StackNode<T>(val data: T) {
        var next: StackNode<T>? = null
    }

    private var top: StackNode<T>? = null

    fun pop(): T {
        val top = top ?: throw EmptyStackException()
        val item = top.data
        this.top = top.next
        return item
    }

    fun push(item: T) {
        val t = StackNode(item)
        t.next = top
        top = t
    }

    fun peek(): T {
        val top = top ?: throw EmptyStackException()
        return top.data
    }

    val isEmpty: Boolean
        get() = top == null
}