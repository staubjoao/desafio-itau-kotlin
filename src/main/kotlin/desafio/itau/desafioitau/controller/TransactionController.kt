package desafio.itau.desafioitau.controller

import desafio.itau.desafioitau.dto.TransactionRequestDTO
import desafio.itau.desafioitau.model.Transaction
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transacao")
class TransactionController {

    @PostMapping
    fun createTransaction(@Valid @RequestBody transaction: TransactionRequestDTO): ResponseEntity<Transaction> {

    }

}