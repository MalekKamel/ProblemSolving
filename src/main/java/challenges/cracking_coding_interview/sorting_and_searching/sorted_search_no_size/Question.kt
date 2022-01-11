package challenges.cracking_coding_interview.sorting_and_searching.sorted_search_no_size

import kotlin.jvm.JvmStatic

object Question {

    fun search(list: Listy, value: Int): Int {
        var index = 1
        while (list.elementAt(index) != -1 && list.elementAt(index) < value) {
            index *= 2
        }
        return binarySearch(list, value, index / 2, index)
    }

    private fun binarySearch(list: Listy, value: Int, _low: Int, _high: Int): Int {
        var low = _low
        var high = _high
        var mid: Int
        while (low <= high) {
            mid = (low + high) / 2
            val middle = list.elementAt(mid)
            if (middle > value || middle == -1) {
                high = mid - 1
            } else if (middle < value) {
                low = mid + 1
            } else {
                return mid
            }
        }
        return -1
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(1, 2, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 16, 18)
        val list = Listy(array)
        for (a in array) {
            println(search(list, a))
        }
        println(search(list, 15))
    }
}