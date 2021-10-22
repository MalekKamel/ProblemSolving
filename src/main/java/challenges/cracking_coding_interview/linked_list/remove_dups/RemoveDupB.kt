package challenges.cracking_coding_interview.linked_list.remove_dups

import challenges.data_structure.LinkedListNode


/*
Write code to remove duplicates from an unsorted linked list.
FOLLOW UP
How would you solve this problem if a temporary buffer is not allowed?
 */
object RemoveDupB {
    private fun deleteDup(head: LinkedListNode?) {
        var current = head
        while (current != null) {
            /* Remove all future nodes that have the same value */
            var runner = current!!
            while (runner.next != null) {
                if (runner.next!!.data == current.data) {
                    runner.next = runner.next!!.next
                } else {
                    runner = runner.next!!
                }
            }
            current = current.next
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var first = LinkedListNode(0, null, null) //AssortedMethods.randomLinkedList(1000, 0, 2);
        val head = first
        var second = first
        for (i in 1..7) {
            second = LinkedListNode(i % 2, null, null)
            first.next = second
            second.prev = first
            first = second
        }
        println(head.printForward())
        deleteDup(head)
        println(head.printForward())
    }
}