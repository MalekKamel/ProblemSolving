package challenges.cracking_coding_interview.object_oriented_design.deck_of_cards

abstract class Card(/* number or face that's on card - a number 2 through 10,
	 * or 11 for Jack, 12 for Queen, 13 for King, or 1 for Ace 
	 */
    protected var faceValue: Int, protected var suit: Suit?
) {
    /* returns whether or not the card is available to be given out to someone */  var isAvailable = true
        private set

    abstract fun value(): Int
    fun suit(): Suit? {
        return suit
    }

    fun markUnavailable() {
        isAvailable = false
    }

    fun markAvailable() {
        isAvailable = true
    }

    fun print() {
        val faceValues = arrayOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
        print(faceValues[faceValue - 1])
        when (suit) {
            Suit.Club -> print("c")
            Suit.Heart -> print("h")
            Suit.Diamond -> print("d")
            Suit.Spade -> print("s")
            null -> TODO()
        }
        print(" ")
    }
}