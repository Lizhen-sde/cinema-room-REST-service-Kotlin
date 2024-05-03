package cinema.controller

import cinema.exception.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest


@ControllerAdvice
class ControllerExceptionHandler {
    @ExceptionHandler(TicketAlreadyPurchasedException::class, OutOfBoundsException::class, TicketNotFoundException::class)
    fun ticketAlreadyPurchasedException(
        e: RuntimeException, request: WebRequest
    ): ResponseEntity<CustomErrorMessage> {
        val body = CustomErrorMessage(
            e.message
        )
        return ResponseEntity<CustomErrorMessage>(body, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IncorrectPasswordException::class)
    fun incorrectPasswordException(
        e: IncorrectPasswordException, request: WebRequest
    ): ResponseEntity<CustomErrorMessage> {
        val body = CustomErrorMessage(
            e.message
        )
        return ResponseEntity<CustomErrorMessage>(body, HttpStatus.UNAUTHORIZED)
    }

}