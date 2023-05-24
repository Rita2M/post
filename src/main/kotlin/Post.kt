data class Post(
    val id: Int = 0, // идентификатор записи
    val text: String? = null, // текст записи
    val date: Int = 600, // время публикации записи
    val fromId: Int = 123987, // идентификатор автора записи(от чбего имени запись)
    val canPin: Boolean = false, // может ли текущий пользователь закрепить запись да/нет
    val markedAsAds: Boolean = true, // содержит ли запись рекламу да/нет
    val ownerId: Int = 23416, //идентификатор вледельца стены
    val canEdit: Boolean = true, // может ли текущий пользователь редактировать запись
    val createdBy: Int = 123, //идентификатор админа опубликовавшего запись
    val replyOwnerId: Int = 321, //id владельца запись в ответ на которую оставлена текущая
    val replyPostId: Int = 567, //id зарписи в ответ на которую была оставлена текущая
    val friendsOnly: Int = 1, //если запись с опцией "только друзья"
    val postType: String = "post", //тип поста
    val signerId: Int = 432, //id поста если запись опубл. от имени сообщества
    val isPinned: Int = 1, // 1 если запись закреплена
    val isFavorite: Boolean = true, //добавлен ли объект в закладки у текущего польз.
    val postponedId: Int = 0, //id отложенной змпись, возвращ.если стояла на таймере
    val attachments: List<Attachment> = listOf(
        PhotoAttachment(photo = Photo()),
        VideoAttachment(video = Video()),
        AudioAttachment(audio = Audio(1)),
        StickerAttachment(sticker = Sticker()),
        DocAttachment(doc = Doc())
    ),
    val copyHistory: List<Reposts> = listOf(),
    var likes: Likes,
    var comments: Comments,
    val copyright: Copyright,
    var reposts: Reposts,
    var views: Views,
    val geo: Geo,
    val donut: Donut,

    )


data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = true,// наличие отметки "мне нравится" от текущего пользователя
    val canLike: Boolean = true,// может ли текущ. пользователь лайкнуть
    val canPublish: Boolean = true// может ли сделать репост
)

data class Comments(
    val count: Int = 0, // количество комментов
    val canPost: Boolean = true, //может ли текущай польз.коментить запись
    val groupsCanPost: Boolean = true, // могут ли сообщества коментить
    val canClose: Boolean = false, // может ли текущий пользователь закрыть комменты
    val canOpen: Boolean = true // -||-    открыть коменты
)

data class Copyright(
    val id: Int = 0,
    val link: String = "ссылка",
    val name: String = "имя",
    val type: String = "тип"
)

data class Reposts(val count: Int = 0, val userReposted: Boolean = true)
data class Views(val count: Int = 0) //просмотры записи

//data class PostSource()
data class Geo(
    val type: String = "джунгли", //тип места
    val coordinates: String = "2313.221",
    val place: String = "Красивое" //описание места
)

data class Donut(
    val isDonut: Boolean = false,//запись доступна только платным подписчикам
    val paidDuration: Int = 12, //время доступа по плюподписке
    val Placeholder: String = "class", //заглушка для пользователей без подписки(класс)
    val canPublishFreeCopy: Boolean = true,//можно ли открыть запись для всех
    val edit_mode: String = "all" //информ. о том какие значения можно изменить
)

