import note.server.Note
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
        NoteService.clear()
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

}