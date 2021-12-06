package challenges.cracking_coding_interview.object_oriented_design.chat_server

import challenges.cracking_coding_interview.object_oriented_design.chat_server.RequestStatus
import challenges.cracking_coding_interview.object_oriented_design.chat_server.Conversation
import challenges.cracking_coding_interview.object_oriented_design.chat_server.UserStatus
import challenges.cracking_coding_interview.object_oriented_design.chat_server.PrivateChat
import challenges.cracking_coding_interview.object_oriented_design.chat_server.GroupChat
import challenges.cracking_coding_interview.object_oriented_design.chat_server.AddRequest
import challenges.cracking_coding_interview.object_oriented_design.chat_server.UserStatusType
import java.util.*

class User(val id: Int, val accountName: String, val fullName: String) {
    var status: UserStatus? = null
    private val privateChats = HashMap<Int, PrivateChat>()
    private val groupChats = ArrayList<GroupChat>()
    private val receivedAddRequests = HashMap<Int, AddRequest>()
    private val sentAddRequests = HashMap<Int, AddRequest>()
    private val contacts = HashMap<Int, User?>()
    fun sendMessageToUser(toUser: User, content: String): Boolean {
        var chat = privateChats[toUser.id]
        if (chat == null) {
            chat = PrivateChat(this, toUser)
            privateChats[toUser.id] = chat
        }
        val message = Message(content, Date())
        return chat.addMessage(message)
    }

    fun sendMessageToGroupChat(groupId: Int, content: String): Boolean {
        val chat = groupChats[groupId]
        if (chat != null) {
            val message = Message(content, Date())
            return chat.addMessage(message)
        }
        return false
    }

    fun addContact(user: User?): Boolean {
        return if (contacts.containsKey(user!!.id)) {
            false
        } else {
            contacts[user.id] = user
            true
        }
    }

    fun receivedAddRequest(req: AddRequest) {
        val senderId = req.fromUser.id
        if (!receivedAddRequests.containsKey(senderId)) {
            receivedAddRequests[senderId] = req
        }
    }

    fun sentAddRequest(req: AddRequest) {
        val receiverId = req.fromUser.id
        if (!sentAddRequests.containsKey(receiverId)) {
            sentAddRequests[receiverId] = req
        }
    }

    fun removeAddRequest(req: AddRequest) {
        if (req.toUser === this) {
            receivedAddRequests.remove(req)
        } else if (req.fromUser === this) {
            sentAddRequests.remove(req)
        }
    }

    fun requestAddUser(accountName: String?) {
        UserManager.addUser(this, accountName)
    }

    fun addConversation(conversation: PrivateChat) {
        val otherUser = conversation.getOtherParticipant(this)
        privateChats[otherUser!!.id] = conversation
    }

    fun addConversation(conversation: GroupChat) {
        groupChats.add(conversation)
    }
}