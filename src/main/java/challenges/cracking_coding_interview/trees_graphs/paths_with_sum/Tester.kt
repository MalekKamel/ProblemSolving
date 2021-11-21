package challenges.cracking_coding_interview.trees_graphs.paths_with_sum

import challenges.util.AssortedMethods
import challenges.util.TreeNode


object Tester {
    @JvmStatic
    fun main(args: Array<String>) {
        var isWorking = true
        while (isWorking) {
            val min = -20
            val max = 20
            val size = 20
            val root: TreeNode = AssortedMethods.randomBST(size, min, max) ?: return
            for (targetSum in (-1).coerceAtMost(min * size - 10)..100.coerceAtLeast(max * size + 10)) {
                val answerA: Int = PathsWithSumA.countPathsWithSum(root, targetSum)
                val answerB: Int = PathsWithSumB.countPathsWithSum(root, targetSum)
                if (answerA > 0 || answerB > 0) {
                    println(targetSum.toString() + ": " + answerA + ", " + answerB + " | " + (answerA == answerB))
                }
                if (answerA != answerB) {
                    isWorking = false
                    break
                }
            }
        }
    }
}