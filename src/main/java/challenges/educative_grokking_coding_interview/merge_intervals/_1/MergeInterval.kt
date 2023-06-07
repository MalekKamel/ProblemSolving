package challenges.educative_grokking_coding_interview.merge_intervals._1

import challenges.educative_grokking_coding_interview.merge_intervals.Interval
import java.util.*


object MergeInterval {
    private fun mergeIntervals(intervals: List<Interval>): List<Interval> {
        // If the list is empty
        val result: MutableList<Interval> = ArrayList<Interval>()
        if (intervals.isEmpty()) {
            println("The list is empty")
            return result
        }
        // Adding pair in the result list
        result.add(Interval(intervals[0].start, intervals[0].end))
        for (i in 1 until intervals.size) {
            // Getting the recent added interval in the result list
            val lastAddedInterval: Interval = result[result.size - 1]
            // Getting and initializing input pair
            val currStart: Int = intervals[i].start
            val currEnd: Int = intervals[i].end
            println("\n\t\t\tCurrent input interval: [$currStart, $currEnd]\n")
            // Getting the ending timestamp of the previous interval
            lastAddedInterval.end
            println("\t\t\t" + "|    " + "curStart" + "   |    " + "curEnd" + "   |")
            println("\t\t\t" + " ----------------------------- ")
            println("\t\t\t|    $currStart          |    $currEnd        |")
        }
        return result
    }

    fun intervalListtoStr(l1: List<Interval>): String {
        var resultStr = "["
        for (i in 0 until l1.size - 1) {
            resultStr += "[" + l1[i].start.toString() + ", " + l1[i].end
                .toString() + "], "
        }
        resultStr += "[" + l1[l1.size - 1].start.toString() + ", " + l1[l1.size - 1].end
            .toString() + "]"
        resultStr += "]"
        return resultStr
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val l1: List<Interval> =
            listOf(Interval(1, 5), Interval(3, 7), Interval(4, 6))
        val l2: List<Interval> = listOf(
            Interval(1, 5),
            Interval(4, 6),
            Interval(6, 8),
            Interval(11, 15)
        )
        val l3: List<Interval> = listOf(
            Interval(3, 7),
            Interval(6, 8),
            Interval(10, 12),
            Interval(11, 15)
        )
        val l4: List<Interval> = listOf(Interval(1, 5))
        val l5: List<Interval> =
            listOf(Interval(1, 9), Interval(3, 8), Interval(4, 4))
        val l6: List<Interval> =
            listOf(Interval(1, 2), Interval(3, 4), Interval(8, 8))
        val l7: List<Interval> = listOf(Interval(1, 5), Interval(1, 3))
        val l8: List<Interval> = listOf(Interval(1, 5), Interval(6, 9))
        val l9: List<Interval> =
            listOf(Interval(0, 0), Interval(1, 18), Interval(1, 3))
        val allIntervals: List<List<Interval>> =
            listOf(l1, l2, l3, l4, l5, l6, l7, l8, l9)
        for (i in allIntervals.indices) {
            println("${i + 1 }. Intervals to merge: " + intervalListtoStr(allIntervals[i]))
            mergeIntervals(
                allIntervals[i]
            )
            println(String(CharArray(100)).replace('\u0000', '-'))
        }
    }
}