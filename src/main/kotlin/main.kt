import note.server.Note

fun main() {

    val original = Post( likes = Likes(6),comments = Comments(), donut = Donut(),
        copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo())

    val originNew = Post(text = "yyy", likes = Likes(), comments = Comments(), donut = Donut(),
    copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo())

    val newPost1 = Post(2,"543", likes =  Likes(0), comments = Comments(), donut = Donut(),
    copyright = Copyright(), reposts = Reposts(), views = Views(), geo = Geo())

   val yyy = WallService.add(original)
    println(yyy)
    println(WallService.add(originNew))
    println(WallService.update(newPost1))
    println(WallService.createComment(2, Comment(donut = Donut(), thread = Thread())))
    println(NoteService.add(Note(text = "первая добавл. заметка")))
    println(NoteService.createComment(1, comment = Comment(text = "новый коментарий", donut = Donut(), thread = Thread())))
    val newNote = Note()
    val newNote2 = Note()
    println(NoteService.add(newNote))
    println(NoteService.add(newNote2))
    println(NoteService.getById(1))
    NoteService.restoreComment(4)



}

