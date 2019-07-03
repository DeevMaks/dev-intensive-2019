package ru.skillbranch.devintensive.models

import java.util.*

class TextMessage(
        id : String,
        from : User?,
        chat : Chat,
        isIncoming : Boolean = false,
        date : Date = Date(),
        var text : String?
) : BaseMessage(id, from, chat,isIncoming, date) {

    override fun formatMessage() = "${from?.firstName} ${from?.lastName} " +
            "${if (isIncoming) "Получил" else "Отправил"} изображение \"$text\""

}