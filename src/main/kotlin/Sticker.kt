 data class Sticker(
     val productId: Int =54,
     val stickerId: Int = 111,
     val isAllowed: Boolean = true
 )
 class StickerAttachment(sticker: Sticker) : Attachment {
     override val type: String
         get() = "Sticker"
 }