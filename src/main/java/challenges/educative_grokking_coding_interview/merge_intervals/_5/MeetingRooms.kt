package challenges.educative_grokking_coding_interview.merge_intervals._5

import challenges.educative_grokking_coding_interview.merge_intervals.Interval

object MeetingRooms {
    private fun minMeetingRooms(intervals: List<Interval>): Int {
        if (intervals.isEmpty()) {
            return 0
        }

        // Create two arrays to store the start times and end times of the intervals
        val startTimes = intervals.map { it.start }.sorted().toIntArray()
        val endTimes = intervals.map { it.end }.sorted().toIntArray()

        // Use two pointers to keep track of the current meetings and the maximum number of meetings
        var currentMeetings = 0
        var maxMeetings = 0
        var i = 0
        var j = 0

        // Iterate over the start times and end times arrays together
        while (i < startTimes.size && j < endTimes.size) {
            // If the current meeting has started before the earliest ending meeting has ended, we need an extra meeting room
            if (startTimes[i] < endTimes[j]) {
                currentMeetings++
                i++
            } else {
                // Otherwise, we can reuse a meeting room
                currentMeetings--
                j++
            }

            // Update the maximum number of meetings
            maxMeetings = maxOf(maxMeetings, currentMeetings)
        }

        return maxMeetings
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val intervals = listOf(
            Interval(2, 8),
            Interval(3, 4),
            Interval(3, 9),
            Interval(5, 11),
            Interval(8, 20),
            Interval(11, 15)
        )

        val minRooms = minMeetingRooms(intervals)
        println("Minimum number of meeting rooms required: $minRooms")
    }
}