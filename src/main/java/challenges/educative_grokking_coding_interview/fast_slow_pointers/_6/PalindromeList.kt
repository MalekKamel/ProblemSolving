package challenges.educative_grokking_coding_interview.fast_slow_pointers._6

import challenges.educative_grokking_coding_interview.fast_slow_pointers.LinkedList
import challenges.educative_grokking_coding_interview.fast_slow_pointers.LinkedListNode
import challenges.educative_grokking_coding_interview.fast_slow_pointers.PrintList.printListWithForwardArrow

/**
Given the head of a linked list, your task is to check whether the linked list is a palindrome or not.
Return TRUE if the linked list is a palindrome; otherwise, return FALSE.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/B1kqL0GDRVn
 */


internal object PalindromeList {
    fun palindrome(head: LinkedListNode?): Boolean {
        // Initialize slow and fast pointers to the head of the linked list
        var slow: LinkedListNode? = head
        var fast: LinkedListNode? = head

        // Find the middle of the linked list using the slow and fast pointers
        while (fast?.next != null) {
            // move slow one step forward
            slow = slow?.next
            // move fast two steps forward
            fast = fast.next?.next
        }
        // Reverse the second half of the linked list starting from the middle node
        var revertData: LinkedListNode? = LinkedListReversal.reverseLinkedList(slow)
        // Compare the first half of the linked list with the reversed second half of the linked list
        val check = compareTwoHalves(head, revertData)
        // Re-reverse the second half of the linked list to restore the original linked list
        revertData = LinkedListReversal.reverseLinkedList(revertData)
        // Return True if the linked list is a palindrome, else False
        return if (check) {
            true
        } else false
    }

    fun compareTwoHalves(firstHalf: LinkedListNode?, secondHalf: LinkedListNode?): Boolean {
        // Compare the corresponding nodes of the first and second halves of the linked list
        var firstHalf: LinkedListNode? = firstHalf
        var secondHalf: LinkedListNode? = secondHalf
        while (firstHalf != null && secondHalf != null) {
            if (firstHalf.data !== secondHalf.data) {
                return false
            } else {
                firstHalf = firstHalf.next
                secondHalf = secondHalf.next
            }
        }
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val input = arrayOf(
            intArrayOf(2, 4, 6, 4, 2),
            intArrayOf(0, 3, 5, 5, 0),
            intArrayOf(9, 27, 4, 4, 27, 9),
            intArrayOf(5, 4, 7, 9, 4, 5),
            intArrayOf(5, 10, 15, 20, 15, 10, 5)
        )
        for (i in input.indices) {
            print(i + 1)
            val list = LinkedList<Int>()
            list.createLinkedList(input[i])
            print(".\tLinked list:  ")
            printListWithForwardArrow(list.head)
            print("\tIs it a palindrome?  ")
            val result = palindrome(list.head)
            if (result) {
                println("Yes")
            } else {
                println("No")
            }
            println(String(CharArray(100)).replace('\u0000', '-'))
        }
    }
}

internal object LinkedListReversal {
    fun reverseLinkedList(slowPtr: LinkedListNode?): LinkedListNode? {
        var prev: LinkedListNode? = null
        var next: LinkedListNode? = null
        var curr = slowPtr
        while (curr != null) {
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        }
        return prev
    }
}

