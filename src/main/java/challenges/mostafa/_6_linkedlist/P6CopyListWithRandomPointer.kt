package challenges.mostafa._6_linkedlist

/**
A linked list of length n is given such that each node contains an additional random pointer, which
could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where
each new node has its value set to the value of its corresponding original node. Both the next and
random pointer of the new nodes should point to new nodes in the copied list such that the pointers
in the original list and copied list represent the same list state. None of the pointers in the new
list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for
the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented
as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to,
or null if it does not point to any node.
Your code will only be given the head of the original linked list.



Example 1:
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Example 2:
Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

Example 3:
Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]

Constraints:

0 <= n <= 1000
-104 <= Node.val <= 104
Node.random is null or is pointing to some node in the linked list.

https://leetcode.com/problems/copy-list-with-random-pointer/description/
 */

internal object P6CopyListWithRandomPointer {

    data class Node(
        var `val`: Int,
        var random_index: Int? = null,
        var next: Node? = null,
        var random: Node? = null
    )

    private fun copyRandomList(node: Node?): Node? {
        if (node == null) return null

        // First pass: Create copied nodes and insert them between original nodes
        var current = node
        while (current != null) {
            val copiedNode = Node(current.`val`)
            copiedNode.next = current.next
            current.next = copiedNode
            current = copiedNode.next
        }

        // Second pass: Set the random pointers of copied nodes
        current = node
        while (current != null) {
            current.next?.random = current.random?.next
            current = current.next?.next
        }

        // Third pass: Separate the original and copied lists
        current = node
        val dummyHead = Node(0)
        var copiedCurrent = dummyHead
        while (current != null) {
            copiedCurrent.next = current.next
            copiedCurrent = copiedCurrent.next!!
            current.next = copiedCurrent.next
            current = current.next
        }

        return dummyHead.next
    }

    @JvmStatic
    fun main(args: Array<String>) {
// Example 1
        val head1 = listOf(
            Node(7, null, null),
            Node(13, 0, null),
            Node(11, 4, null),
            Node(10, 2, null),
            Node(1, 0, null)
        )
        connectNodes(head1)
        val result1 = copyRandomList(head1[0])
        printList(result1)

        // Example 2
        val head2 = listOf(
            Node(1, 1, null),
            Node(2, 1, null)
        )
        connectNodes(head2)
        val result2 = copyRandomList(head2[0])
        printList(result2)

        // Example 3
        val head3 = listOf(
            Node(3, null, null),
            Node(3, 0, null),
            Node(3, null, null)
        )
        connectNodes(head3)
        val result3 = copyRandomList(head3[0])
        printList(result3)
    }

    private fun connectNodes(nodes: List<Node>) {
        for (i in nodes.indices) {
            nodes[i].next = if (i + 1 < nodes.size) nodes[i + 1] else null
            nodes[i].random = if (nodes[i].random_index != null) nodes[nodes[i].random_index!!] else null
        }
    }

    private fun printList(head: Node?) {
        var current = head
        while (current != null) {
            val randomIndex = current.random?.`val` ?: "null"
            println("[${current.`val`}, $randomIndex]")
            current = current.next
        }
    }
}