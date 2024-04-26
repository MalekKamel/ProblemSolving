package challenges.mostafa._1_arrays._2_sliding_window

/**
Given an integer array nums and an integer k, return true if there are two distinct
indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.


Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false

 */

internal object P3ContainsNearbyDuplicate {

    private fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val map = mutableMapOf<Int, Int>()

        for (i in nums.indices) {
            val num = nums[i]
            // i - map[num]!! => The distance
            if (map.containsKey(num) && i - map[num]!! <= k) {
                return true
            }

            map[num] = i
        }

        return false
    }

    private fun containsNearbyDuplicate2(nums: IntArray, k: Int): Boolean {
        val set = HashSet<Int>()

        for (i in nums.indices) {
            val num = nums[i]

            if (set.contains(num)) {
                return true
            }

            set.add(num)

            if (set.size > k) {
                set.remove(nums[i - k])
            }
        }

        return false
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var array = intArrayOf(1, 2, 3, 1)
        var k = 3

        println("First")
        println(containsNearbyDuplicate(array, k))
        println(containsNearbyDuplicate2(array, k))

        println("Second")
        array = intArrayOf(1, 0, 1, 1)
        k = 1
        println(containsNearbyDuplicate(array, k))
        println(containsNearbyDuplicate2(array, k))

        println("Third")
        array = intArrayOf(1, 2, 3, 1, 2, 3)
        k = 2
        println(containsNearbyDuplicate(array, k))
        println(containsNearbyDuplicate2(array, k))
    }
}