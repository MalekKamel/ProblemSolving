package challenges.cracking_coding_interview.linked_list

import challenges.data_structure.LinkedListNode
import challenges.util.AssortedMethods


/**
 * Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
 * the first and last node, not necessarily the exact middle)
 * of a singly linked list, given only access to that node.
 * EXAMPLE
 * Input:the node c from the linked list a->b->c->d->e->f
 * Result: nothing is returned, but the new linked list looks like a->b->d->e->f
 */
object DeleteMiddleNode {

    private fun deleteNode(n: LinkedListNode?): Boolean {
        if (n?.next == null) {
            return false // Failure
        }
        val next = n.next
        n.data = next!!.data
        n.next = next.next
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val head = AssortedMethods.randomLinkedList(10, 0, 10)
        println(head.printForward())
        deleteNode(head.next!!.next!!.next!!.next) // delete node 4
        println(head.printForward())
    }
}