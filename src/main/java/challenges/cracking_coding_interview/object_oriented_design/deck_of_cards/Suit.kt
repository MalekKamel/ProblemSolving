package challenges.cracking_coding_interview.object_oriented_design.deck_of_cards

enum class Suit(val value: Int) {
    Club(0), Diamond(1), Heart(2), Spade(3);

    companion object {
        fun getSuitFromValue(value: Int): Suit? {
            return when (value) {
                0 -> Club
                1 -> Diamond
                2 -> Heart
                3 -> Spade
                else -> null
            }
        }
    }
}