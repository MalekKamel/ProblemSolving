package challenges.cracking_coding_interview.trees_graphs.first_common_ancestor

import challenges.util.TreeNode

/**
 * Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree.
 * Avoid storing additional nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 */
object Question {
    private var TWO_NODES_FOUND = 2
    private var ONE_NODE_FOUND = 1
    private var NO_NODES_FOUND = 0

    // Checks how many 'special' nodes are located under this root
    private fun covers(root: TreeNode?, p: TreeNode, q: TreeNode): Int {
        var ret = NO_NODES_FOUND
        if (root == null) return ret
        if (root == p || root == q) ret += 1
        ret += covers(root.left, p, q)
        return if (ret == TWO_NODES_FOUND) ret else ret + covers(root.right, p, q)
    }

    private fun commonAncestor(root: TreeNode, p: TreeNode, q: TreeNode): TreeNode? {
        if (q == p && (root.left == q || root.right == q)) return root
        val nodesFromLeft = covers(root.left, p, q) // Check left side
        if (nodesFromLeft == TWO_NODES_FOUND) {
            return if (root.left == p || root.left == q) root.left else commonAncestor(root.left!!, p, q)
        } else if (nodesFromLeft == ONE_NODE_FOUND) {
            if (root == p) return p else if (root == q) return q
        }
        val nodesFromRight = covers(root.right, p, q) // Check right side
        if (nodesFromRight == TWO_NODES_FOUND) {
            return if (root.right == p || root.right == q) root.right else commonAncestor(root.right!!, p, q)
        } else if (nodesFromRight == ONE_NODE_FOUND) {
            if (root == p) return p else if (root == q) return q
        }
        return if (nodesFromLeft == ONE_NODE_FOUND &&
            nodesFromRight == ONE_NODE_FOUND
        ) root else null
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val root: TreeNode = TreeNode.createMinimalBST(array) ?: return
        val n3: TreeNode = root.find(1)!!
        val n7: TreeNode = root.find(7)!!
        val ancestor: TreeNode? = commonAncestor(root, n3, n7)
        println(ancestor?.data)
    }

}