package challenges.mostafa._7_binarytree

/**
Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).

Each node will have a reference to its parent node. The definition for Node is below:

class Node {
public int val;
public Node left;
public Node right;
public Node parent;
}
According to the definition of LCA on Wikipedia: “The lowest common ancestor of two
nodes p and q in a tree T is the lowest node that has both p and q as descendants
(where we allow a node to be a descendant of itself).”

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.

Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1

Constraints:

The number of nodes in the tree is in the range [2, 10^5].
-10^9 <= Node.val <= 10^9
All Node.val are unique.
p != q
p and q exist in the tree.

https://leetcode.ca/2020-06-06-1650-Lowest-Common-Ancestor-of-a-Binary-Tree-III/
 */

internal object P10LowestCommonAncestorOfBinaryTree3 {

    class Node(
        var `val`: Int,
        var left: Node? = null,
        var right: Node? = null,
        var parent: Node? = null
    ) {
        override fun toString(): String {
            return `val`.toString()
        }
    }

    private fun lowestCommonAncestor(root: Node, p: Node, q: Node): Node? {
        // Base case: if either p or q is the root, return the root
        if (root == p || root == q) return root

        // Recursive case: traverse the tree
        val left = lowestCommonAncestor(root.left ?: return null, p, q)
        val right = lowestCommonAncestor(root.right ?: return null, p, q)

        // If both left and right are not null, then the current node is the LCA
        if (left != null && right != null) return root

        // Otherwise, return the non-null child
        return left ?: right
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        val root1 = Node(3)
        root1.left = Node(5, parent = root1)
        root1.right = Node(1, parent = root1)
        root1.left?.left = Node(6, parent = root1.left)
        root1.left?.right = Node(2, parent = root1.left)
        root1.left?.right?.left = Node(7, parent = root1.left?.right)
        root1.left?.right?.right = Node(4, parent = root1.left?.right)
        root1.right?.left = Node(0, parent = root1.right)
        root1.right?.right = Node(8, parent = root1.right)

        println(lowestCommonAncestor(root1, root1.left!!, root1.right!!).toString()) // Output: 3

        // Example 2
        val root2 = Node(3)
        root2.left = Node(5, parent = root2)
        root2.right = Node(1, parent = root2)
        root2.left?.left = Node(6, parent = root2.left)
        root2.left?.right = Node(2, parent = root2.left)
        root2.left?.right?.left = Node(7, parent = root2.left?.right)
        root2.left?.right?.right = Node(4, parent = root2.left?.right)
        root2.right?.left = Node(0, parent = root2.right)
        root2.right?.right = Node(8, parent = root2.right)

        println(
            lowestCommonAncestor(
                root2,
                root2.left!!,
                root2.left?.right?.right!!
            ).toString()
        ) // Output: 5

        // Example 3
        val root3 = Node(1)
        root3.left = Node(2, parent = root3)
        println(lowestCommonAncestor(root3, root3, root3.left!!).toString()) // Output: 1
    }
}