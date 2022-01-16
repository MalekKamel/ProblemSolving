package challenges.cracking_coding_interview.sorting_and_searching.rank_from_stream

import challenges.util.AssortedMethods

object Question {
    private var root: RankNode? = null
    private fun track(number: Int) {
        if (root == null) {
            root = RankNode(number)
        } else {
            root!!.insert(number)
        }
    }

    fun getRankOfNumber(number: Int): Int {
        return root!!.getRank(number)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val size = 100
        val list: IntArray = AssortedMethods.randomArray(size, -100, 100)
        for (i in list.indices) {
            track(list[i])
        }
        val tracker = IntArray(size)
        for (i in list.indices) {
            val v = list[i]
            val rank1 = root!!.getRank(
                list[i]
            )
            tracker[rank1] = v
        }
        for (i in 0 until tracker.size - 1) {
            if (tracker[i] != 0 && tracker[i + 1] != 0) {
                if (tracker[i] > tracker[i + 1]) {
                    println("ERROR at $i")
                }
            }
        }
        println("Array: " + AssortedMethods.arrayToString(list))
        println("Ranks: " + AssortedMethods.arrayToString(tracker))
    }
}