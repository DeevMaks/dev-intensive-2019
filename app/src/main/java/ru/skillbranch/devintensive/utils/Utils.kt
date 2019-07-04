package ru.skillbranch.devintensive.utils

object Utils{

    fun parseFullName(fullName:String?) : Pair<String?, String?> {
        if(fullName.isNullOrBlank()) return Pair(null, null)
        val parts : List<String>? = fullName?.trim()?.split(' ')
        val firstName : String? = parts?.getOrNull(0)
        val lastName : String? = parts?.getOrNull(1)

        return Pair(firstName, lastName)
    }

    fun toInitials(firstName:String?, lastName:String?) : String? {
        return when {
            firstName.isNullOrBlank() && lastName.isNullOrBlank() ->  null
            firstName.isNullOrBlank() ->  "${lastName?.trim()?.getOrNull(0)}".toUpperCase()
            lastName.isNullOrBlank() -> "${firstName?.trim()?.getOrNull(0)}".toUpperCase()
            else -> "${firstName.trim()[0]}${lastName.trim()[0]}".toUpperCase()
        }
    }

    val translitMap = mapOf<String, String>("а" to "a", "б" to "b", "в" to "v", "г" to "g", "д" to "d",
            "е" to "e", "ё" to "e", "ж" to "zh", "з" to "z", "и" to "i", "й" to "i", "к" to "k",
            "л" to "l", "м" to "m", "н" to "n", "о" to "o", "п" to "p", "р" to "r", "с" to "s",
            "т" to "t", "у" to "u", "ф" to "f", "х" to "h", "ц" to "c", "ч" to "ch", "ш" to "sh",
            "щ" to "sh", "ъ" to "", "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu", "я" to "ya")

    fun transliteration(payload:String, divider:String=" ") : String {
       return payload.map { char ->
               when {
                   translitMap.containsKey("$char") -> "${translitMap["$char"]}"
                   translitMap.containsKey("$char".toLowerCase()) -> "${translitMap["$char".toLowerCase()]}".capitalize()
                   "$char" == " " -> divider
                   else -> "$char"
               }
       }.joinToString("")
    }

}