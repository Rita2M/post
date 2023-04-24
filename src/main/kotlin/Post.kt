
data class Post(
    val id: Int = 0, // идентификатор записи
    val text: String ?, // текст записи
    val date: Int = 600, // время публикации записи
    val fromId: Int = 123987, // идентификатор автора записи(от чбего имени запись)
    val canPin: Boolean = false, // может ли текущий пользователь закрепить запись да/нет
    val markedAsAds: Boolean = true, // содержит ли запись рекламу да/нет
    val ownerId: Int = 23416, //идентификатор вледельца стены
    val canEdit: Boolean = true, // может ли текущий пользователь редактировать запись
    var Likes: Likes
)
 data class Likes( val count: Int = 0)