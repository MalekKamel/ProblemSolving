package challenges.mostafa._5_queue

import java.util.PriorityQueue

object ExamplesForPriorityQueue {
    @JvmStatic
    fun main(args: Array<String>) {
        // Basic Usage:
        val priorityQueue = PriorityQueue<Int>()
        priorityQueue.offer(3)
        priorityQueue.offer(1)
        priorityQueue.offer(4)
        priorityQueue.offer(2)

        println("Min Heap")
        println(priorityQueue.poll()) // Output: 1
        println(priorityQueue.poll()) // Output: 2
        println(priorityQueue.poll()) // Output: 3
        println(priorityQueue.poll()) // Output: 4

        // Custom Comparator:
        val queue = PriorityQueue<Int>(compareBy { -it })
        val queue2 = PriorityQueue<Int>(Comparator.reverseOrder())
        println("Max Heap")
        queue.offer(3)
        queue.offer(1)
        queue.offer(4)
        queue.offer(2)

        println(queue.poll()) // Output: 4
        println(queue.poll()) // Output: 3
        println(queue.poll()) // Output: 2
        println(queue.poll()) // Output: 1

        println("Max Heap")
        queue2.offer(3)
        queue2.offer(1)
        queue2.offer(4)
        queue2.offer(2)

        println(queue2.poll()) // Output: 4
        println(queue2.poll()) // Output: 3
        println(queue2.poll()) // Output: 2
        println(queue2.poll()) // Output: 1

        // Custom Comparator:
        val priorityQueue2 = PriorityQueue<String> { a, b -> b.length - a.length }
        priorityQueue2.offer("apple")
        priorityQueue2.offer("banana")
        priorityQueue2.offer("cher")

        println(priorityQueue2.poll()) // Output: "banana"
        println(priorityQueue2.poll()) // Output: "cherry"
        println(priorityQueue2.poll()) // Output: "apple"

        // Initializing with a Collection:
        val numbers = listOf(3, 1, 4, 2)
        val priorityQueue3 = PriorityQueue(numbers)

        println(priorityQueue3.poll()) // Output: 1
        println(priorityQueue3.poll()) // Output: 2
        println(priorityQueue3.poll()) // Output: 3
        println(priorityQueue3.poll()) // Output: 4

        // Using a PriorityQueue for a Simple Task Scheduler:
        data class Task(val name: String, val priority: Int)

        val taskQueue = PriorityQueue<Task> { a, b -> a.priority - b.priority }
        taskQueue.offer(Task("Buy groceries", 2))
        taskQueue.offer(Task("Attend meeting", 1))
        taskQueue.offer(Task("Clean the house", 3))

        while (taskQueue.isNotEmpty()) {
            val task = taskQueue.poll()
            println("Executing task: ${task.name}")
        }
    }
}