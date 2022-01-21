package challenges.cracking_coding_interview.moderate.q3_intersection

class Line(var start: Point, var end: Point) {
    var slope = 0.0
    var yintercept = 0.0

    init {
        if (start.x == end.x) {
            slope = Double.POSITIVE_INFINITY
            yintercept = Double.POSITIVE_INFINITY
        } else {
            slope = (end.y - start.y) / (end.x - start.x)
            yintercept = end.y - slope * end.x
        }
    }

    val isVertical: Boolean
        get() = slope == Double.POSITIVE_INFINITY

    override fun toString(): String {
        return "Line [slope=$slope, yintercept=$yintercept, start=$start, end=$end]"
    }

    fun getYFromX(x: Double): Double {
        return if (isVertical) {
            Double.POSITIVE_INFINITY
        } else slope * x + yintercept
    }
}