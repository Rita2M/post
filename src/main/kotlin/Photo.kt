data class Photo(  val id: Int =5,
                   val ownerId: Int =7,
                   val photo_130: String ="hi")
class PhotoAttachment(val photo: Photo) : Attachment{
     override val type:String = "photo"

}


