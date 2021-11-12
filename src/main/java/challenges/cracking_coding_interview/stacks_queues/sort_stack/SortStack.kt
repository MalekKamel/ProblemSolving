package challenges.cracking_coding_interview.stacks_queues.sort_stack

import challenges.util.AssortedMethods.randomIntInRange
import java.util.*


/**
 * Write a program to sort a stack such that the smallest items are on the top.
 * You can use an additional temporary stack, but you may not copy the elements
 * into any other data structure (such as an array).
 * The stack supports the following operations: push, pop, peek, and isEmpty.
 */
object SortStack {
    fun mergeSort(inStack: Stack<Int>): Stack<Int> {
        if (inStack.size <= 1) {
            return inStack
        }
        var left = Stack<Int>()
        var right = Stack<Int>()
        var count = 0
        while (inStack.size != 0) {
            count++
            if (count % 2 == 0) {
                left.push(inStack.pop())
            } else {
                right.push(inStack.pop())
            }
        }
        left = mergeSort(left)
        right = mergeSort(right)
        while (left.size > 0 || right.size > 0) {
            if (left.size == 0) {
                inStack.push(right.pop())
            } else if (right.size == 0) {
                inStack.push(left.pop())
            } else if (right.peek() <= left.peek()) {
                inStack.push(left.pop())
            } else {
                inStack.push(right.pop())
            }
        }
        val reverseStack = Stack<Int>()
        while (inStack.size > 0) {
            reverseStack.push(inStack.pop())
        }
        return reverseStack
    }

    fun sort(s: Stack<Int>) {
        val r: Stack<Int> = Stack<Int>()
        while (!s.isEmpty()) {
            /* Insert each element in s in sorted order into r. */
            val tmp: Int = s.pop()
            while (!r.isEmpty() && r.peek() > tmp) {
                s.push(r.pop())
            }
            r.push(tmp)
        }

        /* Copy the elements back. */
        while (!r.isEmpty()) {
            s.push(r.pop())
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val s: Stack<Int> = Stack<Int>()
        for (i in 0..9) {
            val r = randomIntInRange(0, 1000)
            s.push(r)
        }
        sort(s)
        while (!s.isEmpty()) {
            println(s.pop())
        }
    }
}