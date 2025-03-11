package desafio.itau.desafioitau.controller

import desafio.itau.desafioitau.dto.TransactionRequestDTO
import desafio.itau.desafioitau.exception.InvalidTransactionDateException
import desafio.itau.desafioitau.exception.InvalidTransactionValueException
import desafio.itau.desafioitau.service.impl.TransactionServiceImpl
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transacao")
class TransactionController(private val transactionService: TransactionServiceImpl) {

    @PostMapping
    fun createTransaction(@Valid @RequestBody transaction: TransactionRequestDTO): ResponseEntity<Unit> {
        return try {
            transactionService.createTransaction(transaction)
            ResponseEntity.status(HttpStatus.CREATED).build()
        } catch (e: InvalidTransactionDateException) {
            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null)
        } catch (e: InvalidTransactionValueException) {
            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }
    }

    @DeleteMapping
    fun cleanAllTransactions(): ResponseEntity<Unit> {
        transactionService.cleanAllTransactions()
        return ResponseEntity.status(HttpStatus.OK).body(null)
    }

}