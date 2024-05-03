package cinema.dto

import cinema.model.Ticket

data class ReturnedResponseDto(
    val returned_ticket: Ticket
)
