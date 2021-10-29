package challenges.cracking_coding_interview.linked_list.partition

import challenges.data_structure.LinkedListNode


/**
 * Partition: Write code to partition a linked list around a value x, such that all nodes
 * less than x come before all nodes greater than or equal to x.
 * If x is contained within the list the values of x only need to be after the elements less than x (see below).
 * The partition element x can appear anywhere in the "right partition"; it does not need to
 * appear between the left and right partitions.
 * Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition=5]
 * Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
 */
object PartitionA {

    private fun partition(_node: LinkedListNode?, x: Int): LinkedListNode? {
        var node = _node
        var beforeStart: LinkedListNode? = null
        var beforeEnd: LinkedListNode? = null
        var afterStart: LinkedListNode? = null
        var afterEnd: LinkedListNode? = null

        // Partition list
        while (node != null) {
            val next = node.next
            node.next = null
            if (node.data < x) {
                if (beforeStart == null) {
                    beforeStart = node
                    beforeEnd = beforeStart
                } else {
                    beforeEnd!!.next = node
                    beforeEnd = node
                }
            } else {
                if (afterStart == null) {
                    afterStart = node
                    afterEnd = afterStart
                } else {
                    afterEnd!!.next = node
                    afterEnd = node
                }
            }
            node = next
        }

        // Merge before list and after list
        if (beforeStart == null) return afterStart

        beforeEnd!!.next = afterStart
        return beforeStart
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Create linked list
        val values = intArrayOf(33, 9, 2, 3, 10, 10389, 838, 874578, 5)
        val head = LinkedListNode(values[0], null, null)
        var current = head
        for (i in 1 until values.size) {
            current = LinkedListNode(values[i], null, current)
        }
        println(head.printForward())

        val h = partition(head, 3)

        println(h!!.printForward())
    }

}