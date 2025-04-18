package com.hzlucasf.yuuzaa.exception

import com.hzlucasf.yuuzaa.exception.user.InvalidPasswordException
import com.hzlucasf.yuuzaa.exception.user.InvalidUsernameException
import com.hzlucasf.yuuzaa.exception.user.UserAlreadyRegisteredException
import com.hzlucasf.yuuzaa.exception.user.UserNotFoundException
import com.hzlucasf.yuuzaa.model.exception.response.ExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun exceptionHandler(exception: Exception) =
        ExceptionResponse(exception.message ?: "internal server error")

    @ExceptionHandler(InvalidUsernameException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun exceptionHandler(exception: InvalidUsernameException) =
        ExceptionResponse(exception.message ?: "invalid username")

    @ExceptionHandler(InvalidPasswordException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun exceptionHandler(exception: InvalidPasswordException) =
        ExceptionResponse(exception.message ?: "invalid password")

    @ExceptionHandler(UserAlreadyRegisteredException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun exceptionHandler(exception: UserAlreadyRegisteredException) =
        ExceptionResponse(exception.message ?: "user already registered")

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun exceptionHandler(exception: UserNotFoundException) =
        ExceptionResponse(exception.message ?: "user not found")
}
