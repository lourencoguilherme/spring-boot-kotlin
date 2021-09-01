package com.guimtlo.digitalinovationone.exceptions

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.text.MessageFormat
import java.util.*
import javax.servlet.http.HttpServletRequest


@ControllerAdvice
class ApiExceptionHandler {

    @Autowired
    private lateinit var messageSource: MessageSource

    @ExceptionHandler(ServiceException::class)
    fun objectNotFound(e: ServiceException, request: HttpServletRequest): ResponseEntity<StandardError?>? {
        val messageSource = messageSource.getMessage(e.errorCode.code, e.params.toTypedArray(), LocaleContextHolder.getLocale());

        val err = StandardError(
            timeStamp = System.currentTimeMillis(),
            status = e.errorCode.status.value(),
            code = e.errorCode.code,
            message = messageSource,
            path = request.requestURI
        )
        return ResponseEntity.status(e.errorCode.status).body<StandardError?>(err)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validation(e: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<StandardError?>? {
        val errors: MutableList<FieldMessage> = mutableListOf()
        var messageConcat = ""
        for (x in e.bindingResult.fieldErrors) {
            var message = ""
            x.defaultMessage?.let {
                message = x.defaultMessage!!
            }
            messageConcat += "${x.field}: $message,"
            errors.add(FieldMessage(x.field, message))
        }

        val err = ValidationError(
            timeStamp = System.currentTimeMillis(),
            status = ErrorCode.VALIDATION_BAD_REQUEST.status.value(),
            code = ErrorCode.VALIDATION_BAD_REQUEST.code,
            message = messageConcat,
            path = request.requestURI,
            errors = errors
        )

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body<StandardError?>(err)
    }
}