package challenges.educative_grokking_coding_interview.two_heaps._4

import challenges.util.PrintHyphens
import java.util.*

/**
Given a set of n number of tasks, implement a task scheduler method, tasks(), to run in O(n logn)
time that finds the minimum number of machines required to complete these n tasks.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/YQoGA7Wz5zM
 */
object ScheduleTask {
    private fun tasks(tasksList: ArrayList<ArrayList<Int?>>): Int {
        // to count the total number of machines for "optimalMachines" tasks
        var optimalMachines = 0
        val machinesAvailable = PriorityQueue(Comparator.comparingInt { a: IntArray ->
            a[0]
        })
        val tasksQueue = PriorityQueue(Comparator.comparingInt { a: IntArray -> a[0] })
        for (task in tasksList) {
            tasksQueue.offer(
                intArrayOf(
                    task[0]!!, task[1]!!
                )
            )
        }
        while (!tasksQueue.isEmpty()) { // looping through the tasks list
            // remove from "tasksQueue" the task i with earliest start time
            val task = tasksQueue.poll()
            var machineInUse: IntArray
            if (!machinesAvailable.isEmpty() && task[0] >= machinesAvailable.peek()[0]) {
                // top element is deleted from "machinesAvailable"
                machineInUse = machinesAvailable.poll()
                // schedule task on the current machine
                machineInUse = intArrayOf(
                    task[1], machineInUse[1]
                )
            } else {
                // if there's a conflicting task, increment the
                // counter for machines and store this task's
                // end time on heap "machinesAvailable"
                optimalMachines += 1
                machineInUse = intArrayOf(
                    task[1], optimalMachines
                )
            }
            machinesAvailable.offer(machineInUse)
        }
        // return the total number of machines
        return optimalMachines
    }

    @JvmStatic
    fun main(args: Array<String>) {
        //  Input: A set "tasks_list" of "n" tasks, such that
        // each taskList has a start time and a finish time
        val inputs = listOf(
            listOf(
                listOf(1, 1),
                listOf(5, 5),
                listOf(8, 8),
                listOf(4, 4),
                listOf(6, 6),
                listOf(10, 10),
                listOf(7, 7)
            ),
            listOf(
                listOf(1, 7),
                listOf(1, 7),
                listOf(1, 7),
                listOf(1, 7),
                listOf(1, 7),
                listOf(1, 7)
            ),
            listOf(
                listOf(1, 7),
                listOf(8, 13),
                listOf(5, 6),
                listOf(10, 14),
                listOf(6, 7)
            ),
            listOf(
                listOf(1, 3),
                listOf(3, 5),
                listOf(5, 9),
                listOf(9, 12),
                listOf(12, 13),
                listOf(13, 16),
                listOf(16, 17)
            ),
            listOf(
                listOf(12, 13),
                listOf(13, 15),
                listOf(17, 20),
                listOf(13, 14),
                listOf(19, 21),
                listOf(18, 20)
            )
        )
        // loop to execute till the length of tasks
        val inputTaskList = ArrayList<ArrayList<ArrayList<Int?>>>()
        for (j in inputs.indices) {
            inputTaskList.add(ArrayList())
            for (k in inputs[j].indices) {
                inputTaskList[j].add(ArrayList())
                for (g in inputs[j][k].indices) {
                    inputTaskList[j][k].add(inputs[j][k][g])
                }
            }
        }
        for (i in inputTaskList.indices) {
            println("${i + 1}.\tTask = " + inputTaskList[i].toString())
            // Output: A non-conflicting schedule of tasks in
            // "tasks_list" using the minimum number of machines
            println("\tOptimal number of machines = " + tasks(inputTaskList[i]))
            println(PrintHyphens.repeat("-", 100))
        }
    }
}