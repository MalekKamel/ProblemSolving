package challenges.mostafa._6_linkedlist

/**
Given the head of a linked list and a value x, partition it such that all nodes less than x come
before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example 1:
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
Example 2:

Input: head = [2,1], x = 2
Output: [1,2]


Constraints:

The number of nodes in the list is in the range [0, 200].
-100 <= Node.val <= 100
-200 <= x <= 200

https://leetcode.com/problems/partition-list/description/
 */

internal object P3PartitionList {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null

        override fun toString(): String {
            return `val`.toString()
        }
    }

    /**
     *  1. Problem Explanation
     *  The problem requires rearranging the nodes of a given linked list around a pivot value 'x'.
     *  All nodes with values less than 'x' should appear in the first part of the partitioned list,
     *  and all nodes with values greater than or equal to 'x' should appear in the second part.
     *  Importantly, the relative order of nodes within each partition must be maintained.

     *  2. Pattern Identification and Rationale
     *  The most suitable approach for this problem is to iterate through the linked list once and
     *  maintain two separate linked lists: one for nodes less than 'x' and another for nodes
     *  greater than or equal to 'x'. After iterating through the entire list, we can concatenate
     *  these two lists. This approach allows us to process each node exactly once and maintain
     *  the original relative order within each partition. This is a form of list manipulation
     *  that doesn't strictly fall into a classic algorithm pattern like dynamic programming or
     *  divide and conquer, but rather a direct, iterative approach tailored to linked list operations.
     */

    /**
     *  The time complexity is O(n), where n is the number of nodes
     *  in the linked list.
     *
     *  The function iterates through the linked list once, and for each node, it performs
     *  a constant-time operation to add it to either the "smaller" list or the "greater" list.
     *  This includes updating the respective tail pointers and linking the two lists together at the end.
     *
     *  The key steps and their time complexities are as follows:
     *
     *  Initializing the smallerHead and greaterHead nodes: O(1) time.
     *  Iterating through the linked list: O(n) time, where n is the number of nodes.
     *  For each node:
     *  Checking if the node value is less than x: O(1) time.
     *  Appending the node to the appropriate list (smaller or greater): O(1) time.
     *  Updating the respective tail pointers: O(1) time.
     *  Linking the smaller and greater lists together: O(1) time.
     *  Returning the smallerHead.next: O(1) time.
     *  Therefore, the overall time complexity of the partition function is O(n), where n is
     *  the number of nodes in the linked list.
     */

    /**
     *  Let's break down that efficient Kotlin solution step by step:
     *
     * Imagine you have a train (your linked list) and you want to split it into two smaller trains
     * based on whether the value in each car (node) is less than a certain number, let's say 'x'.
     * You also need to make sure the order of the cars within each of the new trains stays the same
     * as it was in the original train.
     *
     * Here's how the code achieves this:
     *
     * Creating Empty Trains (Dummy Nodes):
     *
     * Kotlin
     *
     * val smallerHead = ListNode(0)
     * val greaterHead = ListNode(0)
     * var smallerTail = smallerHead
     * var greaterTail = greaterHead
     * Think of smallerHead and greaterHead as the engines of our two new trains. They don't actually
     * hold any of the original list's values; they are just placeholders to make it easier to build
     * the new trains. smallerTail and greaterTail are like the end cars of these trains, and we'll
     * use them to add more cars.
     *
     * Going Through the Original Train (Iterating the List):
     *
     * Kotlin
     *
     * var current = head
     * while (current != null) {
     *     // ... process each car ...
     *     current = current.next
     * }
     * We start at the beginning of the original train (head) and look at each car (current) one by
     * one until we reach the end (null).
     *
     * Deciding Which New Train Each Car Goes To:
     *
     * Kotlin
     *
     * if (current.`val` < x) {
     *     smallerTail.next = current
     *     smallerTail = current
     * } else {
     *     greaterTail.next = current
     *     greaterTail = current
     * }
     * For each car (current), we check its value (current.val`).
     *
     * If the value is less than x: We attach this car to the end of the smaller train.
     * smallerTail.next = current connects the last car of the smaller train to the current car.
     * Then, we move the smallerTail to this newly added car (smallerTail = current), as it's now
     * the end of the smaller train.
     * If the value is greater than or equal to x: We do the same thing, but we attach the car to
     * the end of the greater train using greaterTail.
     * Connecting the Two New Trains:
     *
     * Kotlin
     *
     * smallerTail.next = greaterHead.next
     * Once we've processed all the cars from the original train, the smaller train ends at smallerTail,
     * and the greater train starts after the dummy engine greaterHead. We connect the end of the smaller
     * train (smallerTail.next) to the first actual car of the greater train (greaterHead.next).
     *
     * Marking the End of the Second Train:
     *
     * Kotlin
     *
     * greaterTail.next = null
     * It's crucial to make sure the greater train properly ends. We set the next pointer of the last
     * car in the greater train (greaterTail.next) to null.
     *
     * Returning the Start of the First New Train:
     *
     * Kotlin
     *
     * return smallerHead.next
     * Finally, the smallerHead was just a dummy engine. The actual first car of our partitioned list
     * is the one after it (smallerHead.next). We return this as the head of our newly arranged linked list.
     *
     * In essence, the algorithm cleverly builds two separate linked lists while iterating through the original
     * one, maintaining the relative order within each. Then, it simply connects the end of the "smaller than x"
     * list to the beginning of the "greater than or equal to x" list. The dummy head nodes are a neat trick
     * to avoid special handling for empty lists or when the first element falls into either partition.
     */
    private fun partition(head: ListNode?, x: Int): ListNode? {
        val smallerHead = ListNode(0)
        val greaterHead = ListNode(0)
        var smallerTail = smallerHead
        var greaterTail = greaterHead
        var current = head

        while (current != null) {
            if (current.`val` < x) {
                smallerTail.next = current
                smallerTail = current
            } else {
                greaterTail.next = current
                greaterTail = current
            }
            current = current.next
        }

        smallerTail.next = greaterHead.next
        greaterTail.next = null

        return smallerHead.next
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        var head = ListNode(1)
        head.next = ListNode(4)
        head.next?.next = ListNode(3)
        head.next?.next?.next = ListNode(2)
        head.next?.next?.next?.next = ListNode(5)
        head.next?.next?.next?.next?.next = ListNode(2)
        val x1 = 3
        var result1 = partition(head, x1)
        printList(result1)
        // Output: [1,2,2,4,3,5]

        // Example 2
        head = ListNode(2)
        head.next = ListNode(1)
        val x2 = 2
        result1 = partition(head, x2)
        printList(result1)
        // Output: [1,2]
    }

    private fun printList(head: ListNode?) {
        var curr = head
        while (curr != null) {
            print("${curr.`val`} ")
            curr = curr.next
        }
        println()
    }
}