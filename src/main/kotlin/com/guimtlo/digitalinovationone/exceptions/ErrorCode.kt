package com.guimtlo.digitalinovationone.exceptions

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val code: String
) {
    VALIDATION_BAD_REQUEST(
        status = HttpStatus.BAD_REQUEST,
        code = "VALIDATION-BAD-REQUEST-001"
    ),
    JEDI_BY_ID_NOT_FOUNT(
        status = HttpStatus.NOT_FOUND,
        code = "JEDI-NOT-FOUND-001"
    )
}