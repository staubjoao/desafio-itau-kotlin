package desafio.itau.desafioitau.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.time.OffsetDateTime

data class TransactionRequestDTO(
    @field:NotNull
    var valor: Double,

    @field:NotNull
    var dataHora: OffsetDateTime
)
