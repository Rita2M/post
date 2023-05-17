import note.server.Note
import java.lang.RuntimeException

abstract class Service <T : Item> {
    private var items = mutableListOf<T>()
    private var lastIdCom = 0
    private var lastId = 0


    fun add(elem: T): T {
        items += elem
        elem.id = ++lastId
        return items.last()
    }

    fun createComment(itemId: Int, comment: Comment): Comment {
        val note = items.find { it.id == itemId }
            ?: throw ItemNotFoundException("Вы пытаетесь добавить ком. к несуществующей заметке")
        comment.id = ++lastIdCom
        note.comments.add(comment)
        return note.comments.last()
    }

    fun getById(itemId: Int): T {
        val item = items.find { it.id == itemId } ?: throw ItemNotFoundException("заментка не найдена")
        val commentsWithoutDeleted = item.comments.filterNot { it.deleted }.toMutableList()
        return item.apply { comments = commentsWithoutDeleted }
    }
    // отдаем заметку без удалённого комментария
    fun deleteComment(commentId: Int) {
        for (item in items) {
            val comment = item.comments.find { it.id == commentId }
            if (comment != null) {
                if(comment.deleted) {
                    throw DeletedCommentException("Комментарий уже удалён.")
                }
                comment.deleted = true
                return
            }

        }
        throw CommentNotFoundException("Коментарий для удаления не найден")
    }
    fun delete(itemId: Int) {
        val item = items.find { it.id == itemId } ?: throw ItemNotFoundException("Объект для удаления не найден")
        item.comments.clear()
        items.remove(item)
    }
    fun edit(itemId: Int, newString: String ) {
        val item = items.find { it.id == itemId } ?: throw ItemNotFoundException("заметка не обнаружена")
           item.text = newString
  //редактируем только текст заметки
    }
    fun editComment(commentId: Int, newText: String) {
    for (item in items) {
        val comment = item.comments.find { it.id == commentId }
        if (comment != null) {
            if (comment.deleted) {
                throw DeletedCommentException("нельзя редактировать удалённый коммент")
            }
            comment.text = newText
            return
        }
    }
        throw CommentNotFoundException("Коментарий не найден")

    }
    fun get(): List<T> {
        return items
    }
    fun getComments(itemId: Int): List<Comment> {
        val item = items.find { it.id == itemId } ?: throw CommentNotFoundException("Комментарии не найдены")
        return item.comments.filterNot { it.deleted }
    }
    fun getFriendsNotes(): List<T> {
        return listOf()
    }
    fun restoreComment(commentId: Int) {
        for (item in items) {
            val comment = item.comments.find { it.id == commentId }
            if (comment != null) {
                if (!comment.deleted) {
                    throw DeletedCommentException("комментарий не удалён")
                }
                comment.deleted = false
                return
            }
        }

    }
    fun clear() {
        items = mutableListOf()
        lastIdCom = 0
        lastId = 0
    }

}
object NoteService : Service<Note>()


interface Item {
    var id: Int
    var comments: MutableList<Comment>
    var text : String

}
class ItemNotFoundException(massage: String) : RuntimeException(massage)
class DeletedCommentException(message: String) : RuntimeException(message)
class CommentNotFoundException(massage: String) : RuntimeException(massage)

