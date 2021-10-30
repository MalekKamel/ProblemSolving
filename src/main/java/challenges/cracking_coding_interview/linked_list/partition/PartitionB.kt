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
object PartitionB {

    private fun partition(_node: LinkedListNode, x: Int): LinkedListNode? {
        var node: LinkedListNode? = _node
        var beforeStart: LinkedListNode? = null
        var afterStart: LinkedListNode? = null

        // Partition list
        while (node != null) {
            val next = node.next
            if (node.data < x) {
                // Insert node into start of before list
                node.next = beforeStart
                beforeStart = node
            } else {
                // Insert node into front of after list
                node.next = afterStart
                afterStart = node
            }
            node = next
        }

        // Merge before list and after list
        if (beforeStart == null) return afterStart

        val head: LinkedListNode = beforeStart
        while (beforeStart!!.next != null) {
            beforeStart = beforeStart.next
        }
        beforeStart.next = afterStart
        return head
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val length = 20
        val nodes = arrayOfNulls<LinkedListNode>(length)
        for (i in 0 until length) {
            nodes[i] = LinkedListNode(if (i >= length / 2) length - i - 1 else i, null, null)
        }
        for (i in 0 until length) {
            if (i < length - 1) {
                nodes[i]!!.next = nodes[i + 1]
            }
            if (i > 0) {
                nodes[i]!!.prev = nodes[i - 1]
            }
        }
        val head = nodes[0]
        println(head!!.printForward())
        val h = partition(head, 7)
        println(h!!.printForward())
    }

}