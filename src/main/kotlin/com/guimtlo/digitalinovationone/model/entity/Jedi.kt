package com.guimtlo.digitalinovationone.model.entity

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
data class Jedi(
    @Id @Column @GeneratedValue(strategy = GenerationType.AUTO)
    val jediId: Long? = null,

    @field:Size(min = 3, max = 50)
    @field:NotBlank
    val name: String,

    @field:Size(min = 3, max = 50)
    @field:NotBlank
    val lastName: String
)
