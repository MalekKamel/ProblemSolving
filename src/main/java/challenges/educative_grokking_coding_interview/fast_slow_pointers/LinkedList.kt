package challenges.educative_grokking_coding_interview.fast_slow_pointers

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