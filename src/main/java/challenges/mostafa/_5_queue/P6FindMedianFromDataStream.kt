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

        private val minHeap: PriorityQueue<Int> = PriorityQueue() // Stores the larger half of the numbers
        private val maxHeap: PriorityQueue<Int> = PriorityQueue(compareByDescending { it }) // Stores the smaller half of the numbers

        /**
         * 1. Problem Explanation
         * The problem requires us to implement a `MedianFinder` class that supports two main operations:
         * - `addNum(num)`: Adds a new integer to an internal data structure.
         * - `findMedian()`: Returns the median of all the numbers added so far. If the count of numbers
         * is even, the median is the average of the two middle numbers; otherwise, it's the single middle number.
         */

        /**
         * 2. Pattern Identification and Rationale
         * The most suitable pattern for efficiently finding the median of a continuously growing data
         * stream is using two heaps: a min-heap and a max-heap.
         *
         * Rationale:
         * - The max-heap will store the smaller half of the numbers encountered so far, with the largest
         * element of this half at the root.
         * - The min-heap will store the larger half of the numbers, with the smallest element of this
         * half at the root.
         * - By maintaining the balance between the sizes of these two heaps (the sizes should be equal
         * or the max-heap can have one more element), we can easily determine the median.
         * - The top of the max-heap will be the largest element of the smaller half, and the top of
         * the min-heap will be the smallest element of the larger half.
         * - Adding a new number involves comparing it with the tops of the heaps and placing it in
         * the appropriate heap, followed by a balancing step to maintain the size property.
         * - Finding the median then becomes a constant-time operation (O(1)) by looking at the top
         * elements of the heaps.
         *
         * Advantages:
         * - Efficient insertion of new numbers (O(log n) time complexity due to heap operations).
         * - Efficient retrieval of the median (O(1) time complexity).
         * - Space complexity is O(n) to store all the numbers.
         */

        /**
         * 3. Solution Breakdown
         *
         * Step 1: `addNum(num: Int)`
         * - If the max-heap is empty or the new number is less than or equal to the top of the max-heap,
         * add the number to the max-heap.
         * - Otherwise, add the number to the min-heap.
         * - Call the `balance()` function to ensure the heaps maintain the desired size property.
         *
         * Step 2: `balance()`
         * - If the size difference between the max-heap and the min-heap is greater than 1 (i.e., `maxHeap.size - minHeap.size > 1`),
         * move the root of the max-heap to the min-heap.
         * - If the size difference between the min-heap and the max-heap is greater than 1 (i.e., `minHeap.size - maxHeap.size > 1`),
         * move the root of the min-heap to the max-heap.
         * - This ensures that the sizes of the two heaps are either equal or the max-heap has one more element.
         *
         * Step 3: `findMedian(): Double`
         * - If the total number of elements added so far is even (i.e., `maxHeap.size == minHeap.size`),
         * the median is the average of the top elements of the max-heap and the min-heap.
         * - If the total number of elements added so far is odd (which implies `maxHeap.size > minHeap.size`
         * after balancing), the median is the top element of the max-heap.
         */

        fun addNum(num: Int) {
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num)
            } else {
                minHeap.offer(num)
            }
            balance()
        }

        private fun balance() {
            if (maxHeap.size - minHeap.size > 1) {
                minHeap.offer(maxHeap.poll())
            } else if (minHeap.size - maxHeap.size > 1) {
                maxHeap.offer(minHeap.poll())
            }
        }

        fun findMedian(): Double {
            return if (maxHeap.size == minHeap.size) {
                (maxHeap.peek() + minHeap.peek()).toDouble() / 2.0
            } else {
                maxHeap.peek().toDouble()
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