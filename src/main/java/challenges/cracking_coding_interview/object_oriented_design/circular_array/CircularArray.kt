package challenges.cracking_coding_interview.object_oriented_design.circular_array

class CircularArray<T>(size: Int) : Iterable<T> {
    private val items: Array<T?>
    private var head = 0
    private fun convert(index: Int): Int {
        var index = index
        if (index < 0) {
            index += items.size
        }
        return (head + index) % items.size
    }

    fun rotate(shiftRight: Int) {
        head = convert(shiftRight)
    }

    operator fun get(i: Int): T? {
        if (i < 0 || i >= items.size) {
            throw IndexOutOfBoundsException("Index $i is out of bounds")
        }
        return items[convert(i)]
    }

    operator fun set(i: Int, item: T) {
        items[convert(i)] = item
    }

    override fun iterator(): MutableIterator<T> {
        return CircularArrayIterator()
    }

    private inner class CircularArrayIterator : MutableIterator<T> {
        private var _current = -1
        override fun hasNext(): Boolean {
            return _current < items.size - 1
        }

        override fun next(): T {
            _current++
            return items[convert(_current)]!!
        }

        override fun remove() {
            throw UnsupportedOperationException("Remove is not supported by CircularArray")
        }
    }

    init {
        items = arrayOfNulls<Any>(size) as Array<T?>
    }
}