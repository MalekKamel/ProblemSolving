package challenges.cracking_coding_interview.trees_graphs.first_common_ancestor

import challenges.util.TreeNode

/**
 * Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree.
 * Avoid storing additional nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 */
object QuestionF {
    private fun commonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (p == null || q == null) {
            return null
        }
        var ap: TreeNode? = p.parent
        while (ap != null) {
            var aq: TreeNode? = q.parent
            while (aq != null) {
                if (aq == ap) {
                    return aq
                }
                aq = aq.parent
            }
            ap = ap.parent
        }
        return null
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(5, 3, 6, 1, 9, 11)
        val root = TreeNode(20)
        for (a in array) {
            root.insertInOrder(a)
        }
        val n1: TreeNode = root.find(1)!!
        val n9: TreeNode = root.find(9)!!
        val ancestor: TreeNode? = commonAncestor(root, n1, n9)
        println(ancestor?.data)
    }

}