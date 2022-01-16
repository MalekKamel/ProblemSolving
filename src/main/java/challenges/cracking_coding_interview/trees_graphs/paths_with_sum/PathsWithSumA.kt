package challenges.cracking_coding_interview.trees_graphs.paths_with_sum

import challenges.util.TreeNode


object PathsWithSumA {
    fun countPathsWithSum(root: TreeNode?, targetSum: Int): Int {
        if (root == null) return 0

        /* Count paths with sum starting from the root. */
        val pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0)

        /* Try the nodes on the left and right. */
        val pathsOnLeft = countPathsWithSum(root.left, targetSum)
        val pathsOnRight = countPathsWithSum(root.right, targetSum)
        return pathsFromRoot + pathsOnLeft + pathsOnRight
    }

    /* Returns the number of paths with this sum starting from this node. */
    private fun countPathsWithSumFromNode(node: TreeNode?, targetSum: Int, currentSum: Int): Int {
        var currentSum = currentSum
        if (node == null) return 0
        currentSum += node.data
        var totalPaths = 0
        if (currentSum == targetSum) { // Found a path from the root
            totalPaths++
        }
        totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum) // Go left
        totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum) // Go right
        return totalPaths
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
		println(countPathsWithSum(root, 0));*/

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
		println(countPathsWithSum(root, -14));*/
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