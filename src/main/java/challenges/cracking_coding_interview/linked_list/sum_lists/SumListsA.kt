package challenges.cracking_coding_interview.linked_list.sum_lists

import challenges.data_structure.LinkedListNode


object SumListsA {
    private fun addLists(l1: LinkedListNode, l2: LinkedListNode): LinkedListNode? {
        return addLists(l1, l2, 0)
    }

    private fun addLists(l1: LinkedListNode?, l2: LinkedListNode?, carry: Int): LinkedListNode? {
        if (l1 == null && l2 == null && carry == 0) {
            return null
        }
        val result = LinkedListNode()
        var value = carry
        if (l1 != null) {
            value += l1.data
        }
        if (l2 != null) {
            value += l2.data
        }
        result.data = value % 10

        // Recurse
        if (l1 != null || l2 != null) {
            val more = addLists(
                l1?.next,
                l2?.next,
                if (value >= 10) 1 else 0
            )
            result.next = more
        }
        return result
    }

    private fun linkedListToInt(node: LinkedListNode?): Int {
        var value = 0
        if (node!!.next != null) {
            value = 10 * linkedListToInt(node.next)
        }
        return value + node.data
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val lA1 = LinkedListNode(9, null, null)
        val lA2 = LinkedListNode(9, null, lA1)
        val lA3 = LinkedListNode(9, null, lA2)
        val lB1 = LinkedListNode(1, null, null)
        val lB2 = LinkedListNode(0, null, lB1)
        val lB3 = LinkedListNode(0, null, lB2)
        val list3 = addLists(lA1, lB1)
        println("  " + lA1.printForward())
        println("+ " + lB1.printForward())
        println("= " + list3!!.printForward())
        val l1 = linkedListToInt(lA1)
        val l2 = linkedListToInt(lB1)
        val l3 = linkedListToInt(list3)
        print("$l1 + $l2 = $l3\n")
        print(l1.toString() + " + " + l2 + " = " + (l1 + l2))
    }
}