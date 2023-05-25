package challenges.educative_grokking_coding_interview.twe_pointers._2

import challenges.util.PrintHyphens
import java.util.*


/**
Given an array of integers, nums, and an integer value, target, determine if
there are any three integers in nums whose sum is equal to the target, that is,
nums[i] + nums[j] + nums[k] == target. Return TRUE if three such integers exist in the array.
Otherwise, return FALSE.
Note: A valid triplet consists of elements with distinct indexes.
https://www.educative.io/courses/grokking-coding-interview-patterns-java/qAW3LvvJrQy
 */

internal object SumOfThree {
    private fun findSumOfThree(nums: IntArray, target: Int): Boolean {
        // Sort the input list
        Arrays.sort(nums)
        var low: Int
        var high: Int
        var triples: Int

        // Fix one integer at a time and find the other two
        for (i in 0 until nums.size - 2) {
            // Initialize the two pointers
            low = i + 1
            high = nums.size - 1

            // Traverse the list to find the triplet whose sum equals the target
            while (low < high) {
                triples = nums[i] + nums[low] + nums[high]

                // The sum of the triplet equals the target
                if (triples == target) {
                    return true
                } else if (triples < target) {
                    low++
                } else {
                    high--
                }
            }
        }

        // No such triplet found whose sum equals the given target
        return false
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val numsList = arrayOf(
            intArrayOf(3, 7, 1, 2, 8, 4, 5),
            intArrayOf(-1, 2, 1, -4, 5, -3),
            intArrayOf(2, 3, 4, 1, 7, 9),
            intArrayOf(1, -1, 0),
            intArrayOf(2, 4, 2, 7, 6, 3, 1)
        )
        val testList = intArrayOf(10, 7, 20, -1, 8)
        for (i in testList.indices) {
            print(i + 1)
            println(".\tInput array: " + numsList[i].contentToString())
            if (findSumOfThree(numsList[i], testList[i])) {
                println("\tSum for " + testList[i] + " exists ")
            } else {
                println("\tSum for " + testList[i] + " does not exist ")
            }
            println(PrintHyphens.repeat("-", 100))
        }
    }
}