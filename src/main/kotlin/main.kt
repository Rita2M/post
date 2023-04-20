fun main() {
    val service = WallService()
    val original = Post(text = "lol", Likes = Likes(6))
    val newPost1 = Post(2,"543", Likes = Likes(0))
   println(service.add(original))
    println(service.add(Post(text = "Hi", Likes = Likes(5))))
    println(service.update(newPost1))

}