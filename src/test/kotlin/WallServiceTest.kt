import chat.service.ChatNotFoundException
import chat.service.ChatService
import chat.service.Message
import chat.service.MessageNotFoundException
import note.server.Note
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
        NoteService.clear()
        ChatService.clear()
    }

    @Test
    fun updateExisting() {
        WallService.add(
            Post(
                0, "yy", likes = Likes(9), comments = Comments(), donut = Donut(),
                copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo()
            )
        )
        WallService.add(
            Post(
                0, "dd", likes = Likes(4), comments = Comments(), donut = Donut(),
                copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo()
            )
        )

        val update = Post(
            2, "gg", likes = Likes(7), copyright = Copyright(), donut = Donut(),
            comments = Comments(), reposts = Reposts(), views = Views(), geo = Geo()
        )

        val result = WallService.update(update)
        assertTrue(result)

    }

    @Test
    fun updateNotExisting() {
        val service = WallService
        service.add(
            Post(
                0, "tt", likes = Likes(9), comments = Comments(), donut = Donut(),
                copyright = Copyright(), views = Views(), reposts = Reposts(), geo = Geo()
            )
        )
        service.add(
            Post(
                0, "dd", likes = Likes(4), comments = Comments(), donut = Donut(),
                copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo()
            )
        )
        val update = Post(
            10, "gg", likes = Likes(7), comments = Comments(), donut = Donut(),
            copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo()
        )
        val result = service.update(update)
        assertFalse(result)
    }

    @Test
    fun addTest() {

        val post = WallService.add(
            Post(
                text = "трунь", likes = Likes(), comments = Comments(), donut = Donut(),
                copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo()
            )
        )

        val result = post.id
        assertNotEquals(result, 0)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        WallService.add(
            Post(
                0, "yy", likes = Likes(9), comments = Comments(), donut = Donut(),
                copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo()
            )
        )
        WallService.createComment(2, Comment(donut = Donut(), thread = Thread()))
    }

    @Test
    fun createCommentGoodTest() {
        val postNew = WallService.add(
            Post(
                0, "yy", likes = Likes(9), comments = Comments(), donut = Donut(),
                copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo()
            )
        )
        val postId = 1
        WallService.createComment(postId, Comment(donut = Donut(), thread = Thread()))
        assertEquals(postNew.id, postId)
    }


    @Test
    fun addTestService() {
        val note = NoteService.add(Note())
        val result = note.id
        assertNotEquals(result, 2)
    }

    @Test(expected = ItemNotFoundException::class)
    fun createCommentThrow() {
        NoteService.add(Note())
        NoteService.createComment(0, comment = Comment(donut = Donut(), thread = Thread()))
    }

    @Test
    fun createCommentGood() {
        NoteService.add(Note())
        NoteService.createComment(1, comment = Comment(donut = Donut(), thread = Thread()))
    }

    @Test
    fun getByIdGood() {
        val note = NoteService.add(Note())
        val result = NoteService.getById(1)
        assertEquals(result, note)
    }

    @Test(expected = ItemNotFoundException::class)
    fun getByIdThrow() {
        NoteService.add(Note())
        NoteService.getById(2)
    }

    @Test
    fun deleteCommentGoodTest() {
        NoteService.add(Note())
        NoteService.add(Note())
        val commentNew = NoteService.createComment(2, comment = Comment(donut = Donut(), thread = Thread()))
        NoteService.deleteComment(1)
        val result = commentNew.deleted
        assertTrue(result)
    }
    @Test(expected = DeletedCommentException::class)
    fun deleteCommentThrow(){
        NoteService.add(Note())
        NoteService.createComment(1, comment = Comment(donut = Donut(), thread = Thread()))
        NoteService.deleteComment(1)
        NoteService.deleteComment(1)
    }
    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentThrowItemNotFound(){
        NoteService.add(Note())
        NoteService.createComment(1, comment = Comment(donut = Donut(), thread = Thread()))
        NoteService.deleteComment(2)
    }
    @Test
    fun deleteTestGood(){
        NoteService.add(Note())
        NoteService.createComment(1, comment = Comment(donut = Donut(), thread = Thread()))
        NoteService.delete(1)
        val result = NoteService.get()
        assertEquals(0,result.size)
     }
    @Test(expected = ItemNotFoundException::class)
    fun deleteTestNoteThrow(){
        NoteService.add(Note())
        NoteService.delete(2)
    }
    @Test(expected = ItemNotFoundException::class)
    fun editNoteThrow(){
        NoteService.add(Note())
        NoteService.edit(2,"hi")
    }
    @Test
    fun editNoteGoodTest(){
        val newNote = NoteService.add(Note())
        NoteService.edit(1,"rr")
        val result = newNote.text
        assertEquals("rr",result)
    }
    @Test(expected = DeletedCommentException::class)
    fun editCommentTestDelete(){
        NoteService.add(Note())
        NoteService.add(Note())
        NoteService.createComment(2, comment = Comment(donut = Donut(), thread = Thread()))
        NoteService.deleteComment(1)
        NoteService.editComment(1,"zz")
    }
    @Test(expected = CommentNotFoundException::class)
    fun editCommentTestNoItem(){
        NoteService.editComment(1,"qwe")
    }
    @Test
    fun editCommentTestGood(){
        val newNote = NoteService.add(Note())
        NoteService.createComment(1,comment = Comment(donut = Donut(), thread = Thread()))
        NoteService.editComment(1,"great")
        val result = newNote.comments.firstOrNull()?.text
        assertEquals("great",result)
    }
    @Test
    fun getTest(){
        NoteService.add(Note())
        NoteService.add(Note())
        val get = NoteService.get()
        val t = get.lastOrNull()?.id
        assertEquals(2,t)
    }
    @Test
    fun getCommentsTestNote(){
        NoteService.add(Note())
        NoteService.createComment(1, comment = Comment(donut = Donut(), thread = Thread()))
        val getCom = NoteService.getComments(1)
        val dd = getCom.lastOrNull()?.id
        assertEquals(1,dd)
    }
    @Test(expected = CommentNotFoundException::class)
    fun getCommentsTestNoteNoComment(){
        NoteService.getComments(1)
    }
    @Test(expected = DeletedCommentException::class)
    fun restoreCommentThrow(){
        NoteService.add(Note())
        NoteService.createComment(1, comment = Comment(donut = Donut(), thread = Thread()))
        NoteService.restoreComment(1)
    }
    @Test
    fun restoreCommentTestGood(){
        val tt = NoteService.add(Note())
        NoteService.createComment(1, comment = Comment(donut = Donut(), thread = Thread()))
        NoteService.createComment(1, comment = Comment(donut = Donut(), thread = Thread()))
        NoteService.deleteComment(2)
        NoteService.deleteComment(1)
        NoteService.restoreComment(2)
        val result = tt.comments.lastOrNull()!!.deleted
        assertFalse(result)
    }
    @Test
    fun addMessageTest(){
        ChatService.addMessage(1, message = Message("ttt"))
        val result = ChatService.getChats().size
        assertEquals(1, result)
    }
    @Test
    fun getUnreadChatsCountTest(){
        ChatService.addMessage(1, message = Message("ttt"))
        ChatService.addMessage(2, message = Message("ttt"))
        val tf = ChatService.getUnreadChatsCount()
        assertEquals(tf, 2)
    }
    @Test
    fun getLastMessageTest(){
        val message1 = Message("hi")
        ChatService.addMessage(1, message = Message("rrr"))
        ChatService.addMessage(1, message1)
        val lastMessage = ChatService.getLastMessage()
        val result =  lastMessage.size
        assertEquals(result, 1)
        assertEquals(lastMessage[0],message1 )
    }
    @Test
    fun getMessagesTest(){
        val message1 = Message("привет")
        val message2 = Message("мир")
        val message3 = Message("земляне")
        ChatService.addMessage(1, message1)
        ChatService.addMessage(1, message2)
        ChatService.addMessage(1, message3)
        val messages = ChatService.getMessages(1,2)

        assertEquals(message2,messages[0])
        assertEquals(message3,messages[1])
        assertEquals(messages.size, 2)
        assertTrue(messages.all { it.read })
    }
    @Test(expected = ChatNotFoundException::class)
    fun getMessagesThrow(){
        ChatService.getMessages(1,1)
    }
    @Test
    fun deleteMessageTest(){
        val message1 = Message("привет")
        val message2 = Message("мир")
        val message3 = Message("земляне")
        ChatService.addMessage(1, message1)
        ChatService.addMessage(1, message2)
        ChatService.addMessage(1, message3)
        ChatService.deleteMessage(1,1)
        val chat = ChatService.getMessages(1,2)
        assertEquals(chat[0],message1)
        assertEquals(chat[1],message3)
    }
    @Test(expected = MessageNotFoundException::class)
    fun deleteMessageThrow(){
        ChatService.deleteMessage(1,0)
    }
    @Test
    fun deleteChat(){
        ChatService.addMessage(1, message = Message("rrr"))
        ChatService.addMessage(1, message = Message("hello"))
        ChatService.deleteChat(1)
        val result = ChatService.getChats()
        assertEquals(result.size, 0)
    }
    @Test
    fun getChatTest(){
        ChatService.addMessage(1, message = Message("rrr"))
        val result = ChatService.getChat(2)
        //val result2 = ChatService.getChat(1)
        //val yy = ChatService.getChats()
        assertEquals("нет такого чата",result)

    }
    @Test
    fun getChatsTest(){
        val message1 = Message("привет")
        ChatService.addMessage(1, message1)
        val tt = ChatService.getChats()
        assertNotNull(tt[1])
    }
    @Test
    fun editMessageTest(){
        val message1 = Message("привет")
        ChatService.addMessage(1, message1)
        ChatService.editMessage(1,0,"hi")
        val newText = message1.text
        val y = ChatService.getChats()
        val rrr = y[1]!!.messages.getOrNull(0)?.text
        assertEquals(newText,rrr)

    }
    @Test(expected = MessageNotFoundException::class)
    fun editMessageThrow(){
        ChatService.editMessage(1,2,"yh")
    }

}