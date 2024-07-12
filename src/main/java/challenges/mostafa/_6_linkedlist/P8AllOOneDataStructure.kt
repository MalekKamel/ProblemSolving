package challenges.mostafa._6_linkedlist

/**
Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.

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

    class AllOne {
        private val countMap = mutableMapOf<String, Int>()
        private val countToKeys = mutableMapOf<Int, LinkedHashSet<String>>()
        private var minCount = 0
        private var maxCount = 0

        fun inc(key: String) {
            // 1. Handle countMap
            val count = countMap.getOrDefault(key, 0)
            countMap[key] = count + 1

            // 2. Handle cuntToKeys remove
            if (count > 0) {
                countToKeys[count]?.remove(key)
                if (countToKeys[count]?.isEmpty() == true) {
                    countToKeys.remove(count)
                    if (count == minCount) minCount++
                }
            } else {
                minCount = 1
            }

            // 3. Handle cuntToKeys add
            countToKeys.getOrPut(count + 1) { LinkedHashSet() }.add(key)

            // 4. Handle maxCount
            maxCount = maxOf(maxCount, count + 1)
        }

        fun dec(key: String) {
            val count = countMap[key] ?: return

            // 1. Remove The key
            if (count == 1) {
                countMap.remove(key)
                countToKeys[count]?.remove(key)
                if (countToKeys[count]?.isEmpty() == true) {
                    countToKeys.remove(count)
                    if (count == minCount)
                        minCount = if (countMap.isEmpty()) 0 else countToKeys.keys.min()
                }
                return
            }

            // 2. Decrement count
            countMap[key] = count - 1

            // 3. Remove key from countToKeys
            countToKeys[count]?.remove(key)
            if (countToKeys[count]?.isEmpty() == true) {
                countToKeys.remove(count)
                if (count == maxCount) maxCount--
            }

            // 4. Add key to countToKeys
            countToKeys.getOrPut(count - 1) { LinkedHashSet() }.add(key)

            // 5. Minimize
            if (count - 1 < minCount) minCount = count - 1
        }

        fun getMaxKey(): String {
            return countToKeys[maxCount]?.firstOrNull() ?: ""
        }

        fun getMinKey(): String {
            return countToKeys[minCount]?.firstOrNull() ?: ""
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