package cinema.controller
import cinema.dto.StatisticsResponseDto
import cinema.exception.IncorrectPasswordException
import cinema.model.*
import org.springframework.web.bind.annotation.*

@RestController
class CinemaController(val cinemaRoom: CinemaRoom){

    private final val PASSWORD = "super_secret"

    @GetMapping("/seats")
    fun seats(): CinemaRoom {
        return cinemaRoom
    }

    @GetMapping("/stats")
    fun getStatsForm(@RequestParam password: String?): StatisticsResponseDto {
        val statisticsResponseDto: StatisticsResponseDto
        try {
            if (password!! == PASSWORD) {
                statisticsResponseDto = StatisticsResponseDto(
                    cinemaRoom.getCurrentIncome(),
                    cinemaRoom.getAvailableSeatsCount(),
                    cinemaRoom.getNumberOfPurchasedTickets()
                )
                return statisticsResponseDto
            }
            throw IncorrectPasswordException("The password is wrong!")
        } catch(e: RuntimeException) {
            throw IncorrectPasswordException("The password is wrong!")
        }
    }
}