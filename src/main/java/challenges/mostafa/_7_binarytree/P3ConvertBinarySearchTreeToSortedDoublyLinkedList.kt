package challenges.mostafa._7_binarytree

import challenges.mostafa._7_binarytree.P3ConvertBinarySearchTreeToSortedDoublyLinkedList.TreeNode

/**
Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right
pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:

We want to transform this BST into a circular doubly linked list. Each node in a doubly
linked list has a predecessor and successor. For a circular doubly linked list, the predecessor
of the first element is the last element, and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. The "head" symbol
means the node it points to is the smallest element of the linked list.

Specifically, we want to do the transformation in place. After the transformation, the left pointer
of the tree node should point to its predecessor, and the right pointer should point to its
successor. We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST. The solid line indicates the successor relationship,
while the dashed line means the predecessor relationship.

https://leetcode.ca/all/426.html
 */

internal class P3ConvertBinarySearchTreeToSortedDoublyLinkedList {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null

        override fun toString(): String {
            return `val`.toString()
        }
    }

    /**
     * 1. Problem Explanation
     * The problem asks us to take a Binary Search Tree (BST) and convert it into a sorted circular
     * doubly-linked list. The conversion needs to be done in-place, meaning we should reuse the
     * existing `left` and `right` pointers of the BST nodes to serve as the `previous` and `next`
     * pointers of the doubly-linked list, respectively. The resulting linked list should be circular,
     * where the last node's `right` pointer points to the first node, and the first node's `left`
     * pointer points to the last node. We need to return the head (smallest element) of this circular
     * doubly-linked list.

     * 2. Pattern Identification and Rationale
     * The core pattern to solve this problem efficiently is an In-order Traversal of the BST.
     * An in-order traversal visits the nodes of a BST in ascending order of their values. This is crucial
     * because we need to create a sorted doubly-linked list. By processing the nodes in-order, we can
     * naturally build the linked list in the correct sorted sequence.

     * The advantages of using in-order traversal are:
     * - It guarantees that we visit the nodes in the desired sorted order, which is essential for building
     *   a sorted linked list.
     * - It allows us to process each node exactly once, leading to an efficient algorithm.

     * We will need to keep track of the previous node visited during the traversal to establish the
     * `previous` (left pointer) and `next` (right pointer) relationships for the current node.

     * 3. Solution Breakdown
     * We can solve this problem using a recursive in-order traversal approach. We'll maintain a pointer
     * to the previous node visited and a pointer to the head of the linked list (which will be the
     * leftmost node of the BST).

     * Steps:
     * a. Initialize two pointers: `prev` (initially null) to keep track of the previously visited node
     *    and `head` (initially null) to store the head of the doubly-linked list.
     * b. Create a recursive helper function, say `inorder(node: TreeNode?)`.
     * c. Base case for the recursion: If `node` is null, return.
     * d. Recursively call `inorder(node.left)` to process the left subtree first (smallest values).
     * e. Process the current `node`:
     *    - If `prev` is null, it means the current `node` is the first node we are visiting in the
     *      in-order traversal, so it will be the head of our doubly-linked list. Set `head = node`.
     *    - If `prev` is not null, it means we have already visited a node. We need to establish the
     *      doubly-linked list connections:
     *      - Set `prev.right = node` (the previous node's `right` pointer now points to the current node).
     *      - Set `node.left = prev` (the current node's `left` pointer now points to the previous node).
     *    - Update `prev` to the current `node`, as it will be the 'previous' node for the next node
     *      we visit.
     * f. Recursively call `inorder(node.right)` to process the right subtree (larger values).
     * g. After the in-order traversal is complete, we need to make the linked list circular.
     *    If `head` is not null (meaning the BST was not empty), we need to connect the last node
     *    (which will be the `prev` node after the traversal) to the `head` node:
     *    - Set `prev.right = head`.
     *    - Set `head.left = prev`.
     * h. Return the `head` of the circular doubly-linked list.
     */

    private var prev: TreeNode? = null
    private var head: TreeNode? = null

    fun treeToDoublyList(root: TreeNode?): TreeNode? {
        if (root == null) {
            return null
        }
        inorder(root)

        // Make it circular
        head?.left = prev
        prev?.right = head

        return head
    }

    private fun inorder(node: TreeNode?) {
        if (node == null) {
            return
        }

        inorder(node.left)

        if (prev == null) {
            head = node
        } else {
            prev?.right = node
            node.left = prev
        }
        prev = node

        inorder(node.right)
    }


}

fun main(args: Array<String>) {
    // Test Case 1
    val root1 = TreeNode(4)
    root1.left = TreeNode(2)
    root1.right = TreeNode(5)
    root1.left?.left = TreeNode(1)
    root1.left?.right = TreeNode(3)
    val sol1 = P3ConvertBinarySearchTreeToSortedDoublyLinkedList()
    val head1 = sol1.treeToDoublyList(root1)
    if (head1 != null) {
        var current: TreeNode? = head1
        print(current?.`val`)
        current = current?.right
        while (current != head1) {
            print(" <-> ${current?.`val`}")
            current = current?.right
        }
        println()
        var tail: TreeNode? = head1.left
        print(tail?.`val`)
        tail = tail?.left
        while (tail != head1.left) {
            print(" <-> ${tail?.`val`}")
            tail = tail?.left
        }
        println()
    }

    // Test Case 2: Single node
    val root2 = TreeNode(1)
    val sol2 = P3ConvertBinarySearchTreeToSortedDoublyLinkedList()
    val head2 = sol2.treeToDoublyList(root2)
    if (head2 != null) {
        println(head2.`val`)
        println(head2.left?.`val` == head2.`val`)
        println(head2.right?.`val` == head2.`val`)
    }

    // Test Case 3: Empty tree
    val sol3 = P3ConvertBinarySearchTreeToSortedDoublyLinkedList()
    val head3 = sol3.treeToDoublyList(null)
    println(head3 == null)
}