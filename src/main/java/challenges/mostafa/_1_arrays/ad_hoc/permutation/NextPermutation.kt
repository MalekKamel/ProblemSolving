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
    1. Problem Explanation:

    The problem asks us to find the next lexicographically greater permutation of a given array of
    integers. Lexicographical order is similar to alphabetical order but applied to sequences of
    numbers. If we consider all possible permutations of the array sorted in lexicographical order,
    we need to find the permutation that comes immediately after the given one. If the given
    permutation is the last one in lexicographical order (i.e., the array is sorted in descending order),
    we need to rearrange it to the first (smallest) permutation, which is the array sorted in
    ascending order. The modification must be done in place using only constant extra memory.

    2. Pattern Identification and Rationale:

    The pattern to solve this problem relies on understanding how lexicographical order works. To
    find the next greater permutation, we aim to make the smallest possible change to the rightmost
    part of the array that results in a larger permutation.

    The algorithm follows these steps:

    Identify the pivot: We scan the array from right to left to find the first element nums[i] that
    is smaller than the element to its right nums[i+1]. This element nums[i] is our potential pivot.
    This indicates a point where we can potentially increase the number. If no such element exists,
    it means the array is in descending order, and we need to reverse the entire array to get
    the smallest permutation.

    Find the swap element: If a pivot is found at index i (meaning i >= 0), we then scan the subarray
    to the right of i (from right to left) to find the smallest element nums[j] that is greater
    than nums[i].

    Swap: We swap the pivot element nums[i] with the found element nums[j]. This ensures that the prefix
    of the array up to index i is now lexicographically larger.

    Reverse the suffix: Finally, to make the resulting permutation the next lexicographically greater
    one, we need to make the suffix of the array starting from index i+1 as small as possible. Since
    we swapped with the smallest element greater than nums[i] in the suffix, the remaining elements
    in the suffix (from i+1 to the end) are in descending order (or non-increasing). To make this
    suffix the smallest, we reverse it.

    3. Solution Breakdown:

    Find the first decreasing element from the right (index i):

    The code initializes a pointer i to the second-to-last element of the array (nums.size - 2).
    It iterates backwards (while i is greater than or equal to 0) as long as the current
    element nums[i] is greater than or equal to the element to its right nums[i + 1].
    After this loop, i will either be the index of the first decreasing element from the right,
    or it will be -1 if the array is in descending order (no such element exists).
    Find the smallest element larger than nums[i] from the right (index j) and Swap:

    The code checks if a decreasing element was found (if (i >= 0)).
    If i is non-negative:
    It initializes another pointer j to the last element of the array (nums.size - 1).
    It iterates backwards (while j is greater than i) as long as the element at nums[j] is less than
    or equal to the element at nums[i]. This loop finds the smallest element in the suffix that is
    still greater than nums[i].
    It then calls the swap function to exchange the elements at indices i and j.
    Reverse the suffix starting at index i + 1:

    After potentially performing the swap (or if no swap was needed because the array was in
    descending order, in which case i would be -1), the code calls the reverse function with
    the starting index i + 1.
    If i was -1 (descending order), i + 1 will be 0, and the entire array will be reversed,
    resulting in the smallest permutation (ascending order).
    If i was a valid index, the reverse function will reverse the portion of the array to the
    right of the swapped element, ensuring the suffix is in ascending order.
    Helper Functions:

    swap(nums: IntArray, i: Int, j: Int): This function takes the array and two indices i and j as
    input and swaps the elements at these indices using a temporary variable.

    reverse(nums: IntArray, start: Int): This function takes the array and a starting index start
    as input. It reverses the portion of the array from the start index to the end of the array using
    two pointers, i starting at start and j starting at the last element. It swaps elements at these
    pointers and moves them towards the middle until they meet or cross.

    4. Efficient Implementation:

    The provided Kotlin code is efficient and adheres to the problem constraints:

    Time Complexity: The nextPermutation function involves at most three linear scans of a portion of
    the array: one to find the decreasing element (pivot), one to find the element to swap with, and
    one to reverse the suffix. Each of these operations takes O(n) time in the worst case, where n
    is the length of the array. Therefore, the overall time complexity is O(n).

    Space Complexity: The algorithm operates directly on the input array (nums) and uses only
    a constant amount of extra memory for variables like i, j, and temp in the swap function.
    Thus, the space complexity is O(1), satisfying the "constant extra memory" requirement.
     */
    private fun nextPermutation(nums: IntArray) {
        // Find the first decreasing element from the right
        val n = nums.size
        var i = n - 2
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--
        }

        // Finding the swap
        // Find the smallest element larger than nums[i] from the right
        if (i >= 0) {
            var j = n - 1
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