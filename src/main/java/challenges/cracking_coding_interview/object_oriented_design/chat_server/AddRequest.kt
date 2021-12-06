package challenges.cracking_coding_interview.object_oriented_design.chat_server

import java.util.*

class AddRequest(val fromUser: User, val toUser: User?, val date: Date) {
    var status: RequestStatus

    init {
        status = RequestStatus.Unread
    }
}