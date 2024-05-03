package cinema.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.stereotype.Component
import java.util.*

@Component
class CinemaRoom(
    final val total_rows: Int = 9,
    final val total_columns: Int = 9,
    final val available_seats: MutableList<Seat> = mutableListOf(),
) {
    init {

        for (i in 1..total_rows) {
            for (j in 1..total_columns) {
                available_seats.add(Seat(i, j, getSeatPrice(i)))
            }
        }

        for (seat in available_seats) {
            val ticket = Ticket(seat.row, seat.column, seat.price, UUID.randomUUID())
            seat.ticket = ticket
        }

    }

    private final fun getSeatPrice(row: Int): Int {
        return if (row <= 4) {
            10
        } else {
            8
        }
    }

    fun getSeat(row: Int, column: Int): Seat? {
        for(seat in available_seats) {
            if(seat.row == row && seat.column == column) {
                return seat
            }
        }
        return null
    }

    fun getTicket(id: UUID): Ticket? {
        for(seat in available_seats) {
            if(seat.ticket!!.token == id) {
                return seat.ticket
            }
        }
        return null
    }

    @JsonIgnore
    fun getAvailableSeatsCount(): Int {
        var count = 0
        for(seat in available_seats) {
            if(!seat.reserved) {
                count++
            }
        }
        return count
    }

    @JsonIgnore
    fun getNumberOfPurchasedTickets(): Int {
        var count = 0
        for(seat in available_seats) {
            if(seat.reserved) {
                count++
            }
        }
        return count
    }

    @JsonIgnore
    fun getCurrentIncome(): Int {
        var totalIncome = 0
        for(seat in available_seats) {
            if(seat.reserved) {
                totalIncome += seat.price
            }
        }
        return totalIncome
    }
}