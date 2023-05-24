package chat.service

import java.lang.RuntimeException

object  ChatService {
    private var chats = mutableMapOf(5 to Chat())

    fun clear(){
        chats = mutableMapOf()
    }


    fun addMessage(userId: Int, message: Message){
        chats.getOrPut(userId){Chat()}.messages.add(message)
    }
    fun getUnreadChatsCount() = chats.values.count{chat -> chat.messages.any { message -> !message.read } }
    fun getLastMessage() = chats.values.map { chat -> chat.messages.lastOrNull()?: "нет сообщений" }
    fun getMessages(userId: Int, count: Int) : List<Message> {
        val chat = chats[userId]?: throw ChatNotFoundException("чат не найден")
        return chat.messages.takeLast(count).onEach { it.read = true }

    }
    fun deleteMessage(userId: Int, numberMessage: Int) {
        val chat = chats[userId]?: throw MessageNotFoundException("Сообщение не найдено")
        chat.messages.removeAt(numberMessage)
    }
    fun deleteChat(userId: Int) {
        chats.remove(userId)
    }
    fun getChat(userId: Int) = chats[userId]?: "нет такого чата"


    fun getChats()= chats
    fun editMessage(userId: Int, numberMessage: Int, newText: String){
        val chat = chats[userId]?: throw MessageNotFoundException("нет сообщения")
        chat.messages[numberMessage].text = newText
    }
}

data class Chat(
    val messages: MutableList<Message> = mutableListOf()
)
data class Message(
    var text: String,
    var read : Boolean = false
)
class ChatNotFoundException(massage: String) : RuntimeException(massage)
class MessageNotFoundException(massage: String) : RuntimeException(massage)