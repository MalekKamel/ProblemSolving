package challenges.cracking_coding_interview.random_node

import java.util.*

/* One node of a binary tree. The data element stored is a single
 * character.
 */
class TreeNode(var data: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
    private var size = 1
    fun insertInOrder(d: Int) {
        if (d <= data) {
            if (left == null) {
                left = TreeNode(d)
            } else {
                left!!.insertInOrder(d)
            }
        } else {
            if (right == null) {
                right = TreeNode(d)
            } else {
                right!!.insertInOrder(d)
            }
        }
        size++
    }

    fun size(): Int {
        return size
    }

    fun find(d: Int): TreeNode? {
        if (d == data) {
            return this
        } else if (d <= data) {
            return if (left != null) left!!.find(d) else null
        } else if (d > data) {
            return if (right != null) right!!.find(d) else null
        }
        return null
    }

    val randomNode: TreeNode
        get() {
            val leftSize = if (left == null) 0 else left!!.size()
            val random = Random()
            val index = random.nextInt(size)
            return if (index < leftSize) {
                left!!.randomNode
            } else if (index == leftSize) {
                this
            } else {
                right!!.randomNode
            }
        }

    fun getIthNode(i: Int): TreeNode {
        val leftSize = if (left == null) 0 else left!!.size()
        return if (i < leftSize) {
            left!!.getIthNode(i)
        } else if (i == leftSize) {
            this
        } else {
            right!!.getIthNode(i - (leftSize + 1))
        }
    }

}