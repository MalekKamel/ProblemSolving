package challenges.mostafa._7_binarytree

/**
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
two nodes p and q as the lowest node in T that has both p and q as descendants
(where we allow a node to be a descendant of itself).”

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to
the LCA definition.

Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1

Constraints:

The number of nodes in the tree is in the range [2, 10^5].
-10^9 <= Node.val <= 10^9
All Node.val are unique.
p != q
p and q will exist in the tree.

https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 */

internal object P9LowestCommonAncestorOfBinaryTree {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null

        override fun toString(): String {
            return `val`.toString()
        }
    }

    /**
     * 1. Problem Explanation
     * The problem asks us to find the lowest common ancestor (LCA) of two given nodes, p and q, in a binary tree.
     * The LCA is defined as the lowest node in the tree that has both p and q as descendants (including
     * the case where a node is a descendant of itself).
     *
     * 2. Pattern Identification and Rationale
     * The most suitable pattern for this problem is a recursive approach. We can traverse the tree and
     * check for the presence of the target nodes p and q in the subtrees.
     * The recursion allows us to explore the tree structure efficiently.
     *
     * Advantages of recursion:
     * - It naturally fits the tree structure, as each node can be seen as the root of a smaller subtree.
     * - It allows us to propagate information (whether p or q is found in a subtree) up the call stack.
     * - The base cases (reaching a null node or finding one of the target nodes) are straightforward to define.
     *
     * 3. Solution Breakdown
     * The core idea is to traverse the tree recursively. For each node, we check the following:
     * - If the current node is null, it cannot contain p or q, so we return null.
     * - If the current node is either p or q, then this node is a potential ancestor. We return this node
     * because if the other target node is in its subtree, this node is the LCA. If the other target node
     * is not in its subtree, then this node itself is the LCA (as a node can be a descendant of itself).
     * - Recursively call the function for the left and right subtrees of the current node to find p and
     * q in those subtrees.
     * - If both the left and right recursive calls return a non-null node, it means p is in one subtree
     * and q is in the other. Therefore, the current node is the LCA.
     * - If only one of the recursive calls returns a non-null node, it means either p or q (or both) are
     * in that subtree. The non-null node returned by the recursive call is the LCA in this case.
     * - If both recursive calls return null, it means neither p nor q are in the subtrees of the current
     * node, so we return null.
     *
     * 4. Time Complexity
     * The time complexity of this recursive solution is O(N), where N is the number of nodes in the binary tree.
     * In the worst case, we might visit every node in the tree.
     *
     * 5. Efficient Implementation
     */
    /**
    The function `lowestCommonAncestor` takes three parameters:
    1. `root`: the root node of a binary tree
    2. `p`: a node in the binary tree
    3. `q`: another node in the binary tree

    The function is designed to find the lowest common ancestor (LCA) of the two nodes
    `p` and `q` in the binary tree.

    Here's how the function works:

    1. **Base case**: If the `root` is `null`, or if the `root` is equal to either `p` or `q`,
    the function simply returns the `root`. This handles the case where one of the nodes is
    the root itself.

    2. **Recursive calls**: The function recursively calls itself on the left and right subtrees
    of the `root` node, passing the same `p` and `q` nodes as arguments.

    3. **Left and right results**: The function stores the results of the left and right recursive
    calls in the `left` and `right` variables, respectively.

    4. **Finding the LCA**: If both `left` and `right` are not `null`, it means that the `p`
    and `q` nodes are in different subtrees, and the current `root` node is the lowest common
    ancestor. In this case, the function returns the `root`.

    5. **Returning the result**: If only one of the `left` or `right` results is not `null`,
    it means that both `p` and `q` are in the same subtree (either the left or right), and
    the function returns the non-null result.

    In summary, the function recursively traverses the binary tree, following the paths to both
    `p` and `q` nodes. When it finds that the two nodes are in different subtrees, it returns
    the current `root` node as the lowest common ancestor.

    This algorithm has a time complexity of O(n), where n is the number of nodes in the binary tree,
    as it visits each node exactly once in the worst case. The space complexity is O(h), where h
    is the height of the tree, due to the recursive calls on the call stack.
     */
    private fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || root == p || root == q) return root

        val left = lowestCommonAncestor(root.left, p, q)
        val right = lowestCommonAncestor(root.right, p, q)

        if (left != null && right != null) return root

        return left ?: right
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        val root1 = TreeNode(3)
        root1.left = TreeNode(5)
        root1.right = TreeNode(1)
        root1.left?.left = TreeNode(6)
        root1.left?.right = TreeNode(2)
        root1.left?.right?.left = TreeNode(7)
        root1.left?.right?.right = TreeNode(4)
        root1.right?.left = TreeNode(0)
        root1.right?.right = TreeNode(8)
        println(lowestCommonAncestor(root1, root1.left, root1.right)?.`val`) // Output: 3


    }
}
