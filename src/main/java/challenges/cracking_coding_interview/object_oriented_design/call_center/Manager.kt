package challenges.cracking_coding_interview.object_oriented_design.call_center

class Manager(callHandler: CallHandler) : Employee(callHandler) {
    init {
        rank = Rank.Manager
    }
}