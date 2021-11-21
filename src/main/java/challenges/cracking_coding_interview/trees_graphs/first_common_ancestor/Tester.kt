package challenges.cracking_coding_interview.trees_graphs.first_common_ancestor

import challenges.cracking_coding_interview.trees_graphs.first_common_ancestor.QuestionA.commonAncestor
import challenges.cracking_coding_interview.trees_graphs.first_common_ancestor.QuestionC.commonAncestor
import challenges.util.TreeNode


object Tester {
    private fun resultToString(s: String, x: TreeNode?, y: TreeNode?, anc: TreeNode?): String {
        var s = s
        s += ": "
        s += x?.data ?: "null"
        s += " & "
        s += y?.data ?: "null"
        s += " -> "
        s += anc?.data ?: "null"
        return s
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val root: TreeNode = TreeNode.createMinimalBST(array) ?: return
        val nodes: ArrayList<TreeNode> = ArrayList()
        for (a in array) {
            nodes.add(root.find(a)!!)
        }
        nodes.add(TreeNode(11))
        for (x in nodes) {
            for (y in nodes) {
                val r1: TreeNode? = commonAncestor(x, y)
                val r2: TreeNode? = QuestionB.commonAncestor(x, y)
                val r3: TreeNode? = commonAncestor(root, x, y)
                val r4: TreeNode? = QuestionD.commonAncestor(root, x, y)
                val r5: TreeNode? = QuestionE.commonAncestor(root, x, y)
                val s1 = resultToString("A", x, y, r1)
                val s2 = resultToString("B", x, y, r2)
                val s3 = resultToString("C", x, y, r3)
                val s4 = resultToString("D", x, y, r4)
                val s5 = resultToString("D", x, y, r5)
                if (r1 == r2 && r2 == r3 && r3 == r4 && r4 == r5) {
                    println("SUCCESS: $s1")
                } else {
                    println("ERROR")
                    println(s1)
                    println(s2)
                    println(s3)
                    println(s4)
                    println(s5)
                }
            }
        }
    }

}