package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.lang.Math.floor
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24
const val WEEK = DAY * 7
const val MONTH = DAY * 30
const val YEAR = DAY * 365

val humanize = mapOf<TimeUnits, List<String>>(
        TimeUnits.SECOND to listOf<String>("секуда", "секунды", "секунд"),
        TimeUnits.MINUTE to listOf<String>("минута", "минуты", "минут"),
        TimeUnits.HOUR to listOf<String>("час", "часа", "часов"),
        TimeUnits.DAY to listOf<String>("день", "дня", "дней"),
        TimeUnits.WEEK to listOf<String>("неделю", "недели", "недель"),
        TimeUnits.MONTH to listOf<String>("месяц", "месяца", "месяцев"),
        TimeUnits.YEAR to listOf<String>("год", "года", "лет")
)

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy") : String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units:TimeUnits=TimeUnits.SECOND) : Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        TimeUnits.WEEK -> value * WEEK
        TimeUnits.MONTH -> value * MONTH
        TimeUnits.YEAR -> value * YEAR
    }
    this.time = time
    return this
}

fun getHumanTime(count:Int, unit:TimeUnits) : String? = when (count) {
    1, 21, 31, 41, 51 -> "$count ${humanize?.get(unit)?.get(0)}"
    in 2..4, in 22..24, in 32..34, in 42..44, in 52..54 -> "$count ${humanize?.get(unit)?.get(1)}"
    else -> "$count ${humanize?.get(unit)?.get(2)}"
}

fun getHumanDirection(dir:String, humanTime:String?) = if(dir == "future") "через $humanTime" else "$humanTime назад"

fun Date.humanizeDiff(date:Date) : String? {
    val timeDiff = abs(this.time - date.time)
    val direction = if(this.time > date.time) "past" else "future"
    when {
        timeDiff > YEAR -> "${getHumanDirection(direction, getHumanTime((timeDiff / YEAR).toInt(), TimeUnits.YEAR))}"
        timeDiff > MONTH -> "${getHumanDirection(direction, getHumanTime((timeDiff / MONTH).toInt(), TimeUnits.MONTH))}"
        timeDiff > DAY -> "${getHumanDirection(direction, getHumanTime((timeDiff / DAY).toInt(), TimeUnits.DAY))}"
        timeDiff > HOUR -> "${getHumanDirection(direction, getHumanTime((timeDiff / HOUR).toInt(), TimeUnits.HOUR))}"
        timeDiff > MINUTE -> "${getHumanDirection(direction, getHumanTime((timeDiff / MINUTE).toInt(), TimeUnits.MINUTE))}"
        else -> "несколько секунд назад"
    }
    TODO()
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY,
    WEEK,
    MONTH,
    YEAR
}