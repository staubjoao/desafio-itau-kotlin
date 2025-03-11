package desafio.itau.desafioitau.controller

import desafio.itau.desafioitau.service.impl.TransactionServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.DoubleSummaryStatistics

@ExtendWith(SpringExtension::class)
@WebMvcTest(StatisticsController::class)
class StatisticsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var transactionService: TransactionServiceImpl

    @Test
    fun `should return 200 OK with statistics when transactions exist`() {
        val stats = DoubleSummaryStatistics().apply {
            accept(100.0)
            accept(200.0)
            accept(300.0)
        }
        whenever(transactionService.getStatisticsOfTransaction()).thenReturn(stats)

        mockMvc.perform(
            MockMvcRequestBuilders.get("/estatistica")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.count").value(3))
            .andExpect(MockMvcResultMatchers.jsonPath("$.sum").value(600.0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.avg").value(200.0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.min").value(100.0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.max").value(300.0))
    }

    @Test
    fun `should return 200 OK with empty response when no transactions exist`() {
        whenever(transactionService.getStatisticsOfTransaction()).thenReturn(DoubleSummaryStatistics())

        mockMvc.perform(
            MockMvcRequestBuilders.get("/estatistica")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist())
    }
}