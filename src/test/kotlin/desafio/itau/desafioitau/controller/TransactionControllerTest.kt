package desafio.itau.desafioitau.controller

import desafio.itau.desafioitau.dto.TransactionRequestDTO
import desafio.itau.desafioitau.exception.InvalidTransactionDateException
import desafio.itau.desafioitau.exception.InvalidTransactionValueException
import desafio.itau.desafioitau.service.impl.TransactionServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.any
import org.mockito.kotlin.doThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.OffsetDateTime

@ExtendWith(SpringExtension::class)
@WebMvcTest(TransactionController::class)
class TransactionControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var transactionService: TransactionServiceImpl

    @Test
    fun `should return 201 CREATED when transaction is valid`() {
        val request = """{"valor": 100.0, "dataHora": "${OffsetDateTime.now().minusHours(2)}"}"""

        mockMvc.perform(
            MockMvcRequestBuilders.post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        ).andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    fun `should return 422 UNPROCESSABLE ENTITY when transaction date is in the future`() {
        val futureDate = OffsetDateTime.now().plusDays(1).toString()

        val request = """{"valor": 100.0, "dataHora": "$futureDate"}"""

        doThrow(InvalidTransactionDateException("Transaction date cannot be in the future."))
            .`when`(transactionService).createTransaction(any())

        mockMvc.perform(
            MockMvcRequestBuilders.post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        ).andExpect(MockMvcResultMatchers.status().isUnprocessableEntity)
    }


    @Test
    fun `should return 422 UNPROCESSABLE ENTITY when transaction value is negative`() {
        val dateTest = OffsetDateTime.now().minusHours(2).toString()

        val request = """{"valor": -10.0, "dataHora": "$dateTest"}"""

        doThrow(InvalidTransactionDateException("Valor cannot be negative."))
            .`when`(transactionService).createTransaction(any())

        mockMvc.perform(
            MockMvcRequestBuilders.post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        ).andExpect(MockMvcResultMatchers.status().isUnprocessableEntity)
    }

    @Test
    fun `should return 200 OK when cleaning transactions`() {
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/transacao")
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }
}
