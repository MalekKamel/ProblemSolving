package challenges.educative_grokking_coding_interview.k_way_merge._1

import challenges.util.PrintHyphens

/**
Given two sorted integer arrays, 1 nums1 and 2 nums2, and the number of data elements in each array, m and n ,
implement a function that merges the second array into the first one. You have to modify 1 nums1 in place.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/m72yGKyz8Br
 */
internal object MergeSorted {
    private fun printArrayWithMarkers(arr: IntArray, pValue: Int): String {
        var out = "["
        for (i in 0 until arr.size - 1) {
            if (i == pValue) {
                out += "«"
                out += arr[i].toString() + "»" + ", "
            } else {
                out += arr[i].toString() + ", "
            }
        }
        if (pValue == arr.size - 1) {
            out += "«" + arr[arr.size - 1]
            out += "»]"
        } else {
            out += arr[arr.size - 1]
            out += "]"
        }
        return out
    }

    private fun printArrayWithMarkers(arr: IntArray, iValue: Int, jValue: Int): String {
        var out = "["
        for (i in 0 until arr.size - 1) {
            if (i == iValue || i == jValue) {
                out += "«"
                out += arr[i].toString() + "»" + ", "
            } else {
                out += arr[i].toString() + ", "
            }
        }
        if (iValue == arr.size - 1 || jValue == arr.size - 1) {
            out += "«" + arr[arr.size - 1]
            out += "»]"
        } else {
            out += arr[arr.size - 1]
            out += "]"
        }
        return out
    }

    private fun mergeSorted(nums1: IntArray, m: Int, nums2: IntArray, n: Int): IntArray {
        var p1 = m - 1 // set p1 to the last initialised element of nums1
        val p2 = n - 1 // set p2 to the last element of nums2
        println("\n\t\tInitialising p1 to the last data element of nums1:")
        println("\t\t\t" + printArrayWithMarkers(nums1, p1))
        println("\t\t\tp1: " + p1 + ", value: " + nums1[p1])
        println("\n\t\t Initialising p2 to the last data element of nums2:")
        println("\t\t\t" + printArrayWithMarkers(nums2, p2))
        println("\t\t\tp1: " + p2 + ", value: " + nums2[p2])
        // traverse backwards over the nums1 array
        println("\n\tTraversing the nums1 array using pointer p:")
        var x = 0
        for (p in m + n - 1 downTo 0) {
            println("\t\tLoop iteration: $x")
            x += 1
            println("\t\t\t" + printArrayWithMarkers(nums1, p1))
            print("\t\t\tp1: $p1, value: ")
            if (p1 < 0) println(nums1[p1 + 1]) else println(nums1[p1])
            println("\t\t\t" + printArrayWithMarkers(nums2, p2))
            print("\t\t\tp2: $p2, value: ")
            if (p2 < 0) println(nums2[p2 + 1]) else println(nums2[p2])
            // if p1 is not on the first element and the nums1 element is greater than nums2 element
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                println("\t\t\tThe value at p1(" + nums1[p1] + ") > the value at p2(" + nums2[p2] + ")")
                // set the element at index p = the element on index p1 in nums1
                println("\t\t\t\t" + printArrayWithMarkers(nums1, p1, p))
                println("\t\t\t\tp1: " + p1 + ", value: " + nums1[p1])
                println("\t\t\t\tp: " + p + ", value: " + nums1[p])
                nums1[p] = nums1[p1]
                println("\t\t\t\tChanging the value at p to the value at p1:")
                println("\t\t\t\t\t" + printArrayWithMarkers(nums1, p))
                // move the p1 pointer one element back
                println(
                    """				Decrementing p1: $p1 ⟶ ${p1 - 1}
                    """
                )
                p1 -= 1
            }
        }
        return nums1
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val m = intArrayOf(9, 2, 3, 1, 8)
        val n = intArrayOf(6, 1, 4, 2, 1)
        val nums1 = arrayOf(
            intArrayOf(23, 33, 35, 41, 44, 47, 56, 91, 105, 0, 0, 0, 0, 0, 0),
            intArrayOf(1, 2, 0),
            intArrayOf(1, 1, 1, 0, 0, 0, 0),
            intArrayOf(6, 0, 0),
            intArrayOf(12, 34, 45, 56, 67, 78, 89, 99, 0)
        )
        val nums2 = arrayOf(
            intArrayOf(32, 49, 50, 51, 61, 99),
            intArrayOf(7),
            intArrayOf(1, 2, 3, 4),
            intArrayOf(-45, -99),
            intArrayOf(100)
        )
        var k = 1
        for (i in m.indices) {
            print("$k.\tnums1: [")
            for (j in 0 until nums1[i].size - 1) {
                print(nums1[i][j].toString() + ", ")
            }
            println(nums1[i][nums1[i].size - 1].toString() + "], m: " + m[i])
            print("\tnums2: [")
            for (j in 0 until nums2[i].size - 1) {
                print(nums2[i][j].toString() + ", ")
            }
            println(nums2[i][nums2[i].size - 1].toString() + "], n: " + n[i])
            mergeSorted(nums1[i], m[i], nums2[i], n[i])
            println("\tMerged list: None")
            println(PrintHyphens.repeat("-", 100))
            k += 1
        }
    }
}