package org.example.support.exception

import org.example.interfaces.CommonResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun exceptionHandler_4xx(exception: RuntimeException): CommonResponse<*> {
        logger.info("message : {}", exception.message)
        return CommonResponse.successOf(exception, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [Exception::class])
    fun exceptionHandler(exception: Exception): CommonResponse<*> {
        logger.info("message : {}", exception.message)
        return CommonResponse.failOf(exception)
    }

}