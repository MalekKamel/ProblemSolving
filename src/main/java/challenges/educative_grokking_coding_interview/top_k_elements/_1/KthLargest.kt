package challenges.educative_grokking_coding_interview.top_k_elements._1

import java.util.*

/**
Given an infinite stream of integers (sorted or unsorted), nums, design a class to find the kth
largest element in a stream.

Note: It is the kth largest element in the sorted order, not the kth distinct element.
The class should have the following functions, inputs, and return values:
Init(): It takes an array of integers and an integer k and initializes the class object.
Add(value): It takes one integer value, appends it to the stream, and calls the Return kth largest() function.
Return kth largest(): It returns an integer value that represents the kth largest element in the stream.

https://www.educative.io/courses/grokking-coding-interview-patterns-java/mE90WX1DE7E
 */
internal class KthLargest(var k: Int, nums: IntArray) {
    var topKHeap: PriorityQueue<Int> = PriorityQueue()
    private fun printHeapWithMarkers(arr: PriorityQueue<Int>, pvalue: Int): String {
        var out = "["
        val value: Iterator<*> = arr.iterator()
        for (i in arr.indices) {
            if (pvalue == i) {
                out += '«'
                out += value.next().toString() + '»' + ", "
            } else if (i != arr.size - 1) out += value.next().toString() + ", " else out += value.next().toString()
        }
        out += "]"
        return out
    }

    // adds element in the topKHeap
    fun add(`val`: Int) {
        topKHeap.offer(`val`)
        if (topKHeap.size > k) {
            topKHeap.poll()
        }
    }

    // returns kth largest element from topKHeap
    fun returnKthLargest(): Int {
        return topKHeap.peek()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = intArrayOf(3, 6, 9, 10)
            var temp = intArrayOf(3, 6, 9, 10)
            println("Initial stream: " + Arrays.toString(nums))
            println("k: " + 3)
            val kLargest = KthLargest(3, nums)
            val `val` = intArrayOf(4, 7, 10, 8, 15)
            println()
            for (i in `val`.indices) {
                println("\tAdding a new number " + `val`[i] + " to the stream")
                temp = temp.copyOf(temp.size + 1)
                temp[temp.size - 1] = `val`[i]
                println("\t\tNumber stream: " + Arrays.toString(temp))
                kLargest.add(`val`[i])
                println("\tKth largest element in the stream: " + kLargest.returnKthLargest())
                println(String(CharArray(100)).replace('\u0000', '-'))
            }
        }
    }

    // constructor to initialize topKHeap and add values in it
    init {
        for (num in nums) {
            topKHeap.offer(num)
        }
        while (topKHeap.size > k) {
            topKHeap.poll()
        }
    }
}