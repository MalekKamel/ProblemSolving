package challenges.cracking_coding_interview.moderate.q4_tic_tac_toe

enum class Piece {
    Empty, Red, Blue;

    companion object {
        fun from(ordinal: Int): Piece {
            return when (ordinal) {
                1 -> {
                    Blue
                }
                2 -> {
                    Red
                }
                else -> {
                    Empty
                }
            }
        }
    }
}