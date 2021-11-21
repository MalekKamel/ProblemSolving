package challenges.cracking_coding_interview.trees_graphs.random_node

/**
 * You are implementing a binary tree class from scratch which, in addition to insert, find,
 * and delete, has a method getRandomNode() which returns a random node from the tree.
 * All nodes should be equally likely to be chosen. Design and implement an algorithm for
 * getRandomNode, and explain how you would implement the rest of the methods.
 */
object RandomNode {

    @JvmStatic
    fun main(args: Array<String>) {
        val counts = IntArray(10)
        for (i in 0..999999) {
            val tree = Tree()
            val array = intArrayOf(1, 0, 6, 2, 3, 9, 4, 5, 8, 7)
            for (x in array) {
                tree.insertInOrder(x)
            }
            val d = tree.randomNode!!.data
            counts[d]++
        }
        for (i in counts.indices) {
            println(i.toString() + ": " + counts[i])
        }
    }

}