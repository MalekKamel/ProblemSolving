package challenges.cracking_coding_interview.object_oriented_design.deck_of_cards

import java.util.ArrayList

class BlackJackGameAutomator(numPlayers: Int) {
    private var deck: Deck<BlackJackCard>? = null
    private val hands: Array<BlackJackHand?>
    fun dealInitial(): Boolean {
        for (hand in hands) {
            val card1 = deck!!.dealCard()
            val card2 = deck!!.dealCard()
            if (card1 == null || card2 == null) {
                return false
            }
            hand!!.addCard(card1)
            hand.addCard(card2)
        }
        return true
    }

    val blackJacks: ArrayList<Int>
        get() {
            val winners = ArrayList<Int>()
            for (i in hands.indices) {
                if (hands[i]!!.isBlackJack) {
                    winners.add(i)
                }
            }
            return winners
        }

    fun playHand(i: Int): Boolean {
        val hand = hands[i]
        return playHand(hand)
    }

    fun playHand(hand: BlackJackHand?): Boolean {
        while (hand!!.score() < HIT_UNTIL) {
            val card = deck!!.dealCard() ?: return false
            hand.addCard(card)
        }
        return true
    }

    fun playAllHands(): Boolean {
        for (hand in hands) {
            if (!playHand(hand)) {
                return false
            }
        }
        return true
    }

    val winners: ArrayList<Int>
        get() {
            val winners = ArrayList<Int>()
            var winningScore = 0
            for (i in hands.indices) {
                val hand = hands[i]
                if (!hand!!.busted()) {
                    if (hand.score() > winningScore) {
                        winningScore = hand.score()
                        winners.clear()
                        winners.add(i)
                    } else if (hand.score() == winningScore) {
                        winners.add(i)
                    }
                }
            }
            return winners
        }

    fun initializeDeck() {
        val cards = ArrayList<BlackJackCard>()
        for (i in 1..13) {
            for (j in 0..3) {
                val suit: Suit = Suit.getSuitFromValue(j) ?: return
                val card = BlackJackCard(i, suit)
                cards.add(card)
            }
        }
        deck = Deck()
        deck!!.setDeckOfCards(cards)
        deck!!.shuffle()
    }

    fun printHandsAndScore() {
        for (i in hands.indices) {
            print("Hand " + i + " (" + hands[i]!!.score() + "): ")
            hands[i]!!.print()
            println("")
        }
    }

    companion object {
        private const val HIT_UNTIL = 16
    }

    init {
        hands = arrayOfNulls(numPlayers)
        for (i in 0 until numPlayers) {
            hands[i] = BlackJackHand()
        }
    }
}