package challenges.cracking_coding_interview.moderate.q3_intersection

object Tester {
    fun equalish(a: Double, b: Double): Boolean {
        return Math.abs(a - b) < .001
    }

    fun checkIfPointOnLineSegments(start: Point, middle: Point, end: Point): Boolean {
        if (equalish(start.x, middle.x) && equalish(start.y, middle.y)) {
            return true
        }
        if (equalish(middle.x, end.x) && equalish(middle.y, end.y)) {
            return true
        }
        if (start.x == end.x) { // Vertical
            return if (equalish(start.x, middle.x)) {
                Question.isBetween(start, middle, end)
            } else false
        }
        val line = Line(start, end)
        val x = middle.x
        val y = line.slope * x + line.yintercept
        return equalish(y, middle.y)
    }

    fun getPoints(size: Int): ArrayList<Point> {
        val points = ArrayList<Point>()
        var x1 = size * -1
        while (x1 < size) {
            var y1 = size * -1 + 1
            while (y1 < size - 1) {
                points.add(Point(x1.toDouble(), y1.toDouble()))
                y1 += 3
            }
            x1 += 3
        }
        return points
    }

    fun runTest(start1: Point, end1: Point, start2: Point, end2: Point): Boolean {
        val intersection = Question.intersection(start1, end1, start2, end2)
        var validate1 = true
        var validate2 = true
        if (intersection == null) {
            println("No intersection.")
        } else {
            validate1 = checkIfPointOnLineSegments(start1, intersection, end1)
            validate2 = checkIfPointOnLineSegments(start2, intersection, end2)
            if (validate1 && validate2) {
                println("has intersection")
            }
            if (!validate1 || !validate2) {
                println("ERROR -- $validate1, $validate2")
            }
        }
        println("  Start1: " + start1.x + ", " + start1.y)
        println("  End1: " + end1.x + ", " + end1.y)
        println("  Start2: " + start2.x + ", " + start2.y)
        println("  End2: " + end2.x + ", " + end2.y)
        if (intersection != null) {
            println("  Intersection: " + intersection.x + ", " + intersection.y)
        }
        return !(!validate1 || !validate2)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val points = getPoints(10)
        for (start1 in points) {
            for (end1 in points) {
                for (start2 in points) {
                    for (end2 in points) {
                        val success = runTest(start1, end1, start2, end2)
                        if (!success) {
                            return
                        }
                    }
                }
            }
        }
    }
}