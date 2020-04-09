package com.sma6871.kpersiancalendar

import java.time.DayOfWeek

import java.util.*

fun KPersianCalendar.format(formatIn: String): String {
    return format(formatIn, this).toString()
}

fun format(inFormat: CharSequence, inDate: KPersianCalendar): CharSequence? {
    val s = StringBuilder(inFormat)
    var count: Int
    var len = inFormat.length
    var i = 0

    while (i < len) {
        count = 1
        val c = s[i]

        while (i + count < len && s[i + count] == c) {
            count++
        }
        var replacement: String?
        when (c) {
            'A', 'a' -> replacement = inDate.get(Calendar.AM_PM).amPmString()
            'd' -> replacement = zeroPad(inDate.persianDay, count)
            'c', 'E' -> replacement = getDayOfWeekString(inDate[Calendar.DAY_OF_WEEK], count)
            'K', 'h' -> {
                var hour = inDate[Calendar.HOUR]
                if (c == 'h' && hour == 0) {
                    hour = 12
                }
                replacement = zeroPad(hour, count)
            }
            'H', 'k' -> {
                val hour = inDate[Calendar.HOUR_OF_DAY]
                replacement = zeroPad(hour, count)
            }
            'L', 'M' -> replacement = getMonthString(inDate, count)
            'm' -> replacement = zeroPad(inDate[Calendar.MINUTE], count)
            's' -> replacement = zeroPad(inDate[Calendar.SECOND], count)
            'y' -> replacement = getYearString(inDate, count)
            'z' -> TODO("replacement = getTimeZoneString(inDate, count)")
            else -> replacement = null
        }
        if (replacement != null) {
            s.replace(i, i + count, replacement)
            count = replacement.length // CARE: count is used in the for loop above
            len = s.length
        }
        i += count
    }
    return s.toString()
}

fun getYearString(inDate: KPersianCalendar, count: Int): String {
    return if (count <= 2)
        zeroPad(inDate.persianYear % 100, 2)
    else inDate.persianYear.toString()
}

fun getMonthString(inDate: KPersianCalendar, count: Int): String {
    return if (count > 2)
        inDate.persianMonthName
    else
        zeroPad(inDate.persianMonth + 1, count)
}

fun getDayOfWeekString(dayOfWeek: Int, count: Int): String {
    return DayOfWeek.values()[dayOfWeek].let {
        if (count == 1)
            it.getPersianDisplayName()
        else
            it.getPersianDisplayFirstCharString()
    }
}

private fun Int.amPmString(): String {
    return if (this == Calendar.AM) "صبح" else "عصر"
}

private fun zeroPad(inValue: Int, inMinDigits: Int): String {
    return String.format(Locale.getDefault(), "%0" + inMinDigits + "d", inValue);
}

