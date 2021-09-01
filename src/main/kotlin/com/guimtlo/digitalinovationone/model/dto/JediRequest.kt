package com.guimtlo.digitalinovationone.model.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class JediRequest(

    @field:Size(min = 3, max = 10)
    @field:NotBlank
    val name: String,

    @field:NotBlank
    val lastName: String
)