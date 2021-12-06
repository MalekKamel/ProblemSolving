package challenges.cracking_coding_interview.object_oriented_design.chat_server

abstract class Conversation {
    protected var participants = ArrayList<User>()
    var id = 0
        protected set
    var messages = ArrayList<Message>()
        protected set

    fun addMessage(m: Message): Boolean {
        messages.add(m)
        return true
    }
}