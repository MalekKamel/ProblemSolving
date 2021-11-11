package challenges.cracking_coding_interview.stacks_queues.queue_via_stacks

import challenges.util.AssortedMethods.randomIntInRange
import java.util.*


/**
 * How would you design a stack which, in addition to push and pop,
 * has a function min which returns the minimum element? Push,
 * pop and min should all operate in 0(1) time.
 */

object Question {
    @JvmStatic
    fun main(args: Array<String>) {
        val myQueue = MyQueue<Int>()

        // Let's test our code against a "real" queue

        // Let's test our code against a "real" queue
        val test_queue: Queue<Int> = LinkedList<Int>()

        for (i in 0..99) {
            val choice = randomIntInRange(0, 10)
            if (choice <= 5) { // enqueue
                val element = randomIntInRange(1, 10)
                test_queue.add(element)
                myQueue.add(element)
                println("Enqueued $element")
            } else if (test_queue.size > 0) {
                val top1: Int = test_queue.remove()
                val top2 = myQueue.remove()
                if (top1 != top2) { // Check for error
                    println("******* FAILURE - DIFFERENT TOPS: $top1, $top2")
                }
                println("Dequeued $top1")
            }
            if (test_queue.size == myQueue.size()) {
                if (test_queue.size > 0 && test_queue.peek() != myQueue.peek()) {
                    System.out.println(
                        "******* FAILURE - DIFFERENT TOPS: " + test_queue.peek().toString() + ", " + myQueue.peek()
                            .toString() + " ******"
                    )
                }
            } else {
                println("******* FAILURE - DIFFERENT SIZES ******")
            }
        }
    }
}