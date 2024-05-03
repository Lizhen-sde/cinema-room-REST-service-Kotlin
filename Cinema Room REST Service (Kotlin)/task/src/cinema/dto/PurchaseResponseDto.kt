package cinema.dto

import cinema.model.Ticket
import java.util.*

data class PurchaseResponseDto(
    val token: UUID,
    val ticket: Ticket
)
