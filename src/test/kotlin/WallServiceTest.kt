import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun updateExisting() {
        WallService.add(Post(0,"yy", Likes = Likes(9)))
        WallService.add(Post(0, "dd", Likes = Likes(4)))

        val update = Post(2, "gg", Likes = Likes(7))

        val result = WallService.update(update)
        assertTrue(result)

   }
    @Test
    fun updateNotExisting() {
        val service = WallService
        service.add(Post(0, "tt", Likes = Likes(9)))
        service.add(Post(0, "dd", Likes = Likes(4)))
        val update = Post(10, "gg", Likes = Likes(7))
        val result = service.update(update)
        assertFalse(result)
    }
    @Test
    fun addTest() {

        val post = WallService.add(Post(text = "трунь", Likes = Likes()))

        val result = post.id
        assertNotEquals(result,0)
    }
}