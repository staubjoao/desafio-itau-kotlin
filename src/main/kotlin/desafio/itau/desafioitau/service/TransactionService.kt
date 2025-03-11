package desafio.itau.desafioitau.service

import desafio.itau.desafioitau.dto.StatisticsResponseDTO
import desafio.itau.desafioitau.dto.TransactionRequestDTO

interface TransactionService {

    fun createTransaction(transaction: TransactionRequestDTO)
    fun cleanAllTransactions()
    fun getStatisticsOfTransaction(): StatisticsResponseDTO

}