package com.guimtlo.digitalinovationone.exceptions

class ServiceException(
    val errorCode: ErrorCode,
    val params: List<Any> = emptyList()
) : RuntimeException("")