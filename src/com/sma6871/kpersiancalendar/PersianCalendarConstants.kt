/*
  Persian Calendar see: http://code.google.com/p/persian-calendar/
   Copyright (C) 2012  Mortezaadi@gmail.com
   PersianCalendarConstants.java

   Persian Calendar is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.sma6871.kpersiancalendar

import java.time.DayOfWeek
import java.util.*

/**
 *
 * @author Morteza contact: [Mortezaadi@gmail.com](mailto:Mortezaadi@gmail.com)
 * @version 1.0
 */
object PersianCalendarConstants {
    // 00:00:00 UTC (Gregorian) Julian day 0,
// 0 milliseconds since 1970-01-01
    const val MILLIS_JULIAN_EPOCH = -210866803200000L
    // Milliseconds of a day calculated by 24L(hours) * 60L(minutes) *
// 60L(seconds) * 1000L(mili);
    const val MILLIS_OF_A_DAY = 86400000L
    /**
     * The JDN of 1 Farvardin 1; Equivalent to March 19, 622 A.D.
     */
    const val PERSIAN_EPOCH: Long = 1948321
    @JvmField
    val persianMonthNames = arrayOf(
        "\u0641\u0631\u0648\u0631\u062f\u06cc\u0646",  // Farvardin
        "\u0627\u0631\u062f\u06cc\u0628\u0647\u0634\u062a",  // Ordibehesht
        "\u062e\u0631\u062f\u0627\u062f",  // Khordad
        "\u062a\u06cc\u0631",  // Tir
        "\u0645\u0631\u062f\u0627\u062f",  // Mordad
        "\u0634\u0647\u0631\u06cc\u0648\u0631",  // Shahrivar
        "\u0645\u0647\u0631",  // Mehr
        "\u0622\u0628\u0627\u0646",  // Aban
        "\u0622\u0630\u0631",  // Azar
        "\u062f\u06cc",  // Dey
        "\u0628\u0647\u0645\u0646",  // Bahman
        "\u0627\u0633\u0641\u0646\u062f" // Esfand
    )
    @JvmField
    val persianWeekDays = arrayOf(
        "\u0634\u0646\u0628\u0647",  // Shanbeh
        "\u06cc\u06a9\u200c\u0634\u0646\u0628\u0647",  // Yekshanbeh
        "\u062f\u0648\u0634\u0646\u0628\u0647",  // Doshanbeh
        "\u0633\u0647\u200c\u0634\u0646\u0628\u0647",  // Sehshanbeh
        "\u0686\u0647\u0627\u0631\u0634\u0646\u0628\u0647",  // Chaharshanbeh
        "\u067e\u0646\u062c\u200c\u0634\u0646\u0628\u0647",  // Panjshanbeh
        "\u062c\u0645\u0639\u0647" // jome
    )
    fun getFaLocale(): Locale = Locale("fa", "IR")
}


