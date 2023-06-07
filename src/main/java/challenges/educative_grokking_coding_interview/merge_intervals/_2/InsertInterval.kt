package challenges.educative_grokking_coding_interview.merge_intervals._2

import challenges.educative_grokking_coding_interview.merge_intervals.Interval
import challenges.util.PrintHyphens
import java.util.*


internal object InsertInterval {
    private fun insertInterval(existingIntervals: List<Interval>, newInterval: Interval) {
        // Read the starting and ending time of the new interval, into separate variables
        val newStart: Int = newInterval.start
        val newEnd: Int = newInterval.end
        println("The new interval starts at $newStart and ends at $newEnd.")
        // Initialize variables to help in iterating over the existing intervals list
        var i = 0
        val n = existingIntervals.size
        println("There are $n intervals present in the list already.")
        // Initialize an empty list to store the output
        val output: MutableList<Interval> = ArrayList<Interval>()
        // Append all intervals that start before the new interval to the output list
        println("Let's start adding these intervals into our output list one by one, until we come across an overlapping interval.")
        System.out.println(PrintHyphens.repeat("-", 100))
        while (i < n && existingIntervals[i].start < newStart) {
            output.add(existingIntervals[i])
            println("We can add " + (i + 1) + " intervals in our new list without merging any intervals yet:")
            println(display(output))
            i += 1
            System.out.println(PrintHyphens.repeat("-", 100))
        }
        println()
    }

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

    @JvmStatic
    fun main(args: Array<String>) {
        val newInterval = Interval(5, 7)
        val existingIntervals: List<Interval> =
            listOf(Interval(1, 2), Interval(3, 5), Interval(6, 8))
        insertInterval(existingIntervals, newInterval)
    }
}