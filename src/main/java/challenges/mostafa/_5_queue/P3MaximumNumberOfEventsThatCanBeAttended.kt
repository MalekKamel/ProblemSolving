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

    /**
     *  1. Problem Explanation
     *  The problem asks us to find the maximum number of events we can attend given a list of events,
     *  where each event has a start and end day. We can only attend one event on any given day,
     *  and we can attend an event on any day within its start and end day (inclusive).
     *
     *  2. Pattern Identification and Rationale
     *  The core idea to solve this problem is a greedy approach combined with a priority queue (min-heap).
     *  We want to prioritize attending events that end sooner because attending a longer event might block us
     *  from attending multiple shorter events that occur later.
     *  The priority queue will help us keep track of the events that are currently available to attend
     *  (i.e., their start day has passed or is the current day), ordered by their end day.
     *
     *  Rationale:
     *  - Sorting events by their start day allows us to process events in chronological order of their
     *  availability.
     *  - Using a min-heap to store the end days of the currently available events enables us to efficiently
     *    pick the event that ends earliest. By attending the event that ends earliest, we maximize
     *    the chances of attending more events in the future.
     *
     *  3. Solution Breakdown
     *  Step 1: Sort the events based on their start times. If two events have the same start time, we
     *  can sort them by their end times (though not strictly necessary for correctness, it can be a reasonable
     *  tie-breaker).
     *  Step 2: Initialize a min-heap to store the end days of the events that are currently available
     *  to attend.
     *  Step 3: Initialize a variable `attendedEvents` to 0, which will store the count of attended events.
     *  Step 4: Initialize a variable `currentDay` to 1 (the earliest possible start day).
     *  Step 5: Initialize an index `i` to 0 to iterate through the sorted events.
     *  Step 6: While either there are still events to process or there are events in the min-heap:
     *     a. While `i` is within the bounds of the events array and the start day of the current event
     *     (`events[i][0]`) is less than or equal to the `currentDay`, add the end day of this
     *     event (`events[i][1]`) to the min-heap and increment `i`. This step makes all currently
     *     available events eligible for attendance.
     *     b. Remove any events from the min-heap whose end day is before the `currentDay`. These events
     *     are no longer attendable.
     *     c. If the min-heap is not empty, it means there is at least one event we can attend on
     *     the `currentDay`. Attend the event that ends earliest by removing its end day from the min-heap
     *     and increment `attendedEvents`.
     *     d. Increment `currentDay` to move to the next day.
     *  Step 7: Return `attendedEvents`.
     */
    private fun maxEvents(events: Array<IntArray>): Int {
        // Step 1: Sort events by start time for chronological order
        events.sortBy { it[0] }

        // Step 2: Initialize min-heap for end times of available events
        val availableEvents = PriorityQueue<Int>()

        // Step 3: Initialize count of attended events
        var attendedEvents = 0

        // Step 4: Initialize current day
        var currentDay = 1

        // Step 5: Initialize event index
        var i = 0

        // Step 6: Iterate while there are events to process or available events
        while (i < events.size || availableEvents.isNotEmpty()) {
            // Add events that start on or before the current day to the min-heap
            while (i < events.size && events[i][0] <= currentDay) {
                availableEvents.offer(events[i][1])
                i++
            }

            // Remove events that have already ended
            while (availableEvents.isNotEmpty() && availableEvents.peek() < currentDay) {
                availableEvents.poll()
            }

            // Attend an event if possible
            if (availableEvents.isNotEmpty()) {
                availableEvents.poll() // Attend the event that ends earliest
                attendedEvents++
            }

            // Move to the next day
            currentDay++
        }

        // Step 7: Return the total number of attended events
        return attendedEvents
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