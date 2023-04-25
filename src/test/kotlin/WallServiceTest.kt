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
        WallService.add(Post(0,"yy", likes = Likes(9)))
        WallService.add(Post(0, "dd", likes = Likes(4)))

        val update = Post(2, "gg", likes = Likes(7))

        val result = WallService.update(update)
        assertTrue(result)

   }
    @Test
    fun updateNotExisting() {
        val service = WallService
        service.add(Post(0, "tt", likes = Likes(9)))
        service.add(Post(0, "dd", likes = Likes(4)))
        val update = Post(10, "gg", likes = Likes(7))
        val result = service.update(update)
        assertFalse(result)
    }
    @Test
    fun addTest() {

        val post = WallService.add(Post(text = "трунь", likes = Likes()))

        val result = post.id
        assertNotEquals(result,0)
    }
}