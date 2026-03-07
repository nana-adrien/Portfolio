package empire.digiprem.portfolio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform