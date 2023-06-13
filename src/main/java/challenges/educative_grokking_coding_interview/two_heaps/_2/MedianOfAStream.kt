package challenges.educative_grokking_coding_interview.two_heaps._2

import challenges.util.PrintHyphens
import java.util.*

/**
Our task is to implement a data structure that will store a dynamically growing list of integers and provide
access to their median in O(1).

https://www.educative.io/courses/grokking-coding-interview-patterns-java/7Xx1Y64gADA
 */
class MedianOfAStream {
    var maxHeapForSmallNum //containing first half of numbers
            : PriorityQueue<Int> = PriorityQueue { a: Int, b: Int -> b - a }
    var minHeapForLargeNum //containing second half of numbers
            : PriorityQueue<Int> = PriorityQueue { a: Int, b: Int -> a - b }

    fun insertNum(num: Int) {
        if (maxHeapForSmallNum.isEmpty() || maxHeapForSmallNum.peek() >= num) maxHeapForSmallNum.add(num)
        else minHeapForLargeNum.add(
            num
        )

        // either both the heaps will have equal number of elements or max-heap will have one
        // more element than the min-heap
        if (maxHeapForSmallNum.size > minHeapForLargeNum.size + 1) minHeapForLargeNum.add(maxHeapForSmallNum.poll())
        else if (maxHeapForSmallNum.size < minHeapForLargeNum.size) maxHeapForSmallNum.add(
            minHeapForLargeNum.poll()
        )
    }

    fun findMedian(): Double {
        return if (maxHeapForSmallNum.size == minHeapForLargeNum.size) {
            // we have even number of elements, take the average of middle two elements
            maxHeapForSmallNum.peek() / 2.0 + minHeapForLargeNum.peek() / 2.0
        } else maxHeapForSmallNum.peek().toDouble()
        // because max-heap will have one more element than the min-heap
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            // Driver code
            val nums = intArrayOf(35, 22, 30, 25, 1)
            var medianOfAges: MedianOfAStream? = null
            for (i in nums.indices) {
                print(i + 1)
                print(".\tData stream: [")
                medianOfAges = MedianOfAStream()
                for (j in 0..i) {
                    print(nums[j])
                    if (j != i) print(", ")
                    medianOfAges.insertNum(nums[j])
                }
                println("]")
                println("\t\tThe median for the given numbers is: " + medianOfAges.findMedian())
                println(PrintHyphens.repeat("-", 100))
            }
        }
    }

}