package challenges.cracking_coding_interview.object_oriented_design.deck_of_cards

import kotlin.jvm.JvmStatic

object Question {
    @JvmStatic
    fun main(args: Array<String>) {
        val numHands = 5
        val automator = BlackJackGameAutomator(numHands)
        automator.initializeDeck()
        var success = automator.dealInitial()

        if (!success) {
            println("Error. Out of cards.")
            return
        }

        println("-- Initial --")
        automator.printHandsAndScore()
        val blackjacks = automator.blackJacks

        if (blackjacks.isNotEmpty()) {
            print("Blackjack at ")
            for (i in blackjacks) {
                print("$i, ")
            }
            println("")
            return
        }

        success = automator.playAllHands()
        if (!success) {
            println("Error. Out of cards.")
            return
        }

        println("\n-- Completed Game --")
        automator.printHandsAndScore()
        val winners = automator.winners
        if (winners.isNotEmpty()) {
            print("Winners: ")
            for (i in winners) {
                print("$i, ")
            }
            println("")
            return
        }

        println("Draw. All players have busted.")
    }
}