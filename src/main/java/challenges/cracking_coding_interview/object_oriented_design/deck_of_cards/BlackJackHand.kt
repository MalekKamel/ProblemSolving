package challenges.cracking_coding_interview.object_oriented_design.deck_of_cards


class BlackJackHand : Hand<BlackJackCard>() {
    override fun score(): Int {
        val scores = possibleScores()
        var maxUnder = Int.MIN_VALUE
        var minOver = Int.MAX_VALUE
        for (score in scores) {
            if (score in 22 until minOver) {
                minOver = score
            } else if (score in (maxUnder + 1)..21) {
                maxUnder = score
            }
        }
        return if (maxUnder == Int.MIN_VALUE) minOver else maxUnder
    }

    private fun possibleScores(): ArrayList<Int> {
        val scores = ArrayList<Int>()
        if (cards.size == 0) {
            return scores
        }
        for (card in cards) {
            addCardToScoreList(card!!, scores)
        }
        return scores
    }

    private fun addCardToScoreList(card: BlackJackCard, scores: ArrayList<Int>) {
        if (scores.isEmpty()) {
            scores.add(0)
        }
        val length = scores.size
        for (i in 0 until length) {
            val score = scores[i]
            scores[i] = score + card.minValue()
            if (card.minValue() != card.maxValue()) {
                scores.add(score + card.maxValue())
            }
        }
    }

    fun busted(): Boolean {
        return score() > 21
    }

    fun is21(): Boolean {
        return score() == 21
    }

    val isBlackJack: Boolean
        get() {
            if (cards.size != 2) {
                return false
            }
            val first = cards[0]
            val second = cards[1]
            return first.isAce && second.isFaceCard || second.isAce && first.isFaceCard
        }
}