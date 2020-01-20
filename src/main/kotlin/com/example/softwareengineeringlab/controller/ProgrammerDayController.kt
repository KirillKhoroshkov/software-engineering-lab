package com.example.softwareengineeringlab.controller

import com.example.softwareengineeringlab.model.Response
import com.example.softwareengineeringlab.service.ProgrammerDayService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ProgrammerDayController {

    @Autowired
    private lateinit var programmerDayService: ProgrammerDayService

    @GetMapping("/")
    @ResponseBody
    fun get(@RequestParam(required = false) year: Int?,
            @RequestParam(required = false) currentDate: String?): ResponseEntity<Response> {
        return when {
            currentDate != null && year != null -> {
                ResponseEntity.badRequest().build()
            }
            year != null -> {
                ResponseEntity.ok(programmerDayService.findDateOfProgrammerDay(year))
            }
            currentDate != null -> {
                ResponseEntity.ok(programmerDayService.findDaysNumberToNextProgrammerDay(currentDate))
            }
            else -> {
                ResponseEntity.badRequest().build()
            }
        }
    }
}