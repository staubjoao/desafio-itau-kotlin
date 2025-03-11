package desafio.itau.desafioitau.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(InvalidTransactionDateException::class)
    fun handleInvalidTransactionDateException(ex: InvalidTransactionDateException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(mapOf("message" to ex.message))
    }

    @ExceptionHandler(InvalidTransactionValueException::class)
    fun handleInvalidTransactionValueException(ex: InvalidTransactionValueException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(mapOf("message" to ex.message))
    }

}