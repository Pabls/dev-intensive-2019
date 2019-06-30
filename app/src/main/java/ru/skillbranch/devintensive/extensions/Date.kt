package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

val minutesList = listOf<String>("минуту", "минуты", "минут")
val hoursList = listOf<String>("час", "часа", "часов")
val daysList = listOf<String>("день", "дня", "дней")

fun Date.humanizeDiff(): String {
    val currentTime = Date().time
    val thisTime = this.time

    if (currentTime > thisTime) {
        val diff = currentTime - this.time
        return when {
            diff <= 1 * SECOND -> "только что"
            diff <= 45 * SECOND -> "несколько секунд назад"
            diff <= 75 * SECOND -> "минуту назад"
            diff <= (MINUTE * 45) -> "${diff / MINUTE} ${getWord(diff / MINUTE, minutesList)} назад"
            diff <= (MINUTE * 75) -> "час назад"
            diff <= (HOUR * 22) -> "${diff / HOUR} ${getWord(diff / HOUR, hoursList)} назад"
            diff <= (HOUR * 26) -> "день назад"
            diff <= (DAY * 360) -> "${diff / DAY} ${getWord(diff / DAY, daysList)} назад"
            diff > (DAY * 360) -> "более года назад"
            else -> ""
        }
    } else {
        val diff = (currentTime - this.time) * (-1)
        return when {
            diff <= 1 * SECOND -> "только что"
            diff <= 45 * SECOND -> "через несколько секунд"
            diff <= 75 * SECOND -> "через минуту"
            diff <= (MINUTE * 45) -> "через ${diff / MINUTE} ${getWord(diff / MINUTE, minutesList)}"
            diff <= (MINUTE * 75) -> "через час"
            diff <= (HOUR * 22) -> "через ${diff / HOUR} ${getWord(diff / HOUR, hoursList)}"
            diff <= (HOUR * 26) -> "через день"
            diff <= (DAY * 360) -> "через ${diff / DAY} ${getWord(diff / DAY, daysList)}"
            diff > (DAY * 360) -> "более чем через год"
            else -> ""
        }
    }
}

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    var currentTime = this.time

    currentTime += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = currentTime
    return this
}

enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY
}

private fun getWord(value: Long, words: List<String>): String {
    var result: String
    var number = value % 100
    result = if (number in 11..19) {
        words[2]
    } else {
        when ((number % 10).toInt()) {
            1 -> words[0]
            2, 3, 4 -> words[1]
            else -> words[2]
        }
    }
    return result
}