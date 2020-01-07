package com.example.softwareengineeringlab.service.impl

import com.example.softwareengineeringlab.model.Response
import com.example.softwareengineeringlab.service.ProgrammerDayService
import com.example.softwareengineeringlab.util.dateFromDayOfYear
import com.example.softwareengineeringlab.util.daysNumbersOfLeapYearMonths
import com.example.softwareengineeringlab.util.daysNumbersOfMonths
import com.example.softwareengineeringlab.util.isLeapYear
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat

@Service
class ProgrammerDayServiceImpl : ProgrammerDayService {

    override fun findDateOfProgrammerDay(year: Int): Response {
        if (year < 1) {
            return Response(400, "")
        }
        val date = dateFromDayOfYear(256, year)
        val dateFormat = SimpleDateFormat("dd/MM/yy")
        return Response(200, dateFormat.format(date))
    }

    override fun findDaysNumberToNextProgrammerDay(currentDate: String): Response {
        if (currentDate.length < 5) {
            return Response(400, "")
        }
        val day = currentDate.substring(0, 2).toIntOrNull()
        val month = currentDate.substring(2, 4).toIntOrNull()
        val year = currentDate.substring(4).toIntOrNull()
        if (day == null || month == null || year == null) {
            return Response(400, "")
        }
        if (year < 1 || day < 1 || month < 1 || month > 12) {
            return Response(400, "")
        }
        val isLeapYear = isLeapYear(year)
        val daysNumbers = if (isLeapYear) daysNumbersOfLeapYearMonths else daysNumbersOfMonths
        val daysNumberOfYear = if (isLeapYear) 366 else 365
        if (day > daysNumbers[month - 1]) {
            return Response(400, "")
        }
        var passedDaysNumber = 0
        for (i in 0 until (month-1)) {
            passedDaysNumber += daysNumbers[i]
        }
        passedDaysNumber += day
        val daysToProgrammerDay = if (passedDaysNumber < 256) {
            256 - passedDaysNumber
        } else {
            daysNumberOfYear - passedDaysNumber + 256
        }
        return Response(200, "$daysToProgrammerDay")
    }
}