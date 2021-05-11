package com.br.projeto.pf.projectspringboot.controller.advice

import com.br.projeto.pf.projectspringboot.model.Error
import com.br.projeto.pf.projectspringboot.model.ErrorResponse
import javassist.NotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.validation.ConstraintViolationException

private const val ERROR_MESSAGE_PARAMETER = "Parametro(s) Invalido(s)"

@ControllerAdvice
class ControllerExceptionHandler {

    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<ErrorResponse> {
        logger.error("ExceptionHandler -> NotFoundException -> ${e.message}")
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(e.message))
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(e: ConstraintViolationException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            message = ERROR_MESSAGE_PARAMETER,
            data = e.constraintViolations
                .map {
                    Error(
                        it.propertyPath.toString(),
                        it.messageTemplate
                    )
                }
        )
        logger.error("ExceptionHandler -> handleConstraintViolationException -> ${e.message}")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleConstraintViolationException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            message = ERROR_MESSAGE_PARAMETER,
            data = e.bindingResult.allErrors.filterIsInstance<FieldError>()
                .map {
                    Error(
                        it.field,
                        it.defaultMessage
                    )
                }
        )
        logger.error("ExceptionHandler -> MethodArgumentNotValidException -> ${e.message}")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error)
    }
}