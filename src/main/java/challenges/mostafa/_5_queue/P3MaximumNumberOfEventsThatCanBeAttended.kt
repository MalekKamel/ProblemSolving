package challenges.mostafa._5_queue

import java.util.PriorityQueue

/**
You are given an array of events where events[i] = [startDayi, endDayi]. Every event i starts
at startDayi and ends at endDayi.

You can attend an event i at any day d where startTimei <= d <= endTimei. You can only attend
one event at any time d.

Return the maximum number of events you can attend.

Example 1:
Input: events = [[1,2],[2,3],[3,4]]
Output: 3

Explanation: You can attend all the three events.
One way to attend them all is as shown.
Attend the first event on day 1.
Attend the second event on day 2.
Attend the third event on day 3.

Example 2:
Input: events= [[1,2],[2,3],[3,4],[1,2]]
Output: 4

Constraints:

1 <= events.length <= 10^5
events[i].length == 2
1 <= startDayi <= endDayi <= 10^5

https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/description/
 */

internal object P3MaximumNumberOfEventsThatCanBeAttended {

    private fun maxEvents(events: Array<IntArray>): Int {
        // Sort the events based on start time
        events.sortBy { it[0] }

        val n = events.size
        var attended = 0
        var currentDay = 1
        var index = 0

        // Create a min-heap to store events based on end time
        val minHeap = PriorityQueue<Int>()

        while (index < n || minHeap.isNotEmpty()) {
            // Add events that start on the current day to the min-heap
            while (index < n && events[index][0] == currentDay) {
                minHeap.offer(events[index][1])
                index++
            }

            // Remove events that have already ended
            while (minHeap.isNotEmpty() && minHeap.peek() < currentDay) {
                minHeap.poll()
            }

            // Attend the event with the earliest end time
            if (minHeap.isNotEmpty()) {
                minHeap.poll()
                attended++
            }

            currentDay++
        }

        return attended
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        var events = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 3),
            intArrayOf(3, 4))
        var maxAttended = maxEvents(events)
        println("Example 1 Output: $maxAttended") // Output: 3

        // Example 2
        events = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 3),
            intArrayOf(3, 4),
            intArrayOf(1, 2))
        maxAttended = maxEvents(events)
        println("Example 2 Output: $maxAttended") // Output: 4
    }
}