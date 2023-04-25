data class Doc (
    val id: Int = 0,
    val ownerId: Int = 2,
    val title: String = "work"
)
class DocAttachment(doc: Doc) : Attachment {
    override val type: String = "doc"

}