package challenges.cracking_coding_interview.object_oriented_design.chat_server

import challenges.cracking_coding_interview.object_oriented_design.chat_server.RequestStatus
import challenges.cracking_coding_interview.object_oriented_design.chat_server.Conversation
import challenges.cracking_coding_interview.object_oriented_design.chat_server.UserStatus
import java.util.HashMap
import challenges.cracking_coding_interview.object_oriented_design.chat_server.PrivateChat
import challenges.cracking_coding_interview.object_oriented_design.chat_server.GroupChat
import challenges.cracking_coding_interview.object_oriented_design.chat_server.AddRequest
import challenges.cracking_coding_interview.object_oriented_design.chat_server.UserStatusType

class PrivateChat(user1: User?, user2: User?) : Conversation() {
    fun getOtherParticipant(primary: User): User? {
        if (participants[0] === primary) {
            return participants[1]
        } else if (participants[1] === primary) {
            return participants[0]
        }
        return null
    }

    init {
        participants.add(user1!!)
        participants.add(user2!!)
    }
}