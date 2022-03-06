package challenges.cracking_coding_interview.linked_list.palindrome

import challenges.data_structure.LinkedListNode
import java.util.*


/**
 * Implement a function to check if a linked list is a palindrome.
 */
object PalindromeB {
    private fun isPalindrome(head: LinkedListNode?): Boolean {
        var fast = head
        var slow = head
        val stack = Stack<Int>()
        while (fast?.next != null) {
            stack.push(slow!!.data)
            slow = slow.next
            fast = fast.next!!.next
        }

        // Has odd number of elements, so skip the middle
        if (fast != null) slow = slow!!.next

        while (slow != null) {
            val top = stack.pop()
            if (top != slow.data) return false
            slow = slow.next
        }
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val length = 9
        val nodes = arrayOfNulls<LinkedListNode>(length)
        for (i in 0 until length) {
            nodes[i] = LinkedListNode(if (i >= length / 2) length - i - 1 else i, null, null)
        }
        for (i in 0 until length) {
            if (i < length - 1) {
                nodes[i]!!.next = nodes[i + 1]
            }
            if (i > 0) {
                nodes[i]?.prev = nodes[i - 1]
            }
        }
        //nodes[length - 2].data = 9; // Uncomment to ruin palindrome
        val head = nodes[0]
        println(head!!.printForward())
        println(isPalindrome(head))
    }

}