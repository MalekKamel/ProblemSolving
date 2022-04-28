package challenges.leetcode

/**
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order, and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]

https://leetcode.com/problems/add-two-numbers/
 */
object AddTwoNumbers {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    private fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var n1 = l1
        var n2 = l2
        var remaining = 0

        var result: ListNode? = null
        var prev: ListNode? = null

        while (n1 != null || n2 != null) {
            var number = (n1?.`val` ?: 0) + remaining + (n2?.`val` ?: 0)

            if (number > 9) {
                remaining = 1
                number %= 10
            } else {
                remaining = 0
            }

            val node = ListNode(number)
            if (result == null) {
                result = node
                prev = result
            } else {
                prev?.next = node
                prev = node
            }

            n1 = n1?.next
            n2 = n2?.next
        }

        if (remaining > 0) {
            prev?.next = ListNode(remaining)
        }

        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var list1 = listOf(2, 4, 3)
        var list2 = listOf(5, 6, 4)

        val result = addTwoNumbers(createListNode(list1), createListNode(list2))
        var next = result
        while (next != null) {
            println(next.`val`)
            next = next.next
        }

        println("====================")

        list1 = listOf(9, 9, 9, 9, 9, 9, 9)
        list2 = listOf(9, 9, 9, 9)

        val result2 = addTwoNumbers(createListNode(list1), createListNode(list2))
        var next2 = result2
        while (next2 != null) {
            println(next2.`val`)
            next2 = next2.next
        }

    }

    private fun createListNode(list: List<Int>): ListNode? {
        var root: ListNode? = null
        var prev: ListNode? = null
        for (i in list) {
            val next = ListNode(i)
            if (root == null) {
                root = next
                prev = next
            } else {
                prev?.next = next
                prev = next
            }
        }
        return root
    }
}