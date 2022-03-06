package challenges.cracking_coding_interview.linked_list.palindrome

import challenges.data_structure.LinkedListNode


/**
 * Implement a function to check if a linked list is a palindrome.
 */
object PalindromeC {
    class Result(var node: LinkedListNode?, var result: Boolean)

    private fun isPalindromeRecurse(head: LinkedListNode?, length: Int): Result {
        if (head == null || length <= 0) { // Even number of nodes
            return Result(head, true)
        } else if (length == 1) { // Odd number of nodes
            return Result(head.next, true)
        }

        // Recurse on sublist.
        val res = isPalindromeRecurse(head.next, length - 2)

        // If child calls are not a palindrome, pass back up
        // a failure.
        if (!res.result || res.node == null) return res

        // Check if matches corresponding node on other side. 
        res.result = head.data == res.node!!.data

        // Return corresponding node. 
        res.node = res.node!!.next
        return res
    }

    private fun lengthOfList(_n: LinkedListNode?): Int {
        var n = _n
        var size = 0
        while (n != null) {
            size++
            n = n.next
        }
        return size
    }

    private fun isPalindrome(head: LinkedListNode?): Boolean {
        val length = lengthOfList(head)
        val p = isPalindromeRecurse(head, length)
        return p.result
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