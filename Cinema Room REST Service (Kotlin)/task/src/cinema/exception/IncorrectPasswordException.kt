package cinema.exception

class IncorrectPasswordException(val error: String) : RuntimeException(error)