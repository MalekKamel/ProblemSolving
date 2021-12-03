package challenges.cracking_coding_interview.object_oriented_design.deck_of_cards

class BlackJackCard(c: Int, s: Suit?) : Card(c, s) {
    override fun value(): Int {
        return if (isAce) { // Ace
            1
        } else if (isFaceCard) { // Face card
            10
        } else { // Number card
            faceValue
        }
    }

    fun minValue(): Int {
        return if (isAce) { // Ace
            1
        } else {
            value()
        }
    }

    fun maxValue(): Int {
        return if (isAce) { // Ace
            11
        } else {
            value()
        }
    }

    val isAce: Boolean
        get() = faceValue == 1
    val isFaceCard: Boolean
        get() = faceValue >= 11 && faceValue <= 13
}