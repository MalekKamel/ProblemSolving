package challenges.mostafa._1_arrays.ad_hoc.permutation

/**
Given an array nums of distinct integers, return all the possible permutations. You can return
the answer in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]

Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.

https://leetcode.com/problems/permutations/description/

Solution Video: https://www.youtube.com/watch?v=FZe0UqISmUw
 */

internal object Permutations {

    /**
     * In mathematics, a permutation is an arrangement of objects in a specific order.
     * Think of it as all the possible ways you can order a set of items.
     *
     * For example, if you have the set {1, 2, 3}, the possible permutations are:
     *
     * (1, 2, 3)
     * (1, 3, 2)
     * (2, 1, 3)
     * (2, 3, 1)
     * (3, 1, 2)
     * (3, 2, 1)
     * There are 3! (3 factorial, or 3 * 2 * 1 = 6) different permutations in this case.
     *
     * The Kotlin code implements a recursive algorithm to generate all these possible orderings
     * for a given list of integers. Let's break down how it works:
     *
     * Base Case: If the input list has only one element, there's only one possible
     * ordering (the list itself), so it returns a list containing that single-element list.
     *
     * Recursive Step:
     *
     * It iterates through each element in the input list.
     * For each element, it creates a new list called remainingList by removing the current element.
     * It then recursively calls permutationsRecursive on this remainingList to get all possible
     * permutations of the remaining elements.
     * Finally, it takes each permutation of the remainingList and adds the current element to
     * the beginning of it, effectively creating all permutations that start with that element.
     * So, the code systematically builds up all possible orderings by taking each element as
     * the first element and then finding all possible orderings of the remaining elements.
     *
     * As a software engineer, you likely encounter permutations in various scenarios, such as:
     *
     * Algorithm design: For problems involving ordering or sequencing.
     * Testing: Generating different test cases.
     * Cryptography: In certain cryptographic algorithms.
     * Your recursive implementation is a common and elegant way to solve the permutation problem.
     */
    private fun permute(nums: List<Int>): List<List<Int>> {
        if (nums.size == 1) return listOf(nums)

        val result = mutableListOf<List<Int>>()
        for (i in nums.indices) {
            val element = nums[i]
            val remainingList = nums.toMutableList().also { it.removeAt(i) }
            val subPermutations = permute(remainingList)
            for (permutation in subPermutations) {
                result.add(listOf(element) + permutation)
            }
        }
        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = listOf(1, 2, 3)

        println(permute(array))
    }
}