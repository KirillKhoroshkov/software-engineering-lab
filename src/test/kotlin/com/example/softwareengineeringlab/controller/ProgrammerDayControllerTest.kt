package com.example.softwareengineeringlab.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class ProgrammerDayControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun getWithBothParameters() {
        mvc.perform(MockMvcRequestBuilders.get("?year=2020&currentDate=20122020"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun getWithoutParams() {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun getWithYear2017() {
        mvc.perform(MockMvcRequestBuilders.get("?year=2017"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("13/09/17"))
    }

    @Test
    fun getWithYear2000() {
        mvc.perform(MockMvcRequestBuilders.get("?year=2000"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("12/09/00"))
    }

    @Test
    fun getWithYear2020() {
        mvc.perform(MockMvcRequestBuilders.get("?year=2020"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("12/09/20"))
    }

    @Test
    fun getWithYear2100() {
        mvc.perform(MockMvcRequestBuilders.get("?year=2100"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("13/09/00"))
    }

    @Test
    fun getWithNegativeYear() {
        mvc.perform(MockMvcRequestBuilders.get("?year=-1"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(400))
    }

    @Test
    fun getWithInvalidCurrentDate() {
        mvc.perform(MockMvcRequestBuilders.get("?currentDate=aa112000"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(400))
    }

    @Test
    fun getWithIllegalCurrentDate() {
        mvc.perform(MockMvcRequestBuilders.get("?currentDate=-1122000"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(400))
    }

    @Test
    fun getWithImpossibleCurrentDate() {
        mvc.perform(MockMvcRequestBuilders.get("?currentDate=35052000"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(400))
    }

    @Test
    fun getWithTooShortCurrentDate() {
        mvc.perform(MockMvcRequestBuilders.get("?currentDate=11"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(400))
    }

    @Test
    fun getWithCurrentDate13092017() {
        mvc.perform(MockMvcRequestBuilders.get("?currentDate=13092017"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("365"))
    }

    @Test
    fun getWithCurrentDate12092017() {
        mvc.perform(MockMvcRequestBuilders.get("?currentDate=12092017"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("1"))
    }

    @Test
    fun getWithCurrentDate12092020() {
        mvc.perform(MockMvcRequestBuilders.get("?currentDate=12092020"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("366"))
    }

    @Test
    fun getWithCurrentDate13092020() {
        mvc.perform(MockMvcRequestBuilders.get("?currentDate=13092020"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("365"))
    }

    @Test
    fun getWithCurrentDate11092020() {
        mvc.perform(MockMvcRequestBuilders.get("?currentDate=11092020"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("1"))
    }

    @Test
    fun getWithCurrentDate31122017() {
        mvc.perform(MockMvcRequestBuilders.get("?currentDate=31122017"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("256"))
    }

    @Test
    fun getWithCurrentDate31122020() {
        mvc.perform(MockMvcRequestBuilders.get("?currentDate=31122020"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("256"))
    }
}