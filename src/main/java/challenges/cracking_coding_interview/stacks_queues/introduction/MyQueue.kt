package challenges.cracking_coding_interview.stacks_queues.introduction


class MyQueue<T> {
    private class QueueNode<T>(val data: T) {
        var next: QueueNode<T>? = null
    }

    private var first: QueueNode<T>? = null
    private var last: QueueNode<T>? = null

    fun add(item: T) {
        val t = QueueNode(item)
        if (last != null) {
            last!!.next = t
        }
        last = t
        if (first == null) {
            first = last
        }
    }

    fun remove(): T {
        val first = first ?: throw NoSuchElementException()
        val data = first.data
        this.first = first.next
        if (this.first == null) {
            last = null
        }
        return data
    }

    fun peek(): T {
        val first = first ?: throw NoSuchElementException()
        return first.data
    }

    val isEmpty: Boolean
        get() = first == null
}