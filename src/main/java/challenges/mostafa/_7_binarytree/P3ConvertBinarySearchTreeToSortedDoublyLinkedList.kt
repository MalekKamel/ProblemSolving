package challenges.mostafa._7_binarytree

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

internal object P3ConvertBinarySearchTreeToSortedDoublyLinkedList {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null

        override fun toString(): String {
            return `val`.toString()
        }
    }

    private fun treeToDoublyList(root: TreeNode?): TreeNode? {
        if (root == null) return null

        var first: TreeNode? = null
        var last: TreeNode? = null

        fun inorder(node: TreeNode?) {
            if (node == null) return

            inorder(node.left)

            if (first == null) {
                first = node
            } else {
                last?.right = node
                node.left = last
            }
            last = node

            inorder(node.right)
        }

        inorder(root)

        last?.right = first
        first?.left = last

        return first
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Create a BST
        val root = TreeNode(4)
        root.left = TreeNode(2)
        root.right = TreeNode(5)
        root.left?.left = TreeNode(1)
        root.left?.right = TreeNode(3)

        // Convert the BST to a circular doubly-linked list
        val head = treeToDoublyList(root)

        // Traverse the circular doubly-linked list
        var curr = head
        do {
            println(curr?.`val`)
            curr = curr?.right
        } while (curr != head)
    }
}