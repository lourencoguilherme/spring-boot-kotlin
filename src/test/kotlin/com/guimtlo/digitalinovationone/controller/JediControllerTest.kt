package com.guimtlo.digitalinovationone.controller

import com.guimtlo.digitalinovationone.model.dto.JediRequest
import com.guimtlo.digitalinovationone.model.entity.Jedi
import com.guimtlo.digitalinovationone.services.JediService
import com.guimtlo.digitalinovationone.util.jsonToObject
import com.guimtlo.digitalinovationone.util.objectToJson
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post


@WebMvcTest
class JediControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var service: JediService

    @Test
    fun shouldTestCreateJedi() {
        val jediRequest = JediRequest(name = "Teste", lastName = "lastname")
        val jediMock = Jedi(jediId = 1L, name = jediRequest.name, lastName = jediRequest.lastName)

        every { service.createJedi(any()) } returns jediMock

        val result = mockMvc.perform(
            post("/jedis")
                .content(jediRequest.objectToJson())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertEquals(result.status, 201)

        val jedi: Jedi = result.contentAsString.jsonToObject(Jedi::class.java)
        assertEquals(jedi.jediId, jediMock.jediId)
        assertEquals(jedi.name, jediMock.name)
        assertEquals(jedi.lastName, jediMock.lastName)
    }
}