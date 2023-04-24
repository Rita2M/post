
object WallService {
    private var posts = emptyArray<Post>()
    private var lastId =0
    fun clear() {
        posts = emptyArray()
        lastId = 0
    }

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId, Likes = post.Likes.copy())
        return posts.last()
    }

    fun update(newPost: Post): Boolean {
       for ((index, post) in posts.withIndex()) {
           if (post.id == newPost.id) {
               posts[index] = newPost.copy(Likes = post.Likes.copy())
               return true
           }
       }
        return false
    }




}