package com.guimtlo.digitalinovationone.integration

import com.guimtlo.digitalinovationone.exceptions.ErrorCode
import com.guimtlo.digitalinovationone.exceptions.StandardError
import com.guimtlo.digitalinovationone.model.dto.JediRequest
import com.guimtlo.digitalinovationone.model.dto.JediResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.getForObject
import org.springframework.boot.test.web.client.postForObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import kotlin.random.Random

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = [
        "spring.datasource.url=jdbc:h2:mem:testdb"
    ]
)
class JediControllerIntegrationTest(@Autowired val client: TestRestTemplate) {

    @Test
    fun `integration, should test create jedi`() {
        val jediRequest = JediRequest(name = "Teste", lastName = "lastname")
        val response: ResponseEntity<JediResponse> = client.postForEntity("/jedis", jediRequest, JediResponse::class.java)
        Assert.assertEquals(response.statusCode, HttpStatus.CREATED)

        val jedi = response.body

        Assert.assertNotNull(jedi)

        jedi?.let {
            Assert.assertEquals(jedi.jediId, 1L)
            Assert.assertEquals(jedi.name, jediRequest.name)
            Assert.assertEquals(jedi.lastName, jediRequest.lastName)
        }
    }
    @Test
    fun `integration, should return badRequest when create jedi`() {
        val jediRequest = JediRequest(name = "T", lastName = "")
        val response: ResponseEntity<StandardError> = client.postForEntity("/jedis", jediRequest, StandardError::class.java)
        Assert.assertEquals(response.statusCode, HttpStatus.BAD_REQUEST)

        val jedi = response.body

        Assert.assertNotNull(jedi)

        jedi?.let {
            Assert.assertEquals(jedi.code, ErrorCode.VALIDATION_BAD_REQUEST.code)
            Assert.assertEquals(jedi.status, ErrorCode.VALIDATION_BAD_REQUEST.status.value())
        }
    }

}