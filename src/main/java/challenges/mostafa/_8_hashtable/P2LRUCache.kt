package challenges.mostafa._8_hashtable

/**
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise,
add the key-value pair to the cache. If the number of keys exceeds the capacity from
this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.

Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4

Constraints:

1 <= capacity <= 3000
0 <= key <= 10^4
0 <= value <= 10^5
At most 2 * 10^5 calls will be made to get and put.

https://leetcode.com/problems/lru-cache/submissions/1294597231/
 */


/**
 * Problem Explanation:
 * (Same as before) The goal is to implement an LRU cache with O(1) average time complexity for get
 * and put operations, evicting the least recently used item when the capacity is exceeded.
 *
 * Pattern Identification and Rationale:
 * This solution cleverly utilizes the properties of LinkedHashMap. A LinkedHashMap in Kotlin (and Java)
 * maintains the order of its entries. By default, it orders entries based on insertion order. However,
 * it can also be configured to order entries by access order. In this implementation, while it's not
 * explicitly configured for access order during initialization, the get operation cleverly manipulates
 * the insertion order to achieve the LRU behavior.
 *
 * HashMap for O(1) average lookup: LinkedHashMap extends HashMap, so it provides O(1) average time
 * complexity for key lookups (containsKey).
 * Maintaining Order for LRU: By removing and re-inserting the key in the get operation, we move it
 * to the end of the insertion order, effectively marking it as the most recently used. In the put operation,
 * if the key exists, removing and re-inserting updates its position. When the capacity is exceeded,
 * the first element in the insertion order is the least recently used and can be easily removed.
 *
 * Solution Breakdown:
 *
 * LRUCache(capacity: Int):
 *
 * Initializes a LinkedHashMap with the given capacity. By default, LinkedHashMap maintains insertion order.
 * get(key: Int): Int:
 *
 * Checks if the key exists in the cache. If not, returns -1.
 * If the key exists:
 * Retrieves the value associated with the key.
 * Removes the key from the cache.
 * Re-inserts the key with its value. Because LinkedHashMap maintains insertion order, this effectively
 * moves the accessed key to the end of the order, marking it as recently used.
 * Returns the retrieved value.
 * put(key: Int, value: Int):
 *
 * If the key already exists in the cache, it's removed.
 * The key-value pair is then inserted into the cache. This will place it at the end of the insertion
 * order, making it the most recently used.
 * If the size of the cache exceeds the capacity, the first entry in the insertion order (which is the least
 * recently used due to our get and put logic) is removed using cache.keys.first().
 * Time Complexity:
 *
 * get(key: Int):
 *
 * containsKey: O(1) average.
 * cache[key]!!: O(1) average.
 * remove(key): O(1) average.
 * cache[key] = value: O(1) average.
 * Overall, get has an average time complexity of O(1).
 * put(key: Int, value: Int):
 *
 * containsKey: O(1) average.
 * remove(key): O(1) average.
 * cache[key] = value: O(1) average.
 * cache.size: O(1).
 * cache.keys.first(): While technically it iterates to find the first key, in practice, for LinkedHashMap,
 * it's often optimized to be close to O(1).
 * cache.remove(...): O(1) average.
 * Overall, put has an average time complexity of O(1).
 * Efficient Implementation:
 * This implementation is highly efficient and readable due to its direct use of LinkedHashMap. It avoids
 * the need for explicit doubly linked list management, making the code cleaner and less prone to pointer
 * manipulation errors. LinkedHashMap is specifically designed for scenarios like LRU caches, providing
 * the necessary ordering and efficient operations.
 */
class LRUCache(private val capacity: Int) {
    private val cache = LinkedHashMap<Int, Int>()

    fun get(key: Int): Int {
        if (!cache.containsKey(key)) return -1

        val value = cache[key]!!
        cache.remove(key)
        cache[key] = value
        return value
    }

    fun put(key: Int, value: Int) {
        if (cache.containsKey(key)) cache.remove(key)
        cache[key] = value
        if (cache.size > capacity) cache.remove(cache.keys.first())
    }

}

fun main(args: Array<String>) {
    val lRUCache = LRUCache(2)
    lRUCache.put(1, 1) // cache is {1=1}
    lRUCache.put(2, 2) // cache is {1=1, 2=2}
    println(lRUCache.get(1)) // return 1
    lRUCache.put(3, 3) // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    println(lRUCache.get(2)) // returns -1 (not found)
    lRUCache.put(4, 4) // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    println(lRUCache.get(1)) // return -1 (not found)
    println(lRUCache.get(3)) // return 3
    println(lRUCache.get(4)) // return 4
}

