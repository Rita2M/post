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
        WallService.add(Post(0,"yy", likes = Likes(9), comments = Comments(), donut = Donut(),
        copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo()))
        WallService.add(Post(0, "dd", likes = Likes(4), comments = Comments(), donut = Donut(),
        copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo()))

        val update = Post(2, "gg", likes = Likes(7), copyright = Copyright(), donut = Donut(),
        comments = Comments(), reposts = Reposts(), views = Views(), geo = Geo())

        val result = WallService.update(update)
        assertTrue(result)

   }
    @Test
    fun updateNotExisting() {
        val service = WallService
        service.add(Post(0, "tt", likes = Likes(9), comments = Comments(), donut = Donut(),
        copyright = Copyright(), views = Views() , reposts = Reposts(), geo = Geo()))
        service.add(Post(0, "dd", likes = Likes(4), comments = Comments(), donut = Donut(),
        copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo()))
        val update = Post(10, "gg", likes = Likes(7), comments = Comments(), donut = Donut(),
        copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo())
        val result = service.update(update)
        assertFalse(result)
    }
    @Test
    fun addTest() {

        val post = WallService.add(Post(text = "трунь", likes = Likes(), comments = Comments(), donut = Donut(),
        copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo()))

        val result = post.id
        assertNotEquals(result,0)
    }
    
}