package challenges.cracking_coding_interview.trees_graphs.random_node

import java.util.*


class Tree {
    var root: TreeNode? = null
    fun insertInOrder(value: Int) {
        if (root == null) {
            root = TreeNode(value)
        } else {
            root!!.insertInOrder(value)
        }
    }

    fun size(): Int {
        return if (root == null) 0 else root!!.size()
    }

    val randomNode: TreeNode?
        get() {
            if (root == null) return null
            val random = Random()
            val i = random.nextInt(size())
            return root!!.getIthNode(i)
        }
}