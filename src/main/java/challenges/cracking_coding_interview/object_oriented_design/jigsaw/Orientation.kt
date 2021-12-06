package challenges.cracking_coding_interview.object_oriented_design.jigsaw

enum class Orientation {
    LEFT, TOP, RIGHT, BOTTOM;

    // Should stay in this order 
    val opposite: Orientation?
        get() = when (this) {
            LEFT -> RIGHT
            RIGHT -> LEFT
            TOP -> BOTTOM
            BOTTOM -> TOP
            else -> null
        }
}