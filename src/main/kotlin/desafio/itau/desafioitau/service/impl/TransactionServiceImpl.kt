package desafio.itau.desafioitau.service.impl

import desafio.itau.desafioitau.dto.StatisticsResponseDTO
import desafio.itau.desafioitau.dto.TransactionRequestDTO
import desafio.itau.desafioitau.exception.InvalidTransactionDateException
import desafio.itau.desafioitau.exception.InvalidTransactionValueException
import desafio.itau.desafioitau.model.Transaction
import desafio.itau.desafioitau.service.TransactionService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TransactionServiceImpl(transactionList: List<Transaction>) : TransactionService {

    private var transactionList: MutableList<Transaction> = mutableListOf()

    override fun createTransaction(transaction: TransactionRequestDTO) {
        val now = LocalDateTime.now()

        val localDateTimeTransaction: LocalDateTime = transaction.dataHora.toLocalDateTime()

        if (localDateTimeTransaction > now) {
            throw InvalidTransactionDateException("Transaction date cannot be in the future.")
        }

        if (transaction.valor < 0) {
            throw InvalidTransactionValueException("Valor cannot be negative.")
        }

        transactionList.add(Transaction(transaction.valor, localDateTimeTransaction))
    }

    override fun getStatisticsOfTransaction(): StatisticsResponseDTO {
        TODO("Not yet implemented")
    }

}