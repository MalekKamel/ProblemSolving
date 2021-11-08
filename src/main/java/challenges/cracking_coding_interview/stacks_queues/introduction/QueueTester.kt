package challenges.cracking_coding_interview.stacks_queues.introduction

import challenges.util.AssortedMethods
import java.util.*


object QueueTester {
    @JvmStatic
    fun main(args: Array<String>) {
        val array: IntArray = AssortedMethods.randomArray(100, -100, 100)
        val queue1 = MyQueue<Int>()
        val queue2: Queue<Int> = LinkedList()
        for (a in array) {
            if (a < 0) {
                val top1: Int = try {
                    queue1.remove()
                } catch (ex: NoSuchElementException) {
                    Int.MIN_VALUE
                }
                val top2: Int = try {
                    queue2.remove()
                } catch (ex: NoSuchElementException) {
                    Int.MIN_VALUE
                }
                if (top1 != top2) {
                    println("ERROR: mismatching tails")
                } else {
                    println("SUCCESS: matching tails: $top1")
                }
            } else {
                queue1.add(a)
                queue2.add(a)
            }
        }
        while (!queue1.isEmpty || !queue2.isEmpty()) {
            val top1: Int = try {
                queue1.remove()
            } catch (ex: NoSuchElementException) {
                Int.MIN_VALUE
            }
            val top2: Int = try {
                queue2.remove()
            } catch (ex: NoSuchElementException) {
                Int.MIN_VALUE
            }
            if (top1 != top2) {
                println("ERROR: mismatching tails")
            } else {
                println("SUCCESS: matching tails: $top1")
            }
        }
    }
}
