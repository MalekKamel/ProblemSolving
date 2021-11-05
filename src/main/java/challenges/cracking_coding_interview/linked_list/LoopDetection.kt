package challenges.cracking_coding_interview.linked_list

import challenges.data_structure.LinkedListNode


/**
 * Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.
 * DEFINITION
 * Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node,
 * so as to make a loop in the linked list.
 *
 * EXAMPLE
 * Input: A -> B -> C -> D -> E -> C (the same C as earlier)
 * Output: C
 *
 * NOTE
 * This is a modification of a classic interview problem: detect if a linked list has a loop.
 */
object LoopDetection {

    private fun findBeginning(head: LinkedListNode?): LinkedListNode? {
        var slow = head
        var fast = head

        // Find meeting point
        while (fast?.next != null) {
            slow = slow!!.next
            fast = fast.next!!.next
            if (slow == fast) break
        }

        // Error check - there is no meeting point, and therefore no loop
        if (fast?.next == null) return null

        // Move slow to Head. Keep fast at Meeting Point. Each are k steps
        // from the Loop Start. If they move at the same pace, they must
        // meet at Loop Start.
        slow = head

        while (slow != fast) {
            slow = slow!!.next
            fast = fast!!.next
        }

        // Both now point to the start of the loop.
        return fast
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val listLength = 10
        val k = 2

        // Create linked list
        val nodes = arrayOfNulls<LinkedListNode>(listLength)
        for (i in 0 until listLength) {
            nodes[i] = LinkedListNode(i, null, if (i > 0) nodes[i - 1] else null)
        }

        println(nodes[0]?.printForward())

        // Create loop;
        nodes[listLength - 1]!!.next = nodes[listLength - k]
        val loop = findBeginning(nodes[0])
        if (loop == null) {
            println("No Cycle.")
        } else {
            println(loop.data)
        }
    }

}