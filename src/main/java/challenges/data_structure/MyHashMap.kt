package challenges.data_structure


class MyHashMap {
    private val entries = arrayOfNulls<Entry<String, String>?>(INITIAL_SIZE)

    class Entry<K, V>(var key: K, var value: V) {
        var next: Entry<K, V>? = null

        override fun toString(): String {
            return "[$key, $value]"
        }
    }

    fun put(key: String, value: String) {
        val hash = hashOf(key)
        val entry = Entry(key, value)
        if (entries[hash] == null) {
            entries[hash] = entry
            return
        }
        var temp = entries[hash]!!
        while (temp.next != null) {
            if (temp.key.equals(key, ignoreCase = true)) {
                temp.value = value
                return
            }
            temp = temp.next!!
        }
        temp.next = entry
    }

    operator fun get(key: String): String? {
        val hash = hashOf(key)
        if (entries[hash] == null) return null

        var temp = entries[hash]!!

        // Check the entry linked list for march
        // for the given 'key'
        while (temp.key != key && temp.next != null) {
            temp = temp.next!!
        }
        return temp.value
    }


    fun remove(key: String): Boolean {
        val hash = hashOf(key)
        if (entries[hash] == null) return false
        // In case of the first item, we have to remove it and replace it with the next item
        if (entries[hash]!!.key.equals(key, ignoreCase = true)) {
            entries[hash] = entries[hash]!!.next
            return true
        }
        // In other item, just override the next of the previous with the next of current item
        var temp = entries[hash]
        var prevTemp = entries[hash]
        while (temp != null) {
            if (temp.key.equals(key, ignoreCase = true)) {
                prevTemp!!.next = temp.next
                return true
            }
            prevTemp = temp
            temp = temp.next
        }
        return false
    }

    operator fun contains(key: String): Boolean {
        val hash = hashOf(key)
        if (entries[hash] == null) return false
        var temp = entries[hash]
        while (temp != null) {
            if (temp.key.equals(key, ignoreCase = true)) return true
            temp = temp.next
        }
        return false
    }

    fun size(): Int {
        var count = 0
        for (entry in entries) {
            var item = entry
            while (item != null) {
                item = item.next
                count++
            }
        }
        return count
    }

    private fun hashOf(key: String): Int {
        // piggy backing on java string
        // hashcode implementation.
        return key.hashCode() % INITIAL_SIZE
    }

    override fun toString(): String {
        var bucket = 0
        val hashTableStr = StringBuilder()
        for (entry in entries) {
            if (entry == null) {
                continue
            }
            hashTableStr.append("\n bucket[")
                .append(bucket)
                .append("] = ")
                .append(entry.toString())
            bucket++
            var temp = entry.next
            while (temp != null) {
                hashTableStr.append(" -> ")
                hashTableStr.append(temp.toString())
                temp = temp.next
            }
        }
        return hashTableStr.toString()
    }

    companion object {
        private const val INITIAL_SIZE = 16

        @JvmStatic
        fun main(args: Array<String>) {
            val hashTable = MyHashMap()
            // Put some key values.
            for (i in 0..29) {
                val key = i.toString()
                hashTable.put(key, key)
            }

            println("****   HashTable  ***")
            println(hashTable.toString())
            println(
                """
                    
                    Value for key(20) : ${hashTable["20"]}
                    """.trimIndent()
            )
            println("Contains 20: ${hashTable.contains("20")}")
            println("Contains 100: ${hashTable.contains("100")}")

            println("Size: ${hashTable.size()}")

            println("Remove 20: ${hashTable.remove("20")}")
            println("Contains 20: ${hashTable.contains("20")}")

            println("Remove 100: ${hashTable.remove("100")}")
            println("Contains 100: ${hashTable.contains("100")}")

            println("Remove 14: ${hashTable.remove("14")}")
            println("Contains 14: ${hashTable.contains("14")}")

            println("Size: ${hashTable.size()}")

            println(hashTable.toString())

/*
 Result:

 bucket[0] = [0, 0] -> [11, 11] -> [22, 22]
 bucket[1] = [1, 1] -> [12, 12] -> [23, 23]
 bucket[2] = [2, 2] -> [13, 13] -> [24, 24]
 bucket[3] = [3, 3] -> [14, 14] -> [25, 25]
 bucket[4] = [4, 4] -> [15, 15] -> [26, 26]
 bucket[5] = [5, 5] -> [16, 16] -> [27, 27]
 bucket[6] = [6, 6] -> [17, 17] -> [28, 28]
 bucket[7] = [7, 7] -> [18, 18] -> [29, 29]
 bucket[8] = [8, 8] -> [19, 19]
 bucket[9] = [9, 9]
 bucket[10] = [20, 20]
 bucket[11] = [10, 10] -> [21, 21]

 Value for key(20) : 20
 Contains 20: true
 Contains 100: false
 Size: 30
 Remove 20: true
 Contains 20: false
 Remove 100: false
 Contains 100: false
 Remove 14: true
 Contains 14: false
 Size: 28

 bucket[0] = [0, 0] -> [11, 11] -> [22, 22]
 bucket[1] = [1, 1] -> [12, 12] -> [23, 23]
 bucket[2] = [2, 2] -> [13, 13] -> [24, 24]
 bucket[3] = [3, 3] -> [25, 25]
 bucket[4] = [4, 4] -> [15, 15] -> [26, 26]
 bucket[5] = [5, 5] -> [16, 16] -> [27, 27]
 bucket[6] = [6, 6] -> [17, 17] -> [28, 28]
 bucket[7] = [7, 7] -> [18, 18] -> [29, 29]
 bucket[8] = [8, 8] -> [19, 19]
 bucket[9] = [9, 9]
 bucket[10] = [10, 10] -> [21, 21]
*/
        }
    }
}