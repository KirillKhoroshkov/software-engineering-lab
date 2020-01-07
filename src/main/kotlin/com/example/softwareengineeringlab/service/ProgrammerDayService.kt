package com.example.softwareengineeringlab.service

import com.example.softwareengineeringlab.model.Response

interface ProgrammerDayService {

    fun findDateOfProgrammerDay(year: Int): Response

    fun findDaysNumberToNextProgrammerDay(currentDate: String): Response
}