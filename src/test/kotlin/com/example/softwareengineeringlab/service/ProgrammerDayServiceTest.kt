package com.example.softwareengineeringlab.service

import com.example.softwareengineeringlab.model.Response
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProgrammerDayServiceTest {

    @Autowired
    lateinit var programmerDayService: ProgrammerDayService

    @Test
    fun findDateOfProgrammerDayWithYear2017() {
        assertEquals(Response(200, "13/09/17"), programmerDayService.findDateOfProgrammerDay(2017))
    }

    @Test
    fun findDateOfProgrammerDayWithYear2000() {
        assertEquals(Response(200, "12/09/00"), programmerDayService.findDateOfProgrammerDay(2000))
    }

    @Test
    fun findDateOfProgrammerDayWithYear2020() {
        assertEquals(Response(200, "12/09/20"), programmerDayService.findDateOfProgrammerDay(2020))
    }

    @Test
    fun findDateOfProgrammerDayWithYear2100() {
        assertEquals(Response(200, "13/09/00"), programmerDayService.findDateOfProgrammerDay(2100))
    }

    @Test
    fun findDateOfProgrammerDayWithNegativeYear() {
        assertEquals(400, programmerDayService.findDateOfProgrammerDay(-1).errorCode)
    }

    @Test
    fun findDaysNumberToNextProgrammerDayWithInvalidCurrentDate() {
        assertEquals(400, programmerDayService.findDaysNumberToNextProgrammerDay("aa112000").errorCode)
    }

    @Test
    fun findDaysNumberToNextProgrammerDayWithIllegalCurrentDate() {
        assertEquals(400, programmerDayService.findDaysNumberToNextProgrammerDay("-1122000").errorCode)
    }

    @Test
    fun findDaysNumberToNextProgrammerDayWithImpossibleCurrentDate() {
        assertEquals(400, programmerDayService.findDaysNumberToNextProgrammerDay("35052000").errorCode)
    }

    @Test
    fun findDaysNumberToNextProgrammerDayWithTooShortCurrentDate() {
        assertEquals(400, programmerDayService.findDaysNumberToNextProgrammerDay("11").errorCode)
    }

    @Test
    fun findDaysNumberToNextProgrammerDayWithCurrentDate13092017() {
        assertEquals(Response(200, "365"), programmerDayService.findDaysNumberToNextProgrammerDay("13092017"))
    }

    @Test
    fun findDaysNumberToNextProgrammerDayWithCurrentDate12092017() {
        assertEquals(Response(200, "1"), programmerDayService.findDaysNumberToNextProgrammerDay("12092017"))
    }

    @Test
    fun findDaysNumberToNextProgrammerDayWithCurrentDate12092020() {
        assertEquals(Response(200, "366"), programmerDayService.findDaysNumberToNextProgrammerDay("12092020"))
    }

    @Test
    fun findDaysNumberToNextProgrammerDayWithCurrentDate13092020() {
        assertEquals(Response(200, "365"), programmerDayService.findDaysNumberToNextProgrammerDay("13092020"))
    }

    @Test
    fun findDaysNumberToNextProgrammerDayWithCurrentDate11092020() {
        assertEquals(Response(200, "1"), programmerDayService.findDaysNumberToNextProgrammerDay("11092020"))
    }

    @Test
    fun findDaysNumberToNextProgrammerDayWithCurrentDate31122017() {
        assertEquals(Response(200, "256"), programmerDayService.findDaysNumberToNextProgrammerDay("31122017"))
    }

    @Test
    fun findDaysNumberToNextProgrammerDayWithCurrentDate31122020() {
        assertEquals(Response(200, "256"), programmerDayService.findDaysNumberToNextProgrammerDay("31122020"))
    }
}