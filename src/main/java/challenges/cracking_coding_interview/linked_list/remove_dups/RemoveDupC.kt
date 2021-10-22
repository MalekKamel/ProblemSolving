package challenges.cracking_coding_interview.linked_list.remove_dups

import challenges.data_structure.LinkedListNode


/*
Write code to remove duplicates from an unsorted linked list.
FOLLOW UP
How would you solve this problem if a temporary buffer is not allowed?
 */
object RemoveDupC {
    private fun deleteDups(head: LinkedListNode?) {
        if (head == null) return
        var previous = head
        var current = previous.next
        while (current != null) {
            // Look backwards for dups, and remove any that you see.
            var runner = head
            while (runner != current) {
                if (runner!!.data == current!!.data) {
                    val tmp = current.next
                    previous!!.next = tmp
                    current = tmp
                    /*
                     * We know we can't have more than one dup preceding
					 * our element since it would have been removed
					 * earlier.
					 */
                    break
                }
                runner = runner.next
            }

            /*
             * If runner == current, then we didn't find any duplicate
			 * elements in the previous for loop.  We then need to
			 * increment current.
			 * If runner != current, then we must have hit the �break�
			 * condition, in which case we found a dup and current has
			 * already been incremented.
			 */
            if (runner == current) {
                previous = current
                current = current!!.next
            }
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
        deleteDups(head)
        println(head.printForward())
    }
}