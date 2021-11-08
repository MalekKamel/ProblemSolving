package challenges.cracking_coding_interview.stacks_queues.introduction

import challenges.util.AssortedMethods
import java.util.*


object StackTester {
    @JvmStatic
    fun main(args: Array<String>) {
        val array: IntArray = AssortedMethods.randomArray(100, -100, 100)
        val stack1 = MyStack<Int>()
        val stack2 = Stack<Int>()
        for (a in array) {
            if (a < 0) {
                val top1: Int = try {
                    stack1.pop()
                } catch (ex: EmptyStackException) {
                    Int.MIN_VALUE
                }
                val top2: Int = try {
                    stack2.pop()
                } catch (ex: EmptyStackException) {
                    Int.MIN_VALUE
                }
                if (top1 != top2) {
                    println("ERROR: mismatching tops")
                } else {
                    println("SUCCESS: matching tops: $top1")
                }
            } else {
                stack1.push(a)
                stack2.push(a)
            }
        }
        while (!stack1.isEmpty || !stack2.isEmpty()) {
            val top1: Int = try {
                stack1.pop()
            } catch (ex: EmptyStackException) {
                Int.MIN_VALUE
            }
            val top2: Int = try {
                stack2.pop()
            } catch (ex: EmptyStackException) {
                Int.MIN_VALUE
            }
            if (top1 != top2) {
                println("ERROR: mismatching tops")
            } else {
                println("SUCCESS: matching tops: $top1")
            }
        }
    }
}