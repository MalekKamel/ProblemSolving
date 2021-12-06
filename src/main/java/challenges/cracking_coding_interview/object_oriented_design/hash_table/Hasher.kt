package challenges.cracking_coding_interview.object_oriented_design.hash_table

class Hasher<K, V>(capacity: Int) {
    private class LinkedListNode<K, V>(var key: K, var value: V) {
        var next: LinkedListNode<K, V>? = null
        var prev: LinkedListNode<K, V>? = null
        fun printForward(): String {
            val data = "($key,$value)"
            return if (next != null) {
                data + "->" + next!!.printForward()
            } else {
                data
            }
        }
    }

    private val arr: ArrayList<LinkedListNode<K, V>?>

    /* Insert key and value into hash table. */
    fun put(key: K, value: V): V? {
        var node = getNodeForKey(key)
        if (node != null) {
            val oldValue = node.value
            node.value = value // just update the value.
            return oldValue
        }
        node = LinkedListNode(key, value)
        val index = getIndexForKey(key)
        if (arr[index] != null) {
            node.next = arr[index]
            node.next!!.prev = node
        }
        arr[index] = node
        return null
    }

    /* Remove node for key. */
    fun remove(key: K): V? {
        val node = getNodeForKey(key) ?: return null
        if (node.prev != null) {
            node.prev!!.next = node.next
        } else {
            /* Removing head - update. */
            val hashKey = getIndexForKey(key)
            arr[hashKey] = node.next
        }
        if (node.next != null) {
            node.next!!.prev = node.prev
        }
        return node.value
    }

    /* Get value for key. */
    operator fun get(key: K?): V? {
        if (key == null) return null
        val node = getNodeForKey(key)
        return node?.value
    }

    /* Get linked list node associated with a given key. */
    private fun getNodeForKey(key: K): LinkedListNode<K, V>? {
        val index = getIndexForKey(key)
        var current = arr[index]
        while (current != null) {
            if (current.key === key) {
                return current
            }
            current = current.next
        }
        return null
    }

    /* Really stupid function to map a key to an index. */
    fun getIndexForKey(key: K): Int {
        return Math.abs(key.hashCode() % arr.size)
    }

    fun printTable() {
        for (i in arr.indices) {
            val s = if (arr[i] == null) "" else arr[i]!!.printForward()
            println("$i: $s")
        }
    }

    init {
        /* Create list of linked lists. */
        arr = ArrayList()
        arr.ensureCapacity(capacity)
        for (i in 0 until capacity) {
            arr.add(null)
        }
    }
}