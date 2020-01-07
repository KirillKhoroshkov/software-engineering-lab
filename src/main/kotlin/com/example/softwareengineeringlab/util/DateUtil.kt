package com.example.softwareengineeringlab.util

import java.lang.IllegalArgumentException
import java.sql.Date
import java.util.*

val daysNumbersOfMonths = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
val daysNumbersOfLeapYearMonths = listOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

fun isLeapYear(year: Int): Boolean {
    if (year < 1) {
        throw IllegalArgumentException("Param 'year' must be greater than 0")
    }
    return !((year % 4 != 0) || (year % 100 == 0 && year % 400 != 0))
}

fun dateFromDayOfYear(day: Int, year: Int): Date {
    if (year < 1) {
        throw IllegalArgumentException("Param 'year' must be greater than 0")
    }
    if (day < 1) {
        throw IllegalArgumentException("Param 'day' must be greater than 0")
    }
    val isLeapYear = isLeapYear(year)
    if ((isLeapYear && day > 366) || (!isLeapYear && day > 365)) {
        throw IllegalArgumentException("Param 'day' couldn't be greater than days number in year")
    }
    val daysNumbers = if (isLeapYear) daysNumbersOfLeapYearMonths else daysNumbersOfMonths
    var month = 0
    var dayOfMonth = day
    for (daysNumber in daysNumbers.dropLast(1)) {
        if (dayOfMonth < daysNumber) {
            break
        }
        month += 1
        dayOfMonth -= daysNumber
    }
    return Date(GregorianCalendar(year, month, dayOfMonth).time.time)
}