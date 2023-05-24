import java.lang.RuntimeException

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = mutableListOf<Comment>()
    private var lastId =0

    fun createComment(postId: Int, comment: Comment): Comment {
        for ( post  in posts){
            if (post.id == postId) {
                comments += comment.copy(donut = comment.donut.copy(), thread = comment.thread.copy())
                return comments.last()
            }
        }
         throw PostNotFoundException("нет такого поста, комментарий не добавлен")

    }
    fun clear() {
        posts = emptyArray()
        comments = mutableListOf()
        lastId = 0
    }

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId, likes = post.likes.copy())
        return posts.last()
    }

    fun update(newPost: Post): Boolean {
       for ((index, post) in posts.withIndex()) {
           if (post.id == newPost.id) {
               posts[index] = newPost.copy(likes = post.likes.copy())
               return true
           }
       }
        return false
    }
}
class PostNotFoundException(massage: String): RuntimeException(massage)