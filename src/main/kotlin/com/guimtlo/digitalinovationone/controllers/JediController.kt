package com.guimtlo.digitalinovationone.controllers

import com.guimtlo.digitalinovationone.mapper.toJedi
import com.guimtlo.digitalinovationone.mapper.toJediResponse
import com.guimtlo.digitalinovationone.model.dto.JediRequest
import com.guimtlo.digitalinovationone.model.dto.JediResponse
import com.guimtlo.digitalinovationone.services.JediService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/jedis")
class JediController {

    @Autowired
    private lateinit var jediService: JediService

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createJedi(@RequestBody @Valid jediRequest: JediRequest) : JediResponse {
        return jediService.createJedi(jediRequest.toJedi())
            .toJediResponse()
    }

    @GetMapping("/{jedi_id}")
    @ResponseStatus(HttpStatus.OK)
    fun findJediById(@PathVariable("jedi_id") jediId: Long) : JediResponse {
        return jediService.findJediById(jediId).toJediResponse()
    }
}