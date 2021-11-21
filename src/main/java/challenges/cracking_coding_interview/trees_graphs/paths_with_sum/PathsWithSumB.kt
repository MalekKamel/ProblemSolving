package challenges.cracking_coding_interview.trees_graphs.paths_with_sum

import challenges.util.TreeNode

object PathsWithSumB {

    fun countPathsWithSum(root: TreeNode?, targetSum: Int): Int {
        return countPathsWithSum(root, targetSum, 0, HashMap())
    }

    private fun countPathsWithSum(
        node: TreeNode?,
        targetSum: Int,
        runningSum: Int,
        pathCount: HashMap<Int?, Int>
    ): Int {
        var runningSum = runningSum
        if (node == null) return 0 // Base case
        runningSum += node.data

        /* Count paths with sum ending at the current node. */
        val sum = runningSum - targetSum
        var totalPaths = pathCount.getOrDefault(sum, 0)

        /* If runningSum equals targetSum, then one additional path starts at root. Add in this path.*/
        if (runningSum == targetSum) {
            totalPaths++
        }

        /* Add runningSum to pathCounts. */
        incrementHashTable(pathCount, runningSum, 1)

        /* Count paths with sum on the left and right. */
        totalPaths += countPathsWithSum(
            node.left,
            targetSum,
            runningSum,
            pathCount
        )
        totalPaths += countPathsWithSum(node.right, targetSum, runningSum, pathCount)
        incrementHashTable(pathCount, runningSum, -1) // Remove runningSum
        return totalPaths
    }

    private fun incrementHashTable(hashTable: HashMap<Int?, Int>, key: Int, delta: Int) {
        val newCount = hashTable.getOrDefault(key, 0) + delta
        if (newCount == 0) { // Remove when zero to reduce space usage
            hashTable.remove(key)
        } else {
            hashTable[key] = newCount
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        /*
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(-8);
		root.left.right = new TreeNode(8);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(6);
		root.right.left.left = new TreeNode(0);
		System.out.println(countPathsWithSum(root, 0));
		*/

        /*TreeNode root = new TreeNode(-7);
		root.left = new TreeNode(-7);
		root.left.right = new TreeNode(1);
		root.left.right.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(3);
		root.right.right = new TreeNode(20);
		root.right.right.left = new TreeNode(0);
		root.right.right.left.left = new TreeNode(-3);
		root.right.right.left.left.right = new TreeNode(2);
		root.right.right.left.left.right.left = new TreeNode(1);
		System.out.println(countPathsWithSum(root, 0));*/
        val root = TreeNode(0)
        root.left = TreeNode(0)
        root.right = TreeNode(0)
        root.right?.left = TreeNode(0)
        root.right?.left?.right = TreeNode(0)
        root.right?.right = TreeNode(0)
        println(countPathsWithSum(root, 0))
        println(countPathsWithSum(root, 4))
    }
}