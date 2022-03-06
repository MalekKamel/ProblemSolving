package challenges.cracking_coding_interview.linked_list.palindrome

import challenges.data_structure.LinkedListNode

/**
 * Implement a function to check if a linked list is a palindrome.
 */
object PalindromeA {
    private fun isPalindrome(head: LinkedListNode?): Boolean {
        val reversed = reverseAndClone(head)
        return isEqual(head, reversed)
    }

    private fun reverseAndClone(_node: LinkedListNode?): LinkedListNode? {
        var node = _node
        var head: LinkedListNode? = null
        while (node != null) {
            val n = LinkedListNode(node.data) // Clone
            n.next = head
            head = n
            node = node.next
        }
        return head
    }

    private fun isEqual(_lhs: LinkedListNode?, _rhs: LinkedListNode?): Boolean {
        var lhs = _lhs
        var rhs = _rhs
        while (lhs != null && rhs != null) {
            if (lhs.data != rhs.data) return false
            lhs = lhs.next
            rhs = rhs.next
        }
        return lhs == null && rhs == null
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

        // nodes[length - 2].data = 9; // Uncomment to ruin palindrome
        val head = nodes[0]
        println(head!!.printForward())
        println(isPalindrome(head))
    }
}