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

    /**
     * Understanding the Problem: Deep Copy with Random Pointers
     *
     * The challenge here isn't just to copy the val and next pointers of the linked list. Each node
     * also has a random pointer that can point to any node in the original list (including itself or null).
     * A deep copy means creating entirely new nodes with the same values and ensuring that the random
     * pointers in the copied list point to the corresponding nodes in the newly created copied list.
     *
     * The Algorithm: Three Passes
     *
     * The code cleverly solves this problem in three distinct passes through the original linked list:
     *
     * 1. First Pass: Creating and Inserting Copied Nodes
     *
     * var current = node: We initialize a pointer current to the head of the original list.
     * while (current != null): We iterate through each node in the original list.
     * val copiedNode = Node(current.val): For each original node, we create a new node (copiedNode)
     * with the same value.
     * copiedNode.next = current.next: We set the next pointer of the copiedNode to point to the node
     * that was originally after the current node.
     * current.next = copiedNode: We then insert the copiedNode immediately after the current original node.
     * current = copiedNode.next: We advance the current pointer to the next original node (which is
     * now two steps ahead because of the insertion).
     * After the first pass, the list structure will look like this (if the original list was A -> B -> C):
     *
     * A -> A' -> B -> B' -> C -> C'
     *
     * where A', B', and C' are the newly created copied nodes. The random pointers are not yet set in
     * the copied nodes.
     *
     * 2. Second Pass: Setting the Random Pointers of Copied Nodes
     *
     * current = node: We reset the current pointer to the head of the original list.
     * while (current != null): We iterate through the original list again.
     * current.next?.random = current.random?.next: This is the crucial step for setting the random
     * pointers in the copied nodes. Let's break it down:
     * current.next: This refers to the copied node that was inserted after the current original node.
     * current.next?.random: We are trying to set the random pointer of this copied node. The ?. is for
     * safe navigation in case current.next is null (though it shouldn't be in this phase).
     * current.random: This refers to the random pointer of the original node.
     * current.random?.next: If the original node's random pointer is not null, this points to the node
     * that comes after the node pointed to by the original's random pointer. Because of our first pass,
     * the node immediately after any original node is its copy. Therefore, current.random?.next will
     * point to the corresponding copied node in the new list.
     * current = current.next?.next: We advance the current pointer to the next original node (skipping
     * the copied node in between).
     * After the second pass, the random pointers of the copied nodes are correctly set to point to
     * the corresponding copied nodes in the new structure.
     *
     * 3. Third Pass: Separating the Original and Copied Lists
     *
     * current = node: We reset the current pointer to the head of the original list.
     * val dummyHead = Node(0): We create a dummy head node for the copied list. This simplifies the process
     * of building the new list.
     * var copiedCurrent = dummyHead: We initialize a pointer copiedCurrent to the dummy head, which
     * we'll use to traverse and build the copied list.
     * while (current != null): We iterate through the modified original list.
     * copiedCurrent.next = current.next: We link the next pointer of the current node in the copied
     * list (copiedCurrent) to the copied node that is currently after the current original node.
     * copiedCurrent = copiedCurrent.next!!: We move copiedCurrent to the newly linked copied node.
     * The !! is used because we know copiedCurrent.next is not null in this context.
     * current.next = copiedCurrent.next: We restore the next pointer of the current original node
     * to point to the node that comes after the copied node (which was the next original node).
     * current = current.next: We advance the current pointer to the next original node.
     * After the third pass:
     *
     * The original list is restored to its original next pointer structure.
     *
     * The copiedCurrent pointer (starting from dummyHead) has built a completely new linked list
     * containing the copies of the original nodes, with their random pointers correctly set to the corresponding
     * nodes in the new list.
     *
     * return dummyHead.next: Finally, we return the next of the dummy head, which is the actual head of
     * the deep-copied linked list.
     */
    /**
     * Time Complexity: O(n) where n is the number of nodes
     * We make three passes through the list, each taking O(n)
     */
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
            copiedCurrent.next = current.next // The copied node
            copiedCurrent = copiedCurrent.next!! // The copied node
            current.next = copiedCurrent.next // The original node
            current = current.next // The original node
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