package challenges.cracking_coding_interview.moderate.q3_intersection

object Question {
    /* Checks if middle is between start and end. */
    fun isBetween(start: Double, middle: Double, end: Double): Boolean {
        return if (start > end) {
            end <= middle && middle <= start
        } else {
            start <= middle && middle <= end
        }
    }

    /* Checks if middle is between start and end. */
    fun isBetween(start: Point, middle: Point, end: Point): Boolean {
        return isBetween(start.x, middle.x, end.x) && isBetween(start.y, middle.y, end.y)
    }

    fun intersection(start1: Point, end1: Point, start2: Point, end2: Point): Point? {
        /* Compute lines (including slope and y-intercept). */
        val line1 = Line(start1, end1)
        val line2 = Line(start2, end2)

        /* If the lines are parallel, then their extended lines must have same y-intercept.
		 * If so, check that the start or end of one point is on the other line. */if (line1.slope == line2.slope) {
            if (line1.yintercept != line2.yintercept) {
                return null
            }

            /* Check if the start or end of one line is in the other. If so, return that point*/return if (isBetween(
                    start1,
                    start2,
                    end1
                )
            ) start2 else if (isBetween(
                    start1,
                    end2,
                    end1
                )
            ) end2 else if (isBetween(
                    start2,
                    start1,
                    end2
                )
            ) start1 else if (isBetween(
                    start2,
                    end1,
                    end2
                )
            ) end1 else null
        }

        /* Compute the intersection of the infinite lines, and then check if this falls within the
		 * boundary of the line segments. Note that at most one line is vertical. */

        /* Get intersection's x coordinate. If one is vertical, always use its x coordinate. 
		 * Otherwise, compute the intersection's x coordinate based on setting each line's y = mx + b equation
		 * equal and solving for x. */
        val x: Double
        x = if (line1.isVertical || line2.isVertical) { /* If a line is vertical, use its x coordinate. */
            if (line1.isVertical) line1.start.x else line2.start.x
        } else { /* Set y = mx + b equations equal and solve for x */
            (line2.yintercept - line1.yintercept) / (line1.slope - line2.slope)
        }

        /* Get insection's y coordinate using a non-vertical line. Note that if line1 is vertical
		 * then line 2 is not vertical (else it would have been caught earlier). */
        val y = if (line1.isVertical) line2.getYFromX(x) else line1.getYFromX(x)

        /* We now have the intersection of the infinite lines. Check if it's within the boundaries
		 * of each line segment. */
        val intersection = Point(x, y)
        return if (isBetween(
                start1,
                intersection,
                end1
            ) && isBetween(
                start2,
                intersection,
                end2
            )
        ) {
            intersection
        } else null
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val s1 = Point(2147000000.0, 1.0)
        val e1 = Point(-2147000000.0, -1.0)
        val s2 = Point(-10.0, 0.0)
        val e2 = Point(0.0, 0.0)
        val intersection = intersection(s1, e1, s2, e2)
        println("Line Segment 1: $s1 to $e1")
        println("Line Segment 2: $s2 to $e2")
        println("Intersection: " + (intersection ?: "None"))
        if (intersection != null) {
            println("Intersection is on segment1: " + Tester.checkIfPointOnLineSegments(s1, intersection, e1))
            println("Intersection is on segment1: " + Tester.checkIfPointOnLineSegments(s2, intersection, e2))
        }
    }
}