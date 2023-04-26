fun main() {

    val original = Post(text = null, likes = Likes(6),comments = Comments(), donut = Donut(),
        copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo())

    val originNew = Post(text = "yyy", likes = Likes(5), comments = Comments(), donut = Donut(),
    copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo())

    val newPost1 = Post(3,"543", likes = Likes(0), comments = Comments(), donut = Donut(),
    copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo())

   val yyy = WallService.add(original)
    println(yyy)
    println(WallService.add(originNew))
    println(WallService.update(newPost1))
    println()


}