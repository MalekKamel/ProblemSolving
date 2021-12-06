package challenges.cracking_coding_interview.object_oriented_design.chat_server

class GroupChat : Conversation() {
    fun removeParticipant(user: User?) {
        participants.remove(user)
    }

    fun addParticipant(user: User?) {
        participants.add(user!!)
    }
}