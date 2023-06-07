package challenges.educative_grokking_coding_interview.merge_intervals._3

import challenges.educative_grokking_coding_interview.merge_intervals.Interval
import java.util.*


internal object Intersection {
    private fun display(l1: List<Interval>): String {
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

    private fun intervalsIntersection(
        intervalLista: List<Interval>,
        intervalListb: List<Interval>
    ): List<Interval> {
        val intersections: MutableList<Interval> =
            ArrayList<Interval>() // to store all intersecting intervals
        // index "i" to iterate over the length of list a and index "j"
        // to iterate over the length of list b
        var i = 0
        var j = 0
        // while loop will break whenever either of the lists ends
        while (i < intervalLista.size && j < intervalListb.size) {
            // Let's check if intervalLista[i] intervalListb[j]
            // 1. start - the potential startpoint of the intersection
            // 2. end - the potential endpoint of the intersection
            val start: Int = Math.max(intervalLista[i].start, intervalListb[j].start)
            val end: Int = Math.min(intervalLista[i].end, intervalListb[j].end)
            if (start <= end) // if this is an actual intersection
                intersections.add(Interval(start, end)) // add it to the list

            // Move forward in the list whose interval ends earlier
            if (intervalLista[i].end < intervalListb[j].end) i += 1 else j += 1
        }
        return intersections
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val inputIntervalLista: List<List<Interval>> = listOf(
            listOf(Interval(1, 2)),
            listOf(Interval(1, 4), Interval(5, 6), Interval(9, 15)),
            listOf(Interval(3, 6), Interval(8, 16), Interval(17, 25)),
            listOf(
                Interval(4, 7), Interval(9, 16), Interval(17, 28), Interval(39, 50),
                Interval(55, 66), Interval(70, 89)
            ),
            listOf(Interval(1, 3), Interval(5, 6), Interval(7, 8), Interval(12, 15))
        )
        val inputIntervalListb: List<List<Interval>> = listOf(
            listOf(Interval(1, 2)),
            listOf(Interval(2, 4), Interval(5, 7), Interval(9, 15)),
            listOf(Interval(2, 3), Interval(10, 15), Interval(18, 23)),
            listOf(
                Interval(3, 6), Interval(7, 8), Interval(9, 10), Interval(14, 19),
                Interval(23, 33), Interval(35, 40), Interval(45, 59), Interval(60, 64),
                Interval(68, 76)
            ),
            listOf(Interval(2, 4), Interval(7, 10))
        )
        for (i in inputIntervalLista.indices) {
            println( "${i + 1}.\t Interval List A: " + display(inputIntervalLista[i]))
            println("\t Interval List B: " + display(inputIntervalListb[i]))
            println(
                "\t Intersecting intervals in 'A' and 'B' are: " + display(
                    intervalsIntersection(
                        inputIntervalLista[i], inputIntervalListb[i]
                    )
                )
            )
            println(String(CharArray(100)).replace('\u0000', '-'))
        }
    }
}