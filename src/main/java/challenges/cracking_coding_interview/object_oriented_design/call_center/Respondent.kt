package challenges.cracking_coding_interview.object_oriented_design.call_center

class Respondent(callHandler: CallHandler) : Employee(callHandler) {
    init {
        rank = Rank.Responder
    }
}