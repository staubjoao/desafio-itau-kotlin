package desafio.itau.desafioitau.dto

import java.util.*

data class StatisticsResponseDTO (
    var count: Long,
    var sum: Double,
    var avg: Double,
    var min: Double,
    var max: Double,
) {
    constructor(status: DoubleSummaryStatistics) : this(
        status.count,
        status.sum,
        status.average,
        status.min,
        status.max)
}