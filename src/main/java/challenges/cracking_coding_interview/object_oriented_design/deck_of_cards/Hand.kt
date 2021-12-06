package challenges.cracking_coding_interview.object_oriented_design.deck_of_cards

open class Hand<T : Card> {
    protected var cards = ArrayList<T>()
    open fun score(): Int {
        var score = 0
        for (card in cards) {
            score += card.value()
        }
        return score
    }

    fun addCard(card: T) {
        cards.add(card)
    }

    fun print() {
        for (card in cards) {
            card.print()
        }
    }
}