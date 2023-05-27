package challenges.educative_grokking_coding_interview.fast_slow_pointers._2

import challenges.data_structure.LinkedListNode

/**
Check whether or not a linked list contains a cycle. If a cycle exists, return TRUE.
Otherwise, return FALSE. The cycle means that at least one node can be reached
again by traversing the next pointer.
 */

object CycleDetection {
    private fun detectCycle(head: LinkedListNode?): Boolean {
        if (head == null) {
            return false
        }
        // Initialize two pointers, slow and fast, to the head of the linked list
        var slow = head
        var fast = head

        // Run the loop until we reach the end of the
        // linked list or find a cycle
        while (fast?.next != null) {
            // Move the slow pointer one step at a time
            slow = slow?.next
            // Move the fast pointer two steps at a time
            fast = fast.next?.next

            // If there is a cycle, the slow and fast pointers will meet
            if (slow == fast) {
                return true
            }
        }
        // If we reach the end of the linked list and haven't found a cycle, return False
        return false
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val input = arrayOf(
            intArrayOf(2, 4, 6, 8, 10, 12),
            intArrayOf(1, 3, 5, 7, 9, 11),
            intArrayOf(0, 1, 2, 3, 4, 6),
            intArrayOf(3, 4, 7, 9, 11, 17),
            intArrayOf(5, 1, 4, 9, 2, 3)
        )
        val pos = intArrayOf(0, -1, 1, -1, 2)
        for (i in input.indices) {
            val list = LinkedList<Int>()
            list.createLinkedList(input[i])
            print("${i + 1}" + ".\tInput:")
            print("\t")
            if (pos[i] == -1) {
                PrintList.printListWithForwardArrow(list.head)
            } else {
                PrintList.printListWithForwardArrowLoop(list.head)
            }
            println(
                """
	pos: ${pos[i]}"""
            )
            if (pos[i] != -1) {
                val length: Int = list.getLength(list.head)
                val lastNode: LinkedListNode? = list.getNode(list.head, length - 1)
                lastNode?.next = list.getNode(list.head, pos[i])
            }
            println(
                """
	Detected Cycle =  ${detectCycle(list.head)}"""
            )
            println(String(CharArray(100)).replace('\u0000', '-'))
        }
    }
}

// Template for linked list node class

class LinkedListNode     // Constructor will be used to make a LinkedListNode type object
    (var data: Int) {
    var next: LinkedListNode? = null
}


// Template for the linked list
class LinkedList<T> {
    var head: LinkedListNode? =
        null

    // insertNodeAtHead method will insert a LinkedListNode at head
    // of a linked list.
    fun insertNodeAtHead(node: LinkedListNode) {
        if (head == null) {
            head = node
        } else {
            node.next = head
            head = node
        }
    }

    // createLinkedList method will create the linked list using the
    // given integer array with the help of InsertAthead method.
    fun createLinkedList(lst: IntArray) {
        for (i in lst.indices.reversed()) {
            val newNode =
                LinkedListNode(
                    lst[i]
                )
            insertNodeAtHead(newNode)
        }
    }

    // returns the number of nodes in the linked list
    fun getNode(
        head: LinkedListNode?,
        pos: Int
    ): LinkedListNode? {
        var ptr = head
        if (pos != -1) {
            var p = 0
            while (p < pos) {
                ptr = ptr!!.next
                p += 1
            }
            return ptr
        }
        return ptr
    }

    // returns the node at the specified position(index) of the linked list
    fun getLength(head: LinkedListNode? = null): Int {
        var temp = head
        var count = 0
        while (temp != null) {
            count++
            temp = temp.next
        }
        return count
    }
}

// Template for printing the linked list with forward arrows

object PrintList {
    fun printListWithForwardArrow(head: LinkedListNode?) {
        var temp = head
        while (temp != null) {
            print(temp.data) // print node value
            temp = temp.next
            if (temp != null) {
                print(" → ")
            } else {
                // if this is the last node, print null at the end
                print(" → null \n ")
            }
        }
    }

    fun printListWithForwardArrowLoop(head: LinkedListNode?) {
        var temp = head
        while (temp != null) {
            print(temp.data) // print node value
            temp = temp.next
            if (temp != null) {
                print(" → ")
            }
        }
    }
}