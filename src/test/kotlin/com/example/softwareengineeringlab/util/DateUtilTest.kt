package com.example.softwareengineeringlab.util

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import java.lang.IllegalArgumentException
import java.sql.Date

@SpringBootTest
class DateUtilTest {

    @Test
    fun isLeapYearWithYear2000() {
        assertEquals(true, isLeapYear(2000))
    }

    @Test
    fun isLeapYearWithYear2020() {
        assertEquals(true, isLeapYear(2020))
    }

    @Test
    fun isLeapYearWithYear2100() {
        assertEquals(false, isLeapYear(2100))
    }

    @Test
    fun isLeapYearWithYear1900() {
        assertEquals(false, isLeapYear(1900))
    }

    @Test
    fun isLeapYearWithYear2001() {
        assertEquals(false, isLeapYear(2001))
    }

    @Test
    fun isLeapYearWithYear0() {
        assertThrows(IllegalArgumentException::class.java) { isLeapYear(0) }
    }

    @Test
    fun isLeapYearWithNegativeYear() {
        assertThrows(IllegalArgumentException::class.java) { isLeapYear(-1) }
    }

    @Test
    fun dateFromDayOfYearWithDay256Year2017() {
        assertEquals(Date.valueOf("2017-09-13"), dateFromDayOfYear(256, 2017))
    }

    @Test
    fun dateFromDayOfYearWithDay256Year2020() {
        assertEquals(Date.valueOf("2020-09-12"), dateFromDayOfYear(256, 2020))
    }

    @Test
    fun dateFromDayOfYearWithDay170Year2019() {
        assertEquals(Date.valueOf("2019-06-19"), dateFromDayOfYear(170, 2019))
    }

    @Test
    fun dateFromDayOfYearWithDay365Year2019() {
        assertEquals(Date.valueOf("2019-12-31"), dateFromDayOfYear(365, 2019))
    }

    @Test
    fun dateFromDayOfYearWithDay366Year2020() {
        assertEquals(Date.valueOf("2020-12-31"), dateFromDayOfYear(366, 2020))
    }

    @Test
    fun dateFromDayOfYearWithDay366Year2019() {
        assertThrows(IllegalArgumentException::class.java) { dateFromDayOfYear(366, 2019) }
    }

    @Test
    fun dateFromDayOfYearWithDay367Year2020() {
        assertThrows(IllegalArgumentException::class.java) { dateFromDayOfYear(367, 2020) }
    }

    @Test
    fun dateFromDayOfYearWithNegativeDay() {
        assertThrows(IllegalArgumentException::class.java) { dateFromDayOfYear(-1, 2020) }
    }

    @Test
    fun dateFromDayOfYearWithNegativeYear() {
        assertThrows(IllegalArgumentException::class.java) { dateFromDayOfYear(12, -1) }
    }
}