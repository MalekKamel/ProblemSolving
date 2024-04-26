package challenges.mostafa._1_arrays.ad_hoc.permutation

/**
A permutation of an array of integers is an arrangement of its members into a sequence
or linear order.

For example, for arr = [1,2,3], the following are all the permutations of
arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater
permutation of its integer. More formally, if all the permutations of the array
are sorted in one container according to their lexicographical order, then
the next permutation of that array is the permutation that follows it in the sorted container.
If such arrangement is not possible, the array must be rearranged as the lowest possible
order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have
a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.

Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]
Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]
Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100

https://leetcode.com/problems/next-permutation/description/
 */

internal object NextPermutation {
    /**
    Let's break down how to find the next bigger permutation of a number, using an example
    like [1, 2, 3]:

    1. Finding the Dip:

    Imagine the numbers as digits in a number. We want to find the point where the digits
    start decreasing as we move from right to left. In [1, 2, 3],
    this happens at the '2' because '3' is bigger.

    2. Finding the Swap:

    After the 'dip' ('2'), we look for the smallest digit to its right that's still bigger than it.
    In our case, that's '3'.
    We swap the '2' and the '3', giving us [1, 3, 2].

    3. Making it the Smallest:

    To get the smallest possible increase in our permutation, we need to make sure the digits
    after the swap point ('3' in our example) are in ascending order.
    We reverse the digits after '3', which are already in descending order,
    to put them in ascending order.
    That's it! We've found the next permutation: [1, 3, 2].

    Think of it like this: We're trying to find the next biggest number we can make
    with the same digits. We find the 'dip' to see where the number starts getting smaller,
    swap it with the next biggest digit to increase the value, and then rearrange
    the rest to be the smallest possible.
     */
    private fun nextPermutation(nums: IntArray) {
        // Find the first decreasing element from the right
        var i = nums.size - 2
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--
        }

        if (i >= 0) {
            // Find the smallest element larger than nums[i] from the right
            var j = nums.size - 1
            while (j > i && nums[j] <= nums[i]) {
                j--
            }

            // Swap nums[i] and nums[j]
            swap(nums, i, j)
        }

        // Reverse the suffix starting at index i + 1
        reverse(nums, i + 1)
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }

    private fun reverse(nums: IntArray, start: Int) {
        var i = start
        var j = nums.size - 1
        while (i < j) {
            swap(nums, i, j)
            i++
            j--
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var array = intArrayOf(1, 2, 3)
        nextPermutation(array)
        println(array.contentToString())

        array = intArrayOf(3, 2, 1)
        nextPermutation(array)
        println(array.contentToString())

        array = intArrayOf(1, 1, 5)
        nextPermutation(array)
        println(array.contentToString())
    }
}