package challenges.mostafa._6_linkedlist

/**
Design a data structure to store the strings count with the ability to return the strings with
minimum and maximum counts.

Implement the AllOne class:

AllOne() Initializes the object of the data structure.
inc(String key) Increments the count of the string key by 1. If key does not exist in the data
structure, insert it with count 1.
dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after
the decrement, remove it from the data structure. It is guaranteed that key exists in
 the data structure before the decrement.
getMaxKey() Returns one of the keys with the maximal count. If no element exists, return
 an empty string "".
getMinKey() Returns one of the keys with the minimum count. If no element exists, return
an empty string "".
Note that each function must run in O(1) average time complexity.

Example 1:

Input
["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
Output
[null, null, null, "hello", "hello", null, "hello", "leet"]

Explanation
AllOne allOne = new AllOne();
allOne.inc("hello");
allOne.inc("hello");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "hello"
allOne.inc("leet");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "leet"

Constraints:

1 <= key.length <= 10
key consists of lowercase English letters.
It is guaranteed that for each call to dec, key is existing in the data structure.
At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.

https://leetcode.com/problems/all-oone-data-structure/description/
 */

internal object P8AllOOneDataStructure {

    /**
     * Problem Explanation:
     * Design a data structure that efficiently stores the counts of strings and allows retrieval
     * of strings with the minimum and maximum counts in O(1) average time.
     */

    /**
     * Pattern Identification and Rationale:
     * The core requirement of O(1) average time complexity for incrementing, decrementing,
     * and retrieving min/max counts suggests the need for a combination of data structures.
     *
     * 1. A hash map (or Kotlin's `MutableMap`) is suitable for storing the count of each string,
     * allowing for O(1) average time complexity for `inc` and `dec` operations based on the key.
     *
     * 2. To efficiently track minimum and maximum counts and the strings associated with them,
     * we can maintain a doubly linked list where each node represents a specific count. Each
     * node in the linked list will store a set of strings that have that particular count.
     * This structure allows us to move strings between count nodes in O(1) time during `inc` and `dec`.
     *
     * 3. Additionally, we'll need a way to quickly access the count node associated with a given
     * string. Another hash map can be used for this, mapping each string to its corresponding
     * count node in the linked list.
     *
     * Advantages:
     * - `inc` and `dec` operations become O(1) on average due to hash map lookups and linked list manipulations.
     * - `getMaxKey` and `getMinKey` operations become O(1) by simply accessing the head and tail
     * of the count linked list, respectively.
     */
    class AllOne {
        private val stringCounts = mutableMapOf<String, Int>()
        private val countToStrings = mutableMapOf<Int, LinkedHashSet<String>>()
        private var minCount = 0
        private var maxCount = 0

        fun inc(key: String) {
            val currentCount = stringCounts.getOrDefault(key, 0)
            val newCount = currentCount + 1
            stringCounts[key] = newCount

            // Update countToStrings
            if (currentCount > 0) {
                countToStrings[currentCount]?.remove(key)
                if (countToStrings[currentCount]?.isEmpty() == true) {
                    countToStrings.remove(currentCount)
                    if (currentCount == minCount) {
                        minCount = stringCounts.values.minOrNull() ?: 0
                    }
                }
            } else if (stringCounts.size == 1) {
                minCount = 1
                maxCount = 1
            } else if (stringCounts.isNotEmpty()) {
                minCount = minOf(minCount, 1)
            }

            countToStrings.computeIfAbsent(newCount) { LinkedHashSet() }.add(key)
            maxCount = maxOf(maxCount, newCount)
        }

        fun dec(key: String) {
            val currentCount = stringCounts[key] ?: return
            val newCount = currentCount - 1
            stringCounts[key] = newCount

            // Update countToStrings
            countToStrings[currentCount]?.remove(key)
            if (countToStrings[currentCount]?.isEmpty() == true) {
                countToStrings.remove(currentCount)
                if (currentCount == maxCount) {
                    maxCount = stringCounts.values.maxOrNull() ?: 0
                }
                if (currentCount == minCount) {
                    minCount = stringCounts.values.minOrNull() ?: 0
                }
            }

            if (newCount > 0) {
                countToStrings.computeIfAbsent(newCount) { LinkedHashSet() }.add(key)
                minCount = minOf(minCount, newCount)
            } else {
                stringCounts.remove(key)
                if (stringCounts.isEmpty()) {
                    minCount = 0
                    maxCount = 0
                } else if (currentCount == minCount) {
                    minCount = stringCounts.values.minOrNull() ?: 0
                }
            }
        }

        fun getMaxKey(): String {
            return countToStrings[maxCount]?.firstOrNull() ?: ""
        }

        fun getMinKey(): String {
            return countToStrings[minCount]?.firstOrNull() ?: ""
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val allOne = AllOne()

        allOne.inc("hello")
        allOne.inc("hello")
        println(allOne.getMaxKey())   // "hello"
        println(allOne.getMinKey())   // "hello"
        allOne.inc("leet")
        println(allOne.getMaxKey())   // "hello"
        println(allOne.getMinKey())   // "leet"
        allOne.dec("hello")
        println(allOne.getMaxKey())   // "leet"
        println(allOne.getMinKey())   // "leet"
        allOne.dec("hello")
        println(allOne.getMaxKey())   // "leet"
        println(allOne.getMinKey())   // "leet"
    }
}