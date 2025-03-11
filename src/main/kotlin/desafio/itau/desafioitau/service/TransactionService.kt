package desafio.itau.desafioitau.service

import desafio.itau.desafioitau.dto.StatisticsResponseDTO
import desafio.itau.desafioitau.dto.TransactionRequestDTO
import java.util.*

interface TransactionService {

    fun createTransaction(transaction: TransactionRequestDTO)
    fun cleanAllTransactions()
    fun getStatisticsOfTransaction(): DoubleSummaryStatistics

}