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

    private fun insertValueInSortedCyclicList(node: Node?, valueToInsert: Int): Node {
        if (node == null) {
            // Create a new single cyclic list
            val newNode = Node(valueToInsert)
            newNode.next = newNode
            return newNode
        }

        var current = node!!

        do {
            val prev = current
            current = current.next!!

            // Case 1: Current value is equal to valueToInsert
            if (current.value == valueToInsert) {
                // Insert the new node between prev and current
                val newNode = Node(valueToInsert)
                prev.next = newNode
                newNode.next = current
                return node
            }

            // Case 2: valueToInsert is between prev and current values
            if (prev.value <= valueToInsert && valueToInsert <= current.value) {
                // Insert the new node between prev and current
                val newNode = Node(valueToInsert)
                prev.next = newNode
                newNode.next = current
                return node
            }

            // Case 3: We have reached the end of the list and valueToInsert is greater than the last node's value
            if (current == node && valueToInsert > prev.value) {
                // Insert the new node at the end of the list
                val newNode = Node(valueToInsert)
                prev.next = newNode
                newNode.next = current
                return node
            }
        } while (current != node)

        return node
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