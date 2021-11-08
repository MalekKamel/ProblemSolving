package challenges.cracking_coding_interview.stacks_queues.three_in_one

class FullStackException : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)

    companion object {
        private const val serialVersionUID = 1L
    }
}