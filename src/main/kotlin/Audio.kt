
data class Audio(
    val id: Int = 1,
    val ownerId: Int = 12,
    val title: String = "relaxesFm",
    val length: Int = 3
)
class AudioAttachment(val audio: Audio) : Attachment {
    override val type: String
        get() = "Audio"
}