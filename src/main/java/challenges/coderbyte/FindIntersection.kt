package challenges.coderbyte

import java.util.*


/*
Have the function FindIntersection(strArr) read the array of strings stored in strArr which will contain 2
elements: the first element will represent a list of comma-separated numbers sorted in ascending order,
the second element will represent a second list of comma-separated numbers (also sorted). Your goal is
to return a comma-separated string containing the numbers that occur in elements of strArr in sorted order.
If there is no intersection, return the string false.

For example: if strArr contains ["1, 3, 4, 7, 13", "1, 2, 4, 13, 15"] the output should return "1,4,13"
because those numbers appear in both strings. The array given will not be empty, and each string inside
the array will be of numbers sorted in ascending order and may contain negative numbers.
 */
object FindIntersection {

    private fun challenge(strArr: Array<String>): String {
        val firstSet = strArr[0].split(", ").map { it.toInt() }
        val secondSet = strArr[1].split(", ").map { it.toInt() }
        val result = intersection(firstSet, secondSet)
        return if (result.isEmpty()) "false" else result.joinToString(",")
    }

    private fun intersection(a: List<Int>, b: List<Int>): ArrayList<Int> {
        val result = ArrayList<Int>()
        var i = 0
        var j = 0
        while (i < a.size && j < b.size) {
            when {
                a[i] == b[j] -> {
                    result.add(a[i])
                    ++i
                    ++j
                }
                a[i] < b[j] -> {
                    ++i
                }
                a[i] > b[j] -> {
                    ++j
                }
            }
        }
        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(challenge(arrayOf("1, 3, 4, 7, 13", "1, 2, 4, 13, 15"))) // 1,4,13
        println(challenge(arrayOf("1, 3, 9, 10, 17, 18", "1, 4, 9, 10"))) // 1,9,10
        println(challenge(arrayOf("1, 2, 3", "4, 5, 6"))) // false
    }

}