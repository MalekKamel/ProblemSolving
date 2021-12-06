package challenges.cracking_coding_interview.object_oriented_design.jigsaw

enum class Shape {
    INNER, OUTER, FLAT;

    val opposite: Shape?
        get() = when (this) {
            INNER -> OUTER
            OUTER -> INNER
            else -> null
        }
}