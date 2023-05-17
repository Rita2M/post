package note.server

import Comment
import Item

data class Note(
    override var id : Int =0, // id заметки
    //val message : String = "а я текст коммментария", // текст комментария
    val title : String = " я заголовок заметки", //заголовок заметки
    override var text : String = "Я текст заметки", //текст заметки
    //val commentId : Int = 1, // id коммента


    override var comments : MutableList<Comment> = mutableListOf(),
) : Item
