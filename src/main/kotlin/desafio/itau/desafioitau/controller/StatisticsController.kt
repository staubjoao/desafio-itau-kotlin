package desafio.itau.desafioitau.controller

import desafio.itau.desafioitau.dto.StatisticsResponseDTO
import desafio.itau.desafioitau.dto.TransactionRequestDTO
import desafio.itau.desafioitau.exception.InvalidTransactionDateException
import desafio.itau.desafioitau.exception.InvalidTransactionValueException
import desafio.itau.desafioitau.service.impl.TransactionServiceImpl
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/estatistica")
class StatisticsController(private val transactionService: TransactionServiceImpl) {

    @GetMapping
    fun getStatisticsTransactions(): ResponseEntity<StatisticsResponseDTO> {
        val stats = transactionService.getStatisticsOfTransaction()

        if (stats.count == 0L) {
            return ResponseEntity.ok().build()
        }

        return ResponseEntity.ok(StatisticsResponseDTO(stats))
    }


}