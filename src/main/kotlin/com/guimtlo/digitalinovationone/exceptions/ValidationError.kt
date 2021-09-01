package com.guimtlo.digitalinovationone.exceptions

class ValidationError(
    timeStamp: Long,
    status: Int,
    code: String,
    message: String,
    path: String,
    var errors: MutableList<FieldMessage> = mutableListOf()
) : StandardError(
    timeStamp = timeStamp,
    status = status,
    code = code,
    message = message,
    path = path
)  {
    fun addError(field: String, message: String = "") {

        errors.add(FieldMessage(field = field, message = message))
    }
}