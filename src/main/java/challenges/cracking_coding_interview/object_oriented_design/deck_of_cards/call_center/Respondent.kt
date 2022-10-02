package challenges.cracking_coding_interview.object_oriented_design.deck_of_cards.call_center

class Respondent(callHandler: CallHandler) : Employee(callHandler) {
    init {
        rank = Rank.Responder
    }
}