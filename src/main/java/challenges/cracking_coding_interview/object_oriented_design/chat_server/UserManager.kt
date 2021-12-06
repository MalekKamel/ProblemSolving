package challenges.cracking_coding_interview.object_oriented_design.chat_server

import challenges.cracking_coding_interview.object_oriented_design.chat_server.RequestStatus
import challenges.cracking_coding_interview.object_oriented_design.chat_server.Conversation
import challenges.cracking_coding_interview.object_oriented_design.chat_server.UserStatus
import challenges.cracking_coding_interview.object_oriented_design.chat_server.PrivateChat
import challenges.cracking_coding_interview.object_oriented_design.chat_server.GroupChat
import challenges.cracking_coding_interview.object_oriented_design.chat_server.AddRequest
import challenges.cracking_coding_interview.object_oriented_design.chat_server.UserStatusType
import java.util.*

/* UserManager serves as the central place for the core user actions. */
object UserManager {
    private val usersById = HashMap<Int, User>()
    private val usersByAccountName = HashMap<String?, User>()
    private val onlineUsers = HashMap<Int, User>()
    fun addUser(fromUser: User, toAccountName: String?) {
        val toUser = usersByAccountName[toAccountName]
        val req = AddRequest(fromUser, toUser, Date())
        toUser!!.receivedAddRequest(req)
        fromUser.sentAddRequest(req)
    }

    fun approveAddRequest(req: AddRequest) {
        req.status = RequestStatus.Accepted
        val from = req.fromUser
        val to = req.toUser
        from!!.addContact(to)
        to!!.addContact(from)
    }

    fun rejectAddRequest(req: AddRequest) {
        req.status = RequestStatus.Rejected
        val from = req.fromUser
        val to = req.toUser
        from!!.removeAddRequest(req)
        to!!.removeAddRequest(req)
    }

    fun userSignedOn(accountName: String?) {
        val user = usersByAccountName[accountName]
        if (user != null) {
            user.status = UserStatus(UserStatusType.Available, "")
            onlineUsers[user.id] = user
        }
    }

    fun userSignedOff(accountName: String?) {
        val user = usersByAccountName[accountName]
        if (user != null) {
            user.status = UserStatus(UserStatusType.Offline, "")
            onlineUsers.remove(user.id)
        }
    }

}