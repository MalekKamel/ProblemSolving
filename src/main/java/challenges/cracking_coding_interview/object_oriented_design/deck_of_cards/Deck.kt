package challenges.cracking_coding_interview.object_oriented_design.deck_of_cards

import challenges.util.AssortedMethods.randomIntInRange

class Deck<T : Card> {
    private var cards: ArrayList<T>? = null
    private var dealtIndex = 0 // marks first undealt card
    fun setDeckOfCards(deckOfCards: ArrayList<T>?) {
        cards = deckOfCards
    }

    fun shuffle() {
        for (i in cards!!.indices) {
            val j = randomIntInRange(i, cards!!.size - i - 1)
            val card1 = cards!![i]
            val card2 = cards!![j]
            cards!![i] = card2
            cards!![j] = card1
        }
    }

    fun remainingCards(): Int {
        return cards!!.size - dealtIndex
    }

    fun dealHand(number: Int): Array<T?>? {
        if (remainingCards() < number) {
            return null
        }
        val hand = arrayOfNulls<Card>(number) as Array<T?>
        var count = 0
        while (count < number) {
            val card = dealCard()
            if (card != null) {
                hand[count] = card
                count++
            }
        }
        return hand
    }

    fun dealCard(): T? {
        if (remainingCards() == 0) {
            return null
        }
        val card = cards!![dealtIndex]
        card.markUnavailable()
        dealtIndex++
        return card
    }

    fun print() {
        for (card in cards!!) {
            card.print()
        }
    }
}