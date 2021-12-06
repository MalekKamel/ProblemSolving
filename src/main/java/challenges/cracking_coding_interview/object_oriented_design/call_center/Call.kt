package challenges.cracking_coding_interview.object_oriented_design.call_center

/* Represents a call from a user. Calls have a minimum rank and are assigned to the
 * first employee who can handle that call.
 */
class Call(c: Caller) {
    /* Minimal rank of employee who can handle this call. */
    var rank: Rank

    /* Person who is calling. */
    private val caller: Caller

    /* Employee who is handling call. */
    private var handler: Employee? = null

    /* Set employee who is handling call. */
    fun setHandler(e: Employee?) {
        handler = e
    }

    /* Play recorded message to the customer. */
    fun reply(message: String?) {
        println(message)
    }

    fun incrementRank(): Rank {
        if (rank == Rank.Responder) {
            rank = Rank.Manager
        } else if (rank == Rank.Manager) {
            rank = Rank.Director
        }
        return rank
    }

    /* Disconnect call. */
    fun disconnect() {
        reply("Thank you for calling")
    }

    init {
        rank = Rank.Responder
        caller = c
    }
}