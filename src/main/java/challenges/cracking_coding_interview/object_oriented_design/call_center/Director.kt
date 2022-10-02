package challenges.cracking_coding_interview.object_oriented_design.call_center

class Director(callHandler: CallHandler) : Employee(callHandler) {
    init {
        rank = Rank.Director
    }
}