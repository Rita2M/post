fun main() {

    val original = Post(text = null, likes = Likes(6))

    val originNew = Post(text = "yyy", likes = Likes(5))

    val newPost1 = Post(3,"543", likes = Likes(0))

   val yyy = WallService.add(original)
    println(yyy)
    println(WallService.add(originNew))
    println(WallService.update(newPost1))
    println()


}