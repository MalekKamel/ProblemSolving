package challenges.cracking_coding_interview.object_oriented_design.chat_server

import challenges.cracking_coding_interview.object_oriented_design.chat_server.RequestStatus
import challenges.cracking_coding_interview.object_oriented_design.chat_server.Conversation
import challenges.cracking_coding_interview.object_oriented_design.chat_server.UserStatus
import java.util.HashMap
import challenges.cracking_coding_interview.object_oriented_design.chat_server.PrivateChat
import challenges.cracking_coding_interview.object_oriented_design.chat_server.GroupChat
import challenges.cracking_coding_interview.object_oriented_design.chat_server.AddRequest
import challenges.cracking_coding_interview.object_oriented_design.chat_server.UserStatusType

enum class UserStatusType {
    Offline, Away, Idle, Available, Busy
}