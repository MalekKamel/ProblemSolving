package challenges.educative_grokking_coding_interview.top_k_elements._3

import challenges.util.PrintHyphens
import java.util.*

/**
Given a list of points on a plane, where the plane is a 2-D array with (x, y) coordinates, find the k closest
points to the origin (0, 0).

https://www.educative.io/courses/grokking-coding-interview-patterns-java/JEzp359Rjn9
 */
internal object ClosestPoints {
    private fun kClosest(points: Array<Point>, k: Int): List<Point> {
        val maxHeap: PriorityQueue<Point> =
            PriorityQueue<Point>(java.util.Comparator<Point> { p1: Point, p2: Point -> p2.distFromOrigin() - p1.distFromOrigin() })
        // put first 'k' points in the max heap
        for (i in 0 until k) maxHeap.add(points[i])

        // go through the remaining points of the input array, if a Location is closer to the origin than the top Location
        // of the max-heap, remove the top Location from heap and add the Location from the input array
        for (i in k until points.size) {
            if (points[i].distFromOrigin() < maxHeap.peek().distFromOrigin()) {
                maxHeap.poll()
                maxHeap.add(points[i])
            }
        }

        // the heap has 'k' points closest to the origin, return them in a list
        return ArrayList(maxHeap)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val pointsOne: Array<Point> = arrayOf<Point>(
            Point(1, 3), Point(3, 4), Point(2, -1)
        )
        val pointsTwo: Array<Point> = arrayOf<Point>(
            Point(1, 3), Point(2, 4), Point(2, -1), Point(-2, 2), Point(5, 3), Point(3, -2)
        )
        val pointsThree: Array<Point> = arrayOf<Point>(
            Point(1, 3), Point(5, 3), Point(3, -2), Point(-2, 2)
        )
        val pointsFour: Array<Point> = arrayOf<Point>(
            Point(2, -1), Point(-2, 2), Point(1, 3), Point(2, 4)
        )
        val pointsFive: Array<Point> = arrayOf<Point>(
            Point(1, 3), Point(2, 4), Point(2, -1), Point(-2, 2), Point(5, 3), Point(3, -2), Point(5, 3), Point(3, -2)
        )
        val points: Array<Array<Point>> =
            arrayOf(pointsOne, pointsTwo, pointsThree, pointsFour, pointsFive)
        val kList = intArrayOf(2, 3, 1, 4, 5)
        for (i in points.indices) {
            print((i + 1).toString() + ".\tSet of points: ")
            for (p in points[i]) print("[" + p.x.toString() + " , " + p.y.toString() + "] ")
            println(
                """
	K: ${kList[i]}"""
            )
            val result: List<Point> = kClosest(points[i], kList[i])
            print("\tHere are the k = " + kList[i] + " points closest to the origin(0, 0): ")
            for (p in result) print("[" + p.x.toString() + " , " + p.y.toString() + "] ")
            println("\n")
            println(PrintHyphens.repeat("-", 100))
        }
    }
}

class Point(var x: Int, var y: Int) {
    fun distFromOrigin(): Int {
        // ignoring sqrt
        return x * x + y * y
    }
}