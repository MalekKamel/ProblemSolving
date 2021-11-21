package challenges.cracking_coding_interview.first_common_ancestor

import challenges.util.TreeNode

/**
 * Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree.
 * Avoid storing additional nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 */
object QuestionC {

    fun commonAncestor(root: TreeNode?, p: TreeNode, q: TreeNode): TreeNode? {
        if (!covers(root, p) || !covers(root, q)) {
            return null
        } else if (covers(p, q)) {
            return p
        } else if (covers(q, p)) {
            return q
        }
        var sibling: TreeNode? = getSibling(p)
        var parent: TreeNode? = p.parent
        while (!covers(sibling, q)) {
            sibling = getSibling(parent)
            parent = parent?.parent
        }
        return parent
    }

    fun covers(root: TreeNode?, p: TreeNode): Boolean {
        if (root == null) return false
        return if (root === p) true else covers(root.left, p) || covers(root.right, p)
    }

    fun getSibling(node: TreeNode?): TreeNode? {
        if (node?.parent == null) {
            return null
        }
        val parent: TreeNode = node.parent!!
        return if (parent.left === node) parent.right else parent.left
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