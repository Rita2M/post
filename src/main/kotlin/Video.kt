data class Video (
    val  id: Int =1,
    val ownerId: Int =3,
    val title: String ="myVideo",
    val duration: Int =6
)
class VideoAttachment(val video: Video) : Attachment {
    override val type: String = "video"

}