package com.guimtlo.digitalinovationone.mapper

import com.guimtlo.digitalinovationone.model.dto.JediRequest
import com.guimtlo.digitalinovationone.model.dto.JediResponse
import com.guimtlo.digitalinovationone.model.entity.Jedi

fun JediRequest.toJedi() = Jedi(name = this.name, lastName = this.lastName)
fun Jedi.toJediResponse() = JediResponse(jediId = this.jediId!!, name = this.name, lastName = this.lastName)