import org.junit.Assert.*
import org.junit.Test

class WallServiceTest {
    @Test
    fun updateExisting() {
        val service = WallService()
        service.add(Post(0,"yy", Likes = Likes(9)))
        service.add(Post(0, "dd", Likes = Likes(4)))
        val update = Post(2, "gg", Likes = Likes(7))
        val result = service.update(update)
        assertTrue(result)

   }
    @Test
    fun updateNotExisting() {
        val service = WallService()
        service.add(Post(0, "tt", Likes = Likes(9)))
        service.add(Post(0, "dd", Likes = Likes(4)))
        val update = Post(10, "gg", Likes = Likes(7))
        val result = service.update(update)
        assertFalse(result)
    }
    @Test
    fun addTest() {
        val service = WallService()
        val post = service.add(Post(text = "трунь", Likes = Likes()))
        val result = post.id
        assertNotEquals(result,0)
    }
}