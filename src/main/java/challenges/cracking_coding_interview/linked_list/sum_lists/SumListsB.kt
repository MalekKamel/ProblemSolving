package challenges.cracking_coding_interview.linked_list.sum_lists

import challenges.data_structure.LinkedListNode


object SumListsB {
    private fun length(l: LinkedListNode?): Int {
        return if (l == null) {
            0
        } else {
            1 + length(l.next)
        }
    }

    private fun addListsHelper(l1: LinkedListNode?, l2: LinkedListNode?): PartialSum {
        if (l1 == null && l2 == null) {
            return PartialSum()
        }
        val sum = addListsHelper(l1!!.next, l2!!.next)
        val value = sum.carry + l1.data + l2.data
        val fullResult = insertBefore(sum.sum, value % 10)
        sum.sum = fullResult
        sum.carry = value / 10
        return sum
    }

    private fun addLists(_l1: LinkedListNode, _l2: LinkedListNode): LinkedListNode? {
        var l1: LinkedListNode? = _l1
        var l2: LinkedListNode? = _l2
        val len1 = length(l1)
        val len2 = length(l2)
        if (len1 < len2) {
            l1 = padList(l1, len2 - len1)
        } else {
            l2 = padList(l2, len1 - len2)
        }
        val sum = addListsHelper(l1, l2)
        return if (sum.carry == 0) {
            sum.sum
        } else {
            insertBefore(sum.sum, sum.carry)
        }
    }

    private fun padList(l: LinkedListNode?, padding: Int): LinkedListNode? {
        var head = l
        for (i in 0 until padding) {
            head = insertBefore(head, 0)
        }
        return head
    }

    private fun insertBefore(list: LinkedListNode?, data: Int): LinkedListNode {
        val node = LinkedListNode(data)
        if (list != null) {
            node.next = list
        }
        return node
    }

    private fun linkedListToInt(node: LinkedListNode?): Int {
        var node = node
        var value = 0
        while (node != null) {
            value = value * 10 + node.data
            node = node.next
        }
        return value
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val lA1 = LinkedListNode(3, null, null)
        val lA2 = LinkedListNode(1, null, lA1)
        val lB1 = LinkedListNode(5, null, null)
        val lB2 = LinkedListNode(9, null, lB1)
        val lB3 = LinkedListNode(1, null, lB2)
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