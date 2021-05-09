package challenges.misc

/*
The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of
the numbers in this set got duplicated to another number in the set, which results in repetition of one
number and loss of another number.

Given an array num representing the data status of this set after the error. your task is to firstly find
the number occurs twice and then find the number that is missing. Return them in the form of an array.
 */
object SetMismatch {

    private fun solve(arr: IntArray): IntArray {
        val elements = hashMapOf<Int, Int>()

        var duplicated = -1
        var missing = -1

        for ((i, item) in arr.withIndex()) {
            if (elements.containsKey(item)) {
                duplicated = item
                missing = arr[i - 1] + 1
                break
            }
            elements[item] = 1
        }

        return intArrayOf(duplicated, missing)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val result = solve(intArrayOf(1, 2, 3, 2))
        println(result.joinToString()) // 2, 4
    }
}