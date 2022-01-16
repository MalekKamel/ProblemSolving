package challenges.cracking_coding_interview.sorting_and_searching.find_duplicates

import challenges.util.AssortedMethods.arrayToString
import challenges.util.AssortedMethods.randomArray

object Question {
    private fun checkDuplicates(array: IntArray) {
        val bs = BitSet(32000)
        for (i in array.indices) {
            val num = array[i]
            val num0 = num - 1 // bitset starts at 0, numbers start at 1
            if (bs[num0]) {
                println(num)
            } else {
                bs.set(num0)
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = randomArray(30, 1, 30)
        println(arrayToString(array))
        checkDuplicates(array)
    }
}