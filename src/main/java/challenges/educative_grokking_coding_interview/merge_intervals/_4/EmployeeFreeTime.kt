package challenges.educative_grokking_coding_interview.merge_intervals._4

import challenges.educative_grokking_coding_interview.merge_intervals.Interval
import java.util.*


internal object EmployeeFreeTime {
    private fun employeeFreeTime(schedule: ArrayList<ArrayList<Interval>>): List<Interval> {
        val heap = PriorityQueue { a: IntArray, b: IntArray ->
            a[0] - b[0]
        }

        // Iterate for all employees' schedules
        // and add start of each schedule's first interval along with
        // its index value and a value 0.
        for (i in schedule.indices) {
            val employeeSchedule: List<Interval> = schedule[i]
            val interval: Interval = employeeSchedule[0]
            heap.offer(intArrayOf(interval.start, i, 0))
        }

        // Take an empty list to store results.
        val result: MutableList<Interval> = ArrayList<Interval>()

        // Set 'previous' to the start time of the first interval in heap.
        var previous: Int = schedule[heap.peek()[1]][heap.peek()[2]].start

        // Iterate until the heap is empty
        while (!heap.isEmpty()) {
            // Poll an element from the heap and get values of i and j
            val tuple = heap.poll()
            val i = tuple[1]
            val j = tuple[2]

            // Select an interval
            val interval: Interval = schedule[i][j]

            // If the selected interval's start value is greater than the previous value,
            // it means that this interval is free. So, add this interval
            // (previous, interval's end value) into the result.
            if (interval.start > previous) {
                result.add(Interval(previous, interval.start))
            }

            // Update the previous as the maximum of previous and interval's end value.
            previous = Math.max(previous, interval.end)

            // If there is another interval in the current employee's schedule,
            // push that into the heap.
            if (j + 1 < schedule[i].size) {
                val nextInterval: Interval = schedule[i][j + 1]
                heap.offer(intArrayOf(nextInterval.start, i, j + 1))
            }
        }

        // When the heap is empty, return the result.
        return result
    }

    // Function for displaying interval list
    fun display(l1: List<Interval>): String {
        if (l1.isEmpty()) {
            return "[]"
        }
        var resultStr = "["
        for (i in 0 until l1.size - 1) {
            resultStr += "[" + l1[i].start.toString() + ", "
            resultStr +=  "${l1[i].end}], "
        }
        resultStr += "[" + l1[l1.size - 1].start.toString() + ", "
        resultStr +=  "${l1[l1.size - 1].end}]"
        resultStr += "]"
        return resultStr
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val inputs1: List<List<List<Interval>>> = listOf(
            listOf(
                listOf(Interval(1, 2), Interval(5, 6)),
                listOf(Interval(1, 3)),
                listOf(Interval(4, 10))
            ),
            listOf(
                listOf(Interval(1, 3), Interval(6, 7)),
                listOf(Interval(2, 4)),
                listOf(Interval(2, 5), Interval(9, 12))
            ),
            listOf(
                listOf(Interval(2, 3), Interval(7, 9)),
                listOf(Interval(1, 4), Interval(6, 7))
            ),
            listOf(
                listOf(Interval(3, 5), Interval(8, 10)),
                listOf(Interval(4, 6), Interval(9, 12)),
                listOf(Interval(5, 6), Interval(8, 10))
            ),
            listOf(
                listOf(Interval(1, 3), Interval(6, 9), Interval(10, 11)),
                listOf(Interval(3, 4), Interval(7, 12)),
                listOf(Interval(1, 3), Interval(7, 10)),
                listOf(Interval(1, 4)),
                listOf(Interval(7, 10), Interval(11, 12))
            ),
            listOf(
                listOf(Interval(1, 2), Interval(3, 4), Interval(5, 6), Interval(7, 8)),
                listOf(Interval(2, 3), Interval(4, 5), Interval(6, 8))
            ),
            Arrays.asList(
                listOf(
                    Interval(1, 2),
                    Interval(3, 4),
                    Interval(5, 6),
                    Interval(7, 8),
                    Interval(9, 10),
                    Interval(11, 12)
                ),
                listOf(
                    Interval(1, 2),
                    Interval(3, 4),
                    Interval(5, 6),
                    Interval(7, 8),
                    Interval(9, 10),
                    Interval(11, 12)
                ),
                listOf(
                    Interval(1, 2),
                    Interval(3, 4),
                    Interval(5, 6),
                    Interval(7, 8),
                    Interval(9, 10),
                    Interval(11, 12)
                ),
                listOf(
                    Interval(1, 2),
                    Interval(3, 4),
                    Interval(5, 6),
                    Interval(7, 8),
                    Interval(9, 10),
                    Interval(11, 12)
                )
            )
        )
        var i = 1
        val inputs: ArrayList<ArrayList<ArrayList<Interval>>> =
            ArrayList<ArrayList<ArrayList<Interval>>>()
        for (j in inputs1.indices) {
            inputs.add(ArrayList<ArrayList<Interval>>())
            for (k in inputs1[j].indices) {
                inputs[j].add(ArrayList<Interval>())
                for (g in inputs1[j][k].indices) {
                    inputs[j][k].add(inputs1[j][k][g])
                }
            }
        }
        for (j in inputs.indices) {
            println("$i.\tEmployee Schedules:")
            for (s in inputs[j].indices) {
                println("\t\t" + display(inputs[j][s]))
            }
            println(
                "\tEmployees' free time " + display(
                    employeeFreeTime(
                        inputs[j]
                    )
                )
            )
            println(String(CharArray(100)).replace('\u0000', '-'))
            i += 1
        }
    }
}