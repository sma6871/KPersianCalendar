/**
 * Persian Calendar see: http://code.google.com/p/persian-calendar/
 * Copyright (C) 2012  Mortezaadi@gmail.com
 * PersianDateParser.java
 *
 * Persian Calendar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http:></http:>//www.gnu.org/licenses/>.
 */
package com.sma6871.kpersiancalendar

/**
 * Parses text from the beginning of the given string to produce a
 * PersianCalendar.
 *
 *
 *
 * See the [.getPersianDate] method for more information on date
 * parsing.
 *
 * <pre>
 * Example
 *
 * `PersianCalendar pCal =
 * new PersianDateParser("1361/3/1").getPersianDate();
` *
</pre> *
 *
 * @author Morteza contact: [Mortezaadi@gmail.com](mailto:Mortezaadi@gmail.com)
 * @version 1.0
 */
class KPersianDateParser
/**
 * <pre>
 * construct parser with date string assigned
 * the default delimiter is '/'.
 *
 * To assign deferment delimiter use:
 * [.PersianDateParser]
 *
 * Example
 *
 * `PersianCalendar pCal =
 * new PersianDateParser("1361/3/1").getPersianDate();
` *
</pre> *
 *
 * @param dateString
 */(var dateString: String?) {
    var delimiter = "/"

    /**
     * <pre>
     * construct parser with date string assigned
     * the default delimiter is '/'. with this constructor
     * you can set different delimiter to parse the date
     * based on this delimiter.
     * see also:
     * [.PersianDateParser]
     *
     * Example
     *
     * `PersianCalendar pCal =
     * new PersianDateParser("1361-3-1","-").getPersianDate();
    ` *
    </pre> *
     *
     * @param dateString
     * @param delimiter
     */
    constructor(dateString: String, delimiter: String) : this(dateString) {
        this.delimiter = delimiter
    }

    /**
     * Produce the PersianCalendar object from given DateString throws Exception
     * if couldn't parse the text.
     *
     * @return PersianCalendar object
     * @exception RuntimeException
     */
    val kPersianDate: KPersianCalendar
        get() {
            checkDateStringInitialValidation()
            val tokens = splitDateString(normalizeDateString(dateString))
            val year = tokens[0].toInt()
            val month = tokens[1].toInt()
            val day = tokens[2].toInt()
            checkPersianDateValidation(year, month, day)
            val pCal = KPersianCalendar()
            pCal.setPersianDate(year, month, day)
            return pCal
        }

    /**
     * validate the given date
     *
     * @param year
     * @param month
     * @param day
     */
    private fun checkPersianDateValidation(year: Int, month: Int, day: Int) {
        if (year < 1) throw RuntimeException("year is not valid")
        if (month < 1 || month > 12) throw RuntimeException("month is not valid")
        if (day < 1 || day > 31) throw RuntimeException("day is not valid")
        if (month > 6 && day == 31) throw RuntimeException("day is not valid")
        if (month == 12 && day == 30 && !KPersianCalendarUtils.isPersianLeapYear(year)) throw RuntimeException(
            "day is not valid $year is not a leap year"
        )
    }

    /**
     * planned for further calculation before parsing the text
     *
     * @param dateString
     * @return
     */
    private fun normalizeDateString(dateString: String?): String? {
        // dateString = dateString.replace("-", delimiter);
        return dateString
    }

    private fun splitDateString(dateString: String?): Array<String> {
        val tokens = dateString!!.split(delimiter).toTypedArray()
        if (tokens.size != 3) throw RuntimeException("wrong date:$dateString is not a Persian Date or can not be parsed")
        return tokens
    }

    private fun checkDateStringInitialValidation() {
        if (dateString == null) throw RuntimeException("input didn't assing please use setDateString()")
        // if(dateString.length()>10)
        // throw new RuntimeException("wrong date:" + dateString +
        // " is not a Persian Date or can not be parsed" );
    }

}