package empire.digiprem.portfolio.core.domain.util


expect object ConsoleLog {
    fun log(message: Any?)
    fun error(message: Any?)
    fun warn(message: Any?)
}