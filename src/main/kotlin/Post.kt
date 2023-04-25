data class Post(
    val id: Int = 0, // идентификатор записи
    val text: String?, // текст записи
    val date: Int = 600, // время публикации записи
    val fromId: Int = 123987, // идентификатор автора записи(от чбего имени запись)
    val canPin: Boolean = false, // может ли текущий пользователь закрепить запись да/нет
    val markedAsAds: Boolean = true, // содержит ли запись рекламу да/нет
    val ownerId: Int = 23416, //идентификатор вледельца стены
    val canEdit: Boolean = true, // может ли текущий пользователь редактировать запись
    val attachmentArray: Array<Attachment> = arrayOf(
        PhotoAttachment(photo = Photo()),
        VideoAttachment(video = Video()),
        AudioAttachment(audio = Audio(1)),
        StickerAttachment(sticker = Sticker()),
        DocAttachment(doc = Doc())
    ),
    var likes: Likes
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (text != other.text) return false
        if (date != other.date) return false
        if (!attachmentArray.contentEquals(other.attachmentArray)) return false

        return true
    }

    override fun hashCode(): Int {
        return attachmentArray.contentHashCode()
    }
}

data class Likes(val count: Int = 0)
