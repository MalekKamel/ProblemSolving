package challenges.mostafa._5_queue

import java.util.PriorityQueue

/**
The median is the middle value in an ordered integer list. If the size of the list is even,
there is no middle value, and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual
answer will be accepted.

Example 1:

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0

Constraints:

-10^5 <= num <= 10^5
There will be at least one element in the data structure before calling findMedian.
At most 5 * 104 calls will be made to addNum and findMedian.


Follow up:

If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?

https://leetcode.com/problems/find-median-from-data-stream/description/
 */

internal object P6FindMedianFromDataStream {

    /**
    Time Complexity:

    addNum(num): O(log n)
    findMedian(): O(1)
    Space Complexity: O(n)

    Follow up:

    1- If all integer numbers from the stream are in the range [0, 100]:
    In this case, we can use a simple array of size 101 (0 to 100) to store the frequency of
    each number. This will allow us to find the median in O(1) time, as we can simply iterate
    through the frequency array and find the median.

    2- If 99% of all integer numbers from the stream are in the range [0, 100]:
    In this case, we can still use the above solution with the two heaps, as it has a good
    average-case performance. However, we can also use a combination of the above two approaches.
    We can maintain a small heap for the numbers outside the [0, 100] range, and a frequency
    array for the numbers in the [0, 100] range. This will allow us to efficiently handle
    the majority of the numbers while still being able to handle the outliers.
     */

    class MedianFinder {
        private val smallHeap = PriorityQueue<Int>(reverseOrder())
        private val largeHeap = PriorityQueue<Int>()

        fun addNum(num: Int) {
            if (smallHeap.isEmpty() || num <= smallHeap.peek()) {
                smallHeap.offer(num)
            } else largeHeap.offer(num)
            balance()
        }

        private fun balance() {
            if (smallHeap.size > largeHeap.size + 1)
                largeHeap.offer(smallHeap.poll())
            else if (largeHeap.size > smallHeap.size)
                smallHeap.offer(largeHeap.poll())
        }

        fun findMedian(): Double {
            return if (smallHeap.size == largeHeap.size) {
                (smallHeap.peek().toDouble() + largeHeap.peek().toDouble()) / 2
            } else {
                smallHeap.peek().toDouble()
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val medianFinder = MedianFinder()

        medianFinder.addNum(1) // arr = [1]
        medianFinder.addNum(2) // arr = [1, 2]
        println(medianFinder.findMedian()) // Output: 1.5

        medianFinder.addNum(3) // arr[1, 2, 3]
        println(medianFinder.findMedian()) // Output: 2.0

        medianFinder.addNum(4) // arr[1, 2, 3, 4]
        medianFinder.addNum(5) // arr[1, 2, 3, 4, 5]

        println(medianFinder.findMedian()) // Output: 3.0
    }
}