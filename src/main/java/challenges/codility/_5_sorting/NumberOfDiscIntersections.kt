package challenges.codility._5_sorting

/**
 * There are eleven (unordered) pairs of discs that intersect, namely:
 *
 * discs 1 and 4 intersect, and both intersect with all the other discs;
 * disc 2 also intersects with discs 0 and 3.
 * Write a function:
 *
 * fun solution(A: IntArray): Int
 *
 * that, given an array A describing N discs as explained above, returns the number of (unordered)
 *  pairs of intersecting
 * discs. The function should return −1 if the number of intersecting pairs exceeds 10,000,000.
 *
 * Given array A shown above, the function should return 11, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [0..2,147,483,647].
 *
 * https://app.codility.com/programmers/lessons/6-sorting/number_of_disc_intersections/
*/


class NumberOfDiscIntersections {
    /**
     * 1. Problem Explanation
     *
     * The problem asks us to find the number of intersecting pairs of discs. Each disc i has
     * a center at (i, 0) and a radius A[i]. Two discs intersect if the distance between
     * their centers is less than or equal to the sum of their radii. We need to return the count
     * of these intersecting pairs, or -1 if the count exceeds 10,000,000.
     *
     * 2. Pattern Identification and Rationale
     *
     * The relevant pattern here is using a sorted list of events. For each disc, we define
     * a 'start' event at the x-coordinate i - A[i] and an 'end' event at i + A[i].
     * By processing these events in sorted order, we can efficiently track the number of active
     * discs at any given point. When a new disc starts, it will intersect with all the discs that
     * are currently active.
     *
     * This approach is suitable because it allows us to avoid checking every pair of discs directly.
     * By sorting the start and end points, we can process the discs in a way that helps us count
     * intersections efficiently. The time complexity will be dominated by the sorting step,
     * which is O(N log N), making it more efficient than the O(N^2) naive approach for larger values of N.
     *
     * 3. Solution Breakdown
     *
     * Create a list to store the events. Each event will be a pair of (coordinate, type), where
     * type is +1 for a start event and -1 for an end event.
     * Iterate through the array A. For each disc i with radius A[i], add two events to the
     * list: (i - A[i], 1) and (i + A[i], -1).
     * Sort the list of events based on the coordinate. If two events have the same coordinate,
     * process the start event before the end event.
     * Initialize two counters: activeDiscs to 0 and intersectionCount to 0.
     * Iterate through the sorted events.
     * If the event type is +1 (start of a disc):
     * Add the current value of activeDiscs to intersectionCount. This is because the newly
     * started disc intersects with all the discs that were active before it started.
     * Increment activeDiscs.
     * If the event type is -1 (end of a disc):
     * Decrement activeDiscs.
     * After each addition to intersectionCount, check if it exceeds 10,000,000. If it does, return -1.
     * After processing all events, return intersectionCount.
     */
    fun solution(A: IntArray): Int {
        val n = A.size
        val events = mutableListOf<Pair<Long, Int>>()

        for (i in 0 until n) {
            events.add(Pair(i.toLong() - A[i], 1)) // Start event
            events.add(Pair(i.toLong() + A[i], -1)) // End event
        }

        // Sort by coordinate, then start before end
        events.sortWith(
            compareBy(
                { it.first },
                { -it.second },
            )
        )

        var activeDiscs = 0
        var intersectionCount = 0L

        for (event in events) {
            val type = event.second
            if (type == 1) {
                intersectionCount += activeDiscs
                activeDiscs++
            } else {
                activeDiscs--
            }

            if (intersectionCount > 10_000_000) {
                return -1
            }
        }

        return intersectionCount.toInt()
    }
}

fun main() {
    val sol = NumberOfDiscIntersections()
    val A1 = intArrayOf(1, 5, 2, 1, 4, 0)
    println(sol.solution(A1)) // Expected output: 11
}