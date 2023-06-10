package challenges.educative_grokking_coding_interview.linkedlist_inplace_reveresal._4

import challenges.educative_grokking_coding_interview.LinkedList
import challenges.educative_grokking_coding_interview.LinkedListNode
import challenges.educative_grokking_coding_interview.PrintList.printListWithForwardArrow
import challenges.util.PrintHyphens
import java.util.*


/**
Given the head of a singly linked list, reorder the list as if it were folded on itself. For example, if the list is represented as follows:

You don’t need to modify the values in the list’s nodes; only the links between nodes need to be changed.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/gx9zZnEEM7r
 */

object Reorder {
    private fun reorderList(head: LinkedListNode?): LinkedListNode? {
        if (head == null) return null
        // find the middle of linked list
        // in 1->2->3->4->5->6 find 4
        var slow: LinkedListNode? = head
        var fast: LinkedListNode? = head
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        // reverse the second part of the list
        // convert 1->2->3->4->5->6 into 1->2->3 and 6->5->4
        // reverse the second half in-place
        var prev: LinkedListNode? = null
        var curr: LinkedListNode? = slow
        var next: LinkedListNode? = null
        while (curr != null) {
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        }
        // merge two sorted linked lists
        // merge 1->2->3 and 6->5->4 into 1->6->2->5->3->4
        var first: LinkedListNode? = head
        var second: LinkedListNode? = prev
        var temp: LinkedListNode? = head
        while (second?.next != null) {
            temp = temp?.next
            first?.next = second
            second = second.next
            first?.next?.next = temp
            first = first?.next?.next
        }
        return head
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Declaring and creating a linked list
        val obj1 = LinkedList()
        val inputList = listOf(1, 1, 2, 2, 3, -1, 10, 12)
        // Creating Linked Lists
        obj1.createLinkedList(inputList.toIntArray())
        // Displaying original linked list
        print("1. Orignal list: ")
        printListWithForwardArrow(obj1.head)
        // Calling the reorder_list function
        reorderList(obj1.head)
        // Displaying modified linked list
        print("   After folding: ")
        printListWithForwardArrow(obj1.head)
        System.out.println(PrintHyphens.repeat("-", 100))

        // Declaring and creating a linked list
        val obj2 = LinkedList()
        val inputList1 = Arrays.asList(10, 20, -22, 21, -12)
        // Creating Linked Lists
        obj2.createLinkedList(inputList1.toIntArray())
        // Displaying original linked list
        print("2. Orignal list: ")
        printListWithForwardArrow(obj2.head)
        // Calling the reorder_list function
        reorderList(obj2.head)
        // Displaying modified linked list
        print("   After folding: ")
        printListWithForwardArrow(obj2.head)
        System.out.println(PrintHyphens.repeat("-", 100))

        // Declaring and creating a linked list
        val obj3 = LinkedList()
        val inputList2 = Arrays.asList(1, 1, 1)
        // Creating Linked Lists
        obj3.createLinkedList(inputList2.toIntArray())
        // Displaying original linked list
        print("3. Orignal list: ")
        printListWithForwardArrow(obj3.head)
        // Calling the reorder_list function
        reorderList(obj3.head)
        // Displaying modified linked list
        print("   After folding: ")
        printListWithForwardArrow(obj3.head)
        System.out.println(PrintHyphens.repeat("-", 100))

        // Declaring and creating a linked list
        val obj4 = LinkedList()
        val inputList3 = Arrays.asList(-2, -5, -6, 0, -1, -4)
        // Creating Linked Lists
        obj4.createLinkedList(inputList3.toIntArray())
        // Displaying original linked list
        print("4. Orignal list: ")
        printListWithForwardArrow(obj4.head)
        // Calling the reorder_list function
        reorderList(obj4.head)
        // Displaying modified linked list
        print("   After folding: ")
        printListWithForwardArrow(obj4.head)
        System.out.println(PrintHyphens.repeat("-", 100))

        // Declaring and creating a linked list
        val obj5 = LinkedList()
        val inputList4 = Arrays.asList(3, 1, 5, 7, -4, -2, -1, -6)
        // Creating Linked Lists
        obj5.createLinkedList(inputList4.toIntArray())
        // Displaying original linked list
        print("5. Orignal list: ")
        printListWithForwardArrow(obj5.head)
        // Calling the reorder_list function
        reorderList(obj5.head)
        // Displaying modified linked list
        print("   After folding: ")
        printListWithForwardArrow(obj5.head)
        System.out.println(PrintHyphens.repeat("-", 100))
    }
}