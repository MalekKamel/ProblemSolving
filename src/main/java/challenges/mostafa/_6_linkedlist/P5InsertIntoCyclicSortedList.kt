package challenges.mostafa._6_linkedlist

/**
Given a node from a cyclic linked list which is sorted in ascending order, write a function to
insert a value into the list such that it remains a cyclic sorted list. The given node can be
a reference to any single node in the list, and may not be necessarily the smallest value in
the cyclic list.

If there are multiple suitable places for insertion, you may choose any place to insert the new
value. After the insertion, the cyclic list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single cyclic list and
return the reference to that single node. Otherwise, you should return the original given node.

https://leetcode.ca/all/708.html
 */

internal object P5InsertIntoCyclicSortedList {

    class Node(var value: Int, var next: Node? = null)

    /**
     * Problem Explanation:
     * The problem asks us to insert a new value into a sorted cyclic linked list while maintaining
     * its sorted order. The key challenges are:
     * The list is cyclic (last node points to first node)
     * We only have access to a single node, not necessarily the head/smallest value
     * The list must remain sorted after insertion
     * We need to handle edge cases like empty list or single node list
     * We need to find the correct insertion position considering the cyclic nature
     *
     * Pattern Identification and Rationale:
     * This problem uses the Two-Pointer Pattern with a modified approach:
     * We need to traverse the cyclic list to find the correct insertion point
     * The insertion point could be between two nodes or at the "wrap around" point
     * Two-pointer helps us keep track of previous and current nodes for insertion
     * This pattern is suitable because:
     * It allows us to maintain the list's cyclic nature
     * We can compare adjacent values to find the correct insertion position
     * It handles the "wrap around" case efficiently
     */
    private fun insertValueInSortedCyclicList(node: Node?, valueToInsert: Int): Node {
        // Case 1: Empty list
        if (node == null) {
            val newNode = Node(valueToInsert)
            newNode.next = newNode
            return newNode
        }

        // Case 2: Single node
        if (node.next == node) {
            val newNode = Node(valueToInsert)
            node.next = newNode
            newNode.next = node
            return node
        }

        var current: Node = node
        do {
            // Case 3: Regular Insertion
            // - Checks if valueToInsert fits between current and next node values
            // - Example: In list 1 -> 3 -> 5, inserting 2 goes between 1 and 3
            if (valueToInsert >= current.value && valueToInsert <= current.next!!.value) {
                insertBetween(current, current.next!!, valueToInsert)
                return node
            }

            // Case 4: Wrap Around Insertion
            // Handles insertion at the cycle point where the list "wraps around"
            // Example: In list 3 -> 5 -> 1, inserting 6 or 0 at the wrap point
            // Condition checks:
            // current.value > current.next!!.value: Identifies the wrap point
            // valueToInsert >= current.value || valueToInsert <= current.next!!.value: Value belongs
            // at wrap point
            if (current.value > current.next!!.value &&
                (valueToInsert >= current.value || valueToInsert <= current.next!!.value)) {
                insertBetween(current, current.next!!, valueToInsert)
                return node
            }

            current = current.next!!
        } while (current != node)

        // If we haven't inserted yet, insert after the current node
        insertBetween(current, current.next!!, valueToInsert)
        return node
    }

    private fun insertBetween(prev: Node, curr: Node, value: Int) {
        val newNode = Node(value)
        prev.next = newNode
        newNode.next = curr
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Create a cyclic sorted linked list
        val node1 = Node(1)
        val node2 = Node(3)
        val node3 = Node(4)
        node1.next = node2
        node2.next = node3
        node3.next = node1

        // Insert a value into the cyclic sorted linked list
        val newNode = insertValueInSortedCyclicList(node2, 2)
        println("Updated cyclic sorted linked list:")
        printCyclicList(newNode)
    }

    private fun printCyclicList(node: Node?) {
        if (node == null) return
        var current = node
        do {
            print("${current!!.value} -> ")
            current = current.next!!
        } while (current != node)
        println("${node.value}")
    }
}