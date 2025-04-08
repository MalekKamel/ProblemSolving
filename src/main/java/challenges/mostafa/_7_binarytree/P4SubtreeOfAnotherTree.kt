package challenges.mostafa._7_binarytree

/**
Given the roots of two binary trees root and subRoot, return true if there is a subtree of root
with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this
node's descendants. The tree tree could also be considered as a subtree of itself.

Example 1:

Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true

Example 2:

Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false

Constraints:

The number of nodes in the root tree is in the range [1, 2000].
The number of nodes in the subRoot tree is in the range [1, 1000].
-10^4 <= root.val <= 10^4
-10^4 <= subRoot.val <= 10^4

https://leetcode.com/problems/subtree-of-another-tree/description/
 */

internal object P4SubtreeOfAnotherTree {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null

        override fun toString(): String {
            return `val`.toString()
        }
    }

    /**
     * 1. Problem Explanation
     * The problem asks us to determine if a binary tree 'subRoot' is a subtree of another binary
     * tree 'root'.
     * A subtree consists of a node in 'root' and all of its descendants, having the exact same
     * structure and node values as 'subRoot'.
     */
    private fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        val rootString = parenthesize(root)
        val subRootString = parenthesize(subRoot)
        return rootString.contains(subRootString)
    }

    private fun parenthesize(node: TreeNode?): String {
        if (node == null) return "null"
        return "(${node.`val`} ${parenthesize(node.left)} ${parenthesize(node.right)})"
    }

    /**
     * 2. Pattern Identification and Rationale
     * The core idea to solve this problem is to traverse the 'root' tree and, for each node in 'root',
     * check if the subtree rooted at that node is identical to 'subRoot'.
     * This suggests a recursive approach combined with a comparison function.
     * We will use a depth-first search (DFS) pattern to traverse the 'root' tree.
     * For each node visited in 'root', we will use another recursive function to check if the subtree
     * starting from this node is identical to 'subRoot'.

     * 3. Solution Breakdown
     * Step 1: Handle base cases.
     *    - If 'subRoot' is null, it's considered a subtree of any tree (including null), so return true.
     *    - If 'root' is null but 'subRoot' is not, then 'subRoot' cannot be a subtree of 'root',
     *    so return false.

     * Step 2: Check if the subtree rooted at the current 'root' node is equal to 'subRoot'.
     *    - We will create a helper function `areIdentical(p: TreeNode?, q: TreeNode?)` to compare
     *    two binary trees for identical structure and values.

     * Step 3: Recursively search for 'subRoot' in the left and right subtrees of 'root'.
     *    - If the current 'root' node's subtree is not identical to 'subRoot', we recursively call
     *    `isSubtree` on the left and right children of 'root'.
     *    - If either of these recursive calls returns true, it means we found a subtree identical to 'subRoot'.
     */
    private fun isSubtree2(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (subRoot == null) {
            return true
        }
        if (root == null) {
            return false
        }

        return areIdentical(root, subRoot) ||
                isSubtree2(root.left, subRoot) ||
                isSubtree2(root.right, subRoot)
    }

    private fun areIdentical(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) {
            return true
        }
        if (p == null || q == null || p.`val` != q.`val`) {
            return false
        }
        return areIdentical(p.left, q.left) && areIdentical(p.right, q.right)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        val root1 = TreeNode(3)
        root1.left = TreeNode(4)
        root1.right = TreeNode(5)
        root1.left?.left = TreeNode(1)
        root1.left?.right = TreeNode(2)

        val subRoot1 = TreeNode(4)
        subRoot1.left = TreeNode(1)
        subRoot1.right = TreeNode(2)

        println(isSubtree(root1, subRoot1)) // Output: true
        println(isSubtree2(root1, subRoot1)) // Output: true

        // Example 2
        val root2 = TreeNode(3)
        root2.left = TreeNode(4)
        root2.right = TreeNode(5)
        root2.left?.left = TreeNode(1)
        root2.left?.right = TreeNode(2)
        root2.left?.right?.left = TreeNode(0)

        val subRoot2 = TreeNode(4)
        subRoot2.left = TreeNode(1)
        subRoot2.right = TreeNode(2)

        println(isSubtree(root2, subRoot2)) // Output: false
        println(isSubtree2(root2, subRoot2)) // Output: false
    }
}