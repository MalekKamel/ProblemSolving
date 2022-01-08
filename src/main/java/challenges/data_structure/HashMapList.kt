package challenges.data_structure

import java.util.HashMap
import java.util.ArrayList

class HashMapList<T, E> {
    private val map = HashMap<T, ArrayList<E>>()

    /* Insert item into list at key. */
    fun put(key: T, item: E) {
        if (!map.containsKey(key)) {
            map[key] = ArrayList()
        }
        map[key]!!.add(item)
    }

    /* Insert list of items at key. */
    fun put(key: T, items: ArrayList<E>) {
        map[key] = items
    }

    /* Get list of items at key. */
    operator fun get(key: T): ArrayList<E> {
        return map[key]!!
    }

    /* Check if hashmaplist contains key. */
    fun containsKey(key: T): Boolean {
        return map.containsKey(key)
    }

    /* Check if list at key contains value. */
    fun containsKeyValue(key: T, value: E): Boolean {
        val list = get(key) ?: return false
        return list.contains(value)
    }

    /* Get the list of keys. */
    fun keySet(): Set<T> {
        return map.keys
    }

    override fun toString(): String {
        return map.toString()
    }
}