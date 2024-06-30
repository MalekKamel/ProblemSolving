package challenges.mostafa._1_arrays._3_2_pointers

/**
You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]

Given an integer array arr, return the length of the longest subarray, which is a mountain.
Return 0 if there is no mountain subarray.

Example 1:

Input: arr = [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
Example 2:

Input: arr = [2,2,2]
Output: 0
Explanation: There is no mountain.

Constraints:

1 <= arr.length <= 104
0 <= arr[i] <= 104

Follow up:

Can you solve it using only one pass?
Can you solve it in O(1) space?

https://leetcode.com/problems/longest-mountain-in-array/description/
 */

internal object P3LongestMountain {

    private fun longestMountain(arr: IntArray): Int {
        var maxLength = 0
        val n = arr.size
        var i = 1

        while (i < n - 1) {
            var left = i - 1
            var right = i + 1

            if (arr[i] > arr[left] && arr[i] > arr[right]) {

                while (left > 0 && arr[left] > arr[left - 1]) {
                    left--
                }

                while (right < n - 1 && arr[right] > arr[right + 1]) {
                    right++
                }

                maxLength = maxOf(maxLength, right - left + 1)
            }
            i++
        }

        return maxLength
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var array = intArrayOf(2, 1, 4, 7, 3, 2, 5)

        println("1st")
        println(longestMountain(array))

        println("2nd")
        array = intArrayOf(2, 2, 2)
        println(longestMountain(array))
    }
}