data class Comment (
    val id: Int = 0,//id комментария
    val fromId: Int = 0, // id автора комментария
    val date: Int = 12, // дата создания комментария
    val text: String = "good", // текст комментария
    val replyToUser: Int = 12, // id пользователя или сообщества в ответ которому оставили коммент
    val replyToComment: Int = 123, // id коментария в ответ на который оставлен текущий коммент
    val donut: Donut,
    val attachments: Array<Attachment> = arrayOf(PhotoAttachment(photo = Photo(id = 5))),
    val thread: Thread,
    //val parentsStack:  TODO массив id родительских комментариев
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Comment

        if (!attachments.contentEquals(other.attachments)) return false

        return true
    }

    override fun hashCode(): Int {
        return attachments.contentHashCode()
    }
}
data class Thread( //информ.о вложенной ветке с полями
    val count: Int = 0,
    val items: Int = 0, //массив объектов комментариев к записи TODO
    val can_post: Boolean = true, //может ли текущий пользов. оставлять комменты
    val showReplyButton: Boolean = false, //нужно ли отбражать кнопку ответить в ветке
    val groupsCanPost: Boolean = false //могут ли сообщества остаалять ком. в ветке
)
/*data class DonutCom( // vk donut
    val isDon: Boolean = true,
    val placeholder: String = "заглушка для пользов. без подписки")*/

