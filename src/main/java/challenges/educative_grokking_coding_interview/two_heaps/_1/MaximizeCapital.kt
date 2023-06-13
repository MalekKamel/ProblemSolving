package challenges.educative_grokking_coding_interview.two_heaps._1

import challenges.util.PrintHyphens
import java.util.*

/**
A busy investor with an initial capital, c, needs an automated investment program. They can select k distinct projects
from a list of n projects with corresponding capitals requirements and expected profits. For a given project
i, its capital requirement is capitals[i]
, and the profit it yields is profits[i].
The goal is to maximize their cumulative capital by selecting a maximum of k distinct projects to invest in,
subject to the constraint that the investor’s current capital must be greater than or equal to the capital
requirement of all selected projects.
When a selected project from the identified ones is finished, the pure profit from the project, along with
the starting capital of that project is returned to the investor. This amount will be added to the total capital
held by the investor. Now, the investor can invest in more projects with the new total capital. It is important
to note that each project can only be invested once.
As a basic risk-mitigation measure, the investor wants to limit the number of projects they invest in.
For example, if k is 2, the program should identify the two projects that maximize
the investor’s profits while ensuring that the investor’s capital is sufficient to invest in the projects.
Overall, the program should help the investor to make informed investment decisions by picking a list
of a maximum of k distinct projects to maximize the final profit while mitigating the risk.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/JEj181o3RJK
 */
object MaximizeCapital {
    private fun maximumCapital(c: Int, k: Int, capitals: IntArray, profits: IntArray?): Int {
        val capitalMinHeap = PriorityQueue<Int>()
        var i = 0
        // Insert all capitals values to a min-heap
        while (i < capitals.size) {
            capitalMinHeap.add(capitals[i])
            i++
        }
        printCapitalsMinHeap(capitalMinHeap)
        return 0
    }

    private fun printCapitalsMinHeap(capitals: PriorityQueue<Int>) {
        val l: MutableList<Int> = ArrayList()
        while (!capitals.isEmpty()) {
            l.add(capitals.peek())
            capitals.poll()
        }
        println("\t" + l.toString())
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val c = intArrayOf(1, 2, 1, 7, 2)
        val k = intArrayOf(2, 3, 3, 2, 4)
        val capitals = arrayOf(
            intArrayOf(1, 2, 2, 3),
            intArrayOf(1, 3, 4, 5, 6),
            intArrayOf(1, 2, 3, 4),
            intArrayOf(6, 7, 8, 10),
            intArrayOf(2, 3, 5, 6, 8, 12)
        )
        val profits = arrayOf(
            intArrayOf(2, 4, 6, 8),
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(1, 3, 5, 7),
            intArrayOf(4, 8, 12, 14),
            intArrayOf(1, 2, 5, 6, 8, 9)
        )
        for (i in k.indices) {
            println((i + 1).toString() + ".\tGiven capitals: " + capitals[i].contentToString())
            println("  \tAdding capital values...")
            maximumCapital(c[i], k[i], capitals[i], profits[i])
            println(PrintHyphens.repeat("-", 100))
        }
    }
}