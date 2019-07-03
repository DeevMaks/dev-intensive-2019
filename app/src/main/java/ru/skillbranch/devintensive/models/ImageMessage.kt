package ru.skillbranch.devintensive.models

import java.util.*

class ImageMessage(
        id : String,
        from : User?,
        chat : Chat,
        isIncoming : Boolean = false,
        date : Date = Date(),
        var image : String?
) : BaseMessage(id, from, chat,isIncoming, date) {

    override fun formatMessage() = "${from?.firstName} ${from?.lastName} " +
            "${if (isIncoming) "Получил" else "Отправил"} изображение \"$image\""

}