package com.sma6871.kpersiancalendar

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneOffset
import java.util.*

fun DayOfWeek.getPersianDisplayName(): String {
    return when (this.ordinal) {
        DayOfWeek.MONDAY.ordinal -> PersianCalendarConstants.persianWeekDays[2]
        DayOfWeek.TUESDAY.ordinal -> PersianCalendarConstants.persianWeekDays[3]
        DayOfWeek.WEDNESDAY.ordinal -> PersianCalendarConstants.persianWeekDays[4]
        DayOfWeek.THURSDAY.ordinal -> PersianCalendarConstants.persianWeekDays[5]
        DayOfWeek.FRIDAY.ordinal -> PersianCalendarConstants.persianWeekDays[6]
        DayOfWeek.SATURDAY.ordinal -> PersianCalendarConstants.persianWeekDays[0]
        DayOfWeek.SUNDAY.ordinal -> PersianCalendarConstants.persianWeekDays[1]
        else -> PersianCalendarConstants.persianWeekDays[0]
    }
}

fun DayOfWeek.getPersianDisplayFirstCharString(): String {
    return this.getPersianDisplayName()[0].toString()
}

fun LocalDate.toPersianCalendar(): KPersianCalendar {
    return KPersianCalendar(atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli())
}

fun YearMonth.persianlMonthLength(): Int = this.atDay(1).toPersianCalendar().monthLength

fun YearMonth.persianlCalendar() = this.atDay(1).toPersianCalendar()


val KPersianCalendar.monthLength: Int
    get() {
        return if (persianMonth < 6) { // Farvardin ... Shahrivar
            31
        } else if ((persianMonth in 6..10) || (isPersianLeapYear && persianMonth == 11)) { // Mehr ... Bahman and leap year Esfand
            30
        } else 29 // Normal Esfand
    }

fun KPersianCalendar.toLocalDate(): LocalDate {
    return LocalDate.of(get(Calendar.YEAR), get(Calendar.MONTH) + 1, get(Calendar.DAY_OF_MONTH))
}

fun KPersianCalendar.addDays(days: Int): KPersianCalendar {
    val KPersianCalendar = KPersianCalendar(timeInMillis)
    KPersianCalendar.add(Calendar.DAY_OF_MONTH, days)
    return KPersianCalendar
}

fun KPersianCalendar.addMonths(months: Int): KPersianCalendar {
    val KPersianCalendar = KPersianCalendar(timeInMillis)
    KPersianCalendar.addPersianDate(Calendar.MONTH, months)
    return KPersianCalendar
}

fun KPersianCalendar.addYears(years: Int): KPersianCalendar {
    val KPersianCalendar = KPersianCalendar(timeInMillis)
    KPersianCalendar.addPersianDate(Calendar.YEAR, years)
    return KPersianCalendar
}

fun KPersianCalendar.withDay(day: Int): KPersianCalendar {
    val diffDays = day - persianDay
    return KPersianCalendar(timeInMillis).addDays(diffDays)
}

fun KPersianCalendar.withMonth(month: Int): KPersianCalendar {
    val diffMonths = month - persianMonth
    return KPersianCalendar(timeInMillis).addMonths(diffMonths)
}

fun KPersianCalendar.withYear(year: Int): KPersianCalendar {
    val diffYears = year - persianYear
    return KPersianCalendar(timeInMillis).addYears(diffYears)
}

val KPersianCalendar.weekInMonth: Int
    get() {
        return (persianDay + KPersianCalendar(timeInMillis).withDay(1).get(Calendar.DAY_OF_WEEK) - 1) / 7
    }